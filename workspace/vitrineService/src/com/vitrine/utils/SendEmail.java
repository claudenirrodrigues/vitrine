package com.vitrine.utils;

import java.util.HashMap;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public final class SendEmail {
	
	private static SendEmail sendEmail;
	//private static final String FROM = "noreply@modavitrine.com.br";
	private static final String FROM = "contato@modavitrine.com.br";
	private static final String MAIL_SERVER_SMPT = "smtp.umbler.com";
	private static final String USERNAME = "contato@modavitrine.com.br";
	private static final String PASSWORD = "*CTaglh7*";
	private static final String SMTP_PORT = "587";
	private static final String BCC_ADDRESS = "representante@modavitrine.com.br";
	public static final int ENVIAR_CHAVE_SERVICO = 1;
	private static Properties properties;
    
	public enum EnumSendEmail{
		NOME_CONTATO("nomeContato"), 
		SERVICE_KEY("serviceKey");
		
		private String value;
		
		EnumSendEmail(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
		
	}
	
    private SendEmail() {
    	
	}

    public SendEmail getInstance(){
    	if(sendEmail == null){
    		sendEmail = new SendEmail();
    	}
    	return sendEmail;
    }
    


	public static void send(String baseURI, String toAddress, String subject, HashMap<String, String> content, int template) throws MessagingException {
      // Get system properties
      properties = System.getProperties();

      // Setup mail server
      properties.setProperty("mail.smtp.host", MAIL_SERVER_SMPT);
      properties.setProperty("mail.user", USERNAME);
      properties.setProperty("mail.password", PASSWORD);
      properties.setProperty("mail.smtp.port", SMTP_PORT);
      properties.setProperty("mail.smtp.starttls.enable", "true");
      properties.setProperty("mail.smtp.auth", "true");
      properties.setProperty("mail.smtp.ssl.trust", MAIL_SERVER_SMPT);
      
      // Get the default Session object.
      Session session = Session.getDefaultInstance(properties);

         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(FROM));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.BCC, new InternetAddress(BCC_ADDRESS));

         // Set Subject: header field
         message.setSubject(subject);

         // Send the actual HTML message, as big as you like
         message.setContent(getTemplate(baseURI, content,template),"text/html");

         // Send message
         Transport.send(message, USERNAME, PASSWORD);
      
   }

	private static String getTemplate(String baseURI, HashMap<String, String> content, int template) {
		// TODO Auto-generated method stub
		switch (template) {
		case ENVIAR_CHAVE_SERVICO:
			return getTemplateServiceKey(baseURI, content);
		default:
			return "Mensagem enviada automaticamente de www.modavitrine.com.br";
		}
	}

	private static String getTemplateServiceKey(String baseURI, HashMap<String, String> content) {
		String nomeContato = content.get(EnumSendEmail.NOME_CONTATO.getValue());
		String serviceKey = content.get(EnumSendEmail.SERVICE_KEY.getValue());
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(getHeaderTemplate(baseURI, nomeContato));
		sb.append("<div id='content' style='text-align: center;'>");
		sb.append("	<p>O seu cadastro foi efetuado com secesso em nosso sistema de Apoio a Vendas VitrineDash!</p>");
		sb.append("	<p>Segue sua chave de acesso, para finalizar o cadastro ela deverá ser informada.</p>");
		sb.append("	<h2>Chave de Acesso: "+ serviceKey +"</h2>");
		sb.append("</div>");
		sb.append(getFooterTemplate(baseURI));
		return sb.toString();
	}

	private static Object getFooterTemplate(String baseURI) {
		StringBuffer sb =  new StringBuffer();
		
		sb.append("<div>");
		sb.append("	<a href='http://www.modavitrine.com.br'>"); 
		sb.append("		<img style='display: block; margin-left: auto; margin-right: auto;' src='"+baseURI+"/vitrineDashboard/pages/img/email/footer.jpg'/>"); 
		sb.append("	</a>");
		sb.append("</div>");
		sb.append("<p>&nbsp;</p>");
		sb.append("<div style='text-align: center; font-size: 11px;'>");
		sb.append("	<p>Este e-mail foi enviado automaticamente em Moda Vitrine -&nbsp;<a href='http://www.modavitrine.com.br'>www.modavitrine.com.br</a></p>");
		sb.append("</div>");
	
		return sb.toString();
	}

	private static String getHeaderTemplate(String baseURI, String nomeContato) {
		StringBuffer sb =  new StringBuffer();
		
		sb.append("<style>"); 
		sb.append("	@import 'https://fonts.googleapis.com/css?family=Open+Sans';");
		sb.append("	body {");
		sb.append("	   font-family: 'Open Sans', sans-serif;");
		sb.append("	   color: #5B2633;");
		sb.append("	}");
		sb.append("</style>");
		sb.append("<div>&nbsp;</div>");
		sb.append("<div style='text-align: center'>");
		sb.append("	<h1 style='color: #5B2633;'>Ol&aacute; "+ nomeContato +", tudo bem?</h1>");
		sb.append("</div>");
		sb.append("<div>&nbsp;</div>");
		sb.append("<div>");
		sb.append("	<img style='display: block; margin-left: auto; margin-right: auto;' src='"+baseURI+"/vitrineDashboard/pages/img/email/header.jpg'/>");
		sb.append("</div>");
		
		return sb.toString();
	}
	
	
}
