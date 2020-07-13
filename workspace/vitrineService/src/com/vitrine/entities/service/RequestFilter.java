package com.vitrine.entities.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.vitrine.entities.Autorizacao;
import com.vitrine.entities.Usuario;
import com.vitrine.entities.dao.VitrineDataSource;
import com.vitrine.entities.service.security.Authenticator;
import com.vitrine.utils.FormatHelper;
import com.vitrine.utils.HttpHeadersOAuth;
import com.vitrine.utils.Logger;

@Provider
@PreMatching
public class RequestFilter extends VitrineDataSource implements ContainerRequestFilter  {

	
	@Override
	public void filter(ContainerRequestContext requestCtx) throws IOException {

		String path = requestCtx.getUriInfo().getPath();
		Logger.log("Filtrando o request path: " + path, Logger.INFO);
		
		JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
		JsonObject jsonObj;

		// IMPORTANTE!!! Desprezar requisições suspeitas
		if (isRequestSuspect(requestCtx)) {
			Logger.log("Request suspeito.", Logger.WARNING);
			requestCtx.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
			return;
		}

		if (!isPathOAuth(path)) {

			// Para quaisquer outros métodos além de isPathOAuth, o authToken deve ser verificado
			// se não é válido, apenas barrar o acesso.
			try {
				
				String authToken = requestCtx.getHeaderString(HttpHeadersOAuth.AUTHORIZATION);
				String serviceKey = requestCtx.getHeaderString(HttpHeadersOAuth.SERVICE_KEY);
				
				if (!Authenticator.getInstance(getDataSource()).isValidAuthentication(authToken, serviceKey, false)) {
						Logger.log("Token invalido. authToken: "+ authToken + " - serviceKey: " +serviceKey, Logger.WARNING);
						requestCtx.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
						return;
				}
				
				if (!isPathInitialize(path)){
					
					if(!Authenticator.getInstance(getDataSource()).isUserAssigned(authToken)){
						Logger.log("Usuário não atribuido. ",Logger.WARNING);
						
						String baseURI = FormatHelper.getBaseURI(requestCtx.getUriInfo());
						URI uri = new URI(baseURI + Authenticator.URL_SOLICITACAO_ACESSO);
						
						Usuario usuario = Authenticator.getInstance(getDataSource()).getAuthenticatedUser(authToken);
						
						jsonObjBuilder.add("nome", FormatHelper.getNomeUsuario(usuario));
						jsonObjBuilder.add("authToken", authToken);
						jsonObjBuilder.add("redirect", uri.toString());
						jsonObj = jsonObjBuilder.build();
						
						requestCtx.abortWith(Response.status(Response.Status.UNAUTHORIZED)
								.entity(jsonObj.toString())
								.build());
						return;
					}
					
				}
			} catch (GeneralSecurityException e) {
				e.printStackTrace();
				Logger.log("Erro na validação do Token. ", this.getClass().getSimpleName(),e.getMessage(),Logger.ERROR);
				requestCtx.abortWith(Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());
				return;
			} catch (URISyntaxException e) {
				Logger.log("Erro ao redirecionar endereço de acesso. ", this.getClass().getSimpleName(),e.getMessage(),Logger.ERROR);
				requestCtx.abortWith(Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());
				return;
			}
			

		}
	}

	private boolean isPathOAuth(String path) {
		return path.startsWith("/security/authorization") || path.startsWith("/security/authentication");
	}
	
	private boolean isPathInitialize(String path) {
		return path.startsWith("/security/initialize");
	}

	private boolean isRequestSuspect(ContainerRequestContext requestCtx) {

		String path = requestCtx.getUriInfo().getPath();

		if (requestCtx.getRequest().getMethod().equals("OPTIONS")) {

			return true;
		}

		if (!requestCtx.getRequest().getMethod().equals("POST") && 
				(path.startsWith("/security/authorization") || 
						path.startsWith("/security/login") || 
						path.startsWith("/security/logout") || 
						path.startsWith("/security/authentication"))) {
			return true;
		}
		
		/*
		if (!requestCtx.getRequest().getMethod().equals("GET") && path.startsWith("/security/authentication")) {
			return true;
		}*/

		return false;

	}

}