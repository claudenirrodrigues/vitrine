package com.vitrine.entities.dao;

import com.vitrine.entities.Usuario;

public interface UsuarioRepository extends EntityManager<Usuario>{
	/**SOMENTE METODOS ABSTRATOS NESTA INTERFACE
	 * CONSTANTES FICARAM NA SUPERCLASSE **/
	public void executeCreatePerfil(Usuario entity);
	public Usuario executeRetrievePerfis (Usuario entity);
	
}
