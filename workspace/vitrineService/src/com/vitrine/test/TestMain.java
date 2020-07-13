package com.vitrine.test;

import java.lang.reflect.Field;
import java.sql.Date;
import java.util.HashMap;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

import com.vitrine.entities.Usuario;
import com.vitrine.entities.dao.EntityManager.EnumDomains;
import com.vitrine.entities.dao.EntityManager.EnumTypeNull;

public class TestMain {

	Usuario usuario;
	Integer idPerfil;
	String situacao;
	Float valorVarejo;
	Date dataInclusao;
	int i;
	long l;
	
	public TestMain() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static void main(String[] args) {


		JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
		jsonObjBuilder.add("auth_token", "{'userData':'{\n \'kind\': \'plus#person\',\n \'etag\': \'\\\'xw0en60W6-NurXn4VBU-CMjSPEw/VTmH5k8NGUoAliJduRKHO0yrv8M\\\'\',\n \'gender\': \'male\',\n \'emails\': [\n  {\n   \'value\': \'claudenir.rodrigues.oliveira@gmail.com\',\n   \'type\': \'account\'\n  }\n ],\n \'objectType\': \'person\',\n \'id\': \'104518066571645190504\',\n \'displayName\': \'Claudenir Rodrigues\',\n \'name\': {\n  \'familyName\': \'Rodrigues\',\n  \'givenName\': \'Claudenir\'\n },\n \'url\': \'https://plus.google.com/104518066571645190504\',\n \'image\': {\n  \'url\': \'https://lh3.googleusercontent.com/-XdUIqdMkCWA/AAAAAAAAAAI/AAAAAAAAAAA/4252rscbv5M/photo.jpg?sz=50\',\n  \'isDefault\': true\n },\n \'isPlusUser\': true,\n \'language\': \'pt_BR\',\n \'ageRange\': {\n  \'min\': 21\n },\n \'circledByCount\': 3,\n \'verified\': false\n}\n'}'");
		JsonObject jsonObj = jsonObjBuilder.build();
		JsonValue jv = jsonObj.get("auth_token");
		
		
		HashMap<String, String> domainsMap = new HashMap<String, String>();
		
		for (EnumDomains enumDomain : EnumDomains.values()) {
			System.out.println(enumDomain.getParameterName());
			System.out.println(enumDomain);
			// Create an EnumMap.
			domainsMap.put(enumDomain.getParameterName(), enumDomain.getParameterName());
			
		}
		

		TestMain entity = new TestMain();
		entity.idPerfil = 1;
		entity.situacao = "S";
		entity.usuario = new Usuario();
		entity.usuario.setIdUsuario("1");
		entity.usuario.setSenha("123");
		
		try {
			Class<?> cls = Class.forName(entity.getClass().getName().toString());
			
			
			for (Field field : cls.getDeclaredFields()) {
				 
				if(field.getType().getName().equals(EnumTypeNull.STRING.getName())){
					if(domainsMap.containsKey(field.getName())){
						field.setAccessible(true); //Additional line
						System.out.println("Field Value: " + field.get(entity));
						domainsMap.remove(field.getName());
					}
				}
				if(field.getType().getName().equals(EnumTypeNull.INTEGER.getName())){
					if(domainsMap.containsKey(field.getName())){
						field.setAccessible(true); //Additional line
						System.out.println("Field Value: " + field.get(entity));
						domainsMap.remove(field.getName());
					}
				}
				if(field.getType().getName().equals(EnumTypeNull.DATE.getName())){
					if(domainsMap.containsKey(field.getName())){
						field.setAccessible(true); //Additional line
						System.out.println("Field Value: " + field.get(entity));
						domainsMap.remove(field.getName());
					}
				}
				if(field.getType().getName().equals(EnumTypeNull.FLOAT.getName())){
					if(domainsMap.containsKey(field.getName())){
						field.setAccessible(true); //Additional line
						System.out.println("Field Value: " + field.get(entity));
						domainsMap.remove(field.getName());
					}
				}
				
				field.setAccessible(true);
				Class<?> classPersistent = Class.forName(field.getGenericType().getTypeName().toString());
				
		 	    //Object obj = classPersistent.newInstance();
		 	    //obj = obj.getClass().cast(classPersistent);
				Object obj = field.get(entity);
				System.out.println(field.get(entity));
				field.set(field.get(entity),obj);
				
		 	   
			}
			
			//Field field = cls.getField("testeStr");
			//String str = new String();
			///////////////////////////////
			// olha o THIS aqui
			//field.set(this, "String modificada");
			//System.out.println(testeStr);
		//}catch (NoSuchFieldException ex) {
		//	ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		//} catch (InstantiationException ex) {
		//    ex.printStackTrace();
	 	} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} 

	}
	
	
	

}
