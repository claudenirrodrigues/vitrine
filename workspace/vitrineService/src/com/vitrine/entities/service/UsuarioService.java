/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitrine.entities.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.vitrine.entities.Perfil;
import com.vitrine.entities.Pessoa;
import com.vitrine.entities.Usuario;
import com.vitrine.entities.constants.EnumPerfil;
import com.vitrine.entities.dao.EntityManager;
import com.vitrine.entities.dao.EntityManager.EnumInstruction;
import com.vitrine.entities.dao.jdbc.EntityManagerDAO;

/**
 *
 * @author Familia
 */
@Stateless
@Path("entities.usuario")
public class UsuarioService extends AbstractService<Usuario> {

	public UsuarioService() {

	}

	@POST
	@Override
	@Consumes({ MediaType.APPLICATION_JSON })
	public void create(Usuario entity) {
		super.create(entity);
		createBasePerfil(entity);
	}

	@PUT
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void edit(@PathParam("id") String id, Usuario entity) {
		super.edit(entity);
	}

	@PUT
	@Path("/perfil/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void editPerfil(@PathParam("id") String id, Usuario entity) {

	}

	@DELETE
	@Path("{id}")
	public void remove(@PathParam("id") String id) {
		super.remove(new Usuario(id));
	}

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Usuario find(@PathParam("id") String id) {
		
		Usuario usuario = super.find(new Usuario(id));
		if(usuario != null){
			usuario.setPerfilCollection(findPerfil(usuario));
			usuario.setPessoaCollection(findPessoa(usuario));
		}
		return usuario;
	}

	@GET
	@Override
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Usuario> findAll(Usuario entity) {
		return super.findAll(entity == null? new Usuario():entity);
	}

	@Override
	protected EntityManager<Usuario> getEntityManager() {
		EntityManager<Usuario> emDAO = new EntityManagerDAO<Usuario>(getDataSource());
		return emDAO;
	}
	
	private EntityManager<Object> getGenericEntityManager() {
		EntityManager<Object> emDAO = new EntityManagerDAO<Object>(getDataSource());
		return emDAO;
	}
	
	@POST
	@Path("/perfil")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void createPerfil(Usuario entity) {
		getEntityManager().executeCreateRelation(entity, entity.getPerfilCollection(), EnumInstruction.CREATE_PERFIL.getInstruction());

	}
	
	@SuppressWarnings("unchecked")
	@GET
	@Path("/perfil/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Usuario findPerfil(@PathParam("id") String id) {
		Usuario usuario = new Usuario(id);
		Collection<Perfil> perfil = new ArrayList<>();
		perfil.addAll((Collection<? extends Perfil>) getEntityManager().executeRetrieveRelation(usuario, new Perfil(),
				EnumInstruction.RETRIEVE_PERFIS.getInstruction()));
		usuario.setPerfilCollection(perfil);
		return usuario;
	}
	
	@SuppressWarnings("unchecked")
	private Collection<Perfil> findPerfil(Usuario usuario) {
		Collection<Perfil> perfil = new ArrayList<>();
		perfil.addAll((Collection<? extends Perfil>) getEntityManager()
				.executeRetrieveRelation(usuario, new Perfil(),
				EnumInstruction.RETRIEVE_PERFIS.getInstruction()));
		return perfil;
	}
	
	private void createBasePerfil(Usuario entity) {
		Collection<Perfil> perfilCollection = new ArrayList<>();
		Perfil basePerfil = new Perfil();
		basePerfil.setIdPerfil(EnumPerfil.CLIENTE.getIdPerfil());
		perfilCollection.add(basePerfil);

		entity.setPerfilCollection(perfilCollection);
		createPerfil(entity);
	}
	
	public void createPessoa(Usuario entity) {
		for (Pessoa pessoa : entity.getPessoaCollection()) {
			getGenericEntityManager().executeCreate(pessoa);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private Collection<Pessoa> findPessoa(Usuario usuario) {
		Collection<Pessoa> pessoa = new ArrayList<>();
		pessoa.addAll((Collection<? extends Pessoa>) getEntityManager()
				.executeRetrieveRelation(usuario, new Pessoa(),
				EnumInstruction.RETRIEVE_PESSOAS.getInstruction()));
		
		return pessoa;
	}

	
}
