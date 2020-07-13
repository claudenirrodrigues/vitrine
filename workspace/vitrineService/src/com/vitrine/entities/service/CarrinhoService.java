/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitrine.entities.service;

import com.vitrine.entities.Carrinho;
import com.vitrine.entities.Trace;
import com.vitrine.entities.constants.EnumTrace;
import com.vitrine.entities.dao.EntityManager;
import com.vitrine.entities.dao.EntityManager.EnumEntityManager;
import com.vitrine.entities.dao.EntityManager.EnumInstruction;

import java.util.Date;
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

/**
 *
 * @author Familia
 */
@Stateless
@Path("entities.carrinho")
public class CarrinhoService extends AbstractService<Carrinho> {

    public CarrinhoService() {
    
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Carrinho entity) {
        entity.setItemCarrinho(findMaxItem(entity));
        Trace trace = new Trace(EnumTrace.RESERVADO.getSigla(), new Date(), null);
        entity.setTrace(trace);
        super.create(entity);
    }

    private Integer findMaxItem(Carrinho entity) {
		Carrinho carrinho = getEntityManager()
				.executeCustomRetrieve(entity, 
						EnumInstruction.RETRIEVE_MAX.getInstruction());
		Integer itemCarrinho;
		if(carrinho != null && carrinho.getItemCarrinho() != null){
			itemCarrinho =+ carrinho.getItemCarrinho();
		}else{
			itemCarrinho =+ 1;
		}
		
		return itemCarrinho;
	}

	@PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, Carrinho entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        //TODO Carrinho
    	super.remove(new Carrinho());
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Carrinho find(@PathParam("id") String id) {
        //TODO Carrinho
    	return super.find(new Carrinho());
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Carrinho> findAll(Carrinho entity) {
        return super.findAll(entity);
    }

	@Override
	protected EntityManager<Carrinho> getEntityManager() {
		// TODO Carrinho
		return null;
	}

    
    
    
    
}
