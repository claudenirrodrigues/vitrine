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

import com.vitrine.entities.Acabamento;
import com.vitrine.entities.dao.EntityManager;

/**
 *
 * @author Familia
 */
@Stateless
@Path("entities.acabamento")
public class AcabamentoService extends AbstractService<Acabamento> {

    
    public AcabamentoService() {
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Acabamento entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Acabamento entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        //super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Acabamento find(@PathParam("id") Integer id) {
        return new Acabamento();
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Acabamento> findAll(Acabamento entity) {
        return super.findAll(entity);
    }

	@Override
	protected EntityManager<Acabamento> getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

    
    
    
    
}
