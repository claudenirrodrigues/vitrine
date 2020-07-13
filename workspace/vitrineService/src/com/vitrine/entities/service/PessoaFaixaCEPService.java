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
import com.vitrine.entities.PessoaFaixaCEP;
import com.vitrine.entities.PessoaFaixaCEPPK;
import com.vitrine.entities.dao.EntityManager;

/**
 *
 * @author Familia
 */
@Stateless
@Path("entities.pessoafaixacep")
public class PessoaFaixaCEPService extends AbstractService<PessoaFaixaCEP> {

    private PessoaFaixaCEPPK getPrimaryKey(String String) {
        /*
         * Resolver de forma mais simples*/
        return new PessoaFaixaCEPPK();
    }

    public PessoaFaixaCEPService() {
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(PessoaFaixaCEP entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, PessoaFaixaCEP entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        PessoaFaixaCEPPK key = getPrimaryKey(id);
        super.remove(new PessoaFaixaCEP(key));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public PessoaFaixaCEP find(@PathParam("id") String id) {
        PessoaFaixaCEPPK key = getPrimaryKey(id);
        return super.find(new PessoaFaixaCEP(key));
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<PessoaFaixaCEP> findAll(PessoaFaixaCEP entity) {
        return super.findAll(entity);
    }

	@Override
	protected EntityManager<PessoaFaixaCEP> getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

        
}
