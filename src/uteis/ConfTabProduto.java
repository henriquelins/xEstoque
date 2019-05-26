package uteis;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ConfTabProduto extends DefaultTableCellRenderer {
	public ConfTabProduto() {
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		Component renderer = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
				column);

		Color fundo = Color.WHITE;
		Color fonte = Color.BLUE;

		String estoqueAtual = (String) table.getValueAt(row, 4);
		String estoqueMinimo = (String) table.getValueAt(row, 5);
		
		
		String[] texto1 = estoqueAtual.split(" ");
		String[] texto2 = estoqueMinimo.split(" ");

		String eA = texto1[0];
		String eM = texto2[0];
		
				
		int a = Integer.parseInt(eA);
		int m = Integer.parseInt(eM);

		if (a <= m) {

			fundo = Color.RED;
			fonte = Color.WHITE;

		} else if (a >= m && a <= 2 * m) {

			fundo = Color.YELLOW;
			fonte = Color.BLUE;

		} else {

			fundo = Color.GREEN;
			fonte = Color.BLUE;

		}

		if (isSelected) {

			fundo = Color.BLUE;
			fonte = Color.WHITE;

		}

		renderer.setBackground(fundo);
		renderer.setForeground(fonte);

		return renderer;
	}
}