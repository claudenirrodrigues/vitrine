/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitrine.entities.service;

import java.util.List;
import com.vitrine.entities.dao.EntityManager;
import com.vitrine.entities.dao.VitrineDataSource;

/**
 *
 * @author Familia
 */
public abstract class AbstractService<T> extends VitrineDataSource {

    
    public AbstractService() {
    
    }

    protected abstract EntityManager<T> getEntityManager();

    
    public void create(T entity) {
        getEntityManager().executeCreate(entity);
    }

    public void edit(T entity) {
        getEntityManager().executeUpdate(entity);
    }

    public void remove(T entity) {
        getEntityManager().executeDelete(entity);
    }

    public T find(T entity) {
        return getEntityManager().executeRetrieve(entity);
    }

    public List<T> findAll(T entity) {
        return getEntityManager().executeRetrieveAll(entity);
    }

	/*
	public void create(T entity, SecurityContext securityContext) {
		if(!securityContext.isUserInRole("admin")){
			throw new WebApplicationException(403);
		}
		getEntityManager().executeCreate(entity);
	}
    */
	
}
