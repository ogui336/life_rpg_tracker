package br.com.gui336.liferpgtracker.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConnection {

	public static Connection getConnection() throws SQLException {
		Properties prop = new Properties();
		
		
        try (InputStream is = DatabaseConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
            
        	if (is == null) {
        		throw new RuntimeException("Arquivo db.properties não foi encontrado em src/main/resources!");
        	}
        	prop.load(is);
            }catch (IOException e) {
            	throw new RuntimeException("Erro ao ler arquivo db.properties: " + e.getMessage());
            }
    
		String url = prop.getProperty("db.url");
		String user = prop.getProperty("db.user");
		String password = prop.getProperty("db.password");
		
		return DriverManager.getConnection(url,user,password);
	}
}
