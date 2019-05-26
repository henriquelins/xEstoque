package uteis;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.ne.estoque.controller.Produto;
import br.com.ne.estoque.view.CadastroRapidoProdutoView;

public class ListarTabelas {

	public void listarTabelaProdutoView(List<Produto> listaProduto, JTable tab, JComboBox<String> cb) {

		DefaultTableModel model = (DefaultTableModel) tab.getModel();

		model.setNumRows(0);

		if (listaProduto.isEmpty()) {

			JOptionPane.showMessageDialog(null, "Não existem itens para listar!");

			cb.setSelectedIndex(0);

		} else {

			for (Produto p : listaProduto) {

				Object[] linha = { p.getNomeDescricao(), p.getCodigo(), p.getEstoqueAtual(), p.getValorVenda(),
						p.getValorUnitario(), p.getCategoria() };

				model.addRow(linha);

			}

		}

	}

	public void listarTabelaEntradaSaidaProdutoView(List<Produto> listaProduto, JTable tab, JComboBox<String> cb) {

		DefaultTableModel model = (DefaultTableModel) tab.getModel();

		model.setNumRows(0);

		if (listaProduto.isEmpty()) {

			CadastroRapidoProdutoView cadastroRapidoProdutoView = new CadastroRapidoProdutoView();

			cadastroRapidoProdutoView.getTfNomeDescricao().setText((String) cb.getSelectedItem());

			cadastroRapidoProdutoView.setLocationRelativeTo(null);
			cadastroRapidoProdutoView.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			cadastroRapidoProdutoView.setVisible(true);

			cb.setSelectedItem(cadastroRapidoProdutoView.getTfNomeDescricao().getText());		
		
			
		} else {

			for (Produto p : listaProduto) {

				Object[] linha = { p.getCodigo(), p.getNomeDescricao(), p.getValorUnitario(), p.getEstoqueAtual(),
						p.getCategoria() };

				model.addRow(linha);

			}

		}

	}

	public void limparTabela(JTable tab) {

		DefaultTableModel model = (DefaultTableModel) tab.getModel();

		model.setNumRows(0);

	}

}
