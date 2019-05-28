package br.com.ne.estoque.controller;

import java.awt.Color;
import java.awt.Font;
import java.net.ServerSocket;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import br.com.ne.estoque.view.TelaLogin;

public class IniciarAplicativoEstoque {

	public static Produto produto = new Produto();
	public static Operador operador = new Operador();

	private static ServerSocket s;

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {

		// impede que seja criada uma nova instância do programa

		try {

			setS(new ServerSocket(10002));
			IniciarAplicativoEstoque iniaciarAplicativo = new IniciarAplicativoEstoque();
			iniaciarAplicativo.Iniciar();

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "O programa já está aberto!");

		}

	}

	public void Iniciar() {

		try {

			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			TelaLogin frame = new TelaLogin();
			frame.getCbLogin().setForeground(new Color(0, 0, 255));
			frame.getContentPane().setFont(new Font("Arial", Font.PLAIN, 11));
			frame.getCbLogin().setFont(new Font("Arial", Font.BOLD, 14));
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	public static ServerSocket getS() {
		return s;
	}

	public static void setS(ServerSocket s) {

		IniciarAplicativoEstoque.s = s;

	}

}
