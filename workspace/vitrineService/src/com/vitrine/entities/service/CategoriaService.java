/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitrine.entities.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.vitrine.entities.Categoria;
import com.vitrine.entities.dao.AbstractJPAFacade;

/**
 *
 * @author Familia
 */
@Stateless
@Path("entities.categoria")
public class CategoriaService extends AbstractService<Categoria> {

    public CategoriaService() {
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Categoria entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Categoria entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        //TODO Categoria
    	super.remove(new Categoria());
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Categoria find(@PathParam("id") Integer id) {
    	//TODO Categoria
    	return super.find(new Categoria());
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Categoria> findAll(Categoria entity) {
        return super.findAll(entity);
    }

	@Override
	protected com.vitrine.entities.dao.EntityManager<Categoria> getEntityManager() {
		//TODO Categoria
    	return null;
	}

    
    
    
}
