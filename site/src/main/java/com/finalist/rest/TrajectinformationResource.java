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
import org.hippoecm.hst.content.beans.manager.workflow.WorkflowPersistenceManager;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoFolderBean;
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
    				TrajectInfo trajectInfo) {
        
        HstRequestContext requestContext = getRequestContext(servletRequest);
        
        try {
            WorkflowPersistenceManager wpm = (WorkflowPersistenceManager) getPersistenceManager(requestContext);
            HippoFolderBean contentBaseFolder = getMountContentBaseBean(requestContext);
            String trajectInformationPath = contentBaseFolder.getPath() + "/trajectinformation";
            
        // via folderworkflow
            HippoFolderBean trajectFolder = (HippoFolderBean) wpm.getObject(trajectInformationPath);
            
            Workflow wf = wpm.getWorkflow(FOLDER_NODE_WORKFLOW_CATEGORY, trajectFolder.getNode());
            
            javax.jcr.Node newHandleNode = trajectFolder.getNode();
            log.info("node: " + newHandleNode.getName());

            String added = ((FolderWorkflow) wf).add("new-trajectinformation", Trajectinformation.JCR_TYPE, trajectInfo.getId());
            log.info("In site, added: " + added);
            
            HippoBean docuBean = (HippoBean) wpm.getObject(added);
            newHandleNode = docuBean.getNode();
            log.info("gemaakte node: " + newHandleNode.getName());
      
            newHandleNode.setProperty("myassignment:trajectName", trajectInfo.getName());
            wpm.update((HippoBean)newHandleNode);
            
       // document workflow
            
           	//javax.jcr.Node newHandleNode = (javax.jcr.Node) trajectFolder.getNode();
           	
            //DocumentWorkflow dwf = (DocumentWorkflow)wpm.getWorkflow(DEFAULT_WORKFLOW_CATEGORY, newHandleNode);
            
            //dwf.publish();
            //Trajectinformation trajectinformation = (Trajectinformation) getBean(added);
            //log.info("trajectinformation: " + newHandleNode.getNode(added));
           	//trajectinformation.setTrajectId(trajectInfo.getId());
           	//trajectinformation.setTrajectName(trajectInfo.getName());
           	//trajectinformation.setTrajectLength(trajectInfo.getLength());
            //wf.hints();
            
            //String beanPath = wpm.createAndReturn(trajectInformationPath, 
            //									"myassignment:trajectinformation",
            //									trajectInfo.getId(),
            //									true);

            
            /*Trajectinformation trajectinformation = (Trajectinformation) wpm.getObject(beanPath); 

           	trajectinformation.setTrajectId(trajectInfo.getId());
           	trajectinformation.setTrajectName(trajectInfo.getName());
           	trajectinformation.setTrajectLength(trajectInfo.getLength());
           	
        	log.info("Ontvangen van trajectinfo in site: " + trajectinformation.getTrajectId());
           	*/
           	// fill attributes for childnode 
           	/*List<TrajectMeting> trajectMetingen = trajectInfo.getTrajectmetingen();
        	Trajectmeasurement trajectmeting = new Trajectmeasurement();
        	trajectmeting.setTrajectMeasurementTraveltime((long) trajectMetingen.get(0).getReistijd());
        	trajectmeting.setTrajectMeasurementVelocity((long) trajectMetingen.get(0).getSnelheid());
        	Calendar cal = Calendar.getInstance();
        	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        	cal.setTime(sdf.parse(trajectMetingen.get(0).getMetingDatum()));
        	trajectmeting.setTrajectMeasurementDate(cal);
           	log.info("Trajectmeasurement to be added: " + trajectmeting.getTrajectMeasurementDate());
           	*/
        	// TODO add childnode to document node
                
           	//wpm.update(trajectinformation);

            // need this to publish?
           	//javax.jcr.Node newHandleNode = trajectinformation.getNode().getParent();
            //DocumentWorkflow dwf = (DocumentWorkflow)wpm.getWorkflow(DEFAULT_WORKFLOW_CATEGORY, newHandleNode);
            // dwf.publish();

           	wpm.save();
            
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
        } catch (RemoteException e) {
        	log.warn("Remote exception.", e);
		} catch (WorkflowException e) {
        	log.warn("Workflow exception.", e);
		}
		return trajectInfo;

    }

}
