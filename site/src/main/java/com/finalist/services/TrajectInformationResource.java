package com.finalist.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.query.HstQueryManager;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.hippoecm.hst.jaxrs.services.AbstractResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.finalist.beans.Trajectinformation;
import com.finalist.model.TrajectinformationRepresentation;
/**
 * 
 * @author kittyfinalist
 *
 *Resource for the content/context aware RESTful API 
 */
@Deprecated
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_FORM_URLENCODED})
@Path("/myassignment:TrajectInformation/")
public class TrajectInformationResource extends AbstractResource {
		
		public static final Logger log = LoggerFactory.getLogger(TrajectInformationResource.class);

	    @GET
	    @Path("/")
	    public TrajectinformationRepresentation getTrajectinformationResource(
	                           @Context HttpServletRequest servletRequest,
	                           @Context HttpServletResponse servletResponse) {
	        
	    	log.info("GET getTrajectinformationResource");
	    	
	        TrajectinformationRepresentation traject = new TrajectinformationRepresentation();
	               
	        try {

	            HstRequestContext requestContext = RequestContextProvider.get();
	            
	            HstQueryManager hstQueryManager = getHstQueryManager(requestContext);
	            
	            // for plain jaxrs, we do not have a requestContentBean because no resolved sitemapitem
	            HippoBean scope = getMountContentBaseBean(requestContext);
	            
	            HstQuery hstQuery = hstQueryManager.createQuery(scope, Trajectinformation.class, true);  
	                    
	            HstQueryResult result = hstQuery.execute();
	            
                Trajectinformation trajectinformation = (Trajectinformation) result;
	            if (trajectinformation != null) {
	                TrajectinformationRepresentation trajectinformationRep = new TrajectinformationRepresentation().represent(trajectinformation);
	                trajectinformationRep.addLink(getNodeLink(requestContext, trajectinformation));
	                traject = trajectinformationRep;
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
