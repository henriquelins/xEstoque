package uteis;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ConfTabTransacao extends DefaultTableCellRenderer {
	public ConfTabTransacao() {
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
		
		Object text = table.getValueAt(row, 2);
		
		if (text != null && text.equals("EXCLUÍDO")){
			
			fundo = Color.RED;
			fonte = Color.WHITE;
			
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