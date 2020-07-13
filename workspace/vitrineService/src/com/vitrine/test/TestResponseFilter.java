package com.vitrine.test;

import java.io.IOException;
import java.util.logging.Logger;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import com.vitrine.utils.HttpHeadersOAuth;

@Provider
public class TestResponseFilter implements ContainerResponseFilter {

    private final static Logger log = Logger.getLogger( TestResponseFilter.class.getName() );

    @Override
    public void filter( ContainerRequestContext requestCtx, ContainerResponseContext responseCtx ) throws IOException {

        log.info( "Filtering REST Response" );

        responseCtx.getHeaders().add( "Access-Control-Allow-Origin", "*" );    // You may further limit certain client IPs with Access-Control-Allow-Origin instead of '*'
        responseCtx.getHeaders().add( "Access-Control-Allow-Credentials", "true" );
        responseCtx.getHeaders().add( "Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
		//Adicionar esta linha no Fielter
        responseCtx.getHeaders().add( "Access-Control-Allow-Headers", HttpHeadersOAuth.SERVICE_KEY + ", " + HttpHeadersOAuth.AUTH_TOKEN );
    }
}