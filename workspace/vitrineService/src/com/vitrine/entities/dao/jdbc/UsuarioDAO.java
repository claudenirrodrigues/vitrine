 package com.vitrine.entities.dao.jdbc;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import com.vitrine.entities.Perfil;
import com.vitrine.entities.Usuario;
import com.vitrine.entities.dao.AbstractJDBCFacade;
import com.vitrine.entities.dao.UsuarioRepository;

public class UsuarioDAO extends AbstractJDBCFacade<Usuario> implements UsuarioRepository{
	
	
	
	public UsuarioDAO(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public String getProcedure() {
		return "";
	}
	
	@Override
	public void executeCreate(Usuario entity){
		
		
		
		try {
			init();
			
			setCallParameterValue(entity, EnumInstruction.CREATE.getInstruction());
			execute();
			
		} catch (SQLException e) {
			System.err.println("SQLException: "+e.getMessage());
			e.printStackTrace();
		}finally {
			executeClose();
		}
		
	}

	@Override
	public Usuario executeRetrieve(Usuario entity) {
		
		
		
		try {
			init();
			
			setCallParameterValue(entity, EnumInstruction.RETRIEVE.getInstruction());
			
			executeQuery();
			getResultSet().next();
			
			entity.setIdUsuario(getResultSet().getString(EnumDomains.ID_USUARIO.getParameterName()));
			entity.setSenha(getResultSet().getString(EnumDomains.SENHA.getParameterName()));
			
			entity.setTrace(getTrace());
			
		} catch (SQLException e) {
			System.err.println("SQLException: "+e.getMessage());
			e.printStackTrace();
		}finally {
			executeClose();
		}
		return entity;
	}

	@Override
	public void executeUpdate(Usuario entity) {

		
		
		try {
			
			init();
			setCallParameterValue(entity, EnumInstruction.UPDATE.getInstruction());
			
			execute();
			
		} catch (SQLException e) {
			System.err.println("SQLException: "+e.getMessage());
			e.printStackTrace();
		}finally {
			executeClose();
		}
		
	}

	@Override
	public void executeDelete(Usuario entity) {

		
		
		try {
			init();
			
			setCallParameterValue(entity, EnumInstruction.DELETE.getInstruction());
			
			execute();
			
		} catch (SQLException e) {
			System.err.println("SQLException: "+e.getMessage());
			e.printStackTrace();
		}finally {
			executeClose();
		}
		
	}
	
	@Override
	public void executeCreatePerfil(Usuario entity){

		

		try {
			
			init();
			
			Collection<Perfil> perfilCollection = entity.getPerfilCollection();
		
			for (Iterator<Perfil> iterator = perfilCollection.iterator(); iterator.hasNext();) {
				Perfil perfil = (Perfil) iterator.next();
				//setCallParameterValue(perfil, EnumInstruction.CREATE_PERFIL.getInstruction());
				execute();
			}
		
		} catch (SQLException e) {
			System.err.println("SQLException: "+e.getMessage());
			e.printStackTrace();
		}finally {
			executeClose();
		}

	}

	@Override
	public Usuario executeRetrievePerfis(Usuario entity) {
		
		
		
		try {
			
			init();
			
			setCallParameterValue(entity, EnumInstruction.RETRIEVE_PERFIS.getInstruction());
			
			executeQuery();
			
			Collection<Perfil> perfilCollection = new ArrayList<Perfil>();
			
			while(getResultSet().next()){
				Perfil perfil = new Perfil();
				perfil.setIdPerfil(getResultSet().getInt(EnumDomains.ID_PERFIL.getParameterName()));
				perfil.setperfil(getResultSet().getString(EnumDomains.PERFIL.getParameterName()));
				perfilCollection.add(perfil);
			}
			
			entity.setPerfilCollection(perfilCollection);
			
		} catch (SQLException e) {
			System.err.println("SQLException: "+e.getMessage());
			e.printStackTrace();
		}finally {
			executeClose();
		}
		return entity;
	}

	@Override
	public List<Usuario> executeRetrieveAll(Usuario entity) {
		
		
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		try {
			
			init();
			
			setTrace(null, EnumInstruction.RETRIEVE_ALL.getInstruction());
			
			getCallableStatement().setObject(EnumDomains.ID_USUARIO.getParameterIndex(), EnumDomains.ID_USUARIO.getTypeNull().getNull());
			getCallableStatement().setObject(EnumDomains.SENHA.getParameterIndex(), EnumDomains.SENHA.getTypeNull().getNull());
			getCallableStatement().setObject(EnumDomains.ID_PERFIL.getParameterIndex(), EnumDomains.ID_PERFIL.getTypeNull().getNull());
			
			executeQuery();
			
			while(getResultSet().next()){
				Usuario usuario = new Usuario();
				usuario.setIdUsuario(getResultSet().getString("idUsuario"));
				usuario.setSenha(getResultSet().getString("senha"));
				usuario.setTrace(getTrace());
				usuarios.add(usuario);
			}
			
		} catch (SQLException e) {
			System.err.println("SQLException: "+e.getMessage());
			e.printStackTrace();
		}finally {
			executeClose();
		}
		return usuarios;

	}

	@Override
	public void executeCreateRelation(Usuario entity, Collection<?> relations, String instruction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Object> executeRetrieveRelation(Usuario entity, Object relation, String instruction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> executeCustomRetrieveAll(Usuario entity, String instruction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario executeCustomRetrieve(Usuario entity, String instruction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void executeCustom(Usuario entity, String instruction, String msgErro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeCreateOrUpdade(Usuario entity) {
		// TODO Auto-generated method stub
		
	}

	

	
}
