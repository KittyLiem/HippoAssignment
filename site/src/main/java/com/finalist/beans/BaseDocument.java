package com.finalist.beans;

import org.hippoecm.hst.content.beans.ContentNodeBinder;
import org.hippoecm.hst.content.beans.ContentNodeBindingException;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoDocument;

@Node(jcrType="myassignment:basedocument")
public class BaseDocument extends HippoDocument implements ContentNodeBinder{

	@Override
	public boolean bind(Object arg0, javax.jcr.Node arg1)
			throws ContentNodeBindingException {
		// TODO Auto-generated method stub
		return false;
	}

}
