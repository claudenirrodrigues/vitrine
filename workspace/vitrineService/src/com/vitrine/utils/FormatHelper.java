/*
 * Created on 08/01/2004
 *
 * Globalcode
 */
package com.vitrine.utils;

import java.io.StringReader;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.core.UriInfo;

import com.vitrine.entities.Usuario;
import com.vitrine.entities.constants.EnumToken;

public class FormatHelper {
    private static FormatHelper instance = new FormatHelper();
    private SimpleDateFormat dataSemana=new SimpleDateFormat("EEEE dd/MM/yyyy", new Locale("pt","br"));
    private SimpleDateFormat dataCompleta=new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private SimpleDateFormat dataSimples=new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat dataCurta=new SimpleDateFormat("dd/MM/yy");
    private SimpleDateFormat dataDatabase=new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static SimpleDateFormat dataCartao = new SimpleDateFormat("MM/yy");
    public static final SimpleDateFormat FORMAT_HOUR = new SimpleDateFormat("HH:mm");
    
    public static FormatHelper getInstance() {
        return instance;
    }
    private FormatHelper() {
    }
    
    // muda hora para String
    public String horaFormat(java.util.Date data) {
        if(data==null || data.equals("")) return "";
        else return FORMAT_HOUR .format(data);
    }
    
    public String bigDateFormat(java.util.Date data) {
        if(data==null || data.equals("")) return "";
        else return dataSemana.format(data);
    }
    public String fullDateFormat(java.util.Date data) {
        if(data==null || data.equals("")) return "";
        else return dataCompleta.format(data);
    }
    public String simpleDateFormat(java.util.Date data) {
        if(data==null || data.equals("")) return "";
        else return dataSimples.format(data);
    }
    public String shortDateFormat(java.util.Date data) {
        if(data==null || data.equals("")) return "";
        else return dataCurta.format(data);
    }
    public String databaseDateFormat(java.util.Date data) {
        if(data==null || data.equals("")) return "";
        else return dataDatabase.format(data);
    }
    public static String dataCartaoFormat(java.util.Date data) {
        if(data==null || data.equals("")) return "";
        else return dataCartao.format(data);        
    }
    
    // muda String para hora formatada
    public java.util.Date parseHora(String hora) throws ParseException {
        if(hora==null || hora.equals(""))
            return null;
        else {
            return FORMAT_HOUR .parse(hora);
        }
    }
    
    public java.util.Date parseSimpleDate(String data) throws ParseException {
        if(data==null || data.equals(""))
            return null;
        else {
            return dataSimples.parse(data);
        }
    }
    public java.util.Date parseShortDate(String data) throws ParseException {
        if(data==null || data.equals(""))
            return null;
        else {
            return dataCurta.parse(data);
        }
    }
    public java.util.Date parseFullDate(String data) throws ParseException {
        if(data==null || data.equals(""))
            return null;
        else {
            return dataCompleta.parse(data);
        }
    }
    public java.util.Date parseBigDate(String data) throws ParseException {
        if(data==null || data.equals(""))
            return null;
        else {
            return dataSemana.parse(data);
        }
    }
    public java.util.Date parseDatabase(String data) throws ParseException {
        if(data==null || data.equals(""))
            return null;
        else {
            return dataDatabase.parse(data);
        }
    }
    
    public static java.util.Date parseDataCartao(String data) throws ParseException {
        if(data==null || data.equals(""))
            return null;
        else {
            return dataCartao.parse(data);
        }
    }
 
    
    public static String shortLocalTime(java.util.Date date) {
        return FORMAT_HOUR .format(date);
    }
    
    // converte data em string (dd/MM/yyyy HH:mm) para data em string de bd (yyyy/MM/dd HH:mm) 
    public String converteData(String strData){
        Date data = null;
        try {
            data = parseFullDate(strData);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        strData = databaseDateFormat(data);
        return strData;
    }
    // inverte data em string de bd (yyyy/MM/dd HH:mm) para data em string (dd/MM/yyyy HH:mm) 
    public String inverteData(String strData){
        Date data = null;
        try {
            data = parseDatabase(strData);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        strData = fullDateFormat(data);
        return strData;
    }
    
    public static JsonObject jsonFromString(String jsonObjectStr) {

        JsonReader jsonReader = Json.createReader(new StringReader(jsonObjectStr));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();

        return object;
    }
    
    public static long getAuthTokenLifetime() {

		long seconds = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());

		seconds += TimeUnit.MINUTES.toSeconds(EnumToken.PARAMETERS.getAuthTokenLifetime());

		return seconds;

	}
	
    public static String getNomeUsuario(Usuario usuario) {
		if(usuario.getPessoaCollection() != null){
			return usuario.getPessoaCollection().iterator().next().getNomeExibido();
			
		}
		return "Amigo Vitrine";
	}
    
    public static String getParamsQueryString(String... params) {
		String paramsQuery = "?";
		if(params.length > 1){
			int i ;
			for (i = 1; i < params.length; i++) {
				paramsQuery += params[i-1] + "&";
			}
			paramsQuery += params[i-1];
		}else{
			paramsQuery += params[0];
		}
		
    	return paramsQuery;
	}
	public static String getBaseURI(UriInfo uriInfo) throws MalformedURLException {
		return  uriInfo.getAbsolutePath().getScheme()
				+ "://" + uriInfo.getAbsolutePath().getAuthority();
	}
}