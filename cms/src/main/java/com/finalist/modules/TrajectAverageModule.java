package com.finalist.modules;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;



import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;

import org.apache.commons.lang.StringUtils;
import org.hippoecm.repository.api.HippoNode;
import org.onehippo.cms7.services.HippoServiceRegistry;
import org.onehippo.cms7.services.eventbus.HippoEventBus;
import org.onehippo.cms7.services.eventbus.Subscribe;
import org.onehippo.repository.events.HippoWorkflowEvent;
import org.onehippo.repository.modules.DaemonModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TrajectAverageModule implements DaemonModule {
	
	public static final Logger log = LoggerFactory.getLogger(TrajectAverageModule.class);
	
	private Session session;

	@Override
	public void initialize(Session session) throws RepositoryException {
	       this.session = session;
	        HippoServiceRegistry.registerService(this, HippoEventBus.class);

	}

	@Override
	public void shutdown() {
		HippoServiceRegistry.unregisterService(this, HippoEventBus.class);

	}

	@Subscribe
    public void handleEvent(final HippoWorkflowEvent event) {
        if (event.success() && ("publish".equals(event.action()) || "depublish".equals(event.action()))) {
            try {
                final HippoNode handle = (HippoNode) session.getNodeByIdentifier(event.subjectId());
                Node variant = handle.getNode(handle.getName());
                if (variant.isNodeType("myassignment:trajectinformation")) {
                    String subjectUuid = variant.getNode("myassignment:trajectname").getProperty("hippo:docbase").getString();
                    calculateAndstoreAverages(subjectUuid);
                }
            } catch (RepositoryException e) {
                log.error("Failed to process workflow event on {}", event.subjectId(), e);
            }
        }
	}
	
	private double getAverageVelocity(String subjectUuid) throws RepositoryException {
	    double averageVelocity = 0.0;
	    QueryManager queryManager = session.getWorkspace().getQueryManager();
	    @SuppressWarnings("deprecation")
		String language = Query.XPATH;
	    String expression = "//element(*,myassignment:trajectinformation)[myassignment:subject/@hippo:docbase='" + subjectUuid
	            + "' and @hippo:availability='live']";
	    Query query = queryManager.createQuery(expression, language);
	    QueryResult result = query.execute();
	    int numberOfReviews = 0;
	    Long cumulativeVelocity = 0L;
	    NodeIterator nodeIter = result.getNodes();
	    while (nodeIter.hasNext()) {
	        javax.jcr.Node node = nodeIter.nextNode();
	        numberOfReviews++;
	        Long velocity = node.getProperty("myassignment:trajectMeasurementVelocity").getLong();
	        cumulativeVelocity += velocity;
	    }
	    averageVelocity = cumulativeVelocity.doubleValue() / (double) numberOfReviews;
	    return averageVelocity;
	}
	
	private double getAverageTraveltime(String subjectUuid) throws RepositoryException {
	    double averageTraveltime = 0.0;
	    QueryManager queryManager = session.getWorkspace().getQueryManager();
	    @SuppressWarnings("deprecation")
		String language = Query.XPATH;
	    String expression = "//element(*,myassignment:trajectinformation)[myassignment:subject/@hippo:docbase='" + subjectUuid
	            + "' and @hippo:availability='live']";
	    Query query = queryManager.createQuery(expression, language);
	    QueryResult result = query.execute();
	    int numberOfReviews = 0;
	    Long cumulativeTraveltime = 0L;
	    NodeIterator nodeIter = result.getNodes();
	    while (nodeIter.hasNext()) {
	        javax.jcr.Node node = nodeIter.nextNode();
	        numberOfReviews++;
	        Long traveltime= node.getProperty("myassignment:trajectMeasurementTraveltime").getLong();
	        cumulativeTraveltime += traveltime;
	    }
	    averageTraveltime = cumulativeTraveltime.doubleValue() / (double) numberOfReviews;
	    return averageTraveltime;
	}
	
	private Node getTrajectAverageFolderNode(String subjectUuid) throws RepositoryException {
	    Node averagesFolderNode = session.getRootNode();
	    StringBuilder builder = new StringBuilder();
	    builder.append("/myassignment:averages/");
	    builder.append(subjectUuid.charAt(0));
	    builder.append("/");
	    builder.append(subjectUuid.charAt(1));
	    builder.append("/");
	    builder.append(subjectUuid.charAt(2));
	    String averageFolderPath = builder.toString();
	    String[] nodeNames = averageFolderPath.split("/");
	    for (String nodeName : nodeNames) {
	        if (!StringUtils.isEmpty(nodeName)) {
	            if (!averagesFolderNode.hasNode(nodeName)) {
	                averagesFolderNode = averagesFolderNode.addNode(nodeName);
	            } else {
	                averagesFolderNode = averagesFolderNode.getNode(nodeName);
	            }
	        }
	    }
	    return averagesFolderNode;
	}
	
	private void calculateAndstoreAverages(String subjectUuid) throws RepositoryException {
	    double averageVelocity = getAverageVelocity(subjectUuid);
	    double averageTraveltime = getAverageTraveltime(subjectUuid);
	    Node averagesNode = getTrajectAverageFolderNode(subjectUuid);
	    if (!averagesNode.hasNode(subjectUuid)) {
	        averagesNode.addNode(subjectUuid, "myassignment:averagetraject");
	    }
	    Node averageNode = averagesNode.getNode(subjectUuid);
	    averageNode.setProperty("myassignment:trajectname", subjectUuid);
	    averageNode.setProperty("myassignment:averagevelocity", averageVelocity);
	    averageNode.setProperty("myassignment:averagetraveltime", averageTraveltime);
	    session.save();
	}
	
}
