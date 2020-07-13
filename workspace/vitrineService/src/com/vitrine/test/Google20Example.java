package com.vitrine.test;

import java.util.Random;
import java.util.Scanner;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class Google20Example {

    private static final String NETWORK_NAME = "G+";
    private static final String PROTECTED_RESOURCE_URL = "https://www.googleapis.com/plus/v1/people/me";////OAuth 2.0 Playground - List possible operations

    private Google20Example() {
    }

    public static void main(String[] args) throws IOException {
        /**
         * Informar o CLIENT_ID CLIENT_SECRET cadastrado no console do Google 
         * (ou obtido em outro servidor de acesso: Facebook, Tweeter, etc.)
         */
        final String clientId = "27872947337-r903ef3fmhon91iouvsvmlv2d94a5dla.apps.googleusercontent.com";
        final String clientSecret = "O_b311jWVBqHLNbl8c8bLRVD";
        /**
         * Gerar token com IP aqui? (JWT faz isso)
         * Neste caso a segurança aumenta, diminuo os riscos de ataques simultaneos.
         * O usuário poderá utilizar apenas um device.
         * Deverá ter um tratamento para identificar o tipo de device (Mobile ou Desktop) 
         * para permitir acesso simultaneo de um unico usuário
         */
        final String secretState = "secret" + new Random().nextInt(999_999);
        
        final OAuth20Service service = new ServiceBuilder()
                .apiKey(clientId)
                .apiSecret(clientSecret)
                .scope("profile email https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/plus.me") // replace with desired scope
                .state(secretState)
                .callback("http://localhost:8080/vitrineDashboard/pages/autenticacao.html")//http://url_callback_cadastrada_console_google
                .build(GoogleApi20.instance());
        final Scanner in = new Scanner(System.in, "UTF-8");

        System.out.println("=== " + NETWORK_NAME + "'s OAuth Workflow ===");
        System.out.println();

        // Obtain the Authorization URL
        System.out.println("Fetching the Authorization URL...");
        //pass access_type=offline to get refresh token
        //https://developers.google.com/identity/protocols/OAuth2WebServer#preparing-to-start-the-oauth-20-flow
        final Map<String, String> additionalParams = new HashMap<>();
        additionalParams.put("access_type", "offline");
        //force to reget refresh token (if user a are asked not the first time)
        additionalParams.put("prompt", "consent");
        final String authorizationUrl = service.getAuthorizationUrl(additionalParams);
        System.out.println("Recebi URL para efetuar a autorização!");
        System.out.println("Agora redireciono a URL para capturar o código de autorização:");
        System.out.println(authorizationUrl);//Acessar esta url para teste no browser
        System.out.println("Informo o código de autorização retornado");
        //Recebo a seguinte URL: http://url_callback_cadastrada_console_google?state=secret70247&code=4/RbjkXdp2cwPRQesO2daX5QO-kKWkRCZkCcMv0JIc12M 
        System.out.print(">>");//exemplo: code=4/RbjkXdp2cwPRQesO2daX5QO-kKWkRCZkCcMv0JIc12M
        final String code = in.nextLine();
        System.out.println();

        System.out.println("Informo a Palavra Secreta retornada que deverá ser igual a setada 'secretState'='" + secretState + "'.");
        System.out.print(">>");//exemplo: state=secret70247
        final String value = in.nextLine();
        //Aqui pode se fazer a validação do authToken (TestRequestFilter.class)
        if (secretState.equals(value)) {
            System.out.println("Valor do secretState esta correto!");
        } else {
            System.out.println("Valor do secretState não corresponde ao informado !");
            System.out.println("Esperado = " + secretState);
            System.out.println("Recebido      = " + value);
            System.out.println();
        }
        
        //Cria um novo service
        final OAuth20Service newService = new ServiceBuilder()
                .apiKey(clientId)
                .apiSecret(clientSecret)
                .scope("profile email https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/plus.me") // replace with desired scope
                .state("secret")
                .callback("http://localhost:8080/vitrineDashboard/pages/autenticacao.html")//http://url_callback_cadastrada_console_google
                .build(GoogleApi20.instance());
        
        // Trade the Request Token and Verfier for the Access Token
        System.out.println("Trading the Request Token for an Access Token...");
        OAuth2AccessToken accessToken = newService.getAccessToken(code);
        System.out.println("Got the Access Token!");
        System.out.println("(accessToken: " + accessToken
                + ", 'rawResponse'='" + accessToken.getRawResponse() + "')");

        System.out.println("Refreshing the Access Token(1)...");
        OAuth2AccessToken accessToken1 = newService.refreshAccessToken(accessToken.getRefreshToken());
        System.out.println("Refreshed the Access Token(1)!");
        System.out.println("(accessToken1: " + accessToken1
                + ", 'rawResponse'='" + accessToken1.getRawResponse() + "')");
        System.out.println();

        // Now let's go and ask for a protected resource!
        System.out.println("(1)Now we're going to access a protected resource...");
        while (true) {
            System.out.println("(1)Paste fieldnames to fetch (leave empty to get profile, 'exit' to stop example)");
            System.out.print(">>");
            final String query = in.nextLine();
            System.out.println();
            
            final String requestUrl;
            if ("exit".equals(query)) {
                break;
            } else if (query == null || query.isEmpty()) {
                //Object JSON Completo
            	requestUrl = PROTECTED_RESOURCE_URL;//OAuth 2.0 Playground - List possible operations
            } else {
            	//Field Especifico (ex.: email)
                requestUrl = PROTECTED_RESOURCE_URL + "?fields=" + query;
            }

            final OAuthRequest request = new OAuthRequest(Verb.GET, requestUrl, newService);
            service.signRequest(accessToken1, request);
            final Response response = request.send();
            System.out.println();
            System.out.println(response.getCode());
            System.out.println(response.getBody());
            
            System.out.println();
        }
        
        //Novo refresh_token
        System.out.println("Refreshing the Access Token(2)...");
        OAuth2AccessToken accessToken2 = service.refreshAccessToken(accessToken.getRefreshToken());
        System.out.println("Refreshed the Access Token(2)!");
        System.out.println("(accessToken2: " + accessToken2
                + ", 'rawResponse'='" + accessToken2.getRawResponse() + "')");
        System.out.println();

        // Now let's go and ask for a protected resource!
        System.out.println("(2)Now we're going to access a protected resource...");
        while (true) {
            System.out.println("(2)Paste fieldnames to fetch (leave empty to get profile, 'exit' to stop example)");
            System.out.print(">>");
            final String query = in.nextLine();
            System.out.println();
            
            final String requestUrl;
            if ("exit".equals(query)) {
                break;
            } else if (query == null || query.isEmpty()) {
                //Object JSON Completo
            	requestUrl = PROTECTED_RESOURCE_URL;//OAuth 2.0 Playground - List possible operations
            } else {
            	//Field Especifico (ex.: email)
                requestUrl = PROTECTED_RESOURCE_URL + "?fields=" + query;
            }

            final OAuthRequest request = new OAuthRequest(Verb.GET, requestUrl, service);
            service.signRequest(accessToken2, request);
            final Response response = request.send();
            System.out.println();
            System.out.println(response.getCode());
            System.out.println(response.getBody());
            
            System.out.println();
        }
        
        //Novo refresh_token2
        System.out.println("Refreshing the Access Token(2-1)...");
        accessToken2 = service.refreshAccessToken(accessToken2.getRefreshToken());
        System.out.println("Refreshed the Access Token(2-1)!");
        System.out.println("(accessToken2-1: " + accessToken2
                + ", 'rawResponse'='" + accessToken2.getRawResponse() + "')");
        System.out.println();

        // Now let's go and ask for a protected resource!
        System.out.println("(2-1)Now we're going to access a protected resource...");
        while (true) {
            System.out.println("(2-1)Paste fieldnames to fetch (leave empty to get profile, 'exit' to stop example)");
            System.out.print(">>");
            final String query = in.nextLine();
            System.out.println();
            
            final String requestUrl;
            if ("exit".equals(query)) {
                break;
            } else if (query == null || query.isEmpty()) {
                //Object JSON Completo
            	requestUrl = PROTECTED_RESOURCE_URL;//OAuth 2.0 Playground - List possible operations
            } else {
            	//Field Especifico (ex.: email)
                requestUrl = PROTECTED_RESOURCE_URL + "?fields=" + query;
            }

            final OAuthRequest request = new OAuthRequest(Verb.GET, requestUrl, service);
            service.signRequest(accessToken2, request);
            final Response response = request.send();
            System.out.println();
            System.out.println(response.getCode());
            System.out.println(response.getBody());
            
            System.out.println();
        }
    }
}
