package br.com.ne.estoque.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.ne.estoque.model.ProdutoDAO;

public class ProdutoController {

	public boolean cadastrarProduto(Produto produto) {

		boolean sucesso = false;

		ProdutoDAO produtoDAO = new ProdutoDAO();
		sucesso = produtoDAO.cadastrarProduto(produto);

		return sucesso;

	}

	public boolean editarProduto(Produto produto) {

		boolean sucesso = false;

		ProdutoDAO produtoDAO = new ProdutoDAO();
		sucesso = produtoDAO.editarProduto(produto);

		return sucesso;

	}

	public boolean excluirProduto(int id_produto) {

		boolean sucesso = false;

		ProdutoDAO produtoDAO = new ProdutoDAO();
		sucesso = produtoDAO.excluirProduto(id_produto);

		return sucesso;

	}

	public boolean entrada(Produto produto) {

		boolean sucesso = false;

		ProdutoDAO produtoDAO = new ProdutoDAO();
		sucesso = produtoDAO.entrada(produto);

		return sucesso;

	}

	public boolean saida(Produto produto) {

		boolean sucesso = false;

		ProdutoDAO produtoDAO = new ProdutoDAO();
		sucesso = produtoDAO.saida(produto);

		return sucesso;

	}

	public List<Produto> Escolha(String escolha) {

		List<Produto> listaProduto = new ArrayList<Produto>();

		ProdutoDAO produtoDAO = new ProdutoDAO();
		listaProduto = produtoDAO.escolha(escolha);

		return listaProduto;

	}

	public List<Produto> todosOsProdutos() {

		List<Produto> listaProduto = new ArrayList<Produto>();

		ProdutoDAO produtoDAO = new ProdutoDAO();
		listaProduto = produtoDAO.todosOsProdutos();

		return listaProduto;

	}

	public List<Produto> estoqueBaixo() {

		List<Produto> listaProduto = new ArrayList<Produto>();

		ProdutoDAO produtoDAO = new ProdutoDAO();
		listaProduto = produtoDAO.estoqueBaixo();

		return listaProduto;

	}

	public List<Produto> pesquisar(String pesquisa) {

		List<Produto> listaProduto = new ArrayList<Produto>();

		ProdutoDAO produtoDAO = new ProdutoDAO();
		listaProduto = produtoDAO.pesquisar(pesquisa);

		return listaProduto;

	}

	public Produto buscarProduto(int id_produto) {

		Produto produto = new Produto();
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produto = produtoDAO.buscarProduto(id_produto);

		return produto;
	}

	public int buscarId_produto() {

		int id_produto = 0;
		ProdutoDAO produtoDAO = new ProdutoDAO();
		id_produto = produtoDAO.buscarId_produto();

		return id_produto;
	}

	public String buscarCodigo(String setor) {

		ProdutoDAO produtoDAO = new ProdutoDAO();

		String codigo = produtoDAO.buscarCodigo(setor);

		return codigo;

	}

}
