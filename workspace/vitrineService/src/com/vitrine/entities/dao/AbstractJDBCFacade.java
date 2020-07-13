/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitrine.entities.dao;

import java.lang.reflect.Field;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.sql.DataSource;
import javax.sql.rowset.spi.TransactionalWriter;

import com.mysql.jdbc.SQLError;
import com.vitrine.entities.Trace;
import com.vitrine.entities.dao.EntityManager.EnumDomains;
import com.vitrine.entities.dao.EntityManager.EnumEntityManager;
import com.vitrine.entities.dao.EntityManager.EnumTypeNull;
import com.vitrine.utils.Logger;

/**
 *
 * @author Familia
 */
public abstract class AbstractJDBCFacade<T> {

	private Connection connection;
	private CallableStatement callableStatement;
	private ResultSet resultSet;
	private String procedure;
	DataSource dataSource;
	private HashMap<String, String> domainsMap;

	public AbstractJDBCFacade(DataSource dataSource) {
		this.dataSource = dataSource;
		this.procedure = EnumEntityManager.PROCEDURE.getCall() + getParameters();
	}

	private String getParameters() {
		StringBuffer sb = new StringBuffer();
		sb.append("(?");
		for(int i = 1; i < EnumDomains.values().length; i++){
			sb.append(",?");
		}
		sb.append(")}");
		return sb.toString();
	}

	protected abstract String getProcedure();

	protected final void init() throws SQLException {
		try {
			if (dataSource == null) {
				Logger.log("DataSource não foi inicializado.", Logger.ERROR);
			}
			getConnection();
			if(this.connection == null || this.connection.isClosed()){
				getConnection();
			}
			this.callableStatement = this.connection.prepareCall(this.procedure);

			createDomainsMap();

		} catch (SQLException e) {
			throw new SQLException("Erro ao inicializar conexão.", e.getCause());
		}
	}

	protected void createDomainsMap() {
		this.domainsMap = new HashMap<String, String>();
		for (EnumDomains enumDomain : EnumDomains.values()) {
			// Create an DomainsMap.
			this.domainsMap.put(enumDomain.getParameterName(), enumDomain.getParameterName());
		}
	}

	protected void execute() throws SQLException {
		this.callableStatement.execute();
	}

	protected void executeQuery() throws SQLException {
		this.resultSet = this.callableStatement.executeQuery();
	}

	protected void executeTransaction() {
		try {
			this.callableStatement.execute();
		} catch (SQLException e) {
			System.err.println("SQLException: " + e.getMessage());
			e.printStackTrace();
			if (this.connection != null) {
				try {
					System.err.print("Transa��o est� sendo revertida");
					this.connection.rollback();
				} catch (SQLException ex) {
					System.err.println("SQLException: " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		}
	}

	protected void comit() throws SQLException {
		try {
			this.connection.commit();
		} catch (SQLException e) {
			System.err.println("SQLException: " + e.getMessage());
			e.printStackTrace();
			if (this.connection != null) {
				try {
					System.err.print("Transaçaoo está sendo revertida após tentativa de comitar");
					this.connection.rollback();
				} catch (SQLException ex) {
					System.err.println("SQLException: " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		} finally {
			close();
		}
	}

	protected void executeClose() {
		try {
			close();
		} catch (SQLException e) {
			System.err.println("SQLException - executeClose(): " + e.getMessage());
			e.printStackTrace();
		}

	}

	private void close() throws SQLException {
		closeResources(this.connection, this.callableStatement, this.resultSet);
	}

	protected Connection getConnection() throws SQLException {
		try {
			this.connection = this.dataSource.getConnection();
		} catch (SQLException e) {
			Logger.log("Erro ao obter a conexão do datasource", this.getClass().getSimpleName(), e.getMessage(), Logger.ERROR);
			e.printStackTrace();
			throw new SQLException(e.getMessage(), e.getCause());
		}
		return this.connection;
	}

	private void closeResources(Connection conn, CallableStatement cs, ResultSet rs) throws SQLException {
		close(conn, cs, null, rs);
	}

	private static void close(Connection conn, CallableStatement cs, PreparedStatement pstm, ResultSet rs)
			throws SQLException {

		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			Logger.log("Erro ao fechar ResultSet. " + e.getMessage(), Logger.ERROR);
		}
		try {
			if (cs != null)
				cs.close();
		} catch (SQLException e) {
			Logger.log("Erro ao fechar CallableStatement. " + e.getMessage(), Logger.ERROR);
		}
		try {
			if (pstm != null)
				pstm.close();
		} catch (SQLException e) {
			Logger.log("Erro ao fechar PreparedStatement. " + e.getMessage(), Logger.ERROR);
		}
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			Logger.log("Erro ao fechar Connection. " + e.getMessage(), Logger.ERROR);
		}

	}

	protected CallableStatement getCallableStatement() {
		return callableStatement;
	}

	protected ResultSet getResultSet() {
		return resultSet;
	}

	protected DataSource getDataSource() {
		return dataSource;
	}

	protected Trace getTrace() throws SQLException {
		Trace trace = new Trace();

		trace.setSituacao(getResultSet().getString(EntityManager.EnumDomains.SITUACAO.getParameterName()));
		trace.setDataInclusao(getResultSet().getDate(EntityManager.EnumDomains.DATA_INCLUSAO.getParameterName()));
		trace.setDataAlteracao(getResultSet().getDate(EntityManager.EnumDomains.DATA_ALTERACAO.getParameterName()));

		return trace;
	}

	protected void setTrace(Trace trace, String instruction) throws SQLException {

		getCallableStatement().registerOutParameter(EntityManager.EnumDomains.INSTRUCTION.getParameterName(),
				java.sql.Types.VARCHAR);

		getCallableStatement().setObject(EntityManager.EnumDomains.INSTRUCTION.getParameterName(), instruction);
		getCallableStatement().setObject(EntityManager.EnumDomains.SITUACAO.getParameterName(),
				(trace.getSituacao() == null) ? EnumTypeNull.NULL.getNull() : trace.getSituacao());
		getCallableStatement().setObject(EntityManager.EnumDomains.DATA_INCLUSAO.getParameterName(),
				(trace == null || trace.getDataInclusao() == null) ? EnumTypeNull.NULL.getNull()
						: new Date(trace.getDataInclusao().getTime()));
		getCallableStatement().setObject(EntityManager.EnumDomains.DATA_ALTERACAO.getParameterName(),
				(trace == null || trace.getDataAlteracao() == null) ? EnumTypeNull.NULL.getNull()
						: new Date(trace.getDataAlteracao().getTime()));

	}
	
	public void setCallParameterValue(T entity, String instruction) throws SQLException {
		
		getCallableStatement().registerOutParameter(EnumDomains.INSTRUCTION.getParameterName(), java.sql.Types.VARCHAR);
		getCallableStatement().setObject(EnumDomains.ENTITY.getParameterName(),entity.getClass().getSimpleName());
		this.domainsMap.remove(EnumDomains.ENTITY.getParameterName());
		setCallParameterValue(entity, instruction,true);
	}

	public void setCallParameterValue(Object relation, T entity, String instruction) throws SQLException{
		
		getCallableStatement().registerOutParameter(EnumDomains.INSTRUCTION.getParameterName(), java.sql.Types.VARCHAR);
		getCallableStatement().setObject(EnumDomains.ENTITY.getParameterName(),entity.getClass().getSimpleName());
		this.domainsMap.remove(EnumDomains.ENTITY.getParameterName());
		
		try {
			
			for (Field field : entity.getClass().getDeclaredFields()) {
				field.setAccessible(true); 
				if (isBasicEntityReference(field)) {
					if (this.domainsMap.containsKey(field.getName())) {
						getCallableStatement().setObject(field.getName(), field.get(entity));
						this.domainsMap.remove(field.getName());
					}
				}
			}
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			Logger.log("Erro ao incluir Chave da Entidade no  CallableStatement. " + e.getMessage(), Logger.ERROR);
		}

		setCallParameterValue(relation, instruction, true);
		
	}
	
	private void setCallParameterValue(Object entity, String instruction, boolean isEntityPersistent) throws SQLException {

		try {
			
			for (Field field : entity.getClass().getDeclaredFields()) {
				field.setAccessible(true); 
				if (isBasicEntityReference(field)) {
					if (this.domainsMap.containsKey(field.getName())) {
						getCallableStatement().setObject(field.getName(), field.get(entity));
						this.domainsMap.remove(field.getName());
					}
				}else{
					if(isPersistent(field) && field.get(entity) != null){
						setCallParameterValue(field.get(entity), null, false);
					}
				}
			}
			
			if(isEntityPersistent){
				setCallParameterDefault(instruction);
			}
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			Logger.log("Erro ao incluir parametros no  CallableStatement. " + e.getMessage(), Logger.ERROR);
		}
	}

	private void setCallParameterDefault(String instruction) throws SQLException {

		while (this.domainsMap.entrySet().iterator().hasNext()) {
			if(this.domainsMap.entrySet().iterator().next().getKey().equals(EnumDomains.INSTRUCTION.getParameterName())){
				getCallableStatement().setObject(this.domainsMap.entrySet().iterator().next().getValue(),instruction);
			}else{
				getCallableStatement().setObject(this.domainsMap.entrySet().iterator().next().getValue(),EnumTypeNull.NULL.getNull());
			}
			this.domainsMap.remove(this.domainsMap.entrySet().iterator().next().getKey());
		}

	}
	
	protected Object getQueryResult(Object relation) throws SQLException {
		return getQueryResult(relation, true);
	}
	
	private Object getQueryResult(Object relation, boolean isEntityPersistent) throws SQLException {
		Object obj = null;
		try {
			obj = relation.getClass().newInstance();
			for (Field field : obj.getClass().getDeclaredFields()) {
				field.setAccessible(true); 
				if (isBasicEntityReference(field)) {
					//field.set(relation,getResultSet().getObject(field.getName(),field.getType()));/*Conversion not supported for type java.util.Date*/
					if(foundColumn(field.getName())){
						field.set(obj,getResultSet().getObject(field.getName()));
					}
				}else{
					if(isPersistent(field) && isTraceEntityPersistent(field, isEntityPersistent)){
						field.set(obj,field.getType().newInstance());
						field.set(obj,getQueryResult(field.get(obj),false));
					}
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			Logger.log("Erro ao capturar o resuldado da consulta. " + e.getMessage(), Logger.ERROR);
		}catch (InstantiationException e) {
			e.printStackTrace();
			Logger.log("Erro na tentativa de criar um novo objeto. " + e.getMessage(), Logger.ERROR);
		}
		return obj;
	}

	private boolean foundColumn(String columnLabel) throws SQLException {
		
		try {
			getResultSet().findColumn(columnLabel);
			return true;
		} catch (SQLException e) {
			if(e.getSQLState().equals(SQLError.SQL_STATE_COLUMN_NOT_FOUND)){
				return false;
			}
			
			throw new SQLException("Erro desconhecido procurar a coluna "+columnLabel+" no resultset.",e.getSQLState(),e.getErrorCode(),e.getCause());
		}
		
	}

	private boolean isTraceEntityPersistent(Field field , boolean isEntityPersistent) {
		if(field.getType().getSimpleName().equals(Trace.class.getSimpleName())){
			return isEntityPersistent;
		}
		return true;
	}

	private boolean isBasicEntityReference(Field field) {
		if (field.getType().getName().equals(EnumTypeNull.STRING.getName()))return true;
		if (field.getType().getName().equals(EnumTypeNull.INTEGER.getName()))return true;
		if (field.getType().getName().equals(EnumTypeNull.DATE.getName()))return true;
		if (field.getType().getName().equals(EnumTypeNull.FLOAT.getName()))return true;
		if (field.getType().getName().equals(EnumTypeNull.DATE_UTIL.getName()))return true;
		return false;
	}

	private boolean isPersistent(Field field) {
		if (field.getType().getName().equals(EnumTypeNull.COLLECTION.getName()))return false;
		if (field.getType().getName().equals(EnumTypeNull.LONG.getName()))return false;
		if (field.getType().getName().equals(EnumTypeNull.INT.getName()))return false;
		return true;
	}
}
