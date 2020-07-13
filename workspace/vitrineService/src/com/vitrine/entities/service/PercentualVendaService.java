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
import com.vitrine.entities.PercentualVenda;
import com.vitrine.entities.PercentualVendaPK;
import com.vitrine.entities.dao.EntityManager;

/**
 *
 * @author Familia
 */
@Stateless
@Path("entities.percentuavenda")
public class PercentualVendaService extends AbstractService<PercentualVenda> {

    
    private PercentualVendaPK getPrimaryKey(String String) {
        /*
         * Resolver de forma mais simples*/
        return new PercentualVendaPK();
    }

    public PercentualVendaService() {
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(PercentualVenda entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, PercentualVenda entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        PercentualVendaPK key = getPrimaryKey(id);
        PercentualVenda percentuavenda = new PercentualVenda(key);
        super.remove(percentuavenda);
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public PercentualVenda find(@PathParam("id") String id) {
    	PercentualVendaPK key = getPrimaryKey(id);
        PercentualVenda percentuavenda = new PercentualVenda(key);
        return super.find(percentuavenda);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<PercentualVenda> findAll(PercentualVenda entity) {
        return super.findAll(entity);
    }

	@Override
	protected EntityManager<PercentualVenda> getEntityManager() {
		// TODO PercentualVenda
		return null;
	}

        
}
