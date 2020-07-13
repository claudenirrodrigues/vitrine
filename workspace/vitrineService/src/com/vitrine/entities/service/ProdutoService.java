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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import com.vitrine.entities.Produto;
import com.vitrine.entities.dao.EntityManager;

/**
 *
 * @author Familia
 */
@Stateless
@Path("entities.produto")
public class ProdutoService extends AbstractService<Produto> {

    
    public ProdutoService() {
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Produto entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Produto entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(new Produto(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Produto find(@PathParam("id") Integer id) {
        return super.find(new Produto(id));
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Produto> findAll(Produto entity) {
        return super.findAll(entity);
    }

	@Override
	protected EntityManager<Produto> getEntityManager() {
		// TODO Produto
		return null;
	}

        
}
