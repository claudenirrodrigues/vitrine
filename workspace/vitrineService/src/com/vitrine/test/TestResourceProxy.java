package com.vitrine.test;

import java.io.Serializable;
import javax.ejb.Local;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Local
@Path( "test-resource" )
public interface TestResourceProxy extends Serializable {

	
    @POST
    @Path( "login" )
    @Produces( MediaType.APPLICATION_JSON )
    public Response login(
        @Context HttpHeaders httpHeaders,
        @FormParam( "username" ) String username,
        @FormParam( "password" ) String password );

    @GET
    @Path( "get-method" )
    @Produces( MediaType.APPLICATION_JSON )
    public Response getMethod();

    @POST
    @Path( "post-method" )
    @Produces( MediaType.APPLICATION_JSON )
    public Response postMethod();

    @POST
    @Path( "logout" )
    public Response logout(
        @Context HttpHeaders httpHeaders
    );
}