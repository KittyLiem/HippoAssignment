package com.finalist.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;

import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.query.HstQueryManager;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.hippoecm.hst.jaxrs.services.AbstractResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.finalist.beans.TrajectInformation;
import com.finalist.model.TrajectInformationRepresentation;
/**
 * 
 * @author kittyfinalist
 *
 *Resource for the content/context aware RESTful API 
 */

@Path("/myassignment:TrajectInformation/")
public class TrajectInformationResource extends AbstractResource {
		
		public static final Logger log = LoggerFactory.getLogger(TrajectInformationResource.class);

	    @GET
	    @Path("/")
	    public TrajectInformationRepresentation getTrajectInformationResource(
	                           @Context HttpServletRequest servletRequest,
	                           @Context HttpServletResponse servletResponse) {
	        
	    	log.info("GET getTrajectInformationResource");
	    	
	        TrajectInformationRepresentation traject = new TrajectInformationRepresentation();
	               
	        try {

	            HstRequestContext requestContext = RequestContextProvider.get();
	            
	            HstQueryManager hstQueryManager = getHstQueryManager(requestContext);
	            
	            // for plain jaxrs, we do not have a requestContentBean because no resolved sitemapitem
	            HippoBean scope = getMountContentBaseBean(requestContext);
	            
	            HstQuery hstQuery = hstQueryManager.createQuery(scope, TrajectInformation.class, true);  
	                    
	            HstQueryResult result = hstQuery.execute();
	            
                TrajectInformation trajectInformation = (TrajectInformation) result;
	            if (trajectInformation != null) {
	                TrajectInformationRepresentation trajcetInformationRep = new TrajectInformationRepresentation().represent(trajectInformation);
	                trajcetInformationRep.addLink(getNodeLink(requestContext, trajectInformation));
	                traject = trajcetInformationRep;
	            }
	            		
	        } catch (Exception e) {
	            if (log.isDebugEnabled()) {
	                log.warn("Failed to query t.", e);
	            } else {
	                log.warn("Failed to query trajects. {}", e.toString());
	            }
	            
	            throw new WebApplicationException(e, ResponseUtils.buildServerErrorResponse(e));
	        }
	        
	        return traject;
	    
	    }

}
