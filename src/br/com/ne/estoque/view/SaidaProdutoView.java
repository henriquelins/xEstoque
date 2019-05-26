package br.com.ne.estoque.view;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class SaidaProdutoView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3859580814042375023L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SaidaProdutoView dialog = new SaidaProdutoView();
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
	public SaidaProdutoView() {
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setTitle("Saída de Produtos");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 960, 630);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
	}

}
