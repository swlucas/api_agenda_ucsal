package br.ucsal.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	private static final String DRIVER = "org.postgresql.Driver";
	private static final String URL = "jdbc:postgresql://localhost/postgres" ;
	private static final String USER = "postgres";
	private static final String PASSWORD = "lucas58227";

private Connection connection = null;
	
	private static Conexao conexao;
	
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private Conexao() {
		try {
			connection = DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (SQLException e) {
			System.out.println("Erro ao estabeelecer conexão com a base de dados: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public static  Conexao getConexao() {
		if(conexao == null) {
			conexao = new Conexao();
		}
		return conexao;
	}
	
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
