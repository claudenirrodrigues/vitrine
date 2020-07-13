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
import com.vitrine.entities.FaixaCEP;
import com.vitrine.entities.dao.EntityManager;

/**
 *
 * @author Familia
 */
@Stateless
@Path("entities.faixacep")
public class FaixaCEPService extends AbstractService<FaixaCEP> {

    
    public FaixaCEPService() {
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(FaixaCEP entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, FaixaCEP entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        //TODO FaixaCEP
    	super.remove(new FaixaCEP());
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public FaixaCEP find(@PathParam("id") Integer id) {
    	//TODO FaixaCEP
    	return super.find(new FaixaCEP());
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<FaixaCEP> findAll(FaixaCEP entity) {
        return super.findAll(entity);
    }

	@Override
	protected EntityManager<FaixaCEP> getEntityManager() {
		//TODO FaixaCEP
    	return null;
	}

        
}
