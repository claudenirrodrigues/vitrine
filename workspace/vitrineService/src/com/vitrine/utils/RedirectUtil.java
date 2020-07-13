package com.vitrine.utils;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import java.net.URL;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

/**
 * Provides a utility method to create a Response redirecting to another
 * location.
 *
 * @author mir
 */
public class RedirectUtil {

	/**
	 * Redirects to the location provided by uriString in the context of the
	 * request uri provided by baseUri.
	 *
	 * @param uriString
	 * @param baseUri
	 * @return {@link javax.ws.rs.core.Response}
	 */
	public static Response createSeeOtherResponse(String uriString, UriInfo baseUri) {
		try {
			return createSeeOtherResponse(uriString, baseUri.getAbsolutePath().toURL());
		} catch (MalformedURLException ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * Redirects to the location provided by uriString in the context specified
	 * by baseUrl.
	 *
	 * @param uriString
	 * @param baseUrl
	 * @return {@link javax.ws.rs.core.Response}
	 */
	public static Response createSeeOtherResponse(String uriString, URL baseUrl) {

		String htmlBody = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\""
				+ "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">"
				+ "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">" + "<body> <a href=\""
				+ uriString + "\">" + uriString + "</a><body></html>";
		try {
			URL absoluteUrl = new URL(baseUrl, uriString);
			final URI uri = absoluteUrl.toURI();
			ResponseBuilder responseBuilder = Response.status(Response.Status.SEE_OTHER);
			responseBuilder = responseBuilder.entity(htmlBody);
			responseBuilder = responseBuilder.location(uri);
			return responseBuilder.build();
		} catch (URISyntaxException ex) {
			throw new RuntimeException(ex);
		} catch (MalformedURLException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	/**
	 * Redirects to the location provided by uriString in the context specified
	 * by baseUrl.
	 *
	 * @param uriString
	 * @param baseUrl
	 * @return {@link javax.ws.rs.core.Response}
	 */
	public static Response createSeeOtherResponse(URI uri) {

		//String htmlBody = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\""
		//		+ "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">"
		//		+ "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">" + "<body> <a href=\""
		//		+ uri + "\">" + uri + "</a><body></html>";
		
			ResponseBuilder responseBuilder = Response.status(Response.Status.SEE_OTHER);
			//responseBuilder = responseBuilder.entity(htmlBody);
			responseBuilder = responseBuilder.location(uri);
			return responseBuilder.build();
		
	}
}