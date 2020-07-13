 package com.vitrine.entities.dao.jdbc;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import com.vitrine.entities.Estoque;
import com.vitrine.entities.dao.AbstractJDBCFacade;
import com.vitrine.entities.dao.EntityManager;
import com.vitrine.utils.Logger;

public class EntityManagerDAO<T> extends AbstractJDBCFacade<T> implements EntityManager<T>{
	
	
	
	public EntityManagerDAO(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public String getProcedure() {
		return "";
	}
	
	@Override
	public void executeCreate(T entity){
		executeCustom(entity, EnumInstruction.CREATE.getInstruction(), "Erro na recuperação de dados.");
	}

	@Override
	public T executeRetrieve(T entity) {
		return executeCustomRetrieve(entity, EnumInstruction.RETRIEVE.getInstruction());
	}

	@Override
	public void executeUpdate(T entity) {
		executeCustom(entity, EnumInstruction.UPDATE.getInstruction(), "Erro na atualização de dados.");
	}

	@Override
	public void executeDelete(T entity) {
		executeCustom(entity, EnumInstruction.DELETE.getInstruction(), "Erro na deleção de dados.");
	}
	
	@Override
	public List<T> executeRetrieveAll(T entity) {
		return executeCustomRetrieveAll(entity, EnumInstruction.RETRIEVE_ALL.getInstruction());
	}
	
	@Override
	public void executeCreateRelation(T entity, Collection<?> relations, String instruction){

		

		try {
			init();
			
			for (Iterator<?> iterator = relations.iterator(); iterator.hasNext();) {
				setCallParameterValue(iterator.next(), entity, instruction);
				execute();
				createDomainsMap();
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			Logger.log("Erro na criação de dados relacionados.",entity.getClass().getSimpleName(),e.getMessage(), Logger.ERROR);
		}finally {
			executeClose();
		}

	}

	@Override
	public Collection<Object> executeRetrieveRelation(T entity, Object relation,String instruction) {
		
		
		
		Collection<Object> relations = new ArrayList<Object>();
		
		try {
			init();
			
			setCallParameterValue(entity, instruction);
			
			executeQuery();
			
			while(getResultSet().next()){
				
				relations.add(getQueryResult(relation));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			Logger.log("Erro na recuperação de dados relacionados.",entity.getClass().getSimpleName(),e.getMessage(), Logger.ERROR);
		}finally {
			executeClose();
		}
		return relations;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> executeCustomRetrieveAll(T entity, String instruction) {
		
		
		List<T> entities = new ArrayList<T>();
		
		try {
			init();
			
			setCallParameterValue(entity, instruction);
			executeQuery();
			
			while(getResultSet().next()){
				entities.add((T) getQueryResult(entity));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			Logger.log("Erro na recuperação da lista de dados.",entity.getClass().getSimpleName(),e.getMessage(), Logger.ERROR);
		}finally {
			executeClose();
		}
		return entities;

	}

	@SuppressWarnings("unchecked")
	@Override
	public T executeCustomRetrieve(T entity, String instruction) {
		
		
		try {

			init();
			
			setCallParameterValue(entity, instruction);
			
			executeQuery();
			if(getResultSet().next()){
				entity = (T) getQueryResult(entity);
			}else{
				entity = null;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			Logger.log("Erro na recuperação de dados.",entity.getClass().getSimpleName(),e.getMessage(), Logger.ERROR);
		}finally {
			executeClose();
		}
		return entity;
	}

	@Override
	public void executeCustom(T entity, String instruction, String msgErro) {
		
		try {
			init();
			
			setCallParameterValue(entity, instruction);
			execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			Logger.log(msgErro,entity.getClass().getSimpleName(),e.getMessage(), Logger.ERROR);
		}finally {
			executeClose();
		}
		
	}

	@Override
	public void executeCreateOrUpdade(T entity) {
		executeCustom(entity, EnumInstruction.CREATE_UPDATE.getInstruction(), "Erro criação ou Autialização dos dados.");
	}
}
