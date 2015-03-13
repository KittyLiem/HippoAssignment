package com.finalist.components;

import org.hippoecm.hst.component.support.bean.BaseHstComponent;
import org.hippoecm.hst.content.annotations.Persistable;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;

public class TrajectInformationComponent extends BaseHstComponent {

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
		 
	 }
	
}
