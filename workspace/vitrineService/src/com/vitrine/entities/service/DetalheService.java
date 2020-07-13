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
import com.vitrine.entities.Detalhe;
import com.vitrine.entities.dao.EntityManager;

/**
 *
 * @author Familia
 */
@Stateless
@Path("entities.detalhe")
public class DetalheService extends AbstractService<Detalhe> {

	public DetalheService() {

	}

	@POST
	@Override
	@Consumes({ MediaType.APPLICATION_JSON })
	public void create(Detalhe entity) {
		super.create(entity);
	}

	@PUT
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void edit(@PathParam("id") Integer id, Detalhe entity) {
		super.edit(entity);
	}

	@DELETE
	@Path("{id}")
	public void remove(@PathParam("id") Integer id) {
		//TODO Detalhe
		super.remove(new Detalhe());
	}

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Detalhe find(@PathParam("id") Integer id) {
		//TODO Detalhe
		return super.find(new Detalhe());
	}

	@GET
	@Override
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Detalhe> findAll(Detalhe entity) {
		return super.findAll(entity);
	}

	@Override
	protected EntityManager<Detalhe> getEntityManager() {
		//TODO Detalhe
		return null;
	}

	
	

}
