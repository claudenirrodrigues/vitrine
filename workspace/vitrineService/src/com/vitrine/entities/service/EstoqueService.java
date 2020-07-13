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
import com.vitrine.entities.Estoque;
import com.vitrine.entities.dao.EntityManager;
import com.vitrine.entities.dao.EntityManager.EnumInstruction;
import com.vitrine.entities.dao.jdbc.EntityManagerDAO;

/**
 *
 * @author Familia
 */
@Stateless
@Path("entities.estoque")
public class EstoqueService extends AbstractService<Estoque> {

    
    
    public EstoqueService() {
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Estoque entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, Estoque entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
    	super.remove(new Estoque());
    }

    @GET
    @Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
    public Estoque find(@PathParam("id") Integer id) {
        Estoque estoque = new Estoque();
        estoque.setIdEstoque(id);
    	return super.find(estoque);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Estoque> findAll(Estoque entity) {
    	return super.findAll(entity == null? new Estoque():entity);
    }
    
    @GET
    @Path("/summaryAll")
	@Produces({MediaType.APPLICATION_JSON})
    public List<Estoque> findSummaryAll(Estoque entity) {
    	return getEntityManager()
    			.executeCustomRetrieveAll(entity == null? new Estoque():entity, 
    					EnumInstruction.RETRIEVE_SUMMARY_ALL.getInstruction());
    }
    
    @POST
    @Path("/details")
	@Consumes({MediaType.APPLICATION_JSON})
    public List<Estoque> findSummary(Estoque entity) {
        return getEntityManager().executeCustomRetrieveAll(entity == null? new Estoque():entity, 
    					EnumInstruction.RETRIEVE_DETAILS.getInstruction());
    }

	@Override
	protected EntityManager<Estoque> getEntityManager() {
		EntityManager<Estoque> emDAO = new EntityManagerDAO<Estoque>(getDataSource());
		return emDAO;
	}

        
}
