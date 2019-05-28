package br.com.ne.estoque.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;

import br.com.ne.estoque.controller.IniciarAplicativoEstoque;
import br.com.ne.estoque.controller.Produto;
import br.com.ne.estoque.controller.ProdutoController;
import br.com.ne.estoque.controller.Transacao;
import br.com.ne.estoque.controller.TransacaoController;
import br.com.ne.estoque.relatorio.RelatorioProduto;
import br.com.ne.estoque.relatorio.RelatorioTransacao;
import net.sf.jasperreports.engine.JRException;
import uteis.ConfTabProduto;
import uteis.ConfTabTransacao;
import uteis.NovaImagem;

public class ProdutoView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3920382491812473771L;
	private JPanel contentPane;
	private JTable tabProdutos;
	private JTextField tfNomeProduto;
	private JTable tabTransacao;
	private static List<Produto> listaProduto;
	private static List<Transacao> listaTransacao;
	public static List<RelatorioProduto> listaRelatorioProduto;
	public static List<RelatorioTransacao> listaRelatorioTransacao;
	private static String escolhaTempProduto;
	private static String escolhaTempTransacao;
	private JButton btnPesquisar;
	private JButton btnNovo;
	private JComboBox<String> cbExibirProdutos;
	private JComboBox<String> cbTransacao;
	public static Produto produtoTemp = new Produto();
	private JComboBox<String> cbCampoTransacao;
	private JTextField tfInformacaoPesquisar;
	private JButton btnPesquisaTransacao;
	private JButton btnImprimirTransacao;
	private JButton btnImpressaoProdutos;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					ProdutoView frame = new ProdutoView();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProdutoView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ProdutoView.class.getResource("/image/ne.png")));
		setTitle("Estoque NE");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 630);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Arial", Font.PLAIN, 11));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		listaRelatorioProduto = new ArrayList<RelatorioProduto>();
		listaRelatorioTransacao = new ArrayList<RelatorioTransacao>();

		JTabbedPane tpEstoque = new JTabbedPane(JTabbedPane.TOP);
		tpEstoque.setBounds(0, 0, 954, 550);
		contentPane.add(tpEstoque);

		JPanel pProdutos = new JPanel();
		pProdutos.addAncestorListener(new AncestorListener() {

			public void ancestorAdded(AncestorEvent event) {

			}

			public void ancestorMoved(AncestorEvent event) {
			}

			public void ancestorRemoved(AncestorEvent event) {

				DefaultTableModel model = (DefaultTableModel) tabProdutos.getModel();
				model.setNumRows(0);

				cbExibirProdutos.setSelectedIndex(0);
				tfNomeProduto.setText("");

			}
		});
		pProdutos.setFont(new Font("Arial", Font.PLAIN, 14));
		tpEstoque.addTab("Produtos", null, pProdutos, null);
		pProdutos.setLayout(null);

		JScrollPane spProdutos = new JScrollPane();
		spProdutos.setBorder(new LineBorder(Color.LIGHT_GRAY));
		spProdutos.setBounds(26, 126, 896, 396);
		pProdutos.add(spProdutos);

		tabProdutos = new JTable();
		tabProdutos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabProdutos.setBorder(new LineBorder(Color.LIGHT_GRAY));

		tabProdutos.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Id", "C\u00F3digo", "Categoria", "Nome do Produto ", "Estoque Dispon\u00EDvel",
						"Estoque M\u00EDnimo", "Custo Unit\u00E1rio", "Pre\u00E7o Venda", "Custo Total",
						"Total Venda" }));
		tabProdutos.getColumnModel().getColumn(0).setPreferredWidth(45);
		tabProdutos.getColumnModel().getColumn(2).setPreferredWidth(120);
		tabProdutos.getColumnModel().getColumn(3).setPreferredWidth(163);
		tabProdutos.getColumnModel().getColumn(4).setPreferredWidth(104);
		tabProdutos.getColumnModel().getColumn(5).setPreferredWidth(103);
		tabProdutos.getColumnModel().getColumn(6).setPreferredWidth(79);
		spProdutos.setViewportView(tabProdutos);

		tabProdutos.setDefaultRenderer(Object.class, new ConfTabProduto());

		JPanel panelProduto = new JPanel();
		panelProduto.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192)), "Produto",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelProduto.setBounds(26, 10, 344, 105);
		pProdutos.add(panelProduto);
		panelProduto.setLayout(null);

		tfNomeProduto = new JTextField();
		tfNomeProduto.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {

				getRootPane().setDefaultButton(btnPesquisar);

				cbExibirProdutos.setSelectedIndex(0);
			}

		});
		tfNomeProduto.setToolTipText("Digite o nome do produto");
		tfNomeProduto.setBounds(27, 61, 169, 25);
		panelProduto.add(tfNomeProduto);
		tfNomeProduto.setColumns(10);

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setToolTipText("Pesquisar produto");
		btnPesquisar.setBounds(206, 61, 111, 25);
		panelProduto.add(btnPesquisar);
		btnPesquisar.setHorizontalAlignment(SwingConstants.LEFT);
		btnPesquisar.setIcon(new ImageIcon(ProdutoView.class.getResource("/image/pesquisar.png")));

		JButton btnEditar = new JButton("Editar");
		btnEditar.setToolTipText("Editar produto");
		btnEditar.setBounds(127, 24, 90, 25);
		panelProduto.add(btnEditar);
		btnEditar.setHorizontalAlignment(SwingConstants.LEFT);
		btnEditar.setIcon(new ImageIcon(ProdutoView.class.getResource("/image/editar.png")));

		btnNovo = new JButton("Novo");
		btnNovo.setToolTipText("Novo produto");
		btnNovo.setBounds(27, 24, 90, 25);
		panelProduto.add(btnNovo);
		btnNovo.setHorizontalAlignment(SwingConstants.LEFT);
		btnNovo.setIcon(new ImageIcon(ProdutoView.class.getResource("/image/novo.png")));
		getRootPane().setDefaultButton(btnNovo);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setToolTipText("Excluir produto");
		btnExcluir.setBounds(227, 25, 90, 23);
		panelProduto.add(btnExcluir);
		btnExcluir.setHorizontalAlignment(SwingConstants.LEFT);
		btnExcluir.setIcon(new ImageIcon(ProdutoView.class.getResource("/image/lixo.png")));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tabProdutos.getRowCount() == 0) {

					JOptionPane.showMessageDialog(null, "Não há uma planilha aberta!");

				} else {

					int posicao = tabProdutos.getSelectedRow();

					if (posicao == -1) {

						JOptionPane.showMessageDialog(null, "Selecione um produto!");

					} else {

						String produto = listaProduto.get(posicao).getNomeDescricao();

						int resposta = JOptionPane.showConfirmDialog(null,
								"Deseja realmente excluir o produto " + produto + " ?", "Excluir Produto",
								JOptionPane.OK_OPTION);

						if (resposta == JOptionPane.OK_OPTION) {

							int estoqueAnterior = IniciarAplicativoEstoque.produto.getEstoqueAtual();

							boolean sucesso = false;

							int id_produto = listaProduto.get(posicao).getId_produto();

							ProdutoController produtoController = new ProdutoController();
							IniciarAplicativoEstoque.produto = produtoController.buscarProduto(id_produto);

							IniciarAplicativoEstoque.produto.setCodigo("EXCLUÍDO");
							IniciarAplicativoEstoque.produto.setEstoqueAtual(0);
							IniciarAplicativoEstoque.produto.setEstoqueMinimo(0);
							IniciarAplicativoEstoque.produto.setValorUnitario(0);
							IniciarAplicativoEstoque.produto.setValorVenda(0);

							sucesso = produtoController.editarProduto(IniciarAplicativoEstoque.produto);

							if (sucesso == true) {

								int tipo = 3;
								int ajuste = 0;

								TransacaoController transacaoController = new TransacaoController();
								transacaoController.transacao(tipo, ajuste, estoqueAnterior);

								JOptionPane.showMessageDialog(null, "O produto " + produto + " foi excluído!");

								atualizarPlanilha();

							} else {

								JOptionPane.showMessageDialog(null, "O produto " + produto + " não foi excluído!");

							}

						}

					}
				}

			}
		});
		btnExcluir.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		btnNovo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				try {

					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					CadastroProdutoView cadastroProduto = new CadastroProdutoView();

					cadastroProduto.setNovo(true);

					cadastroProduto.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					cadastroProduto.setLocationRelativeTo(null);

					apagarTabelaProduto();

					cadastroProduto.setVisible(true);

				} catch (Exception e) {

					e.printStackTrace();

				}

				cbExibirProdutos.setSelectedIndex(0);
			}
		});
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tabProdutos.getRowCount() == 0) {

					JOptionPane.showMessageDialog(null, "Não há uma planilha aberta!");

				} else {

					int posicao = tabProdutos.getSelectedRow();

					if (posicao == -1) {

						JOptionPane.showMessageDialog(null, "Selecione um produto!");

					} else {

						cbExibirProdutos.setSelectedIndex(0);

						try {

							int id_produto = listaProduto.get(posicao).getId_produto();

							ProdutoController produtoController = new ProdutoController();
							IniciarAplicativoEstoque.produto = produtoController.buscarProduto(id_produto);

							CadastroProdutoView cadastroProduto = new CadastroProdutoView();

							cadastroProduto.getLblLblestoqueatual().setVisible(false);
							cadastroProduto.getTfEstoqueAtual().setVisible(false);

							cadastroProduto.getCbCategoria()
									.setSelectedItem(IniciarAplicativoEstoque.produto.getCategoria());
							cadastroProduto.getTfCodigo()
									.setText(IniciarAplicativoEstoque.produto.getCodigo().toUpperCase());
							cadastroProduto.getTfDescricao()
									.setText(IniciarAplicativoEstoque.produto.getNomeDescricao());
							cadastroProduto.getTfPrecoCusto().setText(
									String.valueOf(IniciarAplicativoEstoque.produto.getValorUnitario()) + "0");
							cadastroProduto.getTfPrecoVenda()
									.setText(String.valueOf(IniciarAplicativoEstoque.produto.getValorVenda()) + "0");
							cadastroProduto.getCbUnidadeMedida()
									.setSelectedItem(IniciarAplicativoEstoque.produto.getUnidadeMedida());
							cadastroProduto.getTfLimiteEstoque()
									.setText(String.valueOf(IniciarAplicativoEstoque.produto.getEstoqueMinimo()));
							cadastroProduto.getTpObservacoes()
									.setText(IniciarAplicativoEstoque.produto.getObservacoes());

							cadastroProduto.setNovo(false);

							if (IniciarAplicativoEstoque.produto.getFoto() != null) {

								ImageIcon iconFrente = new ImageIcon(IniciarAplicativoEstoque.produto.getFoto());

								NovaImagem novaImagem = new NovaImagem();

								try {

									novaImagem = novaImagem.redimensionaImagem(iconFrente,
											cadastroProduto.getLblClickImagem());

								} catch (IOException e1) {

									e1.printStackTrace();
								}

								cadastroProduto.getLblClickImagem().setText("");

								cadastroProduto.getLblClickImagem().setIcon(
										new ImageIcon(iconFrente.getImage().getScaledInstance(novaImagem.getLargura(),
												novaImagem.getAltura(), Image.SCALE_FAST)));

								produtoTemp.setFoto(IniciarAplicativoEstoque.produto.getFoto());

							}

							cadastroProduto.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							cadastroProduto.setLocationRelativeTo(null);

							apagarTabelaProduto();

							cadastroProduto.setVisible(true);

							cadastroProduto.getLblLblestoqueatual().setVisible(true);
							cadastroProduto.getTfEstoqueAtual().setVisible(true);

						} catch (Exception e1) {

							e1.printStackTrace();

						}

					}

				}

			}
		});
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tfNomeProduto.getText().equals("")) {

					JOptionPane.showMessageDialog(null, "Digite o nome do produto!");

				} else {

					tfNomeProduto.getText().toUpperCase();

					ProdutoController produtoController = new ProdutoController();
					setListaProduto(produtoController.pesquisar(tfNomeProduto.getText().toUpperCase()));
					
					if(listaProduto.isEmpty()){
						
						int resposta = JOptionPane.showConfirmDialog(null, "O produto não foi encontrado\ndeseja cadastrá-lo agora?", "Produto não Encontrado",
								JOptionPane.OK_OPTION);

						if (resposta == JOptionPane.OK_OPTION) {

							try {
								UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
								CadastroRapidoProdutoView dialog = new CadastroRapidoProdutoView();
								dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								dialog.setLocationRelativeTo(null);
								dialog.setVisible(true);
							} catch (Exception e1) {
								e1.printStackTrace();
							}

						}
						
					tfNomeProduto.setText("");
					
					
						
					} else {
					
					listarTodosOsProdutos(listaProduto);

					tfNomeProduto.setText("");
					
					}

				}

			}
		});

		JPanel panelEstoque = new JPanel();
		panelEstoque.setLayout(null);
		panelEstoque.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192)), "Estoque dos Produtos",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelEstoque.setBounds(396, 10, 209, 105);
		pProdutos.add(panelEstoque);

		JButton btnEntradaDeEstoque = new JButton("Entrada e Sa\u00EDda");
		btnEntradaDeEstoque.setToolTipText("Entrada e sa\u00EDda de estoque");
		btnEntradaDeEstoque.setIcon(new ImageIcon(ProdutoView.class.getResource("/image/entradaProduto.png")));
		btnEntradaDeEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tabProdutos.getRowCount() == 0) {

					JOptionPane.showMessageDialog(null, "Não há uma planilha aberta!");

				} else {

					int posicao = tabProdutos.getSelectedRow();

					if (posicao == -1) {

						JOptionPane.showMessageDialog(null, "Selecione um produto!");

					} else {

						try {

							int id_produto = listaProduto.get(posicao).getId_produto();

							ProdutoController produtoController = new ProdutoController();
							IniciarAplicativoEstoque.produto = produtoController.buscarProduto(id_produto);

							UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
							EntradaSaidaProdutoView entradaSaidaProdutoView = new EntradaSaidaProdutoView();
							entradaSaidaProdutoView.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

							String situacao = "";

							entradaSaidaProdutoView.getTfNomeDescricao()
									.setText(IniciarAplicativoEstoque.produto.getNomeDescricao());
							entradaSaidaProdutoView.getTfEstoqueAtual()
									.setText(String.valueOf(IniciarAplicativoEstoque.produto.getEstoqueAtual()) + " "
											+ IniciarAplicativoEstoque.produto.getUnidadeMedida());

							if (IniciarAplicativoEstoque.produto
									.getEstoqueAtual() <= IniciarAplicativoEstoque.produto.getEstoqueMinimo()) {

								situacao = "ESTOQUE BAIXO";
								entradaSaidaProdutoView.getTfSituacaoAtual().setForeground(Color.RED);

							} else if (IniciarAplicativoEstoque.produto
									.getEstoqueAtual() > IniciarAplicativoEstoque.produto.getEstoqueMinimo()
									&& IniciarAplicativoEstoque.produto.getEstoqueAtual() < 2
											* IniciarAplicativoEstoque.produto.getEstoqueMinimo()) {

								situacao = "ATENÇÃO NO ESTOQUE";
								entradaSaidaProdutoView.getTfSituacaoAtual().setForeground(Color.ORANGE);

							} else {

								situacao = "ESTOQUE ALTO";
								entradaSaidaProdutoView.getTfSituacaoAtual().setForeground(Color.BLUE);

							}

							entradaSaidaProdutoView.getTfSituacaoAtual().setText(situacao);
							entradaSaidaProdutoView.getTpObservacao()
									.setText(IniciarAplicativoEstoque.produto.getObservacoes());

							if (IniciarAplicativoEstoque.produto.getFoto() != null) {

								ImageIcon iconFrente = new ImageIcon(IniciarAplicativoEstoque.produto.getFoto());

								NovaImagem novaImagem = new NovaImagem();

								try {

									novaImagem = novaImagem.redimensionaImagem(iconFrente,
											entradaSaidaProdutoView.getLblClickImagem());

								} catch (IOException e1) {

									e1.printStackTrace();

								}

								if (novaImagem.getAltura() == 0 || novaImagem.getLargura() == 0) {

									JOptionPane.showMessageDialog(null, "Falha ao tentar abrir imagem!");

								} else if (novaImagem.getAltura() == -1 || novaImagem.getLargura() == -1) {

									JOptionPane.showMessageDialog(null, "Selecione um arquivo jpg ou png!");

								} else {

									entradaSaidaProdutoView.getLblClickImagem().setText("");

									entradaSaidaProdutoView.getLblClickImagem()
											.setIcon(new ImageIcon(
													iconFrente.getImage().getScaledInstance(novaImagem.getLargura(),
															novaImagem.getAltura(), Image.SCALE_FAST)));

								}

								entradaSaidaProdutoView.getLblClickImagem().setText("");

								entradaSaidaProdutoView.getLblClickImagem().setIcon(
										new ImageIcon(iconFrente.getImage().getScaledInstance(novaImagem.getLargura(),
												novaImagem.getAltura(), Image.SCALE_FAST)));

							}

							apagarTabelaProduto();

							entradaSaidaProdutoView.setLocationRelativeTo(null);
							entradaSaidaProdutoView.setVisible(true);

						} catch (Exception e1) {

							e1.printStackTrace();

						}

					}

				}
				cbExibirProdutos.setSelectedIndex(0);

			}
		});
		btnEntradaDeEstoque.setBounds(28, 40, 153, 25);
		panelEstoque.add(btnEntradaDeEstoque);

		JPanel panelExibir = new JPanel();
		panelExibir.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192)), "Exibir Produtos",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelExibir.setBounds(631, 10, 291, 105);
		pProdutos.add(panelExibir);
		panelExibir.setLayout(null);

		cbExibirProdutos = new JComboBox<String>();
		cbExibirProdutos.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

				tfNomeProduto.setText("");

				getRootPane().setDefaultButton(btnImpressaoProdutos);

			}
		});
		cbExibirProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				escolhaTempProduto = "";
				String escolha = String.valueOf(cbExibirProdutos.getSelectedItem());
				escolhaTempProduto = escolha;

				if (escolha.equalsIgnoreCase("ESCOLHA...")) {

					apagarTabelaProduto();

				} else if (escolha.equalsIgnoreCase("PRODUTOS COM ESTOQUE BAIXO")) {

					ProdutoController produtoController = new ProdutoController();
					setListaProduto(produtoController.estoqueBaixo());

					listarTodosOsProdutos(listaProduto);

				} else {

					ProdutoController produtoController = new ProdutoController();
					setListaProduto(produtoController.Escolha(escolha));

					listarTodosOsProdutos(listaProduto);
				}

			}
		});
		cbExibirProdutos.setToolTipText("Escolha a exibi\u00E7\u00E3o");
		cbExibirProdutos.setBounds(45, 25, 201, 20);
		panelExibir.add(cbExibirProdutos);
		cbExibirProdutos.setModel(new DefaultComboBoxModel<String>(
				new String[] { "ESCOLHA...", "TODOS OS PRODUTOS", "PRODUTOS COM ESTOQUE BAIXO", "CRACH\u00C1S",
						"CART\u00D5ES", "COMERCIAL", "ESCRIT\u00D3RIO", "IMPRESS\u00C3O", "SUPORTE T\u00C9CNICO" }));

		btnImpressaoProdutos = new JButton("Imprimir");
		btnImpressaoProdutos.setToolTipText("Imprimir relat\u00F3rio de produtos");
		btnImpressaoProdutos.setIcon(new ImageIcon(ProdutoView.class.getResource("/image/imprimir.png")));
		btnImpressaoProdutos.setHorizontalAlignment(SwingConstants.LEFT);
		btnImpressaoProdutos.setBounds(98, 61, 95, 23);
		panelExibir.add(btnImpressaoProdutos);
		btnImpressaoProdutos.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				if (tabProdutos.getRowCount() == 0) {

					JOptionPane.showMessageDialog(null, "Não há uma planilha aberta!");

				} else {

					String tipo = String.valueOf(cbExibirProdutos.getSelectedItem());

					if (tipo.equalsIgnoreCase("ESCOLHA...") && !tfNomeProduto.equals("")) {

						tipo = "PESQUISA DO OPERADOR";
					}

					String situacao = "";

					for (Produto pr : listaProduto) {

						RelatorioProduto relatorioProduto = new RelatorioProduto();

						if (pr.getEstoqueAtual() <= pr.getEstoqueMinimo()) {

							situacao = "ESTOQUE BAIXO";

						} else if (pr.getEstoqueAtual() >= pr.getEstoqueMinimo()
								&& pr.getEstoqueAtual() <= 2 * pr.getEstoqueMinimo()) {

							situacao = "ESTOQUE MÉDIO";

						} else {

							situacao = "ESTOQUE ALTO";

						}

						relatorioProduto.setTipo(tipo);
						relatorioProduto.setCodigo(pr.getCodigo());
						relatorioProduto.setNome(pr.getNomeDescricao());
						relatorioProduto.setCategoria(pr.getCategoria());
						relatorioProduto.setEstoqueAtual(
								String.valueOf(pr.getEstoqueAtual()) + " " + pr.getUnidadeMedida().toLowerCase());
						relatorioProduto.setSituacao(situacao);

						listaRelatorioProduto.add(relatorioProduto);

					}

					try {

						RelatorioProduto relatorioProduto = new RelatorioProduto();

						relatorioProduto.gerarRelatorioProduto(listaRelatorioProduto);

						listaRelatorioProduto.clear();

					} catch (JRException e) {

						e.printStackTrace();
					}
				}
			}
		});

		JPanel pTransacoesEstoque = new JPanel();
		pTransacoesEstoque.addAncestorListener(new AncestorListener() {

			public void ancestorAdded(AncestorEvent event) {

			}

			public void ancestorMoved(AncestorEvent event) {
			}

			public void ancestorRemoved(AncestorEvent event) {

				DefaultTableModel model = (DefaultTableModel) tabTransacao.getModel();
				model.setNumRows(0);

				cbCampoTransacao.setSelectedIndex(0);
				cbTransacao.setSelectedIndex(0);

			}

		});
		tpEstoque.addTab("Transa\u00E7\u00F5es Estoque e Cadastro de Operadores", null, pTransacoesEstoque, null);
		pTransacoesEstoque.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 126, 896, 396);
		pTransacoesEstoque.add(scrollPane);

		tabTransacao = new JTable();
		tabTransacao.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabTransacao.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Id", "Modalidade", "C\u00F3digo", "Categoria", "Nome do Produto", "Estoque Anterior",
						"Ajuste", "Estoque Atual", "Valor Unit\u00E1rio", "Valor Venda", "Custo Total", "Total Venda",
						"Colaborador", "Data", "Hora" }));
		tabTransacao.getColumnModel().getColumn(0).setPreferredWidth(40);
		tabTransacao.getColumnModel().getColumn(1).setPreferredWidth(150);
		tabTransacao.getColumnModel().getColumn(3).setPreferredWidth(150);
		tabTransacao.getColumnModel().getColumn(4).setPreferredWidth(150);
		tabTransacao.getColumnModel().getColumn(5).setPreferredWidth(100);
		tabTransacao.getColumnModel().getColumn(7).setPreferredWidth(120);
		tabTransacao.getColumnModel().getColumn(12).setPreferredWidth(150);
		scrollPane.setViewportView(tabTransacao);

		tabTransacao.setDefaultRenderer(Object.class, new ConfTabTransacao());

		JPanel panelTransacao = new JPanel();
		panelTransacao.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192)),
				"Pesquisar Transacao do Estoque", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelTransacao.setBounds(184, 10, 425, 104);
		pTransacoesEstoque.add(panelTransacao);
		panelTransacao.setLayout(null);

		btnPesquisaTransacao = new JButton("Pesquisar");
		btnPesquisaTransacao.setToolTipText("Pesquisar transa\u00E7\u00E3o do estoque");
		btnPesquisaTransacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cbCampoTransacao.getSelectedItem().equals("ESCOLHA...")) {

					JOptionPane.showMessageDialog(null, "Selecione o campo da pesquisa!");

				} else if (tfInformacaoPesquisar.getText().equals("")) {

					JOptionPane.showMessageDialog(null, "Digite a informação para pesquisar!");

				} else {

					List<Transacao> listaTransacao = new ArrayList<Transacao>();
					TransacaoController transacaoController = new TransacaoController();
					listaTransacao = transacaoController.pesquisarCampos(
							String.valueOf(cbCampoTransacao.getSelectedItem()), tfInformacaoPesquisar.getText());

					listarTransacoes(listaTransacao);

					tfInformacaoPesquisar.setText("");

				}

			}
		});
		btnPesquisaTransacao.setIcon(new ImageIcon(ProdutoView.class.getResource("/image/pesquisar.png")));
		btnPesquisaTransacao.setBounds(287, 67, 113, 23);
		panelTransacao.add(btnPesquisaTransacao);

		cbCampoTransacao = new JComboBox<String>();
		cbCampoTransacao.setToolTipText("Campos para pesquisar");
		cbCampoTransacao.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

				cbExibirProdutos.setSelectedItem(0);
				apagarTabelaTransacao();

			}
		});
		cbCampoTransacao.setModel(new DefaultComboBoxModel<String>(
				new String[] { "ESCOLHA...", "C\u00D3DIGO", "PRODUTO", "CATEGORIA", "COLABORADOR" }));
		cbCampoTransacao.setBounds(20, 36, 160, 20);
		panelTransacao.add(cbCampoTransacao);

		JLabel lblInformacaoParaPesquisar = new JLabel("Informa\u00E7\u00E3o para pesquisar");
		lblInformacaoParaPesquisar.setBounds(201, 22, 199, 14);
		panelTransacao.add(lblInformacaoParaPesquisar);

		JLabel lblCamposPesquisa = new JLabel("Campos de Pesquisa");
		lblCamposPesquisa.setBounds(20, 22, 160, 14);
		panelTransacao.add(lblCamposPesquisa);

		tfInformacaoPesquisar = new JTextField();
		tfInformacaoPesquisar.setToolTipText("Digite a informa\u00E7\u00E3o aqui");
		tfInformacaoPesquisar.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

				cbTransacao.setSelectedIndex(0);

				getRootPane().setDefaultButton(btnPesquisaTransacao);

			}
		});
		tfInformacaoPesquisar.setBounds(200, 36, 200, 20);
		panelTransacao.add(tfInformacaoPesquisar);
		tfInformacaoPesquisar.setColumns(10);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(
				new TitledBorder(new LineBorder(new Color(192, 192, 192)), "Exibir Transa\u00E7\u00E3o do Estoque",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(631, 10, 291, 105);
		pTransacoesEstoque.add(panel);

		cbTransacao = new JComboBox<String>();
		cbTransacao.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

				tfInformacaoPesquisar.setText("");
				cbCampoTransacao.setSelectedIndex(0);
				apagarTabelaTransacao();

				getRootPane().setDefaultButton(btnImprimirTransacao);

			}
		});
		cbTransacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				setEscolhaTempTransacao("");
				String escolha = String.valueOf(cbTransacao.getSelectedItem());
				setEscolhaTempTransacao(escolha);

				if (escolha.equalsIgnoreCase("ESCOLHA...")) {

					apagarTabelaTransacao();

				} else if (escolha.equalsIgnoreCase("TODAS TRANSAÇÕES")) {

					TransacaoController transacaoController = new TransacaoController();

					listaTransacao = transacaoController.todasTransacoes();
					listarTransacoes(listaTransacao);

				} else if (escolha.equalsIgnoreCase("CRACHÁS")) {

					String tipoTransacao = String.valueOf(cbTransacao.getSelectedItem());

					TransacaoController transacaoController = new TransacaoController();
					listaTransacao = transacaoController.umaTrancacao(tipoTransacao);

					listarTransacoes(listaTransacao);

				} else if (escolha.equalsIgnoreCase("CARTÕES")) {

					String tipoTransacao = String.valueOf(cbTransacao.getSelectedItem());

					TransacaoController transacaoController = new TransacaoController();
					listaTransacao = transacaoController.umaTrancacao(tipoTransacao);

					listarTransacoes(listaTransacao);

				} else if (escolha.equalsIgnoreCase("COMERCIAL")) {

					String tipoTransacao = String.valueOf(cbTransacao.getSelectedItem());

					TransacaoController transacaoController = new TransacaoController();
					listaTransacao = transacaoController.umaTrancacao(tipoTransacao);

					listarTransacoes(listaTransacao);

				} else if (escolha.equalsIgnoreCase("ESCRITÓRIO")) {

					String tipoTransacao = String.valueOf(cbTransacao.getSelectedItem());

					TransacaoController transacaoController = new TransacaoController();
					listaTransacao = transacaoController.umaTrancacao(tipoTransacao);

					listarTransacoes(listaTransacao);

				} else if (escolha.equalsIgnoreCase("IMPRESSÃO")) {

					String tipoTransacao = String.valueOf(cbTransacao.getSelectedItem());

					TransacaoController transacaoController = new TransacaoController();
					listaTransacao = transacaoController.umaTrancacao(tipoTransacao);

					listarTransacoes(listaTransacao);

				} else if (escolha.equalsIgnoreCase("SUPORTE TÉCNICO")) {

					String tipoTransacao = String.valueOf(cbTransacao.getSelectedItem());

					TransacaoController transacaoController = new TransacaoController();
					listaTransacao = transacaoController.umaTrancacao(tipoTransacao);

					listarTransacoes(listaTransacao);

				}

			}
		});
		cbTransacao.setModel(new DefaultComboBoxModel<String>(
				new String[] { "ESCOLHA...", "TODAS TRANSA\u00C7\u00D5ES", "CRACH\u00C1S", "CART\u00D5ES", "COMERCIAL",
						"ESCRIT\u00D3RIO", "IMPRESS\u00C3O", "SUPORTE T\u00C9CNICO" }));
		cbTransacao.setToolTipText("Escolha a exibi\u00E7\u00E3o");
		cbTransacao.setBounds(45, 25, 201, 20);
		panel.add(cbTransacao);

		btnImprimirTransacao = new JButton("Imprimir");
		btnImprimirTransacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tabTransacao.getRowCount() == 0) {

					JOptionPane.showMessageDialog(null, "Não há uma planilha aberta!");

				} else {

					String tipo = String.valueOf(cbTransacao.getSelectedItem());

					if (tipo.equals("ESCOLHA...")) {

						tipo = "PESQUISA DO OPERADOR";

					}

					String modalidade = "";

					for (Transacao tr : listaTransacao) {

						RelatorioTransacao relatorioTransacao = new RelatorioTransacao();

						if (tr.getTipo() == 1) {

							modalidade = "NOVO PRODUTO";

						} else if (tr.getTipo() == 2) {

							modalidade = "EDITAR PRODUTO";

						} else if (tr.getTipo() == 3) {

							modalidade = "EXCLUIR PRODUTO";

						} else if (tr.getTipo() == 4) {

							modalidade = "ENTRADA DE ESTOQUE";

						} else if (tr.getTipo() == 5) {

							modalidade = "SAÍDA DE ESTOQUE";

						} else if (tr.getTipo() == 6) {

							modalidade = "DEVOLUÇÃO DE PRODUTO";

						} else if (tr.getTipo() == 7) {

							modalidade = "PERDAS EM GERAL";

						}

						/*
						 * if (cbCampoTransacao.getSelectedItem().equals(
						 * "ESCOLHA...")) {
						 * 
						 * //tipo = "PESQUISA DO OPERADOR";
						 * 
						 * JOptionPane.showMessageDialog(null, tipo); }
						 */

						relatorioTransacao.setModalidade(modalidade.toUpperCase());
						relatorioTransacao.setCodigo(tr.getProduto().getCodigo());
						relatorioTransacao.setCategoria(tr.getProduto().getCategoria().toUpperCase());
						relatorioTransacao.setNome(tr.getProduto().getNomeDescricao().toUpperCase());
						relatorioTransacao.setEstoqueAnterior(
								tr.getEstoqueAnterior() + " " + tr.getProduto().getUnidadeMedida().toLowerCase());
						relatorioTransacao.setAjuste(String.valueOf(tr.getAjuste()));
						relatorioTransacao.setEstoqueAtual(
								tr.getEstoqueAtual() + " " + tr.getProduto().getUnidadeMedida().toLowerCase());
						relatorioTransacao.setColaborador(tr.getOperador().getNome().toUpperCase());
						relatorioTransacao.setTipo(tipo.toUpperCase());

						listaRelatorioTransacao.add(relatorioTransacao);

					}

					try {

						RelatorioTransacao relatorioTransacao = new RelatorioTransacao();

						relatorioTransacao.gerarRelatorioTransacao(listaRelatorioTransacao);

						listaRelatorioTransacao.clear();

					} catch (JRException e1) {

						e1.printStackTrace();
					}

				}

			}
		});
		btnImprimirTransacao.setIcon(new ImageIcon(ProdutoView.class.getResource("/image/imprimir.png")));
		btnImprimirTransacao.setToolTipText("Imprimir relat\u00F3rio da transa\u00E7\u00E3o");
		btnImprimirTransacao.setHorizontalAlignment(SwingConstants.LEFT);
		btnImprimirTransacao.setBounds(98, 61, 95, 23);
		panel.add(btnImprimirTransacao);

		JPanel pOperador = new JPanel();
		pOperador.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192)), "Cadastro de Operadores",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pOperador.setBounds(26, 11, 137, 104);
		pTransacoesEstoque.add(pOperador);
		pOperador.setLayout(null);

		JButton btnUsuario = new JButton("");
		btnUsuario.setRolloverIcon(new ImageIcon(ProdutoView.class.getResource("/image/cadastro_usuario_2.png")));
		btnUsuario.setFocusable(false);
		btnUsuario.setContentAreaFilled(false);
		btnUsuario.setToolTipText("Cadastrar, editar ou excluir operadores");
		btnUsuario.setBounds(28, 7, 81, 90);
		pOperador.add(btnUsuario);
		btnUsuario.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnUsuario.setHorizontalTextPosition(SwingConstants.CENTER);
		btnUsuario.setIcon(new ImageIcon(ProdutoView.class.getResource("/image/cadastro_usuario_5.png")));
		btnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int resposta = JOptionPane.showConfirmDialog(null, "Deseja cadastrar, editar ou excluir um operador?",
						"Cadastro de Operador", JOptionPane.OK_OPTION);

				if (resposta == JOptionPane.OK_OPTION) {

					cbCampoTransacao.setSelectedIndex(0);
					cbExibirProdutos.setSelectedIndex(0);
					tfInformacaoPesquisar.setText("");

					apagarTabelaTransacao();

					try {

						CadastroUsuarioView dialog = new CadastroUsuarioView();
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setLocationRelativeTo(null);
						dialog.setVisible(true);

					} catch (Exception e) {

						e.printStackTrace();

					}

				}

			}
		});

		JButton btnNewButton = new JButton("Fechar");
		btnNewButton.setToolTipText("Fechar o programa");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int resposta = JOptionPane.showConfirmDialog(null, "Deseja fechar o programa?", "Fechar Programa",
						JOptionPane.OK_OPTION);

				if (resposta == JOptionPane.OK_OPTION) {

					System.exit(0);

				}

			}
		});
		btnNewButton.setIcon(new ImageIcon(ProdutoView.class.getResource("/image/cancelar.png")));
		btnNewButton.setBounds(818, 561, 105, 23);
		contentPane.add(btnNewButton);

		JLabel lblLogado = new JLabel("LOGADO: " + IniciarAplicativoEstoque.operador.getNome().toUpperCase());
		lblLogado.setBounds(30, 561, 431, 18);
		contentPane.add(lblLogado);

		JButton button = new JButton("Logout");
		button.setIcon(new ImageIcon(ProdutoView.class.getResource("/image/exit.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int resposta = JOptionPane.showConfirmDialog(null, "Deseja fazer o Logout?", "Logout",
						JOptionPane.OK_CANCEL_OPTION);

				if (resposta == JOptionPane.OK_OPTION) {

					IniciarAplicativoEstoque.operador.setId_operador(0);
					IniciarAplicativoEstoque.operador.setNome("");
					IniciarAplicativoEstoque.operador.setLogin("");
					IniciarAplicativoEstoque.operador.setSenha("");

					dispose();

					try {

						UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
						TelaLogin frame = new TelaLogin();
						frame.setVisible(true);
						frame.setLocationRelativeTo(null);

					} catch (Exception e1) {

						e1.printStackTrace();

					}

				}

			}
		});
		button.setToolTipText("Fazer Logout");
		button.setBounds(703, 561, 105, 23);
		contentPane.add(button);

	}

	@SuppressWarnings("unused")
	private static void addPopup(Component component, final JPopupMenu popup) {
	}

	public void listarTodosOsProdutos(List<Produto> listaProduto) {

		int id = 1;

		DefaultTableModel model = (DefaultTableModel) tabProdutos.getModel();

		model.setNumRows(0);

		if (listaProduto.isEmpty()) {

			JOptionPane.showMessageDialog(null, "Não existem itens para listar!");

			cbExibirProdutos.setSelectedIndex(0);

		} else

			for (Produto p : listaProduto) {

				DecimalFormat id_prod = new DecimalFormat("0000");
				String idProduto = id_prod.format(id);

				DecimalFormat PrecoVenda = new DecimalFormat("R$ 0.00");
				String PVenda = PrecoVenda.format(p.getValorVenda());

				DecimalFormat PrecoUnitario = new DecimalFormat("R$ 0.00");
				String PUnitario = PrecoUnitario.format(p.getValorUnitario());

				DecimalFormat VTotal = new DecimalFormat("R$ 0.00");
				String Total = VTotal.format(p.getEstoqueAtual() * p.getValorUnitario());

				DecimalFormat VendaTotal = new DecimalFormat("R$ 0.00");
				String TValor = VendaTotal.format(p.getEstoqueAtual() * p.getValorVenda());

				Object[] linha = { idProduto, p.getCodigo().toUpperCase(), p.getCategoria().toUpperCase(),
						p.getNomeDescricao().toUpperCase(),
						p.getEstoqueAtual() + " " + p.getUnidadeMedida().toLowerCase(),
						p.getEstoqueMinimo() + " " + p.getUnidadeMedida().toLowerCase(), PUnitario, PVenda, Total,
						TValor };

				model.addRow(linha);

				id++;

			}

	}

	public void listarTransacoes(List<Transacao> listaTransacao) {

		int id = 1;

		DateFormat formatDatatBR = new SimpleDateFormat("dd-MM-YYYY");
		DateFormat formatHoraBR = new SimpleDateFormat("hh:mm:ss");

		DefaultTableModel model = (DefaultTableModel) tabTransacao.getModel();

		model.setNumRows(0);

		if (listaTransacao.isEmpty()) {

			JOptionPane.showMessageDialog(null, "Não existem itens para listar!");

			cbTransacao.setSelectedIndex(0);

		} else

			for (Transacao t : listaTransacao) {

				DecimalFormat PrecoVenda = new DecimalFormat("R$ 0.00");
				String PVenda = PrecoVenda.format(t.getValorVenda());

				DecimalFormat PrecoUnitario = new DecimalFormat("R$ 0.00");
				String PUnitario = PrecoUnitario.format(t.getValorUnitario());

				DecimalFormat VTotal = new DecimalFormat("R$ 0.00");
				String Total = VTotal.format(t.getEstoqueAtual() * t.getValorUnitario());

				DecimalFormat VendaTotal = new DecimalFormat("R$ 0.00");
				String TValor = VendaTotal.format(t.getEstoqueAtual() * t.getValorVenda());

				String tipoTransacao = "";
				String ajuste = "";

				if (t.getTipo() == 1) {

					tipoTransacao = "NOVO PRODUTO";
					ajuste = String.valueOf(t.getAjuste());

				} else if (t.getTipo() == 2) {

					tipoTransacao = "EDITAR PRODUTO";
					ajuste = String.valueOf(t.getAjuste());

				} else if (t.getTipo() == 3) {

					tipoTransacao = "EXCLUIR PRODUTO";
					ajuste = String.valueOf(t.getAjuste());

				} else if (t.getTipo() == 4) {

					tipoTransacao = "ENTRADA DE ESTOQUE";
					ajuste = "+ " + t.getAjuste();

				} else if (t.getTipo() == 5) {

					tipoTransacao = "SAÍDA DE ESTOQUE";
					ajuste = "- " + t.getAjuste();

				} else if (t.getTipo() == 6) {

					tipoTransacao = "DEVOLUÇÃO DE PRODUTO";
					ajuste = "+ " + t.getAjuste();

				} else if (t.getTipo() == 7) {

					tipoTransacao = "PERDAS EM GERAL";
					ajuste = "- " + t.getAjuste();

				}

				DecimalFormat id_prod = new DecimalFormat("0000");
				String idTransacao = id_prod.format(id);
				String dataBr = formatDatatBR.format(t.getDataTransacao());
				String horaBr = formatHoraBR.format(t.getHoraTransacao());

				Object[] linha = { idTransacao, tipoTransacao, t.getProduto().getCodigo(),
						t.getProduto().getCategoria(), t.getProduto().getNomeDescricao(),
						t.getEstoqueAnterior() + " " + t.getProduto().getUnidadeMedida().toLowerCase(), ajuste,
						t.getEstoqueAtual() + " " + t.getProduto().getUnidadeMedida().toLowerCase(), PUnitario, PVenda,
						Total, TValor, t.getOperador().getNome().toUpperCase(), dataBr, horaBr };

				model.addRow(linha);

				id++;

			}

	}

	public void atualizarPlanilha() {

		if (escolhaTempProduto.equalsIgnoreCase("PRODUTOS COM ESTOQUE BAIXO")) {

			ProdutoController produtoController = new ProdutoController();
			setListaProduto(produtoController.estoqueBaixo());

			listarTodosOsProdutos(listaProduto);

		} else {

			ProdutoController produtoController = new ProdutoController();
			setListaProduto(produtoController.Escolha(escolhaTempProduto));

			listarTodosOsProdutos(listaProduto);
		}

	}

	public void apagarTabelaProduto() {

		DefaultTableModel model = (DefaultTableModel) tabProdutos.getModel();

		model.setNumRows(0);
	}

	public void apagarTabelaTransacao() {

		DefaultTableModel model = (DefaultTableModel) tabTransacao.getModel();

		model.setNumRows(0);
	}

	public static String getEscolhaTempTransacao() {
		return escolhaTempTransacao;
	}

	public static void setEscolhaTempTransacao(String escolhaTempTransacao) {
		ProdutoView.escolhaTempTransacao = escolhaTempTransacao;
	}

	public static List<Produto> getListaProduto() {
		return listaProduto;
	}

	public static void setListaProduto(List<Produto> listaProduto) {
		ProdutoView.listaProduto = listaProduto;
	}
}
