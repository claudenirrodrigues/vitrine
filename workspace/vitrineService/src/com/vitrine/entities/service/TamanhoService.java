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
import com.vitrine.entities.Tamanho;
import com.vitrine.entities.dao.EntityManager;

/**
 *
 * @author Familia
 */
@Stateless
@Path("entities.tamanho")
public class TamanhoService extends AbstractService<Tamanho> {

    
    public TamanhoService() {
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Tamanho entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Tamanho entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(new Tamanho(id)));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Tamanho find(@PathParam("id") Integer id) {
        return super.find(new Tamanho(id));
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Tamanho> findAll(Tamanho entity) {
        return super.findAll(entity);
    }

	@Override
	protected EntityManager<Tamanho> getEntityManager() {
		// TODO Tamanho
		return null;
	}

        
}
