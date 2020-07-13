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
import com.vitrine.entities.Pessoa;
import com.vitrine.entities.PessoaPK;
import com.vitrine.entities.dao.EntityManager;

/**
 *
 * @author Familia
 */
@Stateless
@Path("entities.pessoa")
public class PessoaService extends AbstractService<Pessoa> {


    private PessoaPK getPrimaryKey(String String) {
        /*
         * Resolver de forma mais simples*/
        return new PessoaPK();
    }

    public PessoaService() {
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Pessoa entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, Pessoa entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        //TODO Pessoa
    	PessoaPK key = getPrimaryKey(id);
        super.remove(super.find(new Pessoa()));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Pessoa find(@PathParam("id") String id) {
    	//TODO Pessoa
    	PessoaPK key = getPrimaryKey(id);
        return super.find(new Pessoa());
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Pessoa> findAll(Pessoa entity) {
        return super.findAll(entity);
    }

	@Override
	protected EntityManager<Pessoa> getEntityManager() {
		// TODO Pessoa
		return null;
	}

    
    
}
