package com.vitrine.entities.service.security;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.vitrine.entities.Pessoa;
import com.vitrine.entities.Usuario;
import com.vitrine.entities.service.UsuarioService;
import com.vitrine.entities.service.security.exception.GoogleOAuth20Exception;
import com.vitrine.utils.FormatHelper;
import com.vitrine.utils.HttpHeadersOAuth;
import com.vitrine.utils.Logger;
import com.vitrine.utils.SendEmail;
import com.vitrine.utils.SendEmail.EnumSendEmail;

/**
 *
 * @author Familia
 */
@Stateless
@Path("security")
public class LoginService extends UsuarioService {

	public LoginService() {

	}

	@POST
	@Path("authorization")
	@Produces(MediaType.APPLICATION_JSON)
	public Response authorization(@Context HttpServletRequest httpRequest, @Context UriInfo uriInfo) {

		if (Authorizer.containsValidParameters(httpRequest)) {
			
			JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
			JsonObject jsonObj;
			
			String authToken = httpRequest.getHeader(HttpHeadersOAuth.AUTHORIZATION);
			String serviceKey = httpRequest.getHeader(HttpHeadersOAuth.SERVICE_KEY);
			String origin = httpRequest.getHeader(HttpHeadersOAuth.ORIGIN);
			
			
			try{
				Response responseLogin = getResponseLogin(uriInfo, authToken, serviceKey, origin);
				
				if(responseLogin.getStatus() == Response.Status.OK.getStatusCode()){
					return responseLogin;
				}else{
					throw new GeneralSecurityException("Usuario inválido, deverá realizar nova autenticação!");
				}
				

			}catch (GeneralSecurityException |  RuntimeException e) {
					
					jsonObjBuilder.add("authorization", Authorizer.getInstance().getAuthorizationUrl(origin));
					jsonObjBuilder.add("message", e.getMessage());
					jsonObj = jsonObjBuilder.build();
					return getNoCacheResponseBuilder(Response.Status.OK).entity(jsonObj.toString()).build();
			}
		
		} else {
			JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
			jsonObjBuilder.add("message", "Problema corresponde ao Serviço de Autorização.");
			JsonObject jsonObj = jsonObjBuilder.build();

			return getNoCacheResponseBuilder(Response.Status.UNAUTHORIZED).entity(jsonObj.toString()).build();
		}
	}

	@POST
	@Path("authentication")
	@Produces(MediaType.APPLICATION_JSON)
	public Response authentication(@Context HttpServletRequest httpRequest, @Context UriInfo uriInfo) {

		
		JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
		JsonObject jsonObj;
		
		// Verificar se o Servidor de Autorização retornou com o state e code
		if (!Authorizer.isValidAuthorization(httpRequest)) {

			jsonObjBuilder.add("message", "Acesso negado pelo Servidor de Autorização.");
			jsonObj = jsonObjBuilder.build();

			return getNoCacheResponseBuilder(Response.Status.UNAUTHORIZED).entity(jsonObj.toString()).build();
		}
		
		OAuth2AccessToken accessToken;
		OAuth2AccessToken refreshToken;
		JsonObject userData;
		Usuario usuario;
		
		String state = httpRequest.getHeader(HttpHeadersOAuth.STATE);
		String code = httpRequest.getHeader(HttpHeadersOAuth.CODE);
		String origin = httpRequest.getHeader(HttpHeadersOAuth.ORIGIN);
		String serviceKey = httpRequest.getHeader(HttpHeadersOAuth.SERVICE_KEY);
		String authToken = httpRequest.getHeader(HttpHeadersOAuth.AUTHORIZATION);
		
		
		try {
			
			// Verificar se o usuário já possui uma ServiceKey valida (usuários já cadastrados)
			Response responseLogin = getResponseLogin(uriInfo, authToken, serviceKey, origin);
			
			if(responseLogin.getStatus() == Response.Status.OK.getStatusCode()){
				return responseLogin;
			}else{
				throw new GeneralSecurityException("Não foi possível efetuar login, gerar ServiceKey e AuthToken e enviar para o usuário!");
			}
			
			
		} catch (GeneralSecurityException e){
			e.printStackTrace();
			Logger.log("ServiceKey ou AuthToken inválidos - ServiceKey: "+serviceKey+" - AuthToken: "+authToken
					+" (Gerar ServiceKey e AuthToken e enviar para o usuário.", this.getClass().getSimpleName(),
					e.getMessage(), Logger.INFO);
		}
			
			//Gera Chave de Serviço
			//Capturar accessToken do Servidor de Autorização
			try {
			
				serviceKey = Authorizer.getInstance().generateServiceKey();
				accessToken = Authorizer.getInstance().getAccessToken(state, code, origin);
				refreshToken = Authorizer.getInstance().getRefreshedAccessToken(accessToken, state, origin);
				userData = Authorizer.getInstance().getUserData(state, origin, refreshToken);
				usuario = getUsuarioLogin(userData);
				Authorizer.getInstance().removeState(state);
				Authenticator.getInstance(getDataSource()).createAuthorizer(usuario, serviceKey);
			
			} catch (final GoogleOAuth20Exception |  GeneralSecurityException | IOException e) {
	
				e.printStackTrace();
				
				if(e instanceof GoogleOAuth20Exception){
					Logger.log("Erro ao capturar o accessToken do Servidor de Autorização: ", this.getClass().getSimpleName(),
							e.getMessage(), Logger.ERROR);
		
					jsonObjBuilder.add("message", "Não foi possível obter informações do Servidor de Autorização.");
				}
				
				if(e instanceof NoSuchAlgorithmException){
					Logger.log("Erro ao gerar Chave de Acesso: ", this.getClass().getSimpleName(),
							e.getMessage(), Logger.ERROR);
		
					jsonObjBuilder.add("message", "Não foi possível gerar Chave de Acesso.");
				}
				
				if(e instanceof GeneralSecurityException){
					Logger.log("Erro ao gerar Chave de Acesso: ", this.getClass().getSimpleName(),
							e.getMessage(), Logger.ERROR);
		
					jsonObjBuilder.add("message", "Não foi possível gerar Chave de Acesso.");
				}
				
				if(e instanceof IOException){
					Logger.log("Erro ao capturar o refreshToken do Servidor de Autorização: ", this.getClass().getSimpleName(),
							e.getMessage(), Logger.ERROR);
		
					jsonObjBuilder.add("message", "Não foi possível obter informações do Servidor de Autorização");
				}
				
				jsonObj = jsonObjBuilder.build();
				return getNoCacheResponseBuilder(Response.Status.UNAUTHORIZED).entity(jsonObj.toString()).build();
			}
	
			return redirectInputServiceKey(uriInfo, accessToken, usuario, serviceKey);
		

	}
	
	private Response getResponseLogin(UriInfo uriInfo, String authToken, String serviceKey, String origin) throws GeneralSecurityException {
		
		JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
		JsonObject jsonObj;
		OAuth2AccessToken accessToken;
		OAuth2AccessToken refreshToken;
		JsonObject userData;
		Usuario usuario;
		Usuario usuarioToken;
		
		if(Authenticator.getInstance(getDataSource()).isValidAuthentication(authToken, serviceKey, true)){
			
			// Capturar accessToken relacionado ao Usuario
			try {
				
				//obtem os dados armazenados do usuário
				usuarioToken =  Authenticator.getInstance(getDataSource()).getAuthenticatedUser(authToken);
				
				//obtem os dados atualizados
				accessToken = Authorizer.getInstance().getAccessToken(usuarioToken);
				refreshToken = Authorizer.getInstance().getRefreshedAccessToken(accessToken, serviceKey, origin);
				userData = Authorizer.getInstance().getUserData(serviceKey, origin, refreshToken);
				usuario = getUsuarioLogin(userData);
				
				//valida se o usuário atualizado continua sendo o mesmo
				if(!usuario.equals(usuarioToken)){
					throw new GeneralSecurityException("Usuario inválido, deverá realizar nova autenticação!");
				}
				
				
			} catch (final GoogleOAuth20Exception | GeneralSecurityException | IOException e) {
	
				e.printStackTrace();
				
				if(e instanceof GoogleOAuth20Exception){
					Logger.log("Erro ao inicializar o Serviço de Autorização: ", this.getClass().getSimpleName(),
							e.getMessage(), Logger.ERROR);
		
					jsonObjBuilder.add("message", "Não foi possível inicializar o Serviço de Autorização.");
				}
				
				if(e instanceof GeneralSecurityException){
					Logger.log("Erro ao validar os dados do usuário: ", this.getClass().getSimpleName(),
							e.getMessage(), Logger.ERROR);
		
					jsonObjBuilder.add("message", "Não foi possível validar os dados do usuário.");
				}
				
				if(e instanceof IOException){
					Logger.log("Erro ao capturar o refreshToken do Servidor de Autorização: ", this.getClass().getSimpleName(),
							e.getMessage(), Logger.ERROR);
		
					jsonObjBuilder.add("message", "Não foi possível obter informações do Servidor de Autorização");
				}
				
				jsonObj = jsonObjBuilder.build();
				
				return getNoCacheResponseBuilder(Response.Status.UNAUTHORIZED).entity(jsonObj.toString()).build();
			}

			return login(uriInfo, accessToken, usuario, serviceKey);
			
		}else{
			throw new GeneralSecurityException("Usuario não possui token válido, deverá realizar nova autenticação!");
		}
	}
	
	private Response redirectInputServiceKey(UriInfo uriInfo, OAuth2AccessToken accessToken, Usuario usuario, String serviceKey) {
		
		JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
		JsonObject jsonObj;
		
		try {
			String baseURI = FormatHelper.getBaseURI(uriInfo);
			URI uri = new URI(baseURI + Authenticator.URL_INPUT_SERVICE_KEY);
			
			HashMap<String, String> content = new HashMap<String, String>();
			content.put(EnumSendEmail.NOME_CONTATO.getValue(), FormatHelper.getNomeUsuario(usuario));
			content.put(EnumSendEmail.SERVICE_KEY.getValue(),serviceKey);
			
			SendEmail.send(baseURI, usuario.getIdUsuario(), "Contato Moda Vitrine - CHAVE DE ACESSO ao VitrineDash", content, SendEmail.ENVIAR_CHAVE_SERVICO);

			String authToken = Authenticator.getInstance(getDataSource()).login(accessToken, usuario, serviceKey);
			
			
			String nome = FormatHelper.getNomeUsuario(usuario);
			String email = usuario.getIdUsuario();
			
			jsonObjBuilder.add("nome", nome);
			jsonObjBuilder.add("email", email);
			jsonObjBuilder.add("authToken", authToken);
			jsonObjBuilder.add("inputServiceKey", uri.toString());
			jsonObj = jsonObjBuilder.build();
			
			
			return getNoCacheResponseBuilder(Response.Status.OK)
					.entity(jsonObj.toString())
					.build();
			
			
		} catch (MalformedURLException | URISyntaxException | MessagingException e) {
			e.printStackTrace();
			
			if(e instanceof MalformedURLException){
				Logger.log("Erro ao editar URL de Autenticação: ", this.getClass().getSimpleName(), e.getMessage(), Logger.ERROR);
				jsonObjBuilder = Json.createObjectBuilder();
				jsonObjBuilder.add("message", "Falha ao editar URL de Autenticação.");
			}
			
			if(e instanceof URISyntaxException){
				Logger.log("Não foi possível verificar a URI de Autenticação: ", this.getClass().getSimpleName(), e.getMessage(), Logger.ERROR);
				jsonObjBuilder = Json.createObjectBuilder();
				jsonObjBuilder.add("message", "Falha ao verificar a URI de Autenticação.");
			}
			
			if(e instanceof MessagingException){
				Logger.log("Não foi possível enviar o email com Chave de Acesso: ", this.getClass().getSimpleName(), e.getMessage(), Logger.ERROR);
				jsonObjBuilder = Json.createObjectBuilder();
				jsonObjBuilder.add("message", "Falha ao enviar email com Chave de Acesso.");
			}
			
			jsonObj = jsonObjBuilder.build();

			return getNoCacheResponseBuilder(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObj.toString()).build();
		}
	}

	private Response login(UriInfo uriInfo, OAuth2AccessToken accessToken, Usuario usuario,
			String serviceKey) {
		
		JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
		JsonObject jsonObj;
		
		try {
			String baseURI = FormatHelper.getBaseURI(uriInfo);
			URI uri = new URI(baseURI + Authenticator.URL_AUTHENTICATION_SUCCESS);
			
			String authToken = Authenticator.getInstance(getDataSource()).login(accessToken, usuario, serviceKey);
			
			Logger.log("Enviando authToken: " + authToken, Logger.INFO);
			
			jsonObjBuilder.add("authToken", authToken);
			jsonObjBuilder.add("home", uri.toString());
			jsonObj = jsonObjBuilder.build();
			
			
			return getNoCacheResponseBuilder(Response.Status.OK)
					.entity(jsonObj.toString())
					.build();

		} catch (MalformedURLException e) {
			e.printStackTrace();
			Logger.log("Erro ao editar URL de Autenticação: ", this.getClass().getSimpleName(),
					e.getMessage(), Logger.ERROR);

			jsonObjBuilder = Json.createObjectBuilder();
			jsonObjBuilder.add("message", "Falha ao editar URL de Autenticação.");
			jsonObj = jsonObjBuilder.build();

			return getNoCacheResponseBuilder(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObj.toString()).build();
		} catch (URISyntaxException e) {
			e.printStackTrace();
			Logger.log("Não foi possível verificar a URI de Autenticação: ", this.getClass().getSimpleName(),
					e.getMessage(), Logger.ERROR);

			jsonObjBuilder = Json.createObjectBuilder();
			jsonObjBuilder.add("message", "Falha ao ao verificar a URI de Autenticação.");
			jsonObj = jsonObjBuilder.build();

			return getNoCacheResponseBuilder(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObj.toString()).build();
		}
	}

	@POST
	@Path("/initialize")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Usuario initialize(@Context HttpHeaders httpHeaders) {
		String authToken = httpHeaders.getHeaderString(HttpHeadersOAuth.AUTHORIZATION);
		Usuario usuario = Authenticator.getInstance(getDataSource())
				.getAuthenticatedUser(authToken);
		if(usuario != null && usuario.getIdUsuario() != null){
			usuario = super.find(usuario.getIdUsuario());
		}
		return usuario;
	}

	@POST
	@Path("logout")
	public Response logout(@Context HttpHeaders httpHeaders, @Context UriInfo uriInfo) {
		
		JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
		JsonObject jsonObj;
		
		try {
			String baseURI = FormatHelper.getBaseURI(uriInfo);
			URI uri = new URI(baseURI + Authenticator.URL_LOGOUT);
			
			String authToken = httpHeaders.getHeaderString(HttpHeadersOAuth.AUTHORIZATION);
				
			Authenticator.getInstance(getDataSource()).logout(authToken);
			
			jsonObjBuilder.add("logout", uri.toString());
			jsonObj = jsonObjBuilder.build();
			
			
			return getNoCacheResponseBuilder(Response.Status.OK)
					.entity(jsonObj.toString())
					.build();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
			Logger.log("Erro ao editar URL de Saida: ", this.getClass().getSimpleName(),
					e.getMessage(), Logger.ERROR);

			jsonObjBuilder = Json.createObjectBuilder();
			jsonObjBuilder.add("message", "Falha ao editar URL de Saida.");
			jsonObj = jsonObjBuilder.build();

			return getNoCacheResponseBuilder(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObj.toString()).build();
		} catch (URISyntaxException e) {
			e.printStackTrace();
			Logger.log("Não foi possível verificar a URI de Saida: ", this.getClass().getSimpleName(),
					e.getMessage(), Logger.ERROR);

			jsonObjBuilder = Json.createObjectBuilder();
			jsonObjBuilder.add("message", "Falha ao ao verificar a URI de Saida.");
			jsonObj = jsonObjBuilder.build();

			return getNoCacheResponseBuilder(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObj.toString()).build();
		}
		
	}

	private Response.ResponseBuilder getNoCacheResponseBuilder(Response.Status status) {
		CacheControl cc = new CacheControl();
		cc.setNoCache(true);
		cc.setMaxAge(-1);
		cc.setMustRevalidate(true);
		
		return Response.status(status).cacheControl(cc);
	}

	private Usuario getUsuarioLogin(JsonObject userData) throws GeneralSecurityException {
		
		Usuario usuario = super.find(getIdUsuario(userData));

		// verifica se o usuario não esta cadastrado no banco de dados.
		if (usuario == null || usuario.getIdUsuario() == null) {
			
			// cria um usuario com dados basicos
			usuario = createUsuario(userData);
					
		}

		return usuario;

	}

	private Usuario createUsuario(JsonObject userData) throws GeneralSecurityException {
		
		Usuario usuario = new Usuario(getIdUsuario(userData));
		
		super.create(usuario);
		
		Pessoa pessoa = new Pessoa();
		pessoa.setDataAlteracao(new Date());
		pessoa.setDataInclusao(new Date());
		pessoa.setEmailPrincipal(getIdUsuario(userData));
		pessoa.setIdUsuario(getIdUsuario(userData));
		pessoa.setNome(userData.getJsonObject("name").getString("givenName"));
		pessoa.setNomeExibido(userData.getString("displayName"));
		pessoa.setSobrenome(userData.getJsonObject("name").getString("familyName"));
		
		Collection<Pessoa> collPessoa = new ArrayList<Pessoa>();
		collPessoa.add(pessoa);
		usuario.setPessoaCollection(collPessoa);
		super.createPessoa(usuario);
		
		return usuario;
		
	}

	
	private String getIdUsuario(JsonObject userData) throws GeneralSecurityException {
		if(userData.getJsonArray("emails") != null && userData.getJsonArray("emails").size() > 0){
			for (int i = 0; i < userData.getJsonArray("emails").size(); i++) {
				if(userData.getJsonArray("emails").getJsonObject(i).getString("type").equals("account")){
					return userData.getJsonArray("emails").getJsonObject(i).getString("value");
				}
			}
		}else{
			throw new GeneralSecurityException("Erro: Servidor de Autorização não retornou o ID do usuário.");
		}
		 
		
		return null;
	}
}