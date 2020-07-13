package com.vitrine.test;

import com.vitrine.test.AuthenticatorService;
import com.vitrine.utils.HttpHeadersOAuth;

import java.io.IOException;
import java.util.logging.Logger;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@PreMatching
public class TestRequestFilter implements ContainerRequestFilter {

    private final static Logger log = Logger.getLogger( TestRequestFilter.class.getName() );

    @Override
    public void filter( ContainerRequestContext requestCtx ) throws IOException {

        String path = requestCtx.getUriInfo().getPath();
        log.info( "Filtrando o request path: " + path );

        // IMPORTANTE!!! Desprezar requisições com metodo "OPTIONS"
        if ( requestCtx.getRequest().getMethod().equals( "OPTIONS" ) ) {
            requestCtx.abortWith( Response.status( Response.Status.OK ).build() );

            return;
        }

        //Em seguida, verifique se a chave de serviço existe e é válida.
        AuthenticatorService authenticator = AuthenticatorService.getInstance();
        String serviceKey = requestCtx.getHeaderString( HttpHeadersOAuth.SERVICE_KEY );

        if ( !authenticator.isServiceKeyValid( serviceKey ) ) {
            //Barrar qualquer pessoa sem uma chave de serviço válido
            requestCtx.abortWith( Response.status( Response.Status.UNAUTHORIZED ).build() );

            return;
        }

        //Para quaisquer outros métodos além de login, o authToken deve ser verificado
        if ( !path.startsWith( "/test-resource/login" ) ) {
            String authToken = requestCtx.getHeaderString( HttpHeadersOAuth.AUTH_TOKEN );

            // se não é válido, apenas barrar o acesso.
            if ( !authenticator.isAuthTokenValid( serviceKey, authToken ) ) {
                requestCtx.abortWith( Response.status( Response.Status.UNAUTHORIZED ).build() );
            }
        }
    }
}