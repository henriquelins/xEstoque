package br.com.ne.estoque.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import br.com.ne.estoque.controller.IniciarAplicativoEstoque;
import br.com.ne.estoque.controller.Produto;
import br.com.ne.estoque.controller.ProdutoController;
import br.com.ne.estoque.controller.TransacaoController;

import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class CadastroRapidoProdutoView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2028403995662866199L;
	private final JPanel cpCadastroRapidoProduto = new JPanel();
	private JTextField tfNomeDescricao;
	private JTextField tfCodigo;
	private JTextField tfEstoqueAtual;
	private JTextField tfLimiteEstoque;
	private JComboBox<String> cbCategoria;
	private JComboBox<String> cbUnidadeMedida;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			CadastroRapidoProdutoView dialog = new CadastroRapidoProdutoView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CadastroRapidoProdutoView() {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(CadastroRapidoProdutoView.class.getResource("/image/ne.png")));
		setModal(true);
		setTitle("Cadastro R\u00E1pido de Produto");
		setResizable(false);
		setBounds(100, 100, 315, 315);
		getContentPane().setLayout(new BorderLayout());
		cpCadastroRapidoProduto.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(cpCadastroRapidoProduto, BorderLayout.CENTER);
		cpCadastroRapidoProduto.setLayout(null);

		tfNomeDescricao = new JTextField();
		tfNomeDescricao.setToolTipText("Nome do produto");
		tfNomeDescricao.setBounds(13, 140, 282, 20);
		cpCadastroRapidoProduto.add(tfNomeDescricao);
		tfNomeDescricao.setColumns(10);

		JLabel lblNomeDescricao = new JLabel("Nome do Produto");
		lblNomeDescricao.setBounds(13, 122, 122, 14);
		cpCadastroRapidoProduto.add(lblNomeDescricao);

		JLabel label = new JLabel("Categoria");
		label.setBounds(13, 73, 47, 14);
		cpCadastroRapidoProduto.add(label);

		cbCategoria = new JComboBox<String>();
		cbCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cbCategoria.getSelectedItem().equals("ESCOLHA...")) {

					tfCodigo.setText("");

				} else {

					String setor = "";

					if (cbCategoria.getSelectedItem().equals("CRACHÁS")) {

						setor = "CRA";

					} else if (cbCategoria.getSelectedItem().equals("CARTÕES")) {

						setor = "CRT";

					} else if (cbCategoria.getSelectedItem().equals("IMPRESSÃO")) {

						setor = "IMP";

					} else if (cbCategoria.getSelectedItem().equals("SUPORTE TÉCNICO")) {

						setor = "SUP";

					} else if (cbCategoria.getSelectedItem().equals("ESCRITÓRIO")) {

						setor = "ESC";
					}

					DecimalFormat idProduto6dig = new DecimalFormat("000");

					String codigo = new ProdutoController().buscarCodigo(setor);

					int cod = 0;

					if (!codigo.equals("")) {

						cod = Integer.parseInt(codigo.substring(3, 6));
						tfCodigo.setText(setor + idProduto6dig.format((cod + 1)));

					} else {

						tfCodigo.setText(setor + idProduto6dig.format((1)));

					}

				}

			}
		});
		cbCategoria.setModel(new DefaultComboBoxModel<String>(new String[] { "ESCOLHA...", "CRACH\u00C1S",
				"CART\u00D5ES", "IMPRESS\u00C3O", "SUPORTE T\u00C9CNICO", "COMERCIAL", "ESCRIT\u00D3RIO" }));
		cbCategoria.setToolTipText("Escolha a categoria do produto");
		cbCategoria.setBounds(12, 91, 157, 20);
		cpCadastroRapidoProduto.add(cbCategoria);

		JLabel label_1 = new JLabel("C\u00F3digo");
		label_1.setBounds(181, 73, 57, 14);
		cpCadastroRapidoProduto.add(label_1);

		tfCodigo = new JTextField();
		tfCodigo.setToolTipText("C\u00F3digo do produto");
		tfCodigo.setColumns(10);
		tfCodigo.setBounds(181, 91, 115, 20);
		cpCadastroRapidoProduto.add(tfCodigo);

		JLabel lblEstoqueAtual = new JLabel("Estoque Inicial");
		lblEstoqueAtual.setBounds(112, 171, 77, 14);
		cpCadastroRapidoProduto.add(lblEstoqueAtual);

		tfEstoqueAtual = new JTextField();
		tfEstoqueAtual.setToolTipText("Estoque atual");
		tfEstoqueAtual.setColumns(10);
		tfEstoqueAtual.setBounds(111, 191, 86, 20);
		cpCadastroRapidoProduto.add(tfEstoqueAtual);

		JLabel lblLimiteEstoque = new JLabel("Limite estoque");
		lblLimiteEstoque.setBounds(211, 171, 84, 14);
		cpCadastroRapidoProduto.add(lblLimiteEstoque);

		tfLimiteEstoque = new JTextField();
		tfLimiteEstoque.setToolTipText("Limite m\u00EDnimo do estoque");
		tfLimiteEstoque.setColumns(10);
		tfLimiteEstoque.setBounds(209, 191, 86, 20);
		cpCadastroRapidoProduto.add(tfLimiteEstoque);

		JButton btnCadastroRapido = new JButton("Salvar");
		btnCadastroRapido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				novoProduto();

			}

		});
		btnCadastroRapido.setIcon(new ImageIcon(CadastroRapidoProdutoView.class.getResource("/image/salvar.png")));
		btnCadastroRapido.setToolTipText("Cadastrar Produto");
		btnCadastroRapido.setBounds(36, 240, 107, 23);
		cpCadastroRapidoProduto.add(btnCadastroRapido);

		JLabel lblNewLabel = new JLabel("Produto n\u00E3o encontrado!");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(11, 11, 282, 23);
		cpCadastroRapidoProduto.add(lblNewLabel);

		JLabel lblFaaUmCadastro = new JLabel("Fa\u00E7a um cadastro r\u00E1pido");
		lblFaaUmCadastro.setForeground(SystemColor.textHighlight);
		lblFaaUmCadastro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFaaUmCadastro.setBounds(12, 31, 282, 31);
		cpCadastroRapidoProduto.add(lblFaaUmCadastro);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(CadastroRapidoProdutoView.class.getResource("/image/cancelar.png")));
		btnCancelar.setToolTipText("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				dispose();

			}
		});
		btnCancelar.setBounds(169, 240, 107, 23);
		cpCadastroRapidoProduto.add(btnCancelar);

		JLabel lblUnidDeMedida = new JLabel("Unid. de medida");
		lblUnidDeMedida.setBounds(13, 171, 96, 14);
		cpCadastroRapidoProduto.add(lblUnidDeMedida);

		cbUnidadeMedida = new JComboBox<String>();
		cbUnidadeMedida.setModel(new DefaultComboBoxModel<String>(new String[] { "ESCOLHA...", "FOLHA(S)", "UNIDADE(S)",
				"VOLUME(S)", "CAIXA(S)", "LITRO(S)", "RESMA(S)" }));
		cbUnidadeMedida.setToolTipText("Escolha a unidade de medida");
		cbUnidadeMedida.setBounds(13, 191, 86, 20);
		cpCadastroRapidoProduto.add(cbUnidadeMedida);
	}

	public JTextField getTfNomeDescricao() {
		return tfNomeDescricao;
	}

	public void setTfNomeDescricao(JTextField tfNomeDescricao) {
		this.tfNomeDescricao = tfNomeDescricao;
	}

	private void novoProduto() {

		if (String.valueOf(cbCategoria.getSelectedItem()).equalsIgnoreCase("Escolha...")) {

			JOptionPane.showMessageDialog(null, "Escolha a categoria!");
			cbCategoria.requestFocus();

		} else if (tfNomeDescricao.getText().equals("")) {

			JOptionPane.showMessageDialog(null, "Digite o nome do produto!");
			tfNomeDescricao.requestFocus();

		} else if (tfEstoqueAtual.getText().equals("")) {

			JOptionPane.showMessageDialog(null, "Digite o estoque inicial!");
			tfEstoqueAtual.requestFocus();

		} else if (tfLimiteEstoque.getText().equals("")) {

			JOptionPane.showMessageDialog(null, "Digite o estoque mínimo do produto!");
			tfLimiteEstoque.requestFocus();

		} else {

			int resposta1 = JOptionPane.showConfirmDialog(null, "Deseja cadastrar um produto?", "Cadastro rápido de Produtos",
					JOptionPane.OK_OPTION);

			if (resposta1 == JOptionPane.OK_OPTION) {

				cadastrarProduto();

			} else {
				
				dispose();
				
			}

		}

	}

	private void cadastrarProduto() {

		boolean sucesso = false;

		Produto produto = new Produto();

		produto.setCategoria(String.valueOf(cbCategoria.getSelectedItem()).toUpperCase());
		produto.setCodigo(tfCodigo.getText().toUpperCase());
		produto.setNomeDescricao(tfNomeDescricao.getText().toUpperCase());
		produto.setEstoqueAtual(Integer.valueOf(tfEstoqueAtual.getText()));
		produto.setUnidadeMedida(String.valueOf(cbUnidadeMedida.getSelectedItem()).toUpperCase());
		produto.setEstoqueMinimo(Integer.valueOf(tfLimiteEstoque.getText()));

		produto.setFoto(IniciarAplicativoEstoque.produto.getFoto());

		IniciarAplicativoEstoque.produto = produto;

		ProdutoController produtoController = new ProdutoController();
		sucesso = produtoController.cadastrarProduto(IniciarAplicativoEstoque.produto);

		int id_produto = produtoController.buscarId_produto();

		IniciarAplicativoEstoque.produto.setId_produto(id_produto);

		if (sucesso == true) {

			int tipo = 1;
			int ajuste = 0;
			int estoqueAnterior = 0;

			TransacaoController transacaoController = new TransacaoController();
			transacaoController.transacao(tipo, ajuste, estoqueAnterior);

			JOptionPane.showMessageDialog(null, "O produto cadastrado com sucesso!");

			dispose();

		} else {

			JOptionPane.showMessageDialog(null, "O produto não foi cadastrado!");
			dispose();

		}

	}

}
