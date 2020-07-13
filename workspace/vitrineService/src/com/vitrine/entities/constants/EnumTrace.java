package com.vitrine.entities.constants;


public enum EnumTrace {
	ATIVO(1,"A", "Ativo"),
	INATIVO(2,"I", "Inativo"),
	FECHADO(3,"F", "Fechado"),
	ABERTO(4,"A", "Aberto"), 
	RESERVADO(5,"R", "Reservado");
	
	private final Integer codigo;
	private final String sigla;
	private final String descricao;
	
	private EnumTrace(Integer codigo, String sigla, String descricao) {
		this.codigo = codigo;
		this.sigla = sigla;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getSigla() {
		return sigla;
	}

	public String getDescricao() {
		return descricao;
	}
	
		
	
	
}
