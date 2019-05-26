package br.com.ne.estoque.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.ne.estoque.controller.Operador;
import br.com.ne.estoque.controller.Produto;
import br.com.ne.estoque.controller.Transacao;

public class TransacaoDAO {

	public boolean novaTransacao(Transacao transacao) {
		
		ConectarBanco conectarBanco = new ConectarBanco();
		Connection conexao = conectarBanco.geraConexao();
		
		PreparedStatement insereST = null;
		String sql = "";
		
		boolean sucesso = false;
		
		try {
			
			int index = 1;
			
			sql = "insert into transacao (tipo, data_transacao, hora_transacao, id_produto, valorunitario, valorvenda, estoqueanterior, ajuste, estoqueatual, id_operador) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			insereST = conexao.prepareStatement(sql);
					
			insereST.setInt(index, transacao.getTipo());
			insereST.setDate(++index, transacao.getDataTransacao());
			insereST.setTime(++index, transacao.getHoraTransacao());
			insereST.setInt(++index, transacao.getProduto().getId_produto());
			insereST.setDouble(++index, transacao.getValorUnitario());
			insereST.setDouble(++index, transacao.getValorVenda());
			insereST.setInt(++index, transacao.getEstoqueAnterior());
			insereST.setInt(++index, transacao.getAjuste());
			insereST.setInt(++index, transacao.getEstoqueAtual());
			insereST.setInt(++index, transacao.getOperador().getId_operador());
			
			insereST.execute();
			
			sucesso = true;
			
			return sucesso;
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Erro ao inserir o transação!" + e.getMessage());
			
		} finally {
			
			try {
				
				insereST.close();
				conexao.close();
				
			} catch (Exception e2) {

				JOptionPane.showMessageDialog(null, "Erro ao encerrar a Conexão! Mensagem: " + e2.getMessage());

			}

		}

		return sucesso;
	}

	public List<Transacao> todasTransacoes() {

		ConectarBanco conectarBanco = new ConectarBanco();
		Connection conexao = conectarBanco.geraConexao();

		PreparedStatement insereST = null;
		String sql = "";
		ResultSet rs = null;

		List<Transacao> listaTransacao = new ArrayList<Transacao>();

		try {

			sql = "select * from transacao as tr inner join produto as pr on pr.id_produto = tr.id_produto inner join operador as op on op.id_operador = tr.id_operador";

			insereST = conexao.prepareStatement(sql);
			rs = insereST.executeQuery();

			while (rs.next()) {

				Transacao transacao = new Transacao();
				Produto produto = new Produto();
				Operador operador = new Operador();

				transacao.setId_transacao(rs.getInt("id_transacao"));
				transacao.setTipo(rs.getInt("tipo"));
				transacao.setDataTransacao(rs.getDate("data_transacao"));
				transacao.setHoraTransacao(rs.getTime("hora_transacao"));
				transacao.setValorVenda(rs.getDouble("ValorVenda"));
				transacao.setValorUnitario(rs.getDouble("ValorUnitario"));
				transacao.setEstoqueAnterior(rs.getInt("estoqueanterior"));
				transacao.setAjuste(rs.getInt("ajuste"));
				transacao.setEstoqueAtual(rs.getInt("estoqueAtual"));

				produto.setId_produto(rs.getInt("id_produto"));
				produto.setCategoria(rs.getString("categoria"));
				produto.setCodigo(rs.getString("codigo"));
				produto.setNomeDescricao(rs.getString("NomeDescricao"));
				produto.setValorUnitario(rs.getDouble("ValorUnitario"));
				produto.setValorVenda(rs.getDouble("ValorVenda"));
				produto.setEstoqueAtual(rs.getInt("EstoqueAtual"));
				produto.setUnidadeMedida(rs.getString("UnidadeMedida"));
				produto.setEstoqueMinimo(rs.getInt("EstoqueMinimo"));
				produto.setObservacoes(rs.getString("Observacoes"));
				produto.setFoto(rs.getBytes("foto"));

				operador.setId_operador(rs.getInt("id_operador"));
				operador.setNome(rs.getString("nome"));
				operador.setLogin(rs.getString("login"));

				transacao.setProduto(produto);
				transacao.setOperador(operador);

				listaTransacao.add(transacao);

			}

			return listaTransacao;

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Erro ao gerar a lista da transação!" + e.getMessage());

		} finally {

			try {

				insereST.close();
				conexao.close();

			} catch (Exception e2) {

				JOptionPane.showMessageDialog(null, "Erro ao encerrar a Conexão! Mensagem: " + e2.getMessage());

			}

		}

		return listaTransacao;
	}

	public List<Transacao> umaTransacao(String tipoTransacao) {

		ConectarBanco conectarBanco = new ConectarBanco();
		Connection conexao = conectarBanco.geraConexao();

		PreparedStatement insereST = null;
		String sql = "";
		ResultSet rs = null;

		List<Transacao> listaTransacao = new ArrayList<Transacao>();

		try {

			sql = "select * from transacao as tr inner join produto as pr on pr.id_produto = tr.id_produto inner join operador as op on op.id_operador = tr.id_operador where pr.categoria = ?";

			insereST = conexao.prepareStatement(sql);
			insereST.setString(1, tipoTransacao);
			rs = insereST.executeQuery();

			while (rs.next()) {

				Transacao transacao = new Transacao();
				Produto produto = new Produto();
				Operador operador = new Operador();

				transacao.setId_transacao(rs.getInt("id_transacao"));
				transacao.setTipo(rs.getInt("tipo"));
				transacao.setDataTransacao(rs.getDate("data_transacao"));
				transacao.setHoraTransacao(rs.getTime("hora_transacao"));
				transacao.setValorVenda(rs.getDouble("ValorVenda"));
				transacao.setValorUnitario(rs.getDouble("ValorUnitario"));
				transacao.setEstoqueAnterior(rs.getInt("estoqueanterior"));
				transacao.setAjuste(rs.getInt("ajuste"));
				transacao.setEstoqueAtual(rs.getInt("estoqueAtual"));

				produto.setId_produto(rs.getInt("id_produto"));
				produto.setCategoria(rs.getString("categoria"));
				produto.setCodigo(rs.getString("codigo"));
				produto.setNomeDescricao(rs.getString("NomeDescricao"));
				produto.setValorUnitario(rs.getDouble("ValorUnitario"));
				produto.setValorVenda(rs.getDouble("ValorVenda"));
				produto.setEstoqueAtual(rs.getInt("EstoqueAtual"));
				produto.setUnidadeMedida(rs.getString("UnidadeMedida"));
				produto.setEstoqueMinimo(rs.getInt("EstoqueMinimo"));
				produto.setObservacoes(rs.getString("Observacoes"));
				produto.setFoto(rs.getBytes("foto"));

				operador.setId_operador(rs.getInt("id_operador"));
				operador.setNome(rs.getString("nome"));
				operador.setLogin(rs.getString("login"));

				transacao.setProduto(produto);
				transacao.setOperador(operador);

				listaTransacao.add(transacao);

			}

			return listaTransacao;

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Erro ao gerar a lista da transação!" + e.getMessage());

		} finally {

			try {

				insereST.close();
				conexao.close();

			} catch (Exception e2) {

				JOptionPane.showMessageDialog(null, "Erro ao encerrar a Conexão! Mensagem: " + e2.getMessage());

			}

		}

		return listaTransacao;
	}

	public List<Transacao> pesquisarCampos(String campo, String informacao) {

		ConectarBanco conectarBanco = new ConectarBanco();
		Connection conexao = conectarBanco.geraConexao();

		PreparedStatement insereST = null;
		String sql = "";
		ResultSet rs = null;

		List<Transacao> listaTransacao = new ArrayList<Transacao>();

		if (campo.equals("CÓDIGO")) {

			campo = "pr.codigo";

		} else if (campo.equals("PRODUTO")) {

			campo = "pr.nomedescricao";

		} else if (campo.equals("CATEGORIA")) {

			campo = "pr.categoria";

		} else if (campo.equals("COLABORADOR")) {

			campo = "op.nome";

		}

		try {

			sql = "select * from transacao as tr inner join produto as pr on pr.id_produto = tr.id_produto inner join operador as op on op.id_operador = tr.id_operador where "
					+ campo + " like ?";

			insereST = conexao.prepareStatement(sql);
			insereST.setString(1, "%" + informacao.toUpperCase() + "%");
			rs = insereST.executeQuery();

			while (rs.next()) {

				Transacao transacao = new Transacao();
				Produto produto = new Produto();
				Operador operador = new Operador();

				transacao.setId_transacao(rs.getInt("id_transacao"));
				transacao.setTipo(rs.getInt("tipo"));
				transacao.setDataTransacao(rs.getDate("data_transacao"));
				transacao.setHoraTransacao(rs.getTime("hora_transacao"));
				transacao.setValorVenda(rs.getDouble("ValorVenda"));
				transacao.setValorUnitario(rs.getDouble("ValorUnitario"));
				transacao.setEstoqueAnterior(rs.getInt("estoqueanterior"));
				transacao.setAjuste(rs.getInt("ajuste"));
				transacao.setEstoqueAtual(rs.getInt("estoqueAtual"));

				produto.setId_produto(rs.getInt("id_produto"));
				produto.setCategoria(rs.getString("categoria"));
				produto.setCodigo(rs.getString("codigo"));
				produto.setNomeDescricao(rs.getString("NomeDescricao"));
				produto.setValorUnitario(rs.getDouble("ValorUnitario"));
				produto.setValorVenda(rs.getDouble("ValorVenda"));
				produto.setEstoqueAtual(rs.getInt("EstoqueAtual"));
				produto.setUnidadeMedida(rs.getString("UnidadeMedida"));
				produto.setEstoqueMinimo(rs.getInt("EstoqueMinimo"));
				produto.setObservacoes(rs.getString("Observacoes"));
				produto.setFoto(rs.getBytes("foto"));

				operador.setId_operador(rs.getInt("id_operador"));
				operador.setNome(rs.getString("nome"));
				operador.setLogin(rs.getString("login"));

				transacao.setProduto(produto);
				transacao.setOperador(operador);

				listaTransacao.add(transacao);

			}

			return listaTransacao;

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Erro ao gerar a lista da transação!" + e.getMessage());

		} finally {

			try {

				insereST.close();
				conexao.close();

			} catch (Exception e2) {

				JOptionPane.showMessageDialog(null, "Erro ao encerrar a Conexão! Mensagem: " + e2.getMessage());

			}

		}

		return listaTransacao;
	}

}
