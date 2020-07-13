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
import com.vitrine.entities.Frete;
import com.vitrine.entities.dao.EntityManager;


/**
 *
 * @author Familia
 */
@Stateless
@Path("entities.frete")
public class FreteService extends AbstractService<Frete> {

    
    public FreteService() {
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Frete entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Frete entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        //TODO Frete
    	super.remove(new Frete());
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Frete find(@PathParam("id") Integer id) {
    	//TODO Frete
    	return super.find(new Frete());
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Frete> findAll(Frete entity) {
        return super.findAll(entity);
    }

	@Override
	protected EntityManager<Frete> getEntityManager() {
		//TODO Frete
    	return null;
	}

        
}
