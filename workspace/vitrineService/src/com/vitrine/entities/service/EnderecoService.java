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
import javax.ws.rs.core.PathSegment;
import com.vitrine.entities.Endereco;
import com.vitrine.entities.EnderecoPK;
import com.vitrine.entities.dao.EntityManager;

/**
 *
 * @author Familia
 */
@Stateless
@Path("entities.endereco")
public class EnderecoService extends AbstractService<Endereco> {

    
    private EnderecoPK getPrimaryKey(String pathSegment) {
        /*
         * Resolver de uma forma mais simples
         * */
        return new EnderecoPK();
    }

    public EnderecoService() {
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Endereco entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, Endereco entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        //TODO Endereco
    	com.vitrine.entities.EnderecoPK key = getPrimaryKey(id);
        super.remove(new Endereco());
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Endereco find(@PathParam("id") String id) {
        //TODO Endereco
    	com.vitrine.entities.EnderecoPK key = getPrimaryKey(id);
        return super.find(new Endereco());
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Endereco> findAll(Endereco entity) {
        return super.findAll(entity);
    }

	@Override
	protected EntityManager<Endereco> getEntityManager() {
		//TODO Endereco
    	return null;
	}

    
    
}
