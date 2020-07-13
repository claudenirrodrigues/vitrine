package com.vitrine.test;

import com.vitrine.test.TestResourceProxy;
import com.vitrine.utils.HttpHeadersOAuth;

import java.security.GeneralSecurityException;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.security.auth.login.LoginException;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@Stateless( name = "TestResource", mappedName = "ejb/TestResource" )
public class TestResource implements TestResourceProxy {

    private static final long serialVersionUID = -6663599014192066936L;

    
    public TestResource() {
		
	}

	@Override
    public Response login(
        @Context HttpHeaders httpHeaders,
        @FormParam( "username" ) String username,
        @FormParam( "password" ) String password ) {

        AuthenticatorService authenticator = AuthenticatorService.getInstance();
        String serviceKey = httpHeaders.getHeaderString( HttpHeadersOAuth.SERVICE_KEY );

        try {
            String authToken = authenticator.login( serviceKey, username, password );

            JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
            jsonObjBuilder.add( "auth_token", authToken );
            JsonObject jsonObj = jsonObjBuilder.build();

            return getNoCacheResponseBuilder( Response.Status.OK ).entity( jsonObj.toString() ).build();

        } catch ( final LoginException ex ) {
            JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
            jsonObjBuilder.add( "message", "Problema corresponde ao service key, username e password" );
            JsonObject jsonObj = jsonObjBuilder.build();

            return getNoCacheResponseBuilder( Response.Status.UNAUTHORIZED ).entity( jsonObj.toString() ).build();
        }
    }

    @Override
    public Response getMethod() {
        JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
        jsonObjBuilder.add( "message", "Executado GetMethod" );
        JsonObject jsonObj = jsonObjBuilder.build();

        return getNoCacheResponseBuilder( Response.Status.OK ).entity( jsonObj.toString() ).build();
    }

    @Override
    public Response postMethod() {
        JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
        jsonObjBuilder.add( "message", "Executado PostMethod" );
        JsonObject jsonObj = jsonObjBuilder.build();

        return getNoCacheResponseBuilder( Response.Status.ACCEPTED ).entity( jsonObj.toString() ).build();
    }

    @Override
    public Response logout(
        @Context HttpHeaders httpHeaders ) {
        try {
            AuthenticatorService authenticator = AuthenticatorService.getInstance();
            String serviceKey = httpHeaders.getHeaderString( HttpHeadersOAuth.SERVICE_KEY );
            String authToken = httpHeaders.getHeaderString( HttpHeadersOAuth.AUTH_TOKEN );

            authenticator.logout( serviceKey, authToken );

            return getNoCacheResponseBuilder( Response.Status.NO_CONTENT ).build();
        } catch ( final GeneralSecurityException ex ) {
            return getNoCacheResponseBuilder( Response.Status.INTERNAL_SERVER_ERROR ).build();
        }
    }

    private Response.ResponseBuilder getNoCacheResponseBuilder( Response.Status status ) {
        CacheControl cc = new CacheControl();
        cc.setNoCache( true );
        cc.setMaxAge( -1 );
        cc.setMustRevalidate( true );

        return Response.status( status ).cacheControl( cc );
    }
}