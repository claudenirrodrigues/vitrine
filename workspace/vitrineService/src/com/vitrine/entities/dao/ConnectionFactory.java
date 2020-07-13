package com.vitrine.entities.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.sql.DataSource;



public class ConnectionFactory {

	public ConnectionFactory() {
    
	}

	//@Resource(mappedName="java:/vitrineDatasource")
	@Resource
	private DataSource dataSource;

	private static Connection connection = null;
	private static String USERNAME;
	private static String PASSWORD;
	private static String DB_NAME;
	private static String URL;
	private static String DRIVER;
	
	public Connection getConnectionDataSource() {

		try{
    		if (connection == null || connection.isClosed()) {
    			connection = this.dataSource.getConnection();
    		} 
    		return connection;
	        
	    } catch (Exception e) {
	    	e.printStackTrace();
			throw new RuntimeException(e);
	    }finally {
		    try {
		        if(connection != null)
		        	connection.close();
		    }
		    catch(SQLException e) {
		    	e.printStackTrace();
				throw new RuntimeException(e);
		    }
		}
	}
	
	public static Connection getConnection(DataSource dataSource) {
		if(dataSource == null){
			System.err.println("DataSource n�o foi inicializado.");
			System.out.println("Capturando conex�o via DriverManager.");
			return getConnection();
		}else{
			return getConnectionDataSource(dataSource);
		}
		
	}
	
	private static Connection getConnection() {

		try {
			
			
			if (connection != null) {
				if (!connection.isClosed()) {
					return connection;
				}
			} else {

				Class.forName(getDRIVER());
				connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());
			
			}

			return connection;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}
	
	private static Connection getConnectionDataSource(DataSource datasource) {

		try{
    		if (connection == null || connection.isClosed()) {
    			connection = datasource.getConnection();
    		} 
    		return connection;
	        
	    } catch (Exception e) {
	    	e.printStackTrace();
			throw new RuntimeException(e);
	    }finally {
		    try {
		        if(connection != null)
		        	connection.close();
		    }
		    catch(SQLException e) {
		    	e.printStackTrace();
				throw new RuntimeException(e);
		    }
		}
	}
	
	public static void closeConnection(Connection conn, CallableStatement cs, ResultSet rs) throws SQLException {
        close(conn, cs, null, rs);
    }
	
	public static void closeConnection(Connection conn, CallableStatement cs) throws SQLException {
        close(conn, cs, null, null);
    }
	
	public static void closeConnection(Connection conn, PreparedStatement pstm, ResultSet rs) throws SQLException {
        close(conn, null, pstm, rs);
    }
 
    public static void closeConnection(Connection conn, PreparedStatement pstm) throws SQLException {
        close(conn, null, pstm, null);
    }
 
    public static void closeConnection(Connection conn) throws SQLException {
        close(conn, null, null, null);
    }
 
    private static void close(Connection conn, CallableStatement cs, PreparedStatement pstm, ResultSet rs) throws SQLException{
            
		try {
			if (rs != null) rs.close( );
			if (pstm != null)pstm.close( );
            if (cs != null)cs.close();
            if (conn != null)conn.close( );
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

	public static String getUSERNAME() {
		if(USERNAME == null){
			USERNAME = System.getProperty("com.vitrine.datasource.user");
		}
		return USERNAME;
	}

	public static String getPASSWORD() {
		if(PASSWORD == null){
			PASSWORD = System.getProperty("com.vitrine.datasource.password");
		}
		return PASSWORD;
	}

	public static String getDB_NAME() {
		if(DB_NAME == null){
			DB_NAME = System.getProperty("com.vitrine.datasource.database");
		}
		return DB_NAME;
	}

	public static String getURL() {
		if(URL == null){
			URL = System.getProperty("com.vitrine.datasource.url");
		}
		return URL;
	}

	public static String getDRIVER() {
		if (DRIVER == null) {
			DRIVER = System.getProperty("com.vitrine.datasource.driver");
		}
		return DRIVER;
	}
}

