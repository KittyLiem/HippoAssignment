package com.finalist.services;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.RepositoryException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.lang.StringUtils;
import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.content.annotations.Persistable;
import org.hippoecm.hst.content.beans.ObjectBeanManagerException;
import org.hippoecm.hst.content.beans.manager.workflow.WorkflowPersistenceManager;
import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.query.HstQueryManager;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.query.filter.Filter;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoBeanIterator;
import org.hippoecm.hst.content.beans.standard.HippoFolderBean;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.hippoecm.hst.jaxrs.services.AbstractResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.finalist.beans.TrajectInformation;
import com.finalist.model.TrajectInformationRepresentation;

@Path("/myassignment:TrajectInformation/")
public class TrajectInformationResourceCopy extends AbstractResource {
	
	public static final Logger log = LoggerFactory.getLogger(TrajectInformationResourceCopy.class);

    @GET
    @Path("/trajectinformation/")
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
                
            if (result != null) {
                TrajectInformation trajectInformation = (TrajectInformation) result;
                TrajectInformationRepresentation trajcetInformationRep = new TrajectInformationRepresentation().represent(trajectInformation);
                trajcetInformationRep.addLink(getNodeLink(requestContext, trajectInformation));
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

    @GET
    @Path("/")
    public List<TrajectInformationRepresentation> getTrajectInformationResource(
    						@Context HttpServletRequest servletRequest, 
    						@Context HttpServletResponse servletResponse, 
    						@Context UriInfo uriInfo) {
        
        List<TrajectInformationRepresentation> trajects = new ArrayList<TrajectInformationRepresentation>();
    	log.info("GET getTrajectInformationResource 1");
        
        
        try {

            HstRequestContext requestContext = RequestContextProvider.get();
            
            HstQueryManager hstQueryManager = getHstQueryManager(requestContext);
            
            // for plain jaxrs, we do not have a requestContentBean because no resolved sitemapitem
            HippoBean scope = getMountContentBaseBean(requestContext);
            
            HstQuery hstQuery = hstQueryManager.createQuery(scope, TrajectInformation.class, true);
            
            hstQuery.addOrderByAscending("myasssignment:trajectId");
            
            HstQueryResult result = hstQuery.execute();
            HippoBeanIterator iterator = result.getHippoBeans();

            while (iterator.hasNext()) {
            	TrajectInformation trajectInformation = (TrajectInformation) iterator.nextHippoBean();
                
                if (trajectInformation != null) {
                    TrajectInformationRepresentation trajcetInformationRep = new TrajectInformationRepresentation().represent(trajectInformation);
                    trajcetInformationRep.addLink(getNodeLink(requestContext, trajectInformation));
                   
                    trajects.add(trajcetInformationRep);
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
    
    /*	    
    @GET
    @Path("/trajectinformation/")
    public TrajectInformationRepresentation searchTrajectInformationResource(
    						@Context HttpServletRequest servletRequest, 
    						@Context HttpServletResponse servletResponse, 
    						@Context UriInfo uriInfo,
    						@MatrixParam("trajectId") String trajectId) {
        
        TrajectInformationRepresentation traject = new TrajectInformationRepresentation();
        
        try {

            HstRequestContext requestContext = RequestContextProvider.get();
            
            HstQueryManager hstQueryManager = getHstQueryManager(requestContext);
            
            HippoBean scope = getMountContentBaseBean(requestContext);
            
            HstQuery hstQuery = hstQueryManager.createQuery(scope, TrajectInformation.class, true);
            
           Filter filter = hstQuery.createFilter();
            
            if (!StringUtils.isEmpty(trajectId)) {
                filter.addEqualTo("myassignment:trajectId", trajectId);
            }

            hstQuery.setFilter(filter);
            
            HstQueryResult result = hstQuery.execute();
           

            if (result != null) {
                TrajectInformation trajectInformation = (TrajectInformation) result;	
            	TrajectInformationRepresentation trajcet = new TrajectInformationRepresentation().represent(trajectInformation);
                trajcet.addLink(getNodeLink(requestContext, trajectInformation));
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
   */ 
    
    @Persistable
    @POST
    public TrajectInformation createTrajectInformationResources(
    				@Context HttpServletRequest servletRequest, 
    				@Context HttpServletResponse servletResponse, 
    				@Context UriInfo uriInfo,
    				TrajectInformationRepresentation trajectInformationRepresentation) {
        
        HstRequestContext requestContext = getRequestContext(servletRequest);
        
        try {
            WorkflowPersistenceManager wpm = (WorkflowPersistenceManager) getPersistenceManager(requestContext);
            HippoFolderBean contentBaseFolder = getMountContentBaseBean(requestContext);
            String trajectInformationPath = contentBaseFolder.getPath() + "/trajectinformation";
            String beanPath = wpm.createAndReturn(trajectInformationPath, 
            										"myassignment:trajectinformation", 
            										trajectInformationRepresentation.getTrajectId(),
            										true);
            TrajectInformation trajectInformation = (TrajectInformation) wpm.getObject(beanPath);

            trajectInformation.setTrajectId(trajectInformationRepresentation.getTrajectId());
            trajectInformation.setTrajectName(trajectInformationRepresentation.getTrajectName());
            trajectInformation.setTrajectLength(trajectInformationRepresentation.getTrajectLength());
    
            wpm.update(trajectInformation);
            wpm.save();

            // Note: Retrieve bean again from the repository to return.
            trajectInformation = (TrajectInformation) wpm.getObject(trajectInformation.getPath());

            return trajectInformation;
            
        } catch (ObjectBeanManagerException e) {
            if (log.isDebugEnabled()) {
                log.warn("Failed to create trajectInformation.", e);
            } else {
                log.warn("Failed to create trajectInformation. {}", e.toString());
            }
            
            throw new WebApplicationException(e, ResponseUtils.buildServerErrorResponse(e));
        } catch (RepositoryException e) {
            if (log.isDebugEnabled()) {
                log.warn("Failed to create trajectInformation.", e);
            } else {
                log.warn("Failed to create trajectInformation. {}", e.toString());
            }
            
            throw new WebApplicationException(e, ResponseUtils.buildServerErrorResponse(e));
        }

    }
 
}
