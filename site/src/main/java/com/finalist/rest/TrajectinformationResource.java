/*
 * Copyright 2014 Hippo B.V. (http://www.onehippo.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.finalist.rest;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.jcr.RepositoryException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.common.domain.TrajectInfo;
import org.common.domain.TrajectMeting;
import org.hippoecm.hst.content.annotations.Persistable;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.ObjectBeanManagerException;
import org.hippoecm.hst.content.beans.manager.workflow.BaseWorkflowCallbackHandler;
import org.hippoecm.hst.content.beans.manager.workflow.WorkflowPersistenceManager;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoDocument;
import org.hippoecm.hst.content.beans.standard.HippoFolderBean;
import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.hippoecm.repository.api.Workflow;
import org.hippoecm.repository.api.WorkflowException;
import org.hippoecm.repository.standardworkflow.FolderWorkflow;
import org.onehippo.cms7.essentials.components.paging.Pageable;
import org.onehippo.cms7.essentials.components.rest.BaseRestResource;
import org.onehippo.cms7.essentials.components.rest.ctx.DefaultRestContext;
import org.onehippo.repository.documentworkflow.DocumentWorkflow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.finalist.beans.Trajectinformation;
import com.finalist.beans.Trajectmeasurement;
import com.finalist.services.ResponseUtils;

/**
 * @version "$Id$"
 */

@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_FORM_URLENCODED})
@Path("/Trajectinformation/")
public class TrajectinformationResource extends BaseRestResource {

	public static final Logger log = LoggerFactory.getLogger(TrajectinformationResource.class);
	
    private static final String DEFAULT_WORKFLOW_CATEGORY = "default";
    private static final String FOLDER_NODE_WORKFLOW_CATEGORY = "threepane";

    @GET
    @Path("/")
    public Pageable<Trajectinformation> index(@Context HttpServletRequest request) {
        return findBeans(new DefaultRestContext(this, request), Trajectinformation.class);
    }


    @GET
    @Path("/page/{page}")
    public Pageable<Trajectinformation> page(@Context HttpServletRequest request, @PathParam("page") int page) {
        return findBeans(new DefaultRestContext(this, request, page, DefaultRestContext.PAGE_SIZE), Trajectinformation.class);
    }


    @GET
    @Path("/page/{page}/{pageSize}")
    public Pageable<Trajectinformation> pageForSize(@Context HttpServletRequest request, @PathParam("page") int page, @PathParam("pageSize") int pageSize) {
        return findBeans(new DefaultRestContext(this, request, page, pageSize), Trajectinformation.class);
    }

    
    @Persistable
    @POST
    @Path("/")
    public TrajectInfo createTrajectinformationResource(
    				@Context HttpServletRequest servletRequest, 
    				@Context HttpServletResponse servletResponse, 
    				@Context UriInfo uriInfo,
    				TrajectInfo trajectInfo) throws RemoteException, WorkflowException {
        
        HstRequestContext requestContext = getRequestContext(servletRequest);
        
        try {
            WorkflowPersistenceManager wpm = (WorkflowPersistenceManager) getPersistenceManager(requestContext);
            
            // go to trajectinformatio folder
            HippoFolderBean contentBaseFolder = getMountContentBaseBean(requestContext);
            String trajectInformationPath = contentBaseFolder.getPath() + "/trajectinformation";
            
            // get the folder bean
            HippoFolderBean trajectFolder = (HippoFolderBean) wpm.getObject(trajectInformationPath);
            
            // try to get trajectinformation document with id
            Object hippoDoc = wpm.getObject(trajectFolder.getPath() + "/" + trajectInfo.getId());
            log.info("Object found: " + hippoDoc.getClass().getName());
            
            boolean exists = false;
            if (hippoDoc instanceof Trajectinformation) {
            	exists = true;
            	log.info("The id exists in repository");
            }
            
            wpm.setWorkflowCallbackHandler(new BaseWorkflowCallbackHandler<DocumentWorkflow>() {
                public void processWorkflow(DocumentWorkflow wf) throws Exception {
                    wf.publish();
                }
            });

            if (!exists) {
	        	
	            String trajectPath = wpm.createAndReturn(trajectFolder.getPath(), "myassignment:trajectinformation", trajectInfo.getId(), true);
            
	            Trajectinformation trajectinformation = (Trajectinformation) wpm.getObject(trajectPath);
	             
	            if (trajectinformation == null) {
	                throw new HstComponentException("Failed to add Trajectinformation");
	            }
	            trajectinformation.setTrajectId(trajectInfo.getId());
	            trajectinformation.setTrajectLength((long) trajectInfo.getLength());
	            trajectinformation.setTrajectName(trajectInfo.getName());
	            //Trajectmeasurement trajectmeasurement = trajectinformation.getHippoCompound(trajectPath, beanMappingClass );
	
	            wpm.update(trajectinformation);
	            log.info("trajectinformation: " + trajectinformation.getTrajectId());
            }
            else {
	
	   
	        	// TODO add childnode to document node
	       
            }
            
            return trajectInfo;      
	            
        } catch (ObjectBeanManagerException e) {
            if (log.isDebugEnabled()) {
                log.warn("beanmanager Failed to create trajectInformation.", e);
            } else {
                log.warn("beanmanager Failed to create trajectInformation. {}", e.toString());
            }
            
            throw new WebApplicationException(e, ResponseUtils.buildServerErrorResponse(e));
        } catch (RepositoryException e) {
            if (log.isDebugEnabled()) {
                log.warn("Repository Failed to create trajectinformation.", e);
            } else {
                log.warn("|Repository Failed to create trajectinformation. {}", e.toString());
            }
            
            throw new WebApplicationException(e, ResponseUtils.buildServerErrorResponse(e));
        } /*catch (RemoteException e) {
        	log.warn("Remote exception.", e);
		} catch (WorkflowException e) {
        	log.warn("Workflow exception.", e);
		}*/
		//return trajectInfo;

    }

}
