package br.com.ne.estoque.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.com.ne.estoque.controller.IniciarAplicativoNEEstoque;
import br.com.ne.estoque.controller.Operador;
import br.com.ne.estoque.controller.OperadorController;

public class CadastroUsuarioView extends JDialog {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	static JTextField tfNumero;
	static JTextField tfLogin;
	static JPasswordField pfSenhaAtual;
	static JPasswordField pfNovaSenha;
	static JPasswordField pfRepetirSenha;
	static JTextField tfNome;
	static List<Operador> listaOperador;
	private int posicao = 0;
	private String botao;
	static JComboBox<String> cbNome = new JComboBox<String>();
	static int numeroUsuario = 1;
	JButton btnEditar = new JButton("");
	JButton btnExcluir = new JButton("");
	JButton btnNovo = new JButton("");
	JButton okButton = new JButton("Salvar");
	private JButton cancelButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CadastroUsuarioView dialog = new CadastroUsuarioView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * 
	 * 
	 * 
	 */

	public CadastroUsuarioView() {
		setBackground(Color.LIGHT_GRAY);
		setModal(true);
		setResizable(false);
		setTitle("Cadastro de Operador");
		setIconImage(Toolkit.getDefaultToolkit().getImage(CadastroUsuarioView.class.getResource("/image/ne.png")));
		setBounds(100, 100, 440, 310);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		new OperadorController();

		listaOperador = OperadorController.listaOperador();

		tfNumero = new JTextField();
		tfNumero.setEditable(false);
		tfNumero.setBounds(37, 104, 61, 20);
		contentPanel.add(tfNumero);
		tfNumero.setText(String.valueOf(1));

		tfNumero.setColumns(10);
		tfLogin = new JTextField();
		tfLogin.setEditable(false);
		tfLogin.setColumns(10);
		tfLogin.setBounds(36, 155, 233, 20);
		contentPanel.add(tfLogin);
		tfLogin.setText(String.valueOf(listaOperador.get(posicao).getLogin()));

		pfSenhaAtual = new JPasswordField();
		pfSenhaAtual.setEditable(false);
		pfSenhaAtual.setBounds(34, 203, 102, 20);
		contentPanel.add(pfSenhaAtual);
		pfSenhaAtual.setText(String.valueOf(listaOperador.get(posicao).getSenha()));

		pfNovaSenha = new JPasswordField();
		pfNovaSenha.setEditable(false);
		pfNovaSenha.setBounds(165, 203, 102, 20);
		contentPanel.add(pfNovaSenha);

		for (Operador op : listaOperador) {

			cbNome.addItem(op.getNome().toUpperCase());

		}

		cbNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new OperadorController();
				
				listaOperador = OperadorController.listaOperador();

				posicao = cbNome.getSelectedIndex();

				if (posicao == -1) {

					posicao = 0;

				}

				tfNumero.setText(String.valueOf(posicao +1));
				tfLogin.setText(String.valueOf(listaOperador.get(posicao).getLogin()));
				pfSenhaAtual.setText(listaOperador.get(posicao).getSenha());

			}
		});

		cbNome.setBounds(108, 104, 291, 20);
		contentPanel.add(cbNome);

		JLabel lblNumero = new JLabel("N\u00FAmero");
		lblNumero.setBounds(37, 86, 46, 14);
		contentPanel.add(lblNumero);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(108, 86, 136, 14);
		contentPanel.add(lblNome);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(37, 135, 136, 14);
		contentPanel.add(lblLogin);

		JLabel lblSenhaAtual = new JLabel("Senha Atual");
		lblSenhaAtual.setBounds(37, 186, 69, 14);
		contentPanel.add(lblSenhaAtual);

		JLabel lblNovaSenha = new JLabel("Senha");
		lblNovaSenha.setBounds(167, 186, 69, 14);
		contentPanel.add(lblNovaSenha);

		btnNovo.setFocusable(false);
		btnNovo.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNovo.setRolloverIcon(new ImageIcon(CadastroUsuarioView.class.getResource("/image/adicionar_usuario_2.png")));
		btnNovo.setContentAreaFilled(false);
		btnNovo.setBorderPainted(false);
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				okButton.setVisible(true);

				botao = "salvar";

				btnNovo.setEnabled(false);
				btnEditar.setEnabled(false);
				btnExcluir.setEnabled(false);
				
				tfNumero.setText(String.valueOf(listaOperador.size() + 1));
				cbNome.setVisible(false);
				tfNome.setVisible(true);
				tfNome.setText("");
				tfLogin.setText("");
				tfLogin.setEditable(true);

				pfSenhaAtual.setText("");
				pfNovaSenha.setEditable(true);
				pfRepetirSenha.setEditable(true);

			}

		});
		btnNovo.setIcon(new ImageIcon(CadastroUsuarioView.class.getResource("/image/adicionar_usuario.png")));
		btnNovo.setToolTipText("Novo");
		btnNovo.setBounds(25, 11, 75, 68);
		contentPanel.add(btnNovo);

		btnEditar.setBorderPainted(false);
		btnEditar.setFocusable(false);
		btnEditar.setContentAreaFilled(false);
		btnEditar.setRolloverIcon(new ImageIcon(CadastroUsuarioView.class.getResource("/image/editar_usuario_2.png")));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				okButton.setVisible(true);

				botao = "editar";

				btnNovo.setEnabled(false);
				btnEditar.setEnabled(false);
				btnExcluir.setEnabled(false);
				
				tfNome.setText(String.valueOf(listaOperador.get(posicao).getId_operador()));
				cbNome.setVisible(false);
				tfNome.setVisible(true);
				tfNome.setText(listaOperador.get(posicao).getNome());
				tfNome.setEditable(true);
				tfLogin.setText(listaOperador.get(posicao).getLogin());
				tfLogin.setEditable(true);
				pfSenhaAtual.setText(listaOperador.get(posicao).getSenha());
				pfNovaSenha.setEditable(true);
				pfRepetirSenha.setEditable(true);

			}
		});
		btnEditar.setIcon(new ImageIcon(CadastroUsuarioView.class.getResource("/image/editar_usuario.png")));
		btnEditar.setToolTipText("Editar");
		btnEditar.setBounds(172, 11, 84, 68);
		contentPanel.add(btnEditar);

		btnExcluir.setContentAreaFilled(false);
		btnExcluir
				.setRolloverIcon(new ImageIcon(CadastroUsuarioView.class.getResource("/image/excluir_usuario_2.png")));
		btnExcluir.setIcon(new ImageIcon(CadastroUsuarioView.class.getResource("/image/excluir_usuario.png")));
		btnExcluir.setBorderPainted(false);
		btnExcluir.setFocusable(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o usuário?",
						"Excluir Usuário", JOptionPane.YES_NO_OPTION);

				if (resposta == JOptionPane.YES_OPTION) {

					posicao = cbNome.getSelectedIndex();
					int id_operador = listaOperador.get(posicao).getId_operador();

					tfNumero.setText(String.valueOf(listaOperador.get(posicao).getId_operador()));
					tfLogin.setText(String.valueOf(listaOperador.get(posicao).getLogin()));
					pfSenhaAtual.setText(listaOperador.get(posicao).getSenha());
					
					listaOperador.clear();
					cbNome.removeAllItems();
					cbNome.setSelectedItem(null);
					
					boolean sucesso = false;
					
					OperadorController operadorController = new OperadorController();
					sucesso = operadorController.excluirOperador(id_operador);
					
					if (sucesso == true) {
						
						JOptionPane.showMessageDialog(null, "Operador excluído com sucesso!");
						
					}
					
					OperadorController.listaOperador();

					for (Operador op : listaOperador) {

						cbNome.addItem(op.getNome().toUpperCase());

					}

				}

			}

		});
		btnExcluir.setToolTipText("Excluir");
		btnExcluir.setBounds(315, 11, 84, 68);
		contentPanel.add(btnExcluir);

		JLabel lblRepetirSenha = new JLabel("Repetir Senha");
		lblRepetirSenha.setBounds(297, 186, 69, 14);
		contentPanel.add(lblRepetirSenha);

		pfRepetirSenha = new JPasswordField();
		pfRepetirSenha.setEditable(false);
		pfRepetirSenha.setBounds(297, 203, 102, 20);
		contentPanel.add(pfRepetirSenha);

		tfNome = new JTextField();
		tfNome.setBounds(108, 104, 291, 20);
		contentPanel.add(tfNome);
		tfNome.setColumns(10);
						okButton.setIcon(new ImageIcon(CadastroUsuarioView.class.getResource("/image/salvar.png")));
						okButton.setBounds(206, 240, 86, 23);
						contentPanel.add(okButton);
				
						okButton.setVisible(false);
						{

							okButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {

									OperadorController operadorController = new OperadorController();
									
									String senha = String.valueOf(pfNovaSenha.getPassword());
									String repetirSenha = String.valueOf(pfRepetirSenha.getPassword());

									if (tfNome.getText().equals("")) {

										JOptionPane.showMessageDialog(null, "Digite o Nome do usuário");

										tfNome.requestFocus();

									} else if (tfLogin.getText().equals("")) {

										JOptionPane.showMessageDialog(null, "Digite o Login do usuário");

										tfLogin.requestFocus();

									} else if (String.valueOf(pfNovaSenha.getPassword()).equalsIgnoreCase("")) {

										JOptionPane.showMessageDialog(null, "Digite o Senha do usuário");

										pfNovaSenha.requestFocus();

									} else if (String.valueOf(pfRepetirSenha.getPassword()).equalsIgnoreCase("")) {

										JOptionPane.showMessageDialog(null, "Repita a Senha do usuário");

										pfRepetirSenha.requestFocus();

									} else if (!senha.equalsIgnoreCase(repetirSenha)) {

										JOptionPane.showMessageDialog(null, "As senhas não estão iguais");

										pfNovaSenha.requestFocus();
										pfNovaSenha.setText("");
										pfRepetirSenha.setText("");

									} else {

										if (botao.equals("salvar")) {
											
											boolean sucesso = false;
											
											Operador operador = new Operador();
											
											operador.setNome(tfNome.getText().toUpperCase());
											operador.setLogin(tfLogin.getText().toUpperCase());
											operador.setSenha(String.valueOf(pfNovaSenha.getPassword()));
																			
											sucesso = operadorController.adicionarOperador(operador);
																																
											if (sucesso == true) {
												
												JOptionPane.showMessageDialog(null, "O operador foi inserido com sucesso!");
												
											}									

											listaOperador.clear();
											cbNome.removeAllItems();
											cbNome.setSelectedItem(null);

											listaOperador = OperadorController.listaOperador();

											for (Operador op : listaOperador) {

												cbNome.addItem(op.getNome().toUpperCase());

											}

											tfNome.setText("");
											tfLogin.setText("");
											tfLogin.setEditable(false);
											pfSenhaAtual.setText("");
											pfSenhaAtual.setEditable(false);
											pfNovaSenha.setText("");
											pfNovaSenha.setEditable(false);
											pfRepetirSenha.setText("");
											pfRepetirSenha.setEditable(false);
											tfNumero.setText("");

											posicao = cbNome.getSelectedIndex();

											tfNumero.setText(String.valueOf(posicao + 1));
											tfLogin.setText(String.valueOf(listaOperador.get(posicao).getLogin()));
											pfSenhaAtual.setText(listaOperador.get(posicao).getSenha());

											btnNovo.setEnabled(true);
											btnEditar.setEnabled(true);
											btnExcluir.setEnabled(true);
											
											cbNome.setVisible(true);
											tfNome.setVisible(false);

											botao = "";

											okButton.setVisible(false);

										}

										if (botao.equals("editar")) {
											
											boolean sucesso = false;
											
											Operador operador = new Operador();
											
											posicao = cbNome.getSelectedIndex();
											
											operador.setId_operador(listaOperador.get(posicao).getId_operador());
											operador.setNome(tfNome.getText().toUpperCase());
											operador.setLogin(tfLogin.getText().toUpperCase());
											operador.setSenha(String.valueOf(pfNovaSenha.getPassword()));
																			
											sucesso = operadorController.editarOperador(operador);
											
											if (sucesso == true) {
											
												JOptionPane.showMessageDialog(null, "Operador editado com sucesso!");
												
											}
											
											listaOperador.clear();
											cbNome.removeAllItems();
											cbNome.setSelectedItem(null);
											
											listaOperador = OperadorController.listaOperador();

											for (Operador op : listaOperador) {

												cbNome.addItem(op.getNome().toUpperCase());

											}

											tfNome.setText("");
											tfLogin.setText("");
											tfLogin.setEditable(false);
											pfSenhaAtual.setText("");
											pfSenhaAtual.setEditable(false);
											pfNovaSenha.setText("");
											pfNovaSenha.setEditable(false);
											pfRepetirSenha.setText("");
											pfRepetirSenha.setEditable(false);
											tfNumero.setText("");

											posicao = cbNome.getSelectedIndex();

											tfNumero.setText(String.valueOf(posicao + 1));
											tfLogin.setText(String.valueOf(listaOperador.get(posicao).getLogin()));
											pfSenhaAtual.setText(listaOperador.get(posicao).getSenha());

											btnNovo.setEnabled(true);
											btnEditar.setEnabled(true);
											btnExcluir.setEnabled(true);
								
											cbNome.setVisible(true);
											tfNome.setVisible(false);

											okButton.setVisible(false);

										}

									}

								}
							});
							okButton.setActionCommand("OK");
							getRootPane().setDefaultButton(okButton);
						}
						{
							cancelButton = new JButton("Cancelar");
							cancelButton.setToolTipText("Cancelar");
							cancelButton.setIcon(new ImageIcon(CadastroUsuarioView.class.getResource("/image/cancelar.png")));
							cancelButton.setBounds(300, 240, 99, 23);
							contentPanel.add(cancelButton);
							cancelButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {

									listaOperador.clear();
									cbNome.removeAllItems();
									cbNome.setSelectedItem(null);

									cbNome.setVisible(true);

									dispose();

								}
							});
							cancelButton.setActionCommand("Cancel");
						}
						
						JLabel lblLogado = new JLabel("LOGADO: " + IniciarAplicativoNEEstoque.operador.getNome().toUpperCase());
						lblLogado.setBounds(37, 243, 165, 17);
						contentPanel.add(lblLogado);
		tfNome.setVisible(false);

	}

}
