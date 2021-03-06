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
import com.vitrine.entities.PedidoCompra;
import com.vitrine.entities.dao.EntityManager;

/**
 *
 * @author Familia
 */
@Stateless
@Path("entities.pedidocompra")
public class PedidoCompraService extends AbstractService<PedidoCompra> {

    public PedidoCompraService() {
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(PedidoCompra entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, PedidoCompra entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        PedidoCompra pedidocompra = new PedidoCompra(id);
        super.remove(pedidocompra);
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public PedidoCompra find(@PathParam("id") Integer id) {
    	PedidoCompra pedidocompra = new PedidoCompra(id);
        return super.find(pedidocompra);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<PedidoCompra> findAll(PedidoCompra entity) {
        return super.findAll(entity);
    }

	@Override
	protected EntityManager<PedidoCompra> getEntityManager() {
		// TODO PedidoCompra
		return null;
	}

        
}
