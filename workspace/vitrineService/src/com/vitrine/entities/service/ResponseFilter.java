/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitrine.entities.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.wsdl.extensions.http.HTTPAddress;

import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.util.HttpHeaderNames;

import com.github.scribejava.core.model.HttpClient;
import com.vitrine.entities.service.security.GoogleOAuth20;
import com.vitrine.utils.HttpHeadersOAuth;

/**
 *
 * @author Familia
 */
@Provider
public class ResponseFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext response) {
		response.getHeaders().putSingle("Access-Control-Allow-Origin", "*");
		response.getHeaders().putSingle("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT, DELETE");
		response.getHeaders().putSingle("Access-Control-Allow-Headers",
				HttpHeadersOAuth.CONTENT_TYPE + ", " +
				HttpHeadersOAuth.STATE	+ ", " +
				HttpHeadersOAuth.CODE	+ ", " +
				HttpHeadersOAuth.SERVICE_KEY + ", " + 
				HttpHeadersOAuth.X_CLIENT_IP + ", " +
				HttpHeadersOAuth.AUTHORIZATION + ", " +
				HttpHeadersOAuth.X_FORWARDED_FOR + ", " +
				HttpHeadersOAuth.X_CODINGPEDIA + ", " +
				HttpHeadersOAuth.X_REQUESTED_WITH);
				
		
	}
	
	

}
