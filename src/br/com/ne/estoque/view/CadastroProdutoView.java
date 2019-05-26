package br.com.ne.estoque.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.com.ne.estoque.controller.IniciarAplicativoNEEstoque;
import br.com.ne.estoque.controller.Produto;
import br.com.ne.estoque.controller.ProdutoController;
import br.com.ne.estoque.controller.TransacaoController;
import uteis.JNumberFormatField;
import uteis.JNumberFormatField2;
import uteis.NovaImagem;
import uteis.TamanhoMaxTextField;

public class CadastroProdutoView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3859580814042375023L;
	private JTextField tfCodigo;
	private JTextField tfDescricao;
	private JLabel lblCategoria;
	private JLabel lblValorVenda;
	private JNumberFormatField tfPrecoVenda;
	private JLabel lblValorUnitario;
	private JNumberFormatField tfPrecoCusto;
	private JLabel lblLblestoqueatual;
	private JTextField tfEstoqueAtual;
	private JLabel lblLimeteEstoque;
	private JTextField tfLimiteEstoque;
	private JComboBox<String> cbCategoria;
	private JComboBox<String> cbUnidadeMedida;
	private JTextPane tpObservacoes;
	private JLabel lblClickImagem;
	private String local = "";
	private boolean novo = false;
	private int id_produto = 0;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			CadastroProdutoView dialog = new CadastroProdutoView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CadastroProdutoView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(CadastroProdutoView.class.getResource("/image/ne.png")));
		getContentPane().setForeground(new Color(30, 144, 255));
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setTitle("Dados do Produto");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 960, 630);
		getContentPane().setLayout(null);

		JPanel panelDadosProduto = new JPanel();
		panelDadosProduto.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192)), "Cadastro do Produto",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelDadosProduto.setBounds(20, 21, 437, 529);
		getContentPane().add(panelDadosProduto);
		panelDadosProduto.setLayout(null);

		tpObservacoes = new JTextPane();
		tpObservacoes.setToolTipText("Observa\u00E7\u00F5es sobre o produto");
		tpObservacoes.setBounds(25, 405, 385, 86);
		panelDadosProduto.add(tpObservacoes);
		tpObservacoes.setBorder(new LineBorder(Color.LIGHT_GRAY));

		JLabel lblObservacao = new JLabel("Observa\u00E7\u00F5es");
		lblObservacao.setBounds(25, 380, 80, 14);
		panelDadosProduto.add(lblObservacao);

		tfDescricao = new JTextField();
		tfDescricao.setToolTipText("Nome e/ou descri\u00E7\u00E3o do produto");
		tfDescricao.setBounds(25, 143, 385, 20);
		panelDadosProduto.add(tfDescricao);
		tfDescricao.setColumns(10);

		JLabel lblDescricao = new JLabel("Nome / Descri\u00E7\u00E3o");
		lblDescricao.setBounds(25, 118, 217, 14);
		panelDadosProduto.add(lblDescricao);

		cbCategoria = new JComboBox<String>();
		cbCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

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
		cbCategoria.setToolTipText("Escolha a categoria do produto");
		cbCategoria.setBounds(25, 63, 241, 20);
		panelDadosProduto.add(cbCategoria);
		cbCategoria.setModel(new DefaultComboBoxModel<String>(new String[] { "ESCOLHA...", "CRACH\u00C1S",
				"CART\u00D5ES", "IMPRESS\u00C3O", "SUPORTE T\u00C9CNICO", "ESCRIT\u00D3RIO" }));

		tfCodigo = new JTextField();
		tfCodigo.setBackground(Color.WHITE);
		tfCodigo.setDisabledTextColor(Color.WHITE);
		tfCodigo.setForeground(Color.BLACK);
		tfCodigo.setEditable(false);
		tfCodigo.setToolTipText("C\u00F3digo do produto");
		tfCodigo.setBounds(313, 63, 97, 20);
		panelDadosProduto.add(tfCodigo);
		tfCodigo.setColumns(10);

		TamanhoMaxTextField TamanhoMax3 = new TamanhoMaxTextField();
		TamanhoMax3.setMaxChars(7);

		tfEstoqueAtual = new JTextField();
		tfEstoqueAtual.setToolTipText("Estoque atual");
		tfEstoqueAtual.setBounds(276, 223, 134, 20);
		panelDadosProduto.add(tfEstoqueAtual);
		tfEstoqueAtual.setColumns(10);

		tfEstoqueAtual.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ev) {

				String caracteres = "0987654321";

				if (!caracteres.contains(ev.getKeyChar() + "")) {

					ev.consume();

				}

			}

		});
		tfEstoqueAtual.setDocument(TamanhoMax3);

		lblLblestoqueatual = new JLabel("Estoque Atual");
		lblLblestoqueatual.setBounds(276, 198, 67, 14);
		panelDadosProduto.add(lblLblestoqueatual);

		cbUnidadeMedida = new JComboBox<String>();
		cbUnidadeMedida.setToolTipText("Escolha a unidade de medida");
		cbUnidadeMedida.setBounds(25, 223, 204, 20);
		panelDadosProduto.add(cbUnidadeMedida);
		cbUnidadeMedida.setModel(new DefaultComboBoxModel<String>(new String[] { "ESCOLHA...", "FOLHA(S)", "UNIDADE(S)",
				"VOLUME(S)", "CAIXA(S)", "LITRO(S)", "RESMA(S)" }));

		JLabel lblUnidadeDeMedida = new JLabel("Unidade de medida");
		lblUnidadeDeMedida.setBounds(25, 198, 115, 14);
		panelDadosProduto.add(lblUnidadeDeMedida);

		TamanhoMaxTextField TamanhoMax4 = new TamanhoMaxTextField();
		TamanhoMax4.setMaxChars(7);

		tfLimiteEstoque = new JTextField();
		tfLimiteEstoque.setToolTipText("Limite m\u00EDnimo do estoque");
		tfLimiteEstoque.setBounds(25, 315, 134, 20);
		panelDadosProduto.add(tfLimiteEstoque);
		tfLimiteEstoque.setColumns(10);

		tfLimiteEstoque.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ev) {

				String caracteres = "0987654321";

				if (!caracteres.contains(ev.getKeyChar() + "")) {

					ev.consume();

				}

			}

		});
		tfLimiteEstoque.setDocument(TamanhoMax4);

		lblLimeteEstoque = new JLabel("Limite de Estoque");
		lblLimeteEstoque.setBounds(27, 290, 84, 14);
		panelDadosProduto.add(lblLimeteEstoque);

		lblValorUnitario = new JLabel("Valor Unit\u00E1rio");
		lblValorUnitario.setBounds(198, 290, 80, 14);
		panelDadosProduto.add(lblValorUnitario);

		tfPrecoCusto = new JNumberFormatField(new DecimalFormat("R$ 0.00"));
		tfPrecoCusto.setToolTipText("Valor unit\u00E1rio");
		tfPrecoCusto.setBounds(198, 315, 86, 20);
		panelDadosProduto.add(tfPrecoCusto);
		tfPrecoCusto.setColumns(10);
		tfPrecoCusto.setLimit(6);

		lblValorVenda = new JLabel("Valor de Venda");
		lblValorVenda.setBounds(324, 290, 80, 14);
		panelDadosProduto.add(lblValorVenda);

		tfPrecoVenda = new JNumberFormatField(new DecimalFormat("R$ 0.00"));
		tfPrecoVenda.setToolTipText("Valor de venda");
		tfPrecoVenda.setBounds(324, 315, 86, 20);
		panelDadosProduto.add(tfPrecoVenda);
		tfPrecoVenda.setColumns(10);
		tfPrecoVenda.setLimit(6);

		lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(25, 38, 47, 14);
		panelDadosProduto.add(lblCategoria);

		JLabel lblCódigo = new JLabel("C\u00F3digo");
		lblCódigo.setBounds(313, 38, 97, 14);
		panelDadosProduto.add(lblCódigo);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setToolTipText("Salvar produto");
		btnSalvar.setHorizontalAlignment(SwingConstants.LEFT);
		btnSalvar.setIcon(new ImageIcon(CadastroProdutoView.class.getResource("/image/salvar.png")));
		btnSalvar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (novo == true) {

					novoProduto();

				} else {

					editarProduto();

				}

			}

		});
		btnSalvar.setBounds(711, 561, 97, 23);
		getContentPane().add(btnSalvar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setToolTipText("Cancelar");
		btnCancelar.setHorizontalAlignment(SwingConstants.LEADING);
		btnCancelar.setIcon(new ImageIcon(CadastroProdutoView.class.getResource("/image/cancelar.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				dispose();

			}
		});
		btnCancelar.setBounds(818, 561, 105, 23);
		getContentPane().add(btnCancelar);

		JLabel lblProdutoImagem = new JLabel("");
		lblProdutoImagem.setHorizontalTextPosition(SwingConstants.CENTER);
		lblProdutoImagem.setHorizontalAlignment(SwingConstants.CENTER);
		lblProdutoImagem.setIcon(new ImageIcon(CadastroProdutoView.class.getResource("/image/produto2.png")));
		lblProdutoImagem.setBounds(476, 403, 447, 147);
		getContentPane().add(lblProdutoImagem);

		JLabel lblLogado = new JLabel("LOGADO: " + IniciarAplicativoNEEstoque.operador.getNome().toUpperCase());
		lblLogado.setBounds(20, 561, 435, 23);
		getContentPane().add(lblLogado);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192)), "Imagem do Produto",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(471, 21, 462, 386);
		getContentPane().add(panel);
		panel.setLayout(null);

		lblClickImagem = new JLabel("Clique aqui para escolher a imagem do produto");
		lblClickImagem.setBounds(12, 18, 438, 356);
		panel.add(lblClickImagem);
		lblClickImagem.setBackground(SystemColor.controlShadow);
		lblClickImagem.setToolTipText("Clique aqui e selecione a imagem de exibi\u00E7\u00E3o do produto");
		lblClickImagem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				JFileChooser escolherArquivo = new JFileChooser("c:\\Documentos");
				FileNameExtensionFilter filtroExtensao1 = new FileNameExtensionFilter(".jpg", "jpg");
				FileNameExtensionFilter filtroExtensao2 = new FileNameExtensionFilter(".png", "png");

				UIManager.put("FileChooser.lookInLabelMnemonic", "E");
				UIManager.put("FileChooser.lookInLabelText", "Examinar em");
				UIManager.put("FileChooser.saveInLabelMnemonic", "S");
				UIManager.put("FileChooser.saveInLabelText", "Salvar em");
				UIManager.put("FileChooser.upFolderToolTipText", "Um nível acima");
				UIManager.put("FileChooser.upFolderAccessibleName", "Um nível acima");
				UIManager.put("FileChooser.homeFolderToolTipText", "Desktop");
				UIManager.put("FileChooser.homeFolderAccessibleName", "Desktop");
				UIManager.put("FileChooser.newFolderToolTipText", "Criar nova pasta");
				UIManager.put("FileChooser.newFolderAccessibleName", "Criar nova pasta");
				UIManager.put("FileChooser.listViewButtonToolTipText", "Lista");
				UIManager.put("FileChooser.listViewButtonAccessibleName", "Lista");
				UIManager.put("FileChooser.detailsViewButtonToolTipText", "Detalhes");
				UIManager.put("FileChooser.detailsViewButtonAccessibleName", "Detalhes");
				UIManager.put("FileChooser.fileNameLabelMnemonic", "N");
				UIManager.put("FileChooser.fileNameLabelText", "Nome do arquivo");
				UIManager.put("FileChooser.filesOfTypeLabelMnemonic", "A");
				UIManager.put("FileChooser.filesOfTypeLabelText", "Arquivos do tipo");
				UIManager.put("FileChooser.fileNameHeaderText", "Nome");
				UIManager.put("FileChooser.fileSizeHeaderText", "Tamanho");
				UIManager.put("FileChooser.fileTypeHeaderText", "Tipo");
				UIManager.put("FileChooser.fileDateHeaderText", "Data");
				UIManager.put("FileChooser.fileAttrHeaderText", "Atributos");
				UIManager.put("FileChooser.cancelButtonText", "Cancelar");
				UIManager.put("FileChooser.cancelButtonMnemonic", "C");
				UIManager.put("FileChooser.cancelButtonToolTipText", "Cancelar");
				UIManager.put("FileChooser.openButtonText", "Abrir / Salvar");
				UIManager.put("FileChooser.openButtonMnemonic", "A");
				UIManager.put("FileChooser.openButtonToolTipText", "Abrir");
				UIManager.put("FileChooser.saveButtonText", "Salvar =]");
				UIManager.put("FileChooser.saveButtonToolTipText", "S");
				UIManager.put("FileChooser.saveButtonToolTipText", "Salvar");
				UIManager.put("FileChooser.updateButtonText", "Alterar");
				UIManager.put("FileChooser.updateButtonToolTipText", "A");
				UIManager.put("FileChooser.updateButtonToolTipText", "Alterar");
				UIManager.put("FileChooser.helpButtonText", "Ajuda");
				UIManager.put("FileChooser.helpButtonToolTipText", "A");
				UIManager.put("FileChooser.helpButtonToolTipText", "Ajuda");
				UIManager.put("FileChooser.acceptAllFileFilterText", "Todos os arquivos");

				escolherArquivo.addChoosableFileFilter(filtroExtensao1);
				escolherArquivo.addChoosableFileFilter(filtroExtensao2);

				int retorno = escolherArquivo.showDialog(null, "Selecionar Imagem");

				File arquivo = null;

				if (retorno == JFileChooser.APPROVE_OPTION) {

					arquivo = escolherArquivo.getSelectedFile();
					escolherArquivo.setSelectedFile(arquivo);
					InputStream converter = null;

					local = arquivo.getPath();

					byte[] bytes = new byte[(int) arquivo.length()];

					try {

						converter = new FileInputStream(local);

					} catch (FileNotFoundException e) {

						e.printStackTrace();
					}

					int offset = 0;
					int numRead = 0;
					try {

						while (offset < bytes.length
								&& (numRead = converter.read(bytes, offset, bytes.length - offset)) >= 0) {
							offset += numRead;
						}
					} catch (IOException e) {

						e.printStackTrace();
					}

					IniciarAplicativoNEEstoque.produto.setFoto(bytes);

				}

				if (!local.equals("")) {

					ImageIcon iconFrente = new ImageIcon(local);

					NovaImagem novaImagem = new NovaImagem();

					try {

						novaImagem = novaImagem.redimensionaImagem(iconFrente, lblClickImagem);

					} catch (IOException e) {

						e.printStackTrace();

					}

					if (novaImagem.getAltura() == 0 || novaImagem.getLargura() == 0) {

						JOptionPane.showMessageDialog(null, "Falha ao tentar abrir imagem!");

					} else if (novaImagem.getAltura() == -1 || novaImagem.getLargura() == -1) {

						JOptionPane.showMessageDialog(null, "Selecione um arquivo jpg ou png!");

					} else {

						lblClickImagem.setText("");

						lblClickImagem.setIcon(new ImageIcon(iconFrente.getImage()
								.getScaledInstance(novaImagem.getLargura(), novaImagem.getAltura(), Image.SCALE_FAST)));

					}

				}

			}
		});
		lblClickImagem.setForeground(Color.BLUE);
		lblClickImagem.setHorizontalAlignment(SwingConstants.CENTER);
		lblClickImagem.setHorizontalTextPosition(SwingConstants.CENTER);
		lblClickImagem.setBorder(null);
	}

	@SuppressWarnings("unused")
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	public JTextField getTfCodigo() {
		return tfCodigo;
	}

	public void setTfCodigo(JTextField tfCodigo) {
		this.tfCodigo = tfCodigo;
	}

	public JTextField getTfDescricao() {
		return tfDescricao;
	}

	public void setTfDescricao(JTextField tfDescricao) {
		this.tfDescricao = tfDescricao;
	}

	public JTextField getTfEstoqueAtual() {
		return tfEstoqueAtual;
	}

	public void setTfEstoqueAtual(JNumberFormatField2 tfEstoqueAtual) {
		this.tfEstoqueAtual = tfEstoqueAtual;
	}

	public JTextField getTfLimiteEstoque() {
		return tfLimiteEstoque;
	}

	public void setTfLimiteEstoque(JTextField tfLimiteEstoque) {
		this.tfLimiteEstoque = tfLimiteEstoque;
	}

	public JComboBox<String> getCbCategoria() {
		return cbCategoria;
	}

	public void setCbCategoria(JComboBox<String> cbCategoria) {
		this.cbCategoria = cbCategoria;
	}

	public JComboBox<String> getCbUnidadeMedida() {
		return cbUnidadeMedida;
	}

	public void setCbUnidadeMedida(JComboBox<String> cbUnidadeMedida) {
		this.cbUnidadeMedida = cbUnidadeMedida;
	}

	public JTextPane getTpObservacoes() {
		return tpObservacoes;
	}

	public void setTpObservacoes(JTextPane tpObservacoes) {
		this.tpObservacoes = tpObservacoes;
	}

	public JLabel getLblClickImagem() {
		return lblClickImagem;
	}

	public void setLblClickImagem(JLabel lblClickImagem) {
		this.lblClickImagem = lblClickImagem;
	}

	public JNumberFormatField getTfPrecoVenda() {
		return tfPrecoVenda;
	}

	public void setTfPrecoVenda(JNumberFormatField tfPrecoVenda) {
		this.tfPrecoVenda = tfPrecoVenda;
	}

	public JNumberFormatField getTfPrecoCusto() {
		return tfPrecoCusto;
	}

	public void setTfPrecoCusto(JNumberFormatField tfPrecoCusto) {
		this.tfPrecoCusto = tfPrecoCusto;
	}

	public JLabel getLblLblestoqueatual() {
		return lblLblestoqueatual;
	}

	public void setLblLblestoqueatual(JLabel lblLblestoqueatual) {
		this.lblLblestoqueatual = lblLblestoqueatual;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public boolean isNovo() {
		return novo;
	}

	public void setNovo(boolean novo) {
		this.novo = novo;
	}

	public void novoProduto() {

		if (String.valueOf(cbCategoria.getSelectedItem()).equalsIgnoreCase("Escolha...")) {

			JOptionPane.showMessageDialog(null, "Escolha a categoria!");
			cbCategoria.requestFocus();

		} else if (tfDescricao.getText().equals("")) {

			JOptionPane.showMessageDialog(null, "Digite o nome do produto!");
			tfDescricao.requestFocus();

		} else if (String.valueOf(cbUnidadeMedida.getSelectedItem()).equalsIgnoreCase("Escolha...")) {

			JOptionPane.showMessageDialog(null, "Escolha a unidade de medida!");
			cbUnidadeMedida.requestFocus();

		} else if (tfEstoqueAtual.getText().equals("")) {

			JOptionPane.showMessageDialog(null, "Digite o estoque inicial!");
			tfEstoqueAtual.requestFocus();

		} else if (tfLimiteEstoque.getText().equals("")) {

			JOptionPane.showMessageDialog(null, "Digite o estoque mínimo do produto!");
			tfLimiteEstoque.requestFocus();

		} else if (tfPrecoCusto.getText().equals("R$ 0,00")) {

			JOptionPane.showMessageDialog(null, "Digite o preço de custo!");
			tfPrecoCusto.requestFocus();

		} else if (tfPrecoVenda.getText().equals("R$ 0,00")) {

			JOptionPane.showMessageDialog(null, "Digite o preço de venda!");
			tfPrecoVenda.requestFocus();

		} else {

			int resposta1 = JOptionPane.showConfirmDialog(null, "Deseja cadastrar um produto?", "Cadastro de Produto",
					JOptionPane.OK_OPTION);

			if (resposta1 == JOptionPane.OK_OPTION) {

				if (!local.equals("")) {

					lblClickImagem.setText("");

					cadastrarProduto();

				} else {

					int resposta = JOptionPane.showConfirmDialog(null, "Deseja cadastrar a imagem do produto agora?",
							"Imagem do Produto", JOptionPane.OK_OPTION);

					if (resposta == JOptionPane.OK_OPTION) {

						JFileChooser escolherArquivo = new JFileChooser("c:\\Documentos");
						FileNameExtensionFilter filtroExtensao1 = new FileNameExtensionFilter(".jpg", "jpg");
						FileNameExtensionFilter filtroExtensao2 = new FileNameExtensionFilter(".png", "png");

						UIManager.put("FileChooser.lookInLabelMnemonic", "E");
						UIManager.put("FileChooser.lookInLabelText", "Examinar em");
						UIManager.put("FileChooser.saveInLabelMnemonic", "S");
						UIManager.put("FileChooser.saveInLabelText", "Salvar em");
						UIManager.put("FileChooser.upFolderToolTipText", "Um nível acima");
						UIManager.put("FileChooser.upFolderAccessibleName", "Um nível acima");
						UIManager.put("FileChooser.homeFolderToolTipText", "Desktop");
						UIManager.put("FileChooser.homeFolderAccessibleName", "Desktop");
						UIManager.put("FileChooser.newFolderToolTipText", "Criar nova pasta");
						UIManager.put("FileChooser.newFolderAccessibleName", "Criar nova pasta");
						UIManager.put("FileChooser.listViewButtonToolTipText", "Lista");
						UIManager.put("FileChooser.listViewButtonAccessibleName", "Lista");
						UIManager.put("FileChooser.detailsViewButtonToolTipText", "Detalhes");
						UIManager.put("FileChooser.detailsViewButtonAccessibleName", "Detalhes");
						UIManager.put("FileChooser.fileNameLabelMnemonic", "N");
						UIManager.put("FileChooser.fileNameLabelText", "Nome do arquivo");
						UIManager.put("FileChooser.filesOfTypeLabelMnemonic", "A");
						UIManager.put("FileChooser.filesOfTypeLabelText", "Arquivos do tipo");
						UIManager.put("FileChooser.fileNameHeaderText", "Nome");
						UIManager.put("FileChooser.fileSizeHeaderText", "Tamanho");
						UIManager.put("FileChooser.fileTypeHeaderText", "Tipo");
						UIManager.put("FileChooser.fileDateHeaderText", "Data");
						UIManager.put("FileChooser.fileAttrHeaderText", "Atributos");
						UIManager.put("FileChooser.cancelButtonText", "Cancelar");
						UIManager.put("FileChooser.cancelButtonMnemonic", "C");
						UIManager.put("FileChooser.cancelButtonToolTipText", "Cancelar");
						UIManager.put("FileChooser.openButtonText", "Abrir / Salvar");
						UIManager.put("FileChooser.openButtonMnemonic", "A");
						UIManager.put("FileChooser.openButtonToolTipText", "Abrir");
						UIManager.put("FileChooser.saveButtonText", "Salvar =]");
						UIManager.put("FileChooser.saveButtonToolTipText", "S");
						UIManager.put("FileChooser.saveButtonToolTipText", "Salvar");
						UIManager.put("FileChooser.updateButtonText", "Alterar");
						UIManager.put("FileChooser.updateButtonToolTipText", "A");
						UIManager.put("FileChooser.updateButtonToolTipText", "Alterar");
						UIManager.put("FileChooser.helpButtonText", "Ajuda");
						UIManager.put("FileChooser.helpButtonToolTipText", "A");
						UIManager.put("FileChooser.helpButtonToolTipText", "Ajuda");
						UIManager.put("FileChooser.acceptAllFileFilterText", "Todos os arquivos");

						escolherArquivo.addChoosableFileFilter(filtroExtensao1);
						escolherArquivo.addChoosableFileFilter(filtroExtensao2);

						File arquivo = null;

						int retorno = escolherArquivo.showDialog(null, "Selecionar Imagem");

						if (retorno == JFileChooser.APPROVE_OPTION) {

							arquivo = escolherArquivo.getSelectedFile();
							escolherArquivo.setSelectedFile(arquivo);
							InputStream converter = null;

							local = arquivo.getPath();

							byte[] bytes = new byte[(int) arquivo.length()];

							try {

								converter = new FileInputStream(local);

							} catch (FileNotFoundException e) {

								e.printStackTrace();
							}

							int offset = 0;
							int numRead = 0;

							try {

								while (offset < bytes.length
										&& (numRead = converter.read(bytes, offset, bytes.length - offset)) >= 0) {
									offset += numRead;
								}

							} catch (IOException e) {

								e.printStackTrace();
							}

							IniciarAplicativoNEEstoque.produto.setFoto(bytes);

							arquivo = escolherArquivo.getSelectedFile();
							escolherArquivo.setSelectedFile(arquivo);

							JOptionPane.showMessageDialog(null, arquivo);
							local = arquivo.getPath();

						}

						if (!local.equals("")) {

							ImageIcon iconFrente = new ImageIcon(local);

							NovaImagem novaImagem = new NovaImagem();

							try {

								novaImagem = novaImagem.redimensionaImagem(iconFrente, lblClickImagem);

							} catch (IOException e) {

								e.printStackTrace();

							}

							lblClickImagem.setText("");

							lblClickImagem.setIcon(new ImageIcon(iconFrente.getImage().getScaledInstance(
									novaImagem.getLargura(), novaImagem.getAltura(), Image.SCALE_FAST)));

							cadastrarProduto();

						} else {

							lblClickImagem.setText("Clique aqui para escolher a imagem do produto");

							lblClickImagem.setIcon(new ImageIcon(""));

						}

					} else {

						cadastrarProduto();

					}

				}

				cbCategoria.setSelectedIndex(0);
				tfCodigo.setText("");
				tfDescricao.setText("");
				tfPrecoCusto.setText("");
				tfPrecoVenda.setText("");
				tfEstoqueAtual.setText("");
				cbUnidadeMedida.setSelectedIndex(0);
				tfLimiteEstoque.setText("");
				tpObservacoes.setText("");

				lblClickImagem.setIcon(new ImageIcon(""));
				lblClickImagem.setText("Clique aqui para escolher a imagem do produto");
				local = "";

			} else {

				cbCategoria.setSelectedIndex(0);
				tfCodigo.setText("");
				tfDescricao.setText("");
				tfPrecoCusto.setText("");
				tfPrecoVenda.setText("");
				tfEstoqueAtual.setText("");
				cbUnidadeMedida.setSelectedIndex(0);
				tfLimiteEstoque.setText("");
				tpObservacoes.setText("");

				lblClickImagem.setIcon(new ImageIcon(""));
				lblClickImagem.setText("Clique aqui para escolher a imagem do produto");
				local = "";

			}

		}

	}

	public void cadastrarProduto() {

		boolean sucesso = false;

		Produto produto = new Produto();

		String pC = tfPrecoCusto.getText().replace("R$ ", "");
		String pV = tfPrecoVenda.getText().replace("R$ ", "");

		double precoCusto = Double.valueOf(pC.replace(",", "."));
		double precoVenda = Double.valueOf(pV.replace(",", "."));

		produto.setCategoria(String.valueOf(cbCategoria.getSelectedItem()).toUpperCase());
		produto.setCodigo(tfCodigo.getText().toUpperCase());
		produto.setNomeDescricao(tfDescricao.getText().toUpperCase());
		produto.setValorUnitario(precoCusto);
		produto.setValorVenda(precoVenda);
		produto.setEstoqueAtual(Integer.valueOf(tfEstoqueAtual.getText()));
		produto.setUnidadeMedida(String.valueOf(cbUnidadeMedida.getSelectedItem()).toUpperCase());
		produto.setEstoqueMinimo(Integer.valueOf(tfLimiteEstoque.getText()));
		produto.setObservacoes(tpObservacoes.getText().toUpperCase());

		produto.setFoto(IniciarAplicativoNEEstoque.produto.getFoto());

		IniciarAplicativoNEEstoque.produto = produto;

		ProdutoController produtoController = new ProdutoController();
		sucesso = produtoController.cadastrarProduto(IniciarAplicativoNEEstoque.produto);

		id_produto = produtoController.buscarId_produto();

		IniciarAplicativoNEEstoque.produto.setId_produto(id_produto);

		if (sucesso == true) {

			int tipo = 1;
			int ajuste = 0;
			int estoqueAnterior = 0;

			TransacaoController transacaoController = new TransacaoController();
			transacaoController.transacao(tipo, ajuste, estoqueAnterior);

			int resposta = JOptionPane.showConfirmDialog(null,
					"O produto " + tfDescricao.getText().toUpperCase()
							+ " foi cadastrado com sucesso, \n deseja cadastrar outro produto?",
					"Cadastro com Sucesso!", JOptionPane.OK_OPTION);

			if (resposta == JOptionPane.OK_OPTION) {

				cbCategoria.setSelectedIndex(0);
				tfCodigo.setText("");
				tfDescricao.setText("");
				tfPrecoCusto.setText("");
				tfPrecoVenda.setText("");
				tfEstoqueAtual.setText("");
				cbUnidadeMedida.setSelectedIndex(0);
				tfLimiteEstoque.setText("");
				tpObservacoes.setText("");

				lblClickImagem.setIcon(new ImageIcon(""));
				lblClickImagem.setText("Clique aqui para escolher a imagem do produto");
				local = "";

			} else {

				dispose();

			}

		} else {

			JOptionPane.showMessageDialog(null, "O produto não foi cadastrado!");

		}

	}

	private void editarProduto() {

		if (String.valueOf(cbCategoria.getSelectedItem()).equalsIgnoreCase("Escolha...")) {

			JOptionPane.showMessageDialog(null, "Escolha a categoria!");
			cbCategoria.requestFocus();

		} else if (tfDescricao.getText().equals("")) {

			JOptionPane.showMessageDialog(null, "Digite o nome do produto!");
			tfDescricao.requestFocus();

		} else if (String.valueOf(cbUnidadeMedida.getSelectedItem()).equalsIgnoreCase("Escolha...")) {

			JOptionPane.showMessageDialog(null, "Escolha a unidade de medida!");
			cbUnidadeMedida.requestFocus();

		} else if (tfLimiteEstoque.getText().equals("")) {

			JOptionPane.showMessageDialog(null, "Digite o estoque mínimo do produto!");
			tfLimiteEstoque.requestFocus();

		} else if (tfPrecoCusto.getText().equals("R$ 0,00")) {

			JOptionPane.showMessageDialog(null, "Digite o preço de custo!");
			tfPrecoCusto.requestFocus();

		} else if (tfPrecoVenda.getText().equals("R$ 0,00")) {

			JOptionPane.showMessageDialog(null, "Digite o preço de venda!");
			tfPrecoVenda.requestFocus();

		} else {

			boolean sucesso = false;

			String pC = tfPrecoCusto.getText().replace("R$ ", "");
			String pV = tfPrecoVenda.getText().replace("R$ ", "");

			double precoCusto = Double.valueOf(pC.replace(",", "."));
			double precoVenda = Double.valueOf(pV.replace(",", "."));

			ProdutoView.produtoTemp.setId_produto(IniciarAplicativoNEEstoque.produto.getId_produto());
			ProdutoView.produtoTemp.setCategoria(String.valueOf(cbCategoria.getSelectedItem()).toUpperCase());
			ProdutoView.produtoTemp.setCodigo(tfCodigo.getText().toUpperCase());
			ProdutoView.produtoTemp.setNomeDescricao(tfDescricao.getText().toUpperCase());
			ProdutoView.produtoTemp.setEstoqueAtual(IniciarAplicativoNEEstoque.produto.getEstoqueAtual());
			ProdutoView.produtoTemp.setValorUnitario(precoCusto);
			ProdutoView.produtoTemp.setValorVenda(precoVenda);
			ProdutoView.produtoTemp.setUnidadeMedida(String.valueOf(cbUnidadeMedida.getSelectedItem()).toUpperCase());
			ProdutoView.produtoTemp.setEstoqueMinimo(Integer.valueOf(tfLimiteEstoque.getText()));
			ProdutoView.produtoTemp.setObservacoes(tpObservacoes.getText().toUpperCase());
		
			if (IniciarAplicativoNEEstoque.produto.equals(ProdutoView.produtoTemp)) {

				JOptionPane.showMessageDialog(null, "Nenhum dado do produto foi editado!");

			} else {

				int estoqueAnterior = IniciarAplicativoNEEstoque.produto.getEstoqueAtual();

				ProdutoView.produtoTemp.setFoto(IniciarAplicativoNEEstoque.produto.getFoto());

				IniciarAplicativoNEEstoque.produto = ProdutoView.produtoTemp;

				ProdutoController produtoController = new ProdutoController();

				sucesso = produtoController.editarProduto(IniciarAplicativoNEEstoque.produto);

				if (sucesso == true) {

					int tipo = 2;
					int ajuste = 0;

					TransacaoController transacaoController = new TransacaoController();
					transacaoController.transacao(tipo, ajuste, estoqueAnterior);

					JOptionPane.showMessageDialog(null,
							"O produto " + tfDescricao.getText().toUpperCase() + " foi editado com sucesso!");

					dispose();

				} else {

					JOptionPane.showMessageDialog(null,
							"O produto " + tfDescricao.getText().toUpperCase() + "não  foi editado!");

					dispose();

				}

			}

		}

	}
}
