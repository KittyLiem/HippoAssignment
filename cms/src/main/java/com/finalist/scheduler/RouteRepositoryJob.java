package com.finalist.scheduler;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.onehippo.repository.scheduling.RepositoryJob;
import org.onehippo.repository.scheduling.RepositoryJobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.finalist.domain.TrajectInformation;
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
             
        log.info("JCR Session retrieved from context: {}" + session);
	// get the route info JSON to POJO's using Jackson
        JsonUtil jsonUtil = new JsonUtil();
        TrajectInformation trajectInformatie = jsonUtil.mapTrajectInformation();
        log.info("After JsonUtil " + trajectInformatie);
        
    // save POJO's in repository in Hippo document
        
		}
		finally {
		if (session != null) {
            session.logout();
		}}

	}

    public String toString() {
        return this.getClass().getName();
    }

}
