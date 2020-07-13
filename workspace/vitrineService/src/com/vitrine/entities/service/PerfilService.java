/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitrine.entities.service;

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
import com.vitrine.entities.dao.EntityManager;
import com.vitrine.entities.dao.jdbc.EntityManagerDAO;

/**
 *
 * @author Familia
 */
@Stateless
@Path("entities.perfil")
public class PerfilService extends AbstractService<Perfil> {

	
	public PerfilService() {
	}

	@POST
	@Override
	@Consumes({MediaType.APPLICATION_JSON })
	public void create(Perfil entity) {
		super.create(entity);
	}

	@PUT
	@Path("/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void edit(@PathParam("id") Integer id, Perfil entity) {
		super.edit(entity);
	}

	@DELETE
	@Path("/{id}")
	public void remove(@PathParam("id") Integer id) {
		super.remove(new Perfil(id));
	}

	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON })
	public Perfil find(@PathParam("id") Integer id) {
		return super.find(new Perfil(id));
	}

	@GET
	@Override
	@Produces({MediaType.APPLICATION_JSON })
	public List<Perfil> findAll(Perfil entity) {
		return super.findAll(entity == null? new Perfil():entity);
	}

	@Override
	protected EntityManager<Perfil> getEntityManager() {
		EntityManager<Perfil> em = new EntityManagerDAO<Perfil>(getDataSource());
		return em;
	}

	

}
