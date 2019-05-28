package br.com.ne.estoque.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.ne.estoque.model.TransacaoDAO;

public class TransacaoController {

	public void transacao(Integer tipo, Integer ajuste, Integer estoqueAnterior) {

		boolean sucesso = false;

		Transacao transacao = new Transacao();
		TransacaoDAO transacaoDAO = new TransacaoDAO();

		Date hoje = new Date(System.currentTimeMillis());
		Time hora = new Time(System.currentTimeMillis());

		java.sql.Date DataSql = new java.sql.Date(hoje.getTime());
		java.sql.Time horaSql = new java.sql.Time(hora.getTime());
				
		transacao.setTipo(tipo);
		transacao.setDataTransacao(DataSql);
		transacao.setHoraTransacao(horaSql);
		transacao.setValorUnitario(IniciarAplicativoEstoque.produto.getValorUnitario());
		transacao.setValorVenda(IniciarAplicativoEstoque.produto.getValorVenda());
		transacao.setEstoqueAnterior(estoqueAnterior);
		transacao.setAjuste(ajuste);
		transacao.setEstoqueAtual(IniciarAplicativoEstoque.produto.getEstoqueAtual());
		transacao.setProduto(IniciarAplicativoEstoque.produto);
		transacao.setOperador(IniciarAplicativoEstoque.operador);
	
		sucesso = transacaoDAO.novaTransacao(transacao);
		
		if (sucesso == false) {

			JOptionPane.showMessageDialog(null, "Erro ao executar a transação!");

		}

	}
	
	
	public List <Transacao> todasTransacoes() {
		
		List <Transacao> listaTransacao = new ArrayList<Transacao>();
		
		TransacaoDAO transacaoDAO = new TransacaoDAO();
		listaTransacao = transacaoDAO.todasTransacoes();
		
		return listaTransacao;
	}
	
	public List <Transacao> umaTrancacao(String tipoTransacao) {
		
		List <Transacao> listaTransacao = new ArrayList<Transacao>();
		
		TransacaoDAO transacaoDAO = new TransacaoDAO();
		listaTransacao = transacaoDAO.umaTransacao(tipoTransacao);
		
		return listaTransacao;
	}


	public List <Transacao> pesquisarCampos(String campo, String informacao) {
		
		List<Transacao> listaTransacao = new ArrayList<Transacao>();
		TransacaoDAO transacaoDAO = new TransacaoDAO();
		listaTransacao = transacaoDAO.pesquisarCampos(campo, informacao);
		
		return listaTransacao;
	}

}
