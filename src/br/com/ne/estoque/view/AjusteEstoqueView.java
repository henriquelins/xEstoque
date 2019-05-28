package br.com.ne.estoque.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import br.com.ne.estoque.controller.IniciarAplicativoEstoque;
import br.com.ne.estoque.controller.ProdutoController;
import br.com.ne.estoque.controller.TransacaoController;
import uteis.TamanhoMaxTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class AjusteEstoqueView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4980563874467807765L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfQuantidade;
	private JComboBox<String> cbTransacao;
	private JLabel lblNomeDescricao;
	private JLabel lblEstoqueAtual;
	private JLabel lblDescrição;
	private JTextPane tpDescrevaOQueHouve;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			AjusteEstoqueView dialog = new AjusteEstoqueView();
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
	public AjusteEstoqueView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AjusteEstoqueView.class.getResource("/image/ne.png")));
		setTitle("Editar Estoque");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 450, 461);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cbTransacao.getSelectedItem().equals("ESCOLHA...")) {

					JOptionPane.showMessageDialog(null, "Escolha um tipo de transação de Estoque!");

				} else if (tfQuantidade.getText().equals("0")) {

					JOptionPane.showMessageDialog(null, "Digite um valor!");

				} else if (cbTransacao.getSelectedItem().equals("ENTRADA")) {

					if (tfQuantidade.getText().equals("")) {

						JOptionPane.showMessageDialog(null, "Informe o valor!");

					} else {

						int soma = IniciarAplicativoEstoque.produto.getEstoqueAtual()
								+ Integer.parseInt(tfQuantidade.getText());

						int resposta = JOptionPane.showConfirmDialog(null, "Com a entrada de " + tfQuantidade.getText()
								+ " " + IniciarAplicativoEstoque.produto.getUnidadeMedida()
								+ " o saldo atual do produto " + IniciarAplicativoEstoque.produto.getNomeDescricao()
								+ " foi para " + soma + " " + IniciarAplicativoEstoque.produto.getUnidadeMedida()
								+ ". Deseja confirmar?", "Entrada no Estoque", JOptionPane.OK_OPTION);

						if (resposta == JOptionPane.OK_OPTION) {
							
							int estoqueAnterior = IniciarAplicativoEstoque.produto.getEstoqueAtual();
							
							boolean sucesso = false;

							ProdutoController produtoController = new ProdutoController();

							IniciarAplicativoEstoque.produto.setEstoqueAtual(soma);

							sucesso = produtoController.entrada(IniciarAplicativoEstoque.produto);

							if (sucesso) {

								int tipo = 4;
								int ajuste = Integer.parseInt(tfQuantidade.getText());

								TransacaoController transacaoController = new TransacaoController();
								transacaoController.transacao(tipo, ajuste, estoqueAnterior);							

								dispose();

							} else {

							}

						} else {

							tpDescrevaOQueHouve.setText("");
							tfQuantidade.setText("");
							cbTransacao.setSelectedIndex(0);

						}

					}

				} else if (cbTransacao.getSelectedItem().equals("SAÍDA")) {

					if (tfQuantidade.getText().equals("")) {

						JOptionPane.showMessageDialog(null, "Informe o valor!");

					} else {

						if (IniciarAplicativoEstoque.produto.getEstoqueAtual() < Integer
								.parseInt(tfQuantidade.getText())) {

							JOptionPane.showMessageDialog(null, "O saldo do produto é menor do que o solicitado!");
							tfQuantidade.setText("");
							tfQuantidade.requestFocus();

						} else {

							int subtracao = IniciarAplicativoEstoque.produto.getEstoqueAtual()
									- Integer.parseInt(tfQuantidade.getText());

							int resposta = JOptionPane.showConfirmDialog(null,
									"Com a saída de " + tfQuantidade.getText() + " "
											+ IniciarAplicativoEstoque.produto.getUnidadeMedida()
											+ " o saldo atual do produto "
											+ IniciarAplicativoEstoque.produto.getNomeDescricao() + " foi para "
											+ subtracao + " " + IniciarAplicativoEstoque.produto.getUnidadeMedida()
											+ ". Deseja confirmar?",
									"Saída de Estoque", JOptionPane.OK_OPTION);

							if (resposta == JOptionPane.OK_OPTION) {
								
								int estoqueAnterior = IniciarAplicativoEstoque.produto.getEstoqueAtual();
								
								boolean sucesso = false;

								ProdutoController produtoController = new ProdutoController();

								IniciarAplicativoEstoque.produto.setEstoqueAtual(subtracao);

								sucesso = produtoController.saida(IniciarAplicativoEstoque.produto);

								if (sucesso) {

									int tipo = 5;
									int ajuste = Integer.parseInt(tfQuantidade.getText());

									TransacaoController transacaoController = new TransacaoController();
									transacaoController.transacao(tipo, ajuste, estoqueAnterior);

									dispose();

								}

							} else {

								tpDescrevaOQueHouve.setText("");
								tfQuantidade.setText("");
								cbTransacao.setSelectedIndex(0);

							}

						}

					}

				} else if (cbTransacao.getSelectedItem().equals("DEVOLUÇÃO")) {

					if (tfQuantidade.getText().equals("")) {

						JOptionPane.showMessageDialog(null, "Informe o valor!");

					} else {

						int soma = IniciarAplicativoEstoque.produto.getEstoqueAtual()
								+ Integer.parseInt(tfQuantidade.getText());

						if (tpDescrevaOQueHouve.getText().equals("")) {

							JOptionPane.showMessageDialog(null, "Para confirmar a devolução descreva o motivo!");

							tpDescrevaOQueHouve.requestFocus();

						} else {

							int resposta = JOptionPane.showConfirmDialog(null,
									"Com a devolução de " + tfQuantidade.getText() + " "
											+ IniciarAplicativoEstoque.produto.getUnidadeMedida()
											+ " o saldo atual do produto "
											+ IniciarAplicativoEstoque.produto.getNomeDescricao() + " foi para "
											+ soma + " " + IniciarAplicativoEstoque.produto.getUnidadeMedida()
											+ ". Deseja confirmar?",
									"Devolução de Produto", JOptionPane.OK_OPTION);

							if (resposta == JOptionPane.OK_OPTION) {
								
								int estoqueAnterior = IniciarAplicativoEstoque.produto.getEstoqueAtual();
								
								boolean sucesso = false;

								ProdutoController produtoController = new ProdutoController();

								IniciarAplicativoEstoque.produto.setEstoqueAtual(soma);

								sucesso = produtoController.entrada(IniciarAplicativoEstoque.produto);

								if (sucesso) {
																	
									int tipo = 6;
									int ajuste = Integer.parseInt(tfQuantidade.getText());

									TransacaoController transacaoController = new TransacaoController();
									transacaoController.transacao(tipo, ajuste, estoqueAnterior);

									dispose();

								}

							} else {

								tpDescrevaOQueHouve.setText("");
								tfQuantidade.setText("");
								cbTransacao.setSelectedIndex(0);

							}

						}

					}

				} else if (cbTransacao.getSelectedItem().equals("PERDAS")) {

					if (tfQuantidade.getText().equals("")) {

						JOptionPane.showMessageDialog(null, "Informe o valor!");

					} else {

						int subtracao = IniciarAplicativoEstoque.produto.getEstoqueAtual()
								- Integer.parseInt(tfQuantidade.getText());

						if (IniciarAplicativoEstoque.produto.getEstoqueAtual() < Integer
								.parseInt(tfQuantidade.getText())) {

							JOptionPane.showMessageDialog(null, "O saldo do produto é menor do que o solicitado!");
							tfQuantidade.setText("");
							tfQuantidade.requestFocus();

						} else {

							if (tpDescrevaOQueHouve.getText().equals("")) {

								JOptionPane.showMessageDialog(null, "Para confirmar a devolução descreva o motivo!");

								tpDescrevaOQueHouve.requestFocus();

							} else {

								int resposta = JOptionPane.showConfirmDialog(null,
										"Com a(s) perda(s) de " + tfQuantidade.getText() + " "
												+ IniciarAplicativoEstoque.produto.getUnidadeMedida()
												+ " o saldo atual do produto "
												+ IniciarAplicativoEstoque.produto.getNomeDescricao() + " foi para "
												+ subtracao + " "
												+ IniciarAplicativoEstoque.produto.getUnidadeMedida()
												+ ". Deseja confirmar?",
										"Perdas de Produto", JOptionPane.OK_OPTION);

								if (resposta == JOptionPane.OK_OPTION) {
									
									int estoqueAnterior = IniciarAplicativoEstoque.produto.getEstoqueAtual();
									
									boolean sucesso = false;

									ProdutoController produtoController = new ProdutoController();

									IniciarAplicativoEstoque.produto.setEstoqueAtual(subtracao);

									sucesso = produtoController.saida(IniciarAplicativoEstoque.produto);

									if (sucesso) {
									
										int tipo = 7;
										int ajuste = Integer.parseInt(tfQuantidade.getText());

										TransacaoController transacaoController = new TransacaoController();
										transacaoController.transacao(tipo, ajuste, estoqueAnterior);

										dispose();

									}

								} else {

									tpDescrevaOQueHouve.setText("");
									tfQuantidade.setText("");
									cbTransacao.setSelectedIndex(0);

								}

							}

						}
					}

				}

			}

		});
		btnSalvar.setIcon(new ImageIcon(AjusteEstoqueView.class.getResource("/image/salvar.png")));
		btnSalvar.setToolTipText("Salvar produto");
		btnSalvar.setHorizontalAlignment(SwingConstants.LEFT);
		btnSalvar.setBounds(207, 398, 97, 23);
		contentPanel.add(btnSalvar);

		JButton button_1 = new JButton("Cancelar");
		button_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				dispose();

			}
		});
		button_1.setIcon(new ImageIcon(AjusteEstoqueView.class.getResource("/image/cancelar.png")));
		button_1.setToolTipText("Cancelar");
		button_1.setHorizontalAlignment(SwingConstants.LEADING);
		button_1.setBounds(314, 398, 105, 23);
		contentPanel.add(button_1);

		cbTransacao = new JComboBox<String>();
		cbTransacao.setToolTipText("Escolha a modalidade da transa\u00E7\u00E3o");
		cbTransacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String escolha = String.valueOf(cbTransacao.getSelectedItem());

				if (escolha.equals("DEVOLUÇÃO") || escolha.equals("PERDAS")) {

					lblDescrição.setVisible(true);
					tpDescrevaOQueHouve.setVisible(true);

				} else {

					lblDescrição.setVisible(false);
					tpDescrevaOQueHouve.setVisible(false);

				}

			}
		});
		cbTransacao.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbTransacao.setModel(new DefaultComboBoxModel<String>(
				new String[] { "ESCOLHA...", "ENTRADA", "SA\u00CDDA", "DEVOLU\u00C7\u00C3O", "PERDAS" }));
		cbTransacao.setBounds(48, 70, 158, 20);
		contentPanel.add(cbTransacao);

		JLabel lblTipoTransacao = new JLabel("Transa\u00E7\u00E3o de Estoque");
		lblTipoTransacao.setBounds(48, 44, 158, 23);
		contentPanel.add(lblTipoTransacao);

		lblEstoqueAtual = new JLabel();
		lblEstoqueAtual.setToolTipText("Estoque atualizado do produto");
		lblEstoqueAtual.setForeground(Color.BLACK);
		lblEstoqueAtual.setHorizontalAlignment(SwingConstants.LEFT);
		lblEstoqueAtual.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEstoqueAtual.setBounds(227, 60, 164, 38);
		contentPanel.add(lblEstoqueAtual);

		tpDescrevaOQueHouve = new JTextPane();
		tpDescrevaOQueHouve.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				getRootPane().setDefaultButton(btnSalvar);
				
			}
		});
		tpDescrevaOQueHouve.setForeground(Color.BLACK);
		tpDescrevaOQueHouve.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tpDescrevaOQueHouve.setVisible(false);
		tpDescrevaOQueHouve.setBorder(new LineBorder(Color.LIGHT_GRAY));
		tpDescrevaOQueHouve.setBounds(48, 282, 343, 77);
		contentPanel.add(tpDescrevaOQueHouve);

		lblDescrição = new JLabel("Descri\u00E7\u00E3o do Motivo");
		lblDescrição.setVisible(false);
		lblDescrição.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescrição.setBounds(48, 262, 130, 14);
		contentPanel.add(lblDescrição);

		JPanel pEstoque = new JPanel();
		pEstoque.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192)), "Dados do Produto", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pEstoque.setBounds(24, 11, 395, 376);
		contentPanel.add(pEstoque);
		pEstoque.setLayout(null);

		TamanhoMaxTextField TamanhoMax = new TamanhoMaxTextField();
		TamanhoMax.setMaxChars(7);

		tfQuantidade = new JTextField();
		tfQuantidade.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				getRootPane().setDefaultButton(btnSalvar);
				
			}
		});
		tfQuantidade.setBounds(116, 167, 161, 50);
		pEstoque.add(tfQuantidade);
		tfQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 24));
		tfQuantidade.setToolTipText("Quantidade");
		tfQuantidade.setForeground(Color.RED);
		tfQuantidade.setHorizontalAlignment(SwingConstants.CENTER);
		tfQuantidade.setColumns(10);

		tfQuantidade.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ev) {

				String caracteres = "0987654321";

				if (!caracteres.contains(ev.getKeyChar() + "")) {

					ev.consume();

				}

			}

		});
		tfQuantidade.setDocument(TamanhoMax);
		
				lblNomeDescricao = new JLabel();
				lblNomeDescricao.setToolTipText("Nome do produto");
				lblNomeDescricao.setBounds(25, 108, 342, 33);
				pEstoque.add(lblNomeDescricao);
				lblNomeDescricao.setForeground(Color.BLACK);
				lblNomeDescricao.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblNomeDescricao.setHorizontalAlignment(SwingConstants.CENTER);
				
				JPanel pAjuste = new JPanel();
				pAjuste.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192)), "Valor do ajuste", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				pAjuste.setBounds(25, 149, 342, 82);
				pEstoque.add(pAjuste);
				
						JLabel lblNome = new JLabel("Nome do Produto");
						lblNome.setBounds(25, 92, 167, 14);
						pEstoque.add(lblNome);
						lblNome.setHorizontalAlignment(SwingConstants.LEFT);
						
								JLabel lblValorUnitario = new JLabel("Estoque Atual");
								lblValorUnitario.setBounds(206, 38, 161, 14);
								pEstoque.add(lblValorUnitario);
								lblValorUnitario.setHorizontalAlignment(SwingConstants.LEFT);

	}

	public JLabel getLblNomeDescricao() {
		return lblNomeDescricao;
	}

	public void setLblNomeDescricao(JLabel lblNomeDescricao) {
		this.lblNomeDescricao = lblNomeDescricao;
	}

	public JLabel getLblEstoqueAtual() {
		return lblEstoqueAtual;
	}

	public void setLblEstoqueAtual(JLabel lblEstoqueAtual) {
		this.lblEstoqueAtual = lblEstoqueAtual;
	}
}
