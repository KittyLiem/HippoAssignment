package com.finalist.components;

import javax.jcr.Session;

import org.hippoecm.hst.component.support.bean.BaseHstComponent;
import org.hippoecm.hst.content.annotations.Persistable;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrajectInformationComponent extends BaseHstComponent {

	public static final Logger log = LoggerFactory.getLogger(TrajectInformationComponent.class);
	
	 @Override
	  public void doBeforeRender(final HstRequest request,
	                             final HstResponse response) throws HstComponentException {
	 
	      // get the content bean for the current resolved sitemap item and
	      // set it on the request to make it available for
	      // the renderer like jsp or freemarker
	      HippoBean documentBean = request.getRequestContext().getContentBean();
	      request.setAttribute("document",documentBean);

	      // get the content bean for the root of the current (sub)site and set
	      // it on the request to make it available for
	      // the renderer like jsp or freemarker
	      HippoBean  rootBean = request.getRequestContext().getSiteContentBaseBean();
	      request.setAttribute("root",rootBean);

	    }
	 
	 @Persistable
	 @Override
	 public void doAction(HstRequest request, HstResponse response) throws HstComponentException {
		    try {
		        // NOTE: This session will be logged out automatically in the normal HST request processing thread.
		        Session persistableSession = request.getRequestContext().getSession();
		        
		        // get request context and decide which action to take
		        HstRequestContext requestContext = request.getRequestContext();
		        // add document
		        log.info("In the doAction");
		        // or add measurement to existing document
		        
		    } catch (Exception e) {
		    	log.error("Could not get persistable session");
		    }
	 }
	
}
