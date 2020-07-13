package com.vitrine.entities.dao.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import com.vitrine.entities.Perfil;
import com.vitrine.entities.dao.AbstractJDBCFacade;
import com.vitrine.entities.dao.PerfilRepository;

public class PerfilDAO extends AbstractJDBCFacade<Perfil> implements PerfilRepository {
	
	private static final String PROCEDURE = "{call PerfilProcedure(?,?,?)}";
	
	
	public PerfilDAO(DataSource dataSource) {
		super(dataSource);
	}
	
	@Override
	protected String getProcedure() {
		return PROCEDURE;
	}
	
	@Override
	public void executeCreate(Perfil entity) {
		
		
		try {
			init();
			
			getCallableStatement().registerOutParameter(1, java.sql.Types.VARCHAR);
			
			getCallableStatement().setString(1, EnumInstruction.CREATE.getInstruction());
			getCallableStatement().setInt(2, entity.getIdPerfil());
			getCallableStatement().setString(3, entity.getperfil());
			
			execute();
			
		} catch (SQLException e) {
			System.err.println("SQLException: "+e.getMessage());
			e.printStackTrace();
		}finally {
			executeClose();
		}


	}

	@Override
	public Perfil executeRetrieve(Perfil entity) {
		
		try {
			init();
			
			getCallableStatement().registerOutParameter(1, java.sql.Types.VARCHAR);
			
			getCallableStatement().setObject(1, EnumInstruction.RETRIEVE.getInstruction() );
			getCallableStatement().setObject(2, entity.getIdPerfil());
			getCallableStatement().setObject(3, EnumTypeNull.NULL.getNull());
			
			executeQuery();
			getResultSet().next();
			
			entity.setIdPerfil(getResultSet().getInt("idPerfil"));
			entity.setperfil(getResultSet().getString("perfil"));
			
			
		} catch (SQLException e) {
			System.err.println("SQLException: "+e.getMessage());
			e.printStackTrace();
		}finally {
			executeClose();
		}
		return entity;

	}

	@Override
	public void executeUpdate(Perfil entity) {
		
		
		try {
			init();
			
			getCallableStatement().registerOutParameter(1, java.sql.Types.VARCHAR);
			
			getCallableStatement().setString(1, EnumInstruction.UPDATE.getInstruction());
			getCallableStatement().setInt(2, entity.getIdPerfil());
			getCallableStatement().setString(3, entity.getperfil());
			
			execute();
			
		} catch (SQLException e) {
			System.err.println("SQLException: "+e.getMessage());
			e.printStackTrace();
		}finally {
			executeClose();
		}

	}

	@Override
	public void executeDelete(Perfil entity) {
		
		
		try {
			init();
			
			getCallableStatement().registerOutParameter(1, java.sql.Types.VARCHAR);
			
			getCallableStatement().setObject(1, EnumInstruction.DELETE.getInstruction());
			getCallableStatement().setObject(2, entity.getIdPerfil());
			getCallableStatement().setObject(3, EnumTypeNull.NULL.getNull());
			execute();
			
		} catch (SQLException e) {
			System.err.println("SQLException: "+e.getMessage());
			e.printStackTrace();
		}finally {
			executeClose();
		}


	}

	@Override
	public List<Perfil> executeRetrieveAll(Perfil entity) {
		
		
		List<Perfil> perfis = new ArrayList<Perfil>();
		
		try {
			init();
			
			getCallableStatement().registerOutParameter(1, java.sql.Types.VARCHAR);
			
			getCallableStatement().setObject(1, EnumInstruction.RETRIEVE_ALL.getInstruction());
			getCallableStatement().setObject(2, EnumTypeNull.NULL.getNull());
			getCallableStatement().setObject(3, EnumTypeNull.NULL.getNull());
			
			executeQuery();
			
			while(getResultSet().next()){
				Perfil perfil = new Perfil();
				perfil.setIdPerfil(getResultSet().getInt("idPerfil"));
				perfil.setperfil(getResultSet().getString("perfil"));
				perfis.add(perfil);
			}
			
		} catch (SQLException e) {
			System.err.println("SQLException: "+e.getMessage());
			e.printStackTrace();
		}finally {
			executeClose();
		}
		return perfis;

	}

	

	@Override
	public Collection<Object> executeRetrieveRelation(Perfil entity, Object relation, String instruction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void executeCreateRelation(Perfil entity, Collection<?> relations, String instruction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Perfil> executeCustomRetrieveAll(Perfil entity, String instruction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Perfil executeCustomRetrieve(Perfil entity, String instruction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void executeCustom(Perfil entity, String instruction, String msgErro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeCreateOrUpdade(Perfil entity) {
		// TODO Auto-generated method stub
		
	}

	
	
}
