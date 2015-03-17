package com.finalist.services;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;

import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.query.HstQueryManager;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.standard.HippoBeanIterator;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.hippoecm.hst.jaxrs.services.AbstractResource;
import org.hippoecm.hst.util.PathUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.finalist.beans.TrajectInformation;
import com.finalist.model.TrajectInformationRepresentation;

/**
 * 
 * @author kittyfinalist
 *
 * Resource for Plain RESTful API
 */
@Path("/trajectinformation/")
public class TrajectInformationPlainResource extends AbstractResource {
		
		public static final Logger log = LoggerFactory.getLogger(TrajectInformationPlainResource.class);

	    @GET
	    @Path("/{trajectId}/")
	    public List<TrajectInformationRepresentation> getTrajectInformationResources(
	                           @Context HttpServletRequest servletRequest,
	                           @Context HttpServletResponse servletResponse) {
	        
	    	log.info("GET getTrajectInformationResource");
	    	
	        List<TrajectInformationRepresentation> trajects = new ArrayList<TrajectInformationRepresentation>();
	               
	        try {

	            HstRequestContext requestContext = getRequestContext(servletRequest);
	            
	            HstQueryManager hstQueryManager = getHstQueryManager(requestContext.getSession(), requestContext);
	            
	            String mountContentPath =
	                    requestContext.getResolvedMount().getMount().getContentPath();
	            
	            Node mountContentNode = requestContext.getSession().getRootNode().getNode(PathUtils.normalizePath(mountContentPath));
	            
	            HstQuery hstQuery = hstQueryManager.createQuery(mountContentNode, TrajectInformation.class, true);  
	                    
	            HstQueryResult result = hstQuery.execute();
	            
	            HippoBeanIterator iterator = result.getHippoBeans();
	            
	            while (iterator.hasNext()) {
	            	TrajectInformation trajectInformation =
	                    (TrajectInformation) iterator.nextHippoBean();
	 
	                if (trajectInformation != null) {
	                	TrajectInformationRepresentation trajectRep =
	                        new TrajectInformationRepresentation().represent(trajectInformation);
	                	trajectRep.addLink(getNodeLink(requestContext,
	                			trajectInformation));
	                    trajectRep.addLink(getSiteLink(requestContext,
	                    		trajectInformation));
	                    trajects.add(trajectRep);
	                }
	            }
                	            		
	        } catch (Exception e) {
	            if (log.isDebugEnabled()) {
	                log.warn("Failed to query t.", e);
	            } else {
	                log.warn("Failed to query trajects. {}", e.toString());
	            }
	            
	            throw new WebApplicationException(e, ResponseUtils.buildServerErrorResponse(e));
	        }
	        
	        return trajects;
	    
	    }

}
