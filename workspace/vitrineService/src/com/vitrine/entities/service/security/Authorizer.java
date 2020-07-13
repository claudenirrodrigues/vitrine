package com.vitrine.entities.service.security;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.crypto.KeyGenerator;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;

import com.github.scribejava.core.oauth.OAuth20Service;
import com.github.scribejava.apis.google.GoogleToken;
import com.github.scribejava.core.exceptions.OAuthException;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.Response;
import com.vitrine.entities.Usuario;
import com.vitrine.entities.service.security.exception.GoogleOAuth20Exception;
import com.vitrine.utils.FormatHelper;
import com.vitrine.utils.HttpHeadersOAuth;
import com.vitrine.utils.Logger;

/**
 * 
 * @author Familia
 *
 * Concentra métodos que utilizam dados da sessão do Usuario
 * sem necessidade de acesso à base de dados 
 * e todos os acessos ao Servidor de Autorização (Google, Facebook).
 * 
 * 
 */
public final class Authorizer {

	public static final Integer REMEMBER_LOGIN = new Integer(1);
	private static Map<String, String> temporaryStateStorage = new HashMap<>();
	private static Authorizer authorizer;
	
	
	private Authorizer() {

	}

	public static Authorizer getInstance() {

		if (authorizer == null) {
			authorizer = new Authorizer();
		}

		return authorizer;
	}

	public String getAuthorizationUrl(String origin) {
		String autorizationUrl;
		try{
			String state = getNewState();
			OAuth20Service oAuth20 = getOAuth20Service(state, origin);
			autorizationUrl = oAuth20.getAuthorizationUrl(GoogleOAuth20.getAdditionalParams());
		}catch (GoogleOAuth20Exception e) {
			Logger.log("Erro ao capturar url de autorização.", this.getClass().getSimpleName(), e.getMessage(), Logger.ERROR);
			autorizationUrl = origin + Authenticator.URL_LOGOUT;
		}
		
		return autorizationUrl;
	}

	private OAuth20Service getOAuth20Service(String state, String origin) throws GoogleOAuth20Exception {
		
		if(state == null || state.isEmpty()){
			
			throw new GoogleOAuth20Exception("Valor do State não pode ser nulo. State enviado: "+state);
		}
		
		return getGoogleOAuth20().getOAuth20Service(origin, state);
	}

	public OAuth2AccessToken getAccessToken(Usuario usuario) throws GeneralSecurityException {
		
		if(usuario.getRawResponse() == null ||
				usuario.getAccessToken() == null ||
				usuario.getRefreshToken() == null){
			throw new GeneralSecurityException("Usuario não possui dados necessários armazenados para obter o accessToken.");
		}
		GoogleToken googleToken = 
				new GoogleToken(usuario.getAccessToken(), 
								usuario.getTokenType() != null?usuario.getTokenType():null, 
								usuario.getExpiresIn() != null?usuario.getExpiresIn():null, 
								usuario.getRefreshToken(), 
								usuario.getScope() != null?usuario.getScope():null, 
								usuario.getOpenIdToken() != null?usuario.getOpenIdToken():null, 
								usuario.getRawResponse() != null?usuario.getRawResponse():null);
		
		return googleToken;

	}

	public OAuth2AccessToken getAccessToken(String state, String code, String origin) throws GoogleOAuth20Exception {
		if(!temporaryStateStorage.containsKey(state)){
			throw new GoogleOAuth20Exception("Valor do state não foi localizado.");
		}
		return getGoogleOAuth20().getAccessToken(code, this.getOAuth20Service(state, origin));
		
	}

	public OAuth2AccessToken getRefreshedAccessToken(OAuth2AccessToken accessToken, String state, String origin) throws IOException, GoogleOAuth20Exception {
		
		return getGoogleOAuth20().getRefreshedAccessToken(accessToken, this.getOAuth20Service(state, origin));
		
	}

	public JsonObject getUserData(String state, String origin, OAuth2AccessToken refreshToken) {
		
		
		String userData = null;
		try {
			Response response = getGoogleOAuth20().getDataOAuth20Service(null, refreshToken, this.getOAuth20Service(state, origin));
			userData = response.getBody();
		} catch (IOException | GoogleOAuth20Exception e) {
			e.printStackTrace();
			Logger.log("Erro ao capturar dados do usuario no Servidor de Autorização: ", this.getClass().getSimpleName(),
					e.getMessage(), Logger.ERROR);
		}
		
		return FormatHelper.jsonFromString(userData);
	}

	public static boolean isValidAuthorization(HttpServletRequest httpRequest) {
		return !(httpRequest.getHeader(HttpHeadersOAuth.STATE) == null ||
					httpRequest.getHeader(HttpHeadersOAuth.STATE).isEmpty()) &&
				!(httpRequest.getHeader(HttpHeadersOAuth.CODE) == null ||
					httpRequest.getHeader(HttpHeadersOAuth.CODE).isEmpty()) &&
				containsValidParameters(httpRequest);
	}

	public static boolean containsValidParameters(HttpServletRequest httpRequest) {
		return !(httpRequest.getHeader(HttpHeadersOAuth.ORIGIN) == null ||
						httpRequest.getHeader(HttpHeadersOAuth.ORIGIN).isEmpty());
	}

	private GoogleOAuth20 getGoogleOAuth20() {
		return new GoogleOAuth20();
	}

	public String getNewState() {
		String state = UUID.randomUUID().toString();
		temporaryStateStorage.put(state, state);
		return state;
		
	}

	public void removeState(String state) {
		if(temporaryStateStorage.containsKey(state)){
			temporaryStateStorage.remove(state);
		}
		
	}
	
	public String generateServiceKey() throws NoSuchAlgorithmException {
		String serviceKey = getEncodedKey();
		return serviceKey;
	}

	private String getEncodedKey() throws NoSuchAlgorithmException{
		SecureRandom rand = new SecureRandom();
		KeyGenerator generator;
		generator = KeyGenerator.getInstance("AES");
		generator.init(rand);
		generator.init(256);
		return new String(Base64.getEncoder().encode(generator.generateKey().getEncoded()));
	}
}
