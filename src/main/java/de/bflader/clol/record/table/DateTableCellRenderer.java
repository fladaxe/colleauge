package de.bflader.clol.record.table;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class DateTableCellRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 6104943570140629819L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		return super.getTableCellRendererComponent(table, format.format((Date) value), isSelected, hasFocus, row,
				column);
	}
}