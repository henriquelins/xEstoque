package br.com.ne.estoque.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import br.com.ne.estoque.controller.IniciarAplicativoNEEstoque;
import br.com.ne.estoque.controller.Operador;
import br.com.ne.estoque.controller.OperadorController;
import uteis.PesquisarComboBox;
import uteis.TamanhoMaxTextField;

public class TelaLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5922907749894607031L;
	private JPanel contentPane;
	private JPasswordField pfSenha;
	private JComboBox<String> cbLogin;
	private JPanel panelLogin;
	private JButton btnConfirmar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					TelaLogin frame = new TelaLogin();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaLogin() {
		setName("Login");
		setType(Type.POPUP);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaLogin.class.getResource("/image/ne.png")));
		setTitle("Estoque NE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 154);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		TamanhoMaxTextField TamanhoMax = new TamanhoMaxTextField();
		TamanhoMax.setMaxChars(7);

		panelLogin = new JPanel();
		panelLogin.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192)), "Controle de Acesso", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelLogin.setBounds(17, 8, 260, 106);
		contentPane.add(panelLogin);
		panelLogin.setLayout(null);

		cbLogin = new JComboBox<String>();
		cbLogin.setToolTipText("Digite o Login");
		cbLogin.setModel(new DefaultComboBoxModel<String>(new String[] { "" }));
		cbLogin.setBounds(70, 18, 172, 30);
		panelLogin.add(cbLogin);
		cbLogin.setBorder(new LineBorder(Color.LIGHT_GRAY));
		cbLogin.setEditable(true);
		cbLogin.setFont(new Font("Arial", Font.BOLD, 14));

		cbLogin.setEditable(true);
		new PesquisarComboBox(cbLogin);

		pfSenha = new JPasswordField();
		pfSenha.setForeground(new Color(0, 0, 255));
		pfSenha.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {

				getRootPane().setDefaultButton(btnConfirmar);

			}

			@Override
			public void focusLost(FocusEvent arg0) {

				getRootPane().setDefaultButton(null);

			}
		});
		pfSenha.setToolTipText("Digite a senha");
		pfSenha.setBounds(70, 60, 80, 30);
		panelLogin.add(pfSenha);
		pfSenha.setBorder(new LineBorder(Color.LIGHT_GRAY));
		pfSenha.setFont(new Font("Arial", Font.PLAIN, 14));
		pfSenha.setDocument(TamanhoMax);

		btnConfirmar = new JButton(" OK");
		btnConfirmar.setHorizontalAlignment(SwingConstants.LEFT);
		btnConfirmar.setToolTipText("Confirmar login");

		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (cbLogin.getSelectedItem().equals("Selecione...")
						&& String.valueOf(pfSenha.getPassword()).equals("")) {

					JOptionPane.showMessageDialog(null, "Selecione o Login e digite a senha!");

				} else if (cbLogin.getSelectedItem().equals("Selecione...")) {

					JOptionPane.showMessageDialog(null, "Selecione o Login!");

				} else if (String.valueOf(pfSenha.getPassword()).equals("")) {

					JOptionPane.showMessageDialog(null, "Digite a senha!");

				} else {

					OperadorController operadorRN = new OperadorController();

					IniciarAplicativoNEEstoque.operador = operadorRN.login(String.valueOf(cbLogin.getSelectedItem()).toUpperCase(),
							String.valueOf(pfSenha.getPassword()));

					if (IniciarAplicativoNEEstoque.operador.getLogin() != null) {
						
						dispose();
						
						try {
							UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
							ProdutoView frame = new ProdutoView();
							frame.setLocationRelativeTo(null);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					

					} else {

						JOptionPane.showMessageDialog(null, "Login não confirmado");
						pfSenha.setText("");
						pfSenha.requestFocus();

					}

				}

			}
		});
		btnConfirmar.setBounds(155, 59, 87, 33);
		panelLogin.add(btnConfirmar);
		btnConfirmar.setIcon(new ImageIcon(TelaLogin.class.getResource("/image/login.png")));
		btnConfirmar.setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(15, 22, 49, 25);
		panelLogin.add(lblLogin);
		lblLogin.setFont(new Font("Arial", Font.PLAIN, 16));

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(13, 65, 57, 25);
		panelLogin.add(lblSenha);
		lblSenha.setFont(new Font("Arial", Font.PLAIN, 16));

		List<Operador> listaOperador = new ArrayList<Operador>();

		listaOperador = OperadorController.listaOperador();

		for (Operador op : listaOperador) {

			cbLogin.addItem(op.getLogin());

		}
	}

	/*public List<Producao> getLista() {
		return lista;
	}

	public void setLista(List<Producao> lista) {
		this.lista = lista;
	}*/

	public JComboBox<String> getCbLogin() {
		return cbLogin;
	}

	public void setCbLogin(JComboBox<String> cbLogin) {
		this.cbLogin = cbLogin;
	}
}
