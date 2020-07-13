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
import com.vitrine.entities.Numeracao;
import com.vitrine.entities.dao.EntityManager;

/**
 *
 * @author Familia
 */
@Stateless
@Path("entities.numeracao")
public class NumeracaoService extends AbstractService<Numeracao> {

    
    public NumeracaoService() {
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Numeracao entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Numeracao entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
    	//TODO Numeracao
    	super.remove(new Numeracao());
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Numeracao find(@PathParam("id") Integer id) {
    	//TODO Numeracao
    	return super.find(new Numeracao());
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Numeracao> findAll(Numeracao entity) {
        return super.findAll(entity);
    }

	@Override
	protected EntityManager<Numeracao> getEntityManager() {
		//TODO Numeracao
    	return null;
	}

}
