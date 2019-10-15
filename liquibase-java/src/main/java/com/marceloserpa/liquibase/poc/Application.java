package com.marceloserpa.liquibase.poc;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.FileSystemResourceAccessor;

public class Application {

	public static void main(String[] args) throws SQLException, LiquibaseException {
		
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("org.postgresql.Driver");
		ds.setUrl("jdbc:postgresql://localhost:5555/postgres");
		ds.setUsername("postgres");
		ds.setPassword("docker");
		
		
		Connection connection = ds.getConnection();
		
		Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));

		String path = "src/main/resources/db/migration/changelog.yaml";
		Liquibase liquibase = new liquibase.Liquibase(path, new FileSystemResourceAccessor(), database);
		
		liquibase.update(new Contexts(), new LabelExpression());

	}
	
}
