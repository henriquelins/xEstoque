package br.com.ne.estoque.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.ne.estoque.model.OperadorDAO;

public class OperadorController {

	static OperadorDAO operadorDAO = new OperadorDAO();

	public boolean adicionarOperador(Operador operador) {

		boolean sucesso = false;
		
		sucesso = operadorDAO.adicionarOperador(operador);
		
		return sucesso;

	}

	public boolean editarOperador(Operador operador) {
		
		boolean sucesso = false;

		sucesso = operadorDAO.editarOperador(operador);
		
		return sucesso;

	}

	public static List<Operador> listaOperador() {

		List<Operador> listaOperador = new ArrayList<Operador>();

		listaOperador = operadorDAO.listaOperador();

		return listaOperador;

	}

	public Operador login(String login, String senha) {

		Operador operador = new Operador();

		operador = operadorDAO.login(login, senha);

		return operador;

	}

	public Operador buscarOperador(String nome) {

		Operador operador = new Operador();

		operador = operadorDAO.buscarOperador(nome);

		return operador;

	}

	public boolean excluirOperador(Integer id_operador) {
		
		boolean sucesso = false;
		
		sucesso = operadorDAO.excluirOperador(id_operador);
		
		return sucesso;

	}
	
	public List <Operador> pesquisarLoginOperador(String pesquisa){
		
		List <Operador> listaOperador = new ArrayList<Operador>();
		
		listaOperador = operadorDAO.pesquisarLoginOperador(pesquisa);
		
		return listaOperador;
		
	}

}
