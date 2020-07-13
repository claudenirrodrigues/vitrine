package com.vitrine.entities.constants;

import com.vitrine.entities.Perfil;

public enum EnumPerfil {
	ADMINISTRADOR(1,"Administrador", new Perfil(1)),
	REPRESENTANTE(2,"Representante", new Perfil(2)),
	CLIENTE(3,"Cliente", new Perfil(3));
	
	private final Integer idPerfil;
	private final String perfil;
	private final Perfil entity;
	
	private EnumPerfil(Integer idPerfil, String perfil, Perfil entity) {
		this.idPerfil = idPerfil;
		this.perfil = perfil;
		this.entity = entity;
	}
	
	public Integer getIdPerfil(){
		return idPerfil;
	}

	public String getPerfil() {
		return perfil;
	}

	public Perfil getEntity() {
		return entity;
	}
	
	
	
}
