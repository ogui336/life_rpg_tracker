package br.com.gui336.liferpgtracker.data;

import java.sql.Connection;
import java.sql.SQLException;

public class MainTest {

	public static void main(String[] src) {
		System.out.println("Tentando conectar ao banco de dados...");

        try (Connection conn = DatabaseConnection.getConnection()) {
            
            // Se chegou até aqui sem disparar exceção, a conexão foi bem-sucedida!
            if (conn != null && !conn.isClosed()) {
                System.out.println("✅ Conexão estabelecida com sucesso!");
                System.out.println("Banco de dados conectado: " + conn.getCatalog());
            }

        } catch (SQLException e) {
            System.err.println("❌ Erro ao conectar ao banco de dados!");
            System.err.println("Causa do erro: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("❌ Ocorreu um erro inesperado!");
            e.printStackTrace();
        }
		
	}
}
