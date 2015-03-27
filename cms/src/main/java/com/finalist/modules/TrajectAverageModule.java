package com.finalist.modules;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

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
            // averagetraject node will be saved under UUID of handle
            if (variant.isNodeType("myassignment:trajectinformation")) {
               String subjectUuid = handle.getIdentifier();
               String trajectName = variant.getProperty("myassignment:trajectName").getString();
               calculateAndstoreAverages(subjectUuid, trajectName);
            }
         } catch (RepositoryException e) {
            log.error("Failed to process workflow event on {}", event.subjectId(), e);
         }
      }
   }

   private double getAverageVelocity(final String subjectUuid) throws RepositoryException {
      return computeAverage(subjectUuid, "Velocity");
   }

   private double getAverageTraveltime(final String subjectUuid) throws RepositoryException {
      return computeAverage(subjectUuid, "Traveltime");
   }

   private double computeAverage(final String subjectUuid, final String averageToCompute) {
      double average = 0.0;
      try {
         // Search for live version of velocity & traveltime via the handle
         
         HippoNode handle = (HippoNode) session.getNodeByIdentifier(subjectUuid);
         
         int numberOfReviews = 0;
         Long cumulative = 0L;
         
         NodeIterator nodeIter = handle.getNodes();
         while (nodeIter.hasNext()) {
            javax.jcr.Node node = nodeIter.nextNode();
            numberOfReviews++;
            // only use live versions of value for average
            if (node.getProperty("hippo:availability").equals("live")) {
               Long propertyValue = node.getProperty("myassignment:trajectMeasurement" + averageToCompute).getLong();
               cumulative += propertyValue;
               break;
            }
         }
         average = cumulative.doubleValue() / (double) numberOfReviews;
      } catch (RepositoryException e) {
         log.info("Could not add average node to repository: " + e);
      }
      return average;
   }

   private Node getTrajectAverageFolderNode(final String subjectUuid) throws RepositoryException {
      Node averagesFolderNode = session.getRootNode();
      StringBuilder builder = new StringBuilder();
      builder.append("/myassignment:averagetraject/");
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

   private void calculateAndstoreAverages(final String subjectUuid, final String trajectName) throws RepositoryException {
      double averageVelocity = getAverageVelocity(subjectUuid);
      double averageTraveltime = getAverageTraveltime(subjectUuid);
      Node averagesNode = getTrajectAverageFolderNode(subjectUuid);
      if (!averagesNode.hasNode(subjectUuid)) {
         averagesNode.addNode(subjectUuid, "myassignment:averagetraject");
      }
      Node averageNode = averagesNode.getNode(subjectUuid);
      averageNode.setProperty("myassignment:trajectname", trajectName);
      averageNode.setProperty("myassignment:averagevelocity", averageVelocity);
      averageNode.setProperty("myassignment:averagetraveltime", averageTraveltime);
      session.save();
   }

}
