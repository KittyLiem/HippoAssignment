package com.finalist.rest;

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
import org.hippoecm.hst.util.PathUtils;
import org.onehippo.cms7.essentials.components.paging.Pageable;
import org.onehippo.cms7.essentials.components.rest.BaseRestResource;
import org.onehippo.cms7.essentials.components.rest.ctx.DefaultRestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.finalist.beans.Trajectinformation;
import com.finalist.model.TrajectinformationRepresentation;
import com.finalist.services.ResponseUtils;

/**
 * 
 * @author kittyfinalist
 *
 * Resource for Plain RESTful API
 */
@Path("/trajectinformation/")
public class TrajectInformationPlainResource extends BaseRestResource {
		
		public static final Logger log = LoggerFactory.getLogger(TrajectInformationPlainResource.class);
	    @GET
	    @Path("/")
	    public Pageable<Trajectinformation> index(@Context HttpServletRequest request) {
	        return findBeans(new DefaultRestContext(this, request), Trajectinformation.class);
	    }
/*

	    @GET
	    @Path("/page/{page}")
	    public Pageable<TrajectInformation> page(@Context HttpServletRequest request, @PathParam("page") int page) {
	        return findBeans(new DefaultRestContext(this, request, page, DefaultRestContext.PAGE_SIZE), TrajectInformation.class);
	    }


	    @GET
	    @Path("/page/{page}/{pageSize}")
	    public Pageable<TrajectInformation> pageForSize(@Context HttpServletRequest request, @PathParam("page") int page, @PathParam("pageSize") int pageSize) {
	        return findBeans(new DefaultRestContext(this, request, page, pageSize), TrajectInformation.class);
	    }

*/
	    @GET
	    @Path("/")
	    public List<TrajectinformationRepresentation> getTrajectInformationPlainResources(
	                           @Context HttpServletRequest servletRequest,
	                           @Context HttpServletResponse servletResponse) {
	        
	    	log.info("GET getTrajectInformationPlainResource");
	    	
	        List<TrajectinformationRepresentation> trajects = new ArrayList<TrajectinformationRepresentation>();
	               
	        try {

	            HstRequestContext requestContext = getRequestContext(servletRequest);
	            
	            HstQueryManager hstQueryManager = getHstQueryManager(requestContext.getSession(), requestContext);
	            
	            String mountContentPath =
	                    requestContext.getResolvedMount().getMount().getContentPath();
	            
	            Node mountContentNode = requestContext.getSession().getRootNode().getNode(PathUtils.normalizePath(mountContentPath));
	            
	            HstQuery hstQuery = hstQueryManager.createQuery(mountContentNode, Trajectinformation.class, true);  
	                    
	            HstQueryResult result = hstQuery.execute();
	            
	            HippoBeanIterator iterator = result.getHippoBeans();
	            
	            while (iterator.hasNext()) {
	            	Trajectinformation trajectinformation =
	                    (Trajectinformation) iterator.nextHippoBean();
	 
	                if (trajectinformation != null) {
	                	TrajectinformationRepresentation trajectRep =
	                        new TrajectinformationRepresentation().represent(trajectinformation);
	                	trajectRep.addLink(getNodeLink(requestContext,
	                			trajectinformation));
	                    trajectRep.addLink(getSiteLink(requestContext,
	                    		trajectinformation));
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
