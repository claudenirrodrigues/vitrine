package com.vitrine.entities.dao;

import javax.annotation.Resource;
import javax.sql.DataSource;

public class VitrineDataSource {

	@Resource
	private DataSource dataSource;
	
	protected DataSource getDataSource() {
		return dataSource;
	}

}
