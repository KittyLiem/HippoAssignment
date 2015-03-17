package com.finalist.scheduler;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryResult;

import org.hippoecm.hst.content.beans.manager.workflow.WorkflowPersistenceManager;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.onehippo.repository.scheduling.RepositoryJob;
import org.onehippo.repository.scheduling.RepositoryJobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.finalist.domain.TrajectInformationObject;
import com.finalist.util.JsonUtil;



public class RouteRepositoryJob implements RepositoryJob {

	public static final Logger log = LoggerFactory.getLogger(RouteRepositoryJob.class);


	@Override
	public void execute(RepositoryJobExecutionContext context)
			throws RepositoryException {
		
		Session session = null;
		try {
			// get session and output
			session = context.createSystemSession();
             
			//log.info("JCR Session retrieved from context: {}" + session);
		// get the route info JSON to POJO's using Jackson
			JsonUtil jsonUtil = new JsonUtil();
			TrajectInformationObject trajectInformatie = jsonUtil.mapTrajectInformation();
			log.info("After JsonUtil " + trajectInformatie.getFeatures().get(0).getId());
        
        // save POJO's in repository in Hippo document using restapi



        /*for (Feature feature: trajectInformatie.getFeatures()) {
        	for (NodeIterator i=r.getNodes(); i.hasNext();)
        		{
        		Node current = i.nextNode();
        		if (feature.getId().equals(current.getProperty())) {
        			
        		}
        		
        	}
        	
        } */
        
        
		}

		finally {
			if (session != null) {
				session.logout();
			}
		}

	}

    public String toString() {
        return this.getClass().getName();
    }

}
