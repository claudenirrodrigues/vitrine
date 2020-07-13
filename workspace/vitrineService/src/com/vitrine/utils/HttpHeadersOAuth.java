package com.vitrine.utils;

import javax.ws.rs.core.HttpHeaders;

public interface HttpHeadersOAuth extends HttpHeaders{

    public static final String SERVICE_KEY = "serviceKey";
    public static final String AUTH_TOKEN = "authToken";
	public static final String STATE = "state";
	public static final String CODE = "code";
	public static final String X_CLIENT_IP	= "X-Client-IP";
	public static final String X_REQUESTED_WITH	= "X-Requested-With"; 
	public static final String X_CODINGPEDIA	= "X-Codingpedia";
	public static final String ORIGIN = "Origin";
	public static final String X_FORWARDED_FOR = "X-Forwarded-For";
}