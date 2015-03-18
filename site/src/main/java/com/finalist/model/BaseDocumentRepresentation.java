package com.finalist.model;

import javax.jcr.RepositoryException;

import org.hippoecm.hst.jaxrs.model.content.HippoDocumentRepresentation;

import com.finalist.beans.BaseDocument;

public class BaseDocumentRepresentation extends HippoDocumentRepresentation {

    
    public BaseDocumentRepresentation represent(BaseDocument bean) throws RepositoryException {
        super.represent(bean);
        return this;
    }


}
