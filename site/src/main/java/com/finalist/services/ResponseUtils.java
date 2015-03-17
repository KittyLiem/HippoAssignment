package com.finalist.services;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseUtils {
	
	public static final Logger log = LoggerFactory.getLogger(ResponseUtils.class);

    private ResponseUtils() {
        
    }
    
    public static Response buildServerErrorResponse(Throwable th) {
        return Response.serverError().entity(th.getCause() != null ? th.getCause().toString() : th.toString()).build();
    }
	
}
