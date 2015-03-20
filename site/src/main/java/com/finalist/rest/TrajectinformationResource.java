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
import org.hippoecm.hst.content.annotations.Persistable;
import org.hippoecm.hst.content.beans.ObjectBeanManagerException;
import org.hippoecm.hst.content.beans.manager.workflow.WorkflowPersistenceManager;
import org.hippoecm.hst.content.beans.standard.HippoFolderBean;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.onehippo.cms7.essentials.components.paging.Pageable;
import org.onehippo.cms7.essentials.components.rest.BaseRestResource;
import org.onehippo.cms7.essentials.components.rest.ctx.DefaultRestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.finalist.beans.Trajectinformation;
import com.finalist.model.TrajectinformationRepresentation;
import com.finalist.services.ResponseUtils;

/**
 * @version "$Id$"
 */

@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_FORM_URLENCODED})
@Path("/Trajectinformation/")
public class TrajectinformationResource extends BaseRestResource {

	public static final Logger log = LoggerFactory.getLogger(TrajectinformationResource.class);

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
            String trajectInformationPath = contentBaseFolder.getPath() + "/trajectinformation/" + trajectInfo.getId();
            log.info("In site, path: " + trajectInformationPath);
            
            String beanPath = wpm.createAndReturn(trajectInformationPath, 
            										"myassignment:trajectinformation", 
            										trajectInfo.getId(),
            										true);
            Trajectinformation trajectinformation = (Trajectinformation) wpm.getObject(beanPath);
            log.info("Ontvangen van trajectinfo insite: " + trajectinformation.getName());

            trajectinformation.setTrajectId(trajectInfo.getId());
            trajectinformation.setTrajectName(trajectInfo.getName());
            trajectinformation.setTrajectLength(trajectInfo.getLength());
    
            wpm.update(trajectinformation);
            wpm.save();

            // Note: Retrieve bean again from the repository to return.
            trajectinformation = (Trajectinformation) wpm.getObject(trajectinformation.getPath());
            trajectInfo = new TrajectInfo().represent(trajectinformation);
            

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
                log.warn("Repository Failed to create trajectInformation.", e);
            } else {
                log.warn("|Repository Failed to create trajectInformation. {}", e.toString());
            }
            
            throw new WebApplicationException(e, ResponseUtils.buildServerErrorResponse(e));
        }

    }

}
