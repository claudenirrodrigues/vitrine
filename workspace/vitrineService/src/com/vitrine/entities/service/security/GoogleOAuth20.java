package com.vitrine.entities.service.security;

import java.util.Random;
import java.util.UUID;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.exceptions.OAuthException;
import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.vitrine.entities.service.security.exception.GoogleOAuth20Exception;
import com.vitrine.utils.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GoogleOAuth20 {

	// OAuth 2.0 Playground - List possible operations
	private static final String PROTECTED_RESOURCE_URL = "https://www.googleapis.com/plus/v1/people/me";
	/**
	 * Informar o CLIENT_ID CLIENT_SECRET cadastrado no console do Google (ou
	 * obtido em outro servidor de acesso: Facebook, Tweeter, etc.)
	 */
	private static final String CLIENT_ID = "27872947337-r903ef3fmhon91iouvsvmlv2d94a5dla.apps.googleusercontent.com";
	private static final String CLIENT_SECRET = "O_b311jWVBqHLNbl8c8bLRVD";
	// http://url_callback_cadastrada_console_google
	//private static final String URL_CALLBACK = "http://localhost:8080/vitrineDashboard/pages/autenticacao.html";//desenv
	//private static final String URL_CALLBACK = "http://vitrinejw.jelastic.websolute.net.br/vitrineDashboard/pages/autenticacao.html";//prod
	private static final String URL_CALLBACK = "/vitrineDashboard/pages/autenticacao.html";//recebe Origin do HttpServletRequest
	private static final String SCOPE = "profile email https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/plus.me";
	
	public GoogleOAuth20() {

	}

	public final OAuth20Service getOAuth20Service(String origin, String state) {
		/**
		 * Gerar token com IP aqui? (JWT faz isso) Neste caso a segurança
		 * aumenta, diminuo os riscos de ataques simultaneos. O usuário poderá
		 * utilizar apenas um device. Deverá ter um tratamento para identificar
		 * o tipo de device (Mobile ou Desktop) para permitir acesso simultaneo
		 * de um unico usuário
		 */
		OAuth20Service oAuth20 = new ServiceBuilder()
				.apiKey(CLIENT_ID)
				.apiSecret(CLIENT_SECRET)
				.scope(SCOPE)
				.state(state)
				.callback(origin + URL_CALLBACK)
				.build(GoogleApi20.instance());

		return oAuth20;
	}

	/**
	 * Passar o parametro access_type=offline para obter o refresh token
	 * https://developers.google.com/identity/protocols/OAuth2WebServer#preparing-to-start-the-oauth-20-flow
	 */
	public static final Map<String, String> getAdditionalParams() {

		final Map<String, String> additionalParams = new HashMap<>();

		additionalParams.put("access_type", "offline");
		additionalParams.put("prompt", "consent");
		//additionalParams.put("prompt", "select_account");

		return additionalParams;

	}

	/**
	 * Recebo a seguinte URL:
	 * http://url_callback_cadastrada_console_google?state=secret70247&code=4/RbjkXdp2cwPRQesO2daX5QO-kKWkRCZkCcMv0JIc12M
	 * 
	 * @param code
	 * @param state
	 * @throws IOException
	 */
	public OAuth2AccessToken getAccessToken(String code, OAuth20Service oAuth20) {

		// Efetuar Request Token e Verificar Access Token
		OAuth2AccessToken accessToken = null;
		try {
			accessToken = oAuth20.getAccessToken(code);
			System.out.println("accessToken: " + accessToken + ", rawResponse:" + accessToken.getRawResponse());

			//Refreshing the Access Token...
			//Manter o accessToken original
			//accessToken = getRefreshedAccessToken(accessToken, oAuth20);
		
		} catch (IOException e) {
			e.printStackTrace();
			Logger.log("Erro ao efetuar Request Token e Verificar Access Token: ", this.getClass().getSimpleName(),
					e.getMessage(), Logger.ERROR);
		}

		return accessToken;

	}

	public OAuth2AccessToken getRefreshedAccessToken(OAuth2AccessToken accessToken, OAuth20Service oAuth20) throws IOException {
		accessToken = oAuth20.refreshAccessToken(accessToken.getRefreshToken());
		System.out.println("accessToken: " + accessToken + ", rawResponse: " + accessToken.getRawResponse());
		return accessToken;
	}

	public Response getDataOAuth20Service(String url, OAuth2AccessToken accessToken, OAuth20Service oAuth20) {

		if (url == null || url.isEmpty()) {
			// Object JSON Completo
			url = PROTECTED_RESOURCE_URL;// OAuth 2.0 Playground List
												// possible operations
		} else {
			url = PROTECTED_RESOURCE_URL + "?fields=" + url;
		}

		final OAuthRequest request = new OAuthRequest(Verb.GET, url, oAuth20);
		oAuth20.signRequest(accessToken, request);
		return request.send();

	}

}
