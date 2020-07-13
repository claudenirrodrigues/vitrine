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

import com.vitrine.entities.Cor;
import com.vitrine.entities.dao.EntityManager;

/**
 *
 * @author Familia
 */
@Stateless
@Path("entities.cor")
public class CorService extends AbstractService<Cor> {

    
    public CorService() {
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Cor entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Cor entity) {
    	//TODO Cor
    	super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
    	//TODO Categoria
    	super.remove(new Cor());
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Cor find(@PathParam("id") Integer id) {
    	//TODO Categoria
    	return super.find(new Cor());
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Cor> findAll(Cor entity) {
        return super.findAll(entity);
    }

	@Override
	protected EntityManager<Cor> getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

    
        
}
