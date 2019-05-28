package br.com.ne.estoque.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import br.com.ne.estoque.controller.IniciarAplicativoEstoque;

public class EntradaSaidaProdutoView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3859580814042375023L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfNomeDescricao;
	private JTextField tfEstoqueAtual;
	private JTextField tfSituacaoAtual;
	private JLabel lblClickImagem;
	private JTextPane tpObservacao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			EntradaSaidaProdutoView dialog = new EntradaSaidaProdutoView();
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
	public EntradaSaidaProdutoView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(EntradaSaidaProdutoView.class.getResource("/image/ne.png")));
		setResizable(false);
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setTitle("Entrada e Sa\u00EDda de Produtos");
		setModal(true);
		setBounds(100, 100, 960, 630);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		lblClickImagem = new JLabel("Produto sem imagem");
		lblClickImagem.setForeground(Color.BLUE);
		lblClickImagem.setBorder(null);
		lblClickImagem.setHorizontalTextPosition(SwingConstants.LEFT);
		lblClickImagem.setHorizontalAlignment(SwingConstants.CENTER);
		lblClickImagem.setBounds(34, 249, 882, 279);
		
		contentPanel.add(lblClickImagem);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();

			}
		});
		btnCancelar.setIcon(new ImageIcon(EntradaSaidaProdutoView.class.getResource("/image/cancelar.png")));
		btnCancelar.setToolTipText("Cancelar");
		btnCancelar.setHorizontalAlignment(SwingConstants.LEADING);
		btnCancelar.setBounds(818, 561, 105, 23);
		contentPanel.add(btnCancelar);

		JPanel panelProduto = new JPanel();
		panelProduto.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192)), "Dados do Produto", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelProduto.setBounds(22, 11, 908, 210);
		contentPanel.add(panelProduto);
		panelProduto.setLayout(null);

		JLabel lblNomeDescricao = new JLabel("Nome do Produto");
		lblNomeDescricao.setBounds(29, 21, 105, 14);
		panelProduto.add(lblNomeDescricao);

		tfNomeDescricao = new JTextField();
		tfNomeDescricao.setForeground(Color.BLACK);
		tfNomeDescricao.setBackground(Color.WHITE);
		tfNomeDescricao.setEditable(false);
		tfNomeDescricao.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfNomeDescricao.setBounds(29, 41, 577, 31);
		panelProduto.add(tfNomeDescricao);
		tfNomeDescricao.setColumns(10);

		tfEstoqueAtual = new JTextField();
		tfEstoqueAtual.setForeground(Color.BLACK);
		tfEstoqueAtual.setBackground(Color.WHITE);
		tfEstoqueAtual.setEditable(false);
		tfEstoqueAtual.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfEstoqueAtual.setColumns(10);
		tfEstoqueAtual.setBounds(635, 41, 241, 31);
		panelProduto.add(tfEstoqueAtual);

		JLabel lblEstoqueAtual = new JLabel("Estoque Atual");
		lblEstoqueAtual.setBounds(636, 21, 105, 14);
		panelProduto.add(lblEstoqueAtual);

		JLabel lblSituaoAtual = new JLabel("Situa\u00E7\u00E3o Atual");
		lblSituaoAtual.setBounds(635, 82, 127, 14);
		panelProduto.add(lblSituaoAtual);

		tfSituacaoAtual = new JTextField();
		tfSituacaoAtual.setBackground(Color.WHITE);
		tfSituacaoAtual.setEditable(false);
		tfSituacaoAtual.setForeground(Color.BLACK);
		tfSituacaoAtual.setCaretColor(Color.BLACK);
		tfSituacaoAtual.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfSituacaoAtual.setColumns(10);
		tfSituacaoAtual.setBounds(635, 102, 241, 31);
		panelProduto.add(tfSituacaoAtual);

		JLabel lblObservacoes = new JLabel("Observa\u00E7\u00F5es");
		lblObservacoes.setBounds(29, 82, 105, 14);
		panelProduto.add(lblObservacoes);

		tpObservacao = new JTextPane();
		tpObservacao.setForeground(Color.BLACK);
		tpObservacao.setEditable(false);
		tpObservacao.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tpObservacao.setBorder(new LineBorder(Color.LIGHT_GRAY));
		tpObservacao.setBounds(29, 102, 577, 84);
		panelProduto.add(tpObservacao);
		
				JButton btnAlterarEstoque = new JButton("Entrada e Sa\u00EDda de Estoque");
				btnAlterarEstoque.setIcon(new ImageIcon(EntradaSaidaProdutoView.class.getResource("/image/saidaProduto.png")));
				btnAlterarEstoque.setBounds(635, 149, 241, 37);
				panelProduto.add(btnAlterarEstoque);
				btnAlterarEstoque.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						try {

							UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
							AjusteEstoqueView ajusteEstoqueView = new AjusteEstoqueView();
							ajusteEstoqueView.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							ajusteEstoqueView.setLocationRelativeTo(null);

							ajusteEstoqueView.getLblNomeDescricao()
									.setText(IniciarAplicativoEstoque.produto.getNomeDescricao());
							ajusteEstoqueView.getLblEstoqueAtual()
									.setText(String.valueOf(IniciarAplicativoEstoque.produto.getEstoqueAtual()) + " "
											+ IniciarAplicativoEstoque.produto.getUnidadeMedida());

							ajusteEstoqueView.setVisible(true);

							tfEstoqueAtual.setText(String.valueOf(IniciarAplicativoEstoque.produto.getEstoqueAtual()) + " "
									+ IniciarAplicativoEstoque.produto.getUnidadeMedida());
							
							if (IniciarAplicativoEstoque.produto.getEstoqueAtual() <= IniciarAplicativoEstoque.produto.getEstoqueMinimo()) {

								tfSituacaoAtual.setText("ESTOQUE BAIXO");
								tfSituacaoAtual.setForeground(Color.RED);

							} else if (IniciarAplicativoEstoque.produto.getEstoqueAtual() > IniciarAplicativoEstoque.produto.getEstoqueMinimo()
									&& IniciarAplicativoEstoque.produto.getEstoqueAtual() < 2 * IniciarAplicativoEstoque.produto.getEstoqueMinimo()) {

								tfSituacaoAtual.setText("ATENÇÃO NO ESTOQUE");
								tfSituacaoAtual.setForeground(Color.ORANGE);

							} else {

								tfSituacaoAtual.setText("ESTOQUE ALTO");
								tfSituacaoAtual.setForeground(Color.BLUE);

							}
							

						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				});
		
		JLabel lblLogado = new JLabel("LOGADO: " + IniciarAplicativoEstoque.operador.getNome().toUpperCase());
		lblLogado.setBounds(22, 567, 423, 23);
		contentPanel.add(lblLogado);
		
		JPanel pImagem = new JPanel();
		pImagem.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192)), "Imagem do Produto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pImagem.setBounds(22, 232, 908, 307);
		contentPanel.add(pImagem);
		pImagem.setLayout(null);
	}

	public JTextField getTfNomeDescricao() {
		return tfNomeDescricao;
	}

	public void setTfNomeDescricao(JTextField tfNomeDescricao) {
		this.tfNomeDescricao = tfNomeDescricao;
	}

	public JTextField getTfEstoqueAtual() {
		return tfEstoqueAtual;
	}

	public void setTfEstoqueAtual(JTextField tfEstoqueAtual) {
		this.tfEstoqueAtual = tfEstoqueAtual;
	}

	public JTextField getTfSituacaoAtual() {
		return tfSituacaoAtual;
	}

	public void setTfSituacaoAtual(JTextField tfSituacaoAtual) {
		this.tfSituacaoAtual = tfSituacaoAtual;
	}

	public JLabel getLblClickImagem() {
		return lblClickImagem;
	}

	public void setLblClickImagem(JLabel lblClickImagem) {
		this.lblClickImagem = lblClickImagem;
	}

	public JTextPane getTpObservacao() {
		return tpObservacao;
	}

	public void setTpObservacao(JTextPane tpObservacao) {
		this.tpObservacao = tpObservacao;
	}
}
