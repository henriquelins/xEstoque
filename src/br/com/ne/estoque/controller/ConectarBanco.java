package br.com.ne.estoque.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConectarBanco {
	
	private static String host= "localhost";
	private static String porta = "5432";
	private static String login = "postgres";
	private static String senha = "1006";
	private static String banco = "INTEGRADO";
	
	private static String url = "jdbc:postgresql://" + host + ":" + porta + "/" + banco;

	private static final String driver = "org.postgresql.Driver";

	public Connection geraConexao() {

		Connection conexao = null;

		try {

			Class.forName(driver);

			return DriverManager.getConnection(url, login, senha);

		} catch (ClassNotFoundException e) {

			JOptionPane.showMessageDialog(null, "Classe não encontrada. Erro: " + e.getMessage());

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, "Ocorreu um erro de SQL. Erro: " + e.getMessage());

		}

		return conexao;
	}

	
}
