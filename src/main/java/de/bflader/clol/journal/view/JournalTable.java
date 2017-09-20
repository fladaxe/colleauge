package de.bflader.clol.journal.view;

import java.util.Date;
import java.util.Enumeration;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;

import de.bflader.clol.common.gui.DateTableCellRenderer;
import de.bflader.clol.common.gui.RatingRenderer;

public class JournalTable extends JTable {

	private static final long serialVersionUID = 5093541026718964069L;

	public JournalTableModel model = new JournalTableModel();

	public JournalTable() {
		super();
		setModel(model);
		setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setDefaultRenderer(Date.class, new DateTableCellRenderer());
		setDefaultRenderer(Integer.class, new RatingRenderer());
		getTableHeader().setReorderingAllowed(false);
		setAutoCreateRowSorter(true);
		adjustColumnWidths();
	}

	private void adjustColumnWidths() {
		Enumeration<TableColumn> columns = getTableHeader().getColumnModel().getColumns();
		while (columns.hasMoreElements()) {
			TableColumn column = columns.nextElement();
			JournalTableColumns journalColumn = JournalTableColumns.values()[column.getModelIndex()];
			column.setMinWidth(journalColumn.getMinWidth());
			column.setPreferredWidth(journalColumn.getPreferredWidth());
		}
	}
}
