package br.com.ne.estoque.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.ne.estoque.controller.Produto;

public class ProdutoDAO {

	public boolean cadastrarProduto(Produto produto) {

		ConectarBanco conectarBanco = new ConectarBanco();
		Connection conexao = conectarBanco.geraConexao();

		PreparedStatement insereST = null;
		String sql = "";

		boolean sucesso = false;

		try {

			int index = 1;

			sql = "INSERT INTO produto (categoria, codigo, nomeDescricao, valorUnitario, valorVenda, estoqueAtual, unidadeMedida, estoqueMinimo, observacoes, foto  ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			insereST = conexao.prepareStatement(sql);

			insereST.setString(index, produto.getCategoria());
			insereST.setString(++index, produto.getCodigo());
			insereST.setString(++index, produto.getNomeDescricao());
			insereST.setDouble(++index, produto.getValorUnitario());
			insereST.setDouble(++index, produto.getValorVenda());
			insereST.setInt(++index, produto.getEstoqueAtual());
			insereST.setString(++index, produto.getUnidadeMedida());
			insereST.setInt(++index, produto.getEstoqueMinimo());
			insereST.setString(++index, produto.getObservacoes());
			insereST.setBytes(++index, produto.getFoto());

			insereST.executeUpdate();

			sucesso = true;

			return sucesso;

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Erro ao inserir o produto!" + e.getMessage());

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

	public List<Produto> escolha(String escolha) {

		ConectarBanco conectarBanco = new ConectarBanco();
		Connection conexao = conectarBanco.geraConexao();

		PreparedStatement insereST = null;
		ResultSet rs = null;
		String sql = "";

		List<Produto> listaProduto = new ArrayList<Produto>();

		try {

			if (escolha.equals("TODOS OS PRODUTOS")) {

				sql = "SELECT * FROM produto ORDER BY codigo ASC";

				insereST = conexao.prepareStatement(sql);
				rs = insereST.executeQuery();

			} else {

				sql = "SELECT * FROM produto where categoria = ? ORDER BY codigo ASC";

				insereST = conexao.prepareStatement(sql);
				insereST.setString(1, escolha);
				rs = insereST.executeQuery();

			}

			while (rs.next()) {

				Produto produto = new Produto();

				if (!rs.getString("codigo").equals("EXCLUÍDO")) {

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

					listaProduto.add(produto);

				}

			}

			return listaProduto;

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Erro ao gerar a lista. Mensagem: " + e.getMessage());

		} finally {

			try {

				insereST.close();
				rs.close();
				conexao.close();

			} catch (Exception e2) {

				JOptionPane.showMessageDialog(null, "Erro ao encerrar a Conexão. Mensagem: " + e2.getMessage());

			}

		}

		return listaProduto;

	}

	public List<Produto> todosOsProdutos() {

		ConectarBanco conectarBanco = new ConectarBanco();
		Connection conexao = conectarBanco.geraConexao();

		PreparedStatement insereST = null;
		ResultSet rs = null;
		String sql = "";

		List<Produto> listaProduto = new ArrayList<Produto>();

		try {

			sql = "SELECT * FROM produto ORDER BY codigo ASC";

			insereST = conexao.prepareStatement(sql);
			rs = insereST.executeQuery();

			while (rs.next()) {

				Produto produto = new Produto();

				if (!rs.getString("codigo").equals("EXCLUÍDO")) {

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

					listaProduto.add(produto);

				}

			}

			return listaProduto;

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Erro ao gerar a lista. Mensagem: " + e.getMessage());

		} finally {

			try {

				insereST.close();
				rs.close();
				conexao.close();

			} catch (Exception e2) {

				JOptionPane.showMessageDialog(null, "Erro ao encerrar a Conexão. Mensagem: " + e2.getMessage());

			}

		}

		return listaProduto;

	}

	public boolean editarProduto(Produto produto) {

		boolean sucesso = false;

		ConectarBanco conectarBanco = new ConectarBanco();
		Connection conexao = conectarBanco.geraConexao();

		PreparedStatement insereST = null;
		String sql = "";

		try {

			int index = 1;

			sql = "UPDATE produto SET categoria = ?,  codigo  = ?, nomeDescricao = ?, valorUnitario = ?, valorVenda = ?, unidadeMedida = ?, estoqueMinimo = ?, observacoes = ?, foto = ?  WHERE id_produto = ?";

			insereST = conexao.prepareStatement(sql);

			insereST.setString(index, produto.getCategoria());
			insereST.setString(++index, produto.getCodigo());
			insereST.setString(++index, produto.getNomeDescricao());
			insereST.setDouble(++index, produto.getValorUnitario());
			insereST.setDouble(++index, produto.getValorVenda());
			insereST.setString(++index, produto.getUnidadeMedida());
			insereST.setInt(++index, produto.getEstoqueMinimo());
			insereST.setString(++index, produto.getObservacoes());
			insereST.setBytes(++index, produto.getFoto());
			insereST.setInt(++index, produto.getId_produto());

			insereST.executeUpdate();

			sucesso = true;

			return sucesso;

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Erro ao alterar o cliente. Mensagem: " + e.getMessage());

		} finally {

			try {

				insereST.close();
				conexao.close();

			} catch (Exception e2) {

				JOptionPane.showMessageDialog(null, "Erro ao encerrar a Conexão. Mensagem: " + e2.getMessage());

			}

		}

		return sucesso;

	}

	public boolean excluirProduto(int id_produto) {

		boolean sucesso = false;

		ConectarBanco conectarBanco = new ConectarBanco();
		Connection conexao = conectarBanco.geraConexao();

		PreparedStatement insereST = null;
		String sql = "";

		try {

			sql = "DELETE FROM produto WHERE id_produto = ?";

			insereST = conexao.prepareStatement(sql);

			insereST.setInt(1, id_produto);

			insereST.execute();

			sucesso = true;

			return sucesso;

		} catch (SQLException e) {

			e.printStackTrace();

			return sucesso;

		} finally {

			try {

				insereST.close();
				conexao.close();

			} catch (Exception e2) {

				JOptionPane.showMessageDialog(null, "Erro ao encerrar a Conexão. Mensagem: " + e2.getMessage());

			}

		}

	}

	public boolean entrada(Produto produto) {

		boolean sucesso = false;

		ConectarBanco conectarBanco = new ConectarBanco();
		Connection conexao = conectarBanco.geraConexao();

		PreparedStatement insereST = null;
		String sql = "";

		try {

			int index = 1;

			sql = "UPDATE produto SET estoqueAtual = ? WHERE id_produto = ?";

			insereST = conexao.prepareStatement(sql);

			insereST.setInt(index, produto.getEstoqueAtual());
			insereST.setInt(++index, produto.getId_produto());

			insereST.executeUpdate();

			sucesso = true;

			return sucesso;

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Erro ao dar entrada no estoque! Mensagem: " + e.getMessage());

		} finally {

			try {

				insereST.close();
				conexao.close();

			} catch (Exception e2) {

				JOptionPane.showMessageDialog(null, "Erro ao encerrar a Conexão. Mensagem: " + e2.getMessage());

			}

		}

		return sucesso;

	}

	public boolean saida(Produto produto) {

		boolean sucesso = false;

		ConectarBanco conectarBanco = new ConectarBanco();
		Connection conexao = conectarBanco.geraConexao();

		PreparedStatement insereST = null;
		String sql = "";

		try {

			int index = 1;

			sql = "UPDATE produto SET estoqueAtual = ? WHERE id_produto = ?";

			insereST = conexao.prepareStatement(sql);

			insereST.setInt(index, produto.getEstoqueAtual());
			insereST.setInt(++index, produto.getId_produto());

			insereST.executeUpdate();

			sucesso = true;

			return sucesso;

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Erro ao dar entrada no estoque! Mensagem: " + e.getMessage());

		} finally {

			try {

				insereST.close();
				conexao.close();

			} catch (Exception e2) {

				JOptionPane.showMessageDialog(null, "Erro ao encerrar a Conexão. Mensagem: " + e2.getMessage());

			}

		}

		return sucesso;

	}

	public List<Produto> estoqueBaixo() {

		ConectarBanco conectarBanco = new ConectarBanco();
		Connection conexao = conectarBanco.geraConexao();

		PreparedStatement insereST = null;
		ResultSet rs = null;
		String sql = "";

		List<Produto> listaProduto = new ArrayList<Produto>();

		try {

			sql = "SELECT * FROM produto ORDER BY codigo ASC";

			insereST = conexao.prepareStatement(sql);
			rs = insereST.executeQuery();

			while (rs.next()) {

				int estoqueAtual = rs.getInt("EstoqueAtual");
				int estoqueMinimo = rs.getInt("EstoqueMinimo");

				if (estoqueAtual <= estoqueMinimo) {

					Produto produto = new Produto();

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

					if (!produto.getCategoria().equalsIgnoreCase("EXCLUÍDO")) {

						listaProduto.add(produto);

					}

				}

			}

			return listaProduto;

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Erro ao gerar a lista. Mensagem: " + e.getMessage());

		} finally {

			try {

				insereST.close();
				rs.close();
				conexao.close();

			} catch (Exception e2) {

				JOptionPane.showMessageDialog(null, "Erro ao encerrar a Conexão. Mensagem: " + e2.getMessage());

			}

		}

		return listaProduto;

	}

	public List<Produto> pesquisar(String pesquisa) {

		ConectarBanco conectarBanco = new ConectarBanco();
		Connection conexao = conectarBanco.geraConexao();

		PreparedStatement insereST = null;
		ResultSet rs = null;
		String sql = "";

		List<Produto> listaProduto = new ArrayList<Produto>();

		try {

			sql = "SELECT * FROM produto WHERE NomeDescricao like ? ORDER BY codigo ASC";

			insereST = conexao.prepareStatement(sql);
			insereST.setString(1, "%" + pesquisa + "%");
			rs = insereST.executeQuery();

			while (rs.next()) {

				Produto produto = new Produto();

				if (!rs.getString("codigo").equals("EXCLUÍDO")) {

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

					listaProduto.add(produto);

				}

			}

			return listaProduto;

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Erro ao gerar a lista. Mensagem: " + e.getMessage());

		} finally {

			try {

				insereST.close();
				rs.close();
				conexao.close();

			} catch (Exception e2) {

				JOptionPane.showMessageDialog(null, "Erro ao encerrar a Conexão. Mensagem: " + e2.getMessage());

			}

		}

		return listaProduto;

	}

	public Produto buscarProduto(int id_produto) {

		ConectarBanco conectarBanco = new ConectarBanco();
		Connection conexao = conectarBanco.geraConexao();

		PreparedStatement insereST = null;
		ResultSet rs = null;
		String sql = "";

		Produto produto = new Produto();

		try {

			sql = "SELECT * FROM produto WHERE id_produto = ?";

			insereST = conexao.prepareStatement(sql);
			insereST.setInt(1, id_produto);
			rs = insereST.executeQuery();

			while (rs.next()) {

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

			}

			return produto;

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Erro ao gerar a lista. Mensagem: " + e.getMessage());

		} finally {

			try {

				insereST.close();
				rs.close();
				conexao.close();

			} catch (Exception e2) {

				JOptionPane.showMessageDialog(null, "Erro ao encerrar a Conexão. Mensagem: " + e2.getMessage());

			}

		}

		return produto;

	}

	public int buscarId_produto() {

		ConectarBanco conectarBanco = new ConectarBanco();
		Connection conexao = conectarBanco.geraConexao();

		PreparedStatement insereST = null;
		ResultSet rs = null;
		String sql = "";

		int id_produto = 0;

		try {

			sql = "SELECT id_produto FROM produto";

			insereST = conexao.prepareStatement(sql);
			rs = insereST.executeQuery();

			while (rs.next()) {

				id_produto = rs.getInt("id_produto");

			}

			return id_produto;

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Erro ao gerar a lista. Mensagem: " + e.getMessage());

		} finally {

			try {

				insereST.close();
				rs.close();
				conexao.close();

			} catch (Exception e2) {

				JOptionPane.showMessageDialog(null, "Erro ao encerrar a Conexão. Mensagem: " + e2.getMessage());

			}

		}

		return id_produto;

	}

	public String buscarCodigo(String setor) {

		ConectarBanco conectarBanco = new ConectarBanco();
		Connection conexao = conectarBanco.geraConexao();

		PreparedStatement insereST = null;
		ResultSet rs = null;
		String sql = "";

		String codigo = "";

		try {

			sql = "SELECT codigo FROM produto where codigo like ? order by codigo asc";

			insereST = conexao.prepareStatement(sql);
			insereST.setString(1, "%" + setor + "%");
			rs = insereST.executeQuery();

			while (rs.next()) {

				if (!rs.getString("codigo").equals("EXCLUÍDO")) {

					codigo = rs.getString("codigo");

				}

			}

			return codigo;

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Erro ao gerar a lista. Mensagem: " + e.getMessage());

		} finally {

			try {

				insereST.close();
				rs.close();
				conexao.close();

			} catch (Exception e2) {

				JOptionPane.showMessageDialog(null, "Erro ao encerrar a Conexão. Mensagem: " + e2.getMessage());

			}

		}

		return codigo;

	}

}
