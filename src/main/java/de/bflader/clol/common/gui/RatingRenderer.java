package de.bflader.clol.common.gui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public final class RatingRenderer implements TableCellRenderer, ListCellRenderer<Integer> {

	private DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();
	private DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();

	@Override
	public Component getListCellRendererComponent(JList<? extends Integer> list, Integer value, int index,
			boolean isSelected, boolean cellHasFocus) {
		JLabel label = (JLabel) listRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		label.setIcon(UIHelper.getRatingImage((int) value));
		return label;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		JLabel label = (JLabel) tableRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
				column);
		label.setIcon(UIHelper.getRatingImage((int) value));
		return label;

		// Component comp = super.getTableCellRendererComponent(table, value,
		// isSelected, hasFocus, row, column);
		// JLabel img = UIHelper.getRatingImage((int) value);
		// if (isSelected) {
		// img.setBackground(UIManager.getColor("Table.selectionBackground"));
		// img.setForeground(UIManager.getColor("Table.selectionForeground"));
		// } else {
		// img.setBackground(UIManager.getColor("Table.background"));
		// img.setForeground(UIManager.getColor("Table.foreground"));
		// }
		// return img;
	}

}