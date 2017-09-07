package de.bflader.clol.record.table;

import java.util.Date;
import java.util.Enumeration;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

import de.bflader.clol.record.Record;

public class RecordTable extends JTable {

	private static final long serialVersionUID = 5093541026718964069L;

	private RecordTableModel model;

	public RecordTable(Record record) {
		super();
		model = new RecordTableModel(record);
		setModel(model);
		setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		setDefaultRenderer(Date.class, new DateTableCellRenderer());
		getTableHeader().setReorderingAllowed(false);
		setAutoCreateRowSorter(true);
		adjustColumnWidths();
	}

	private void adjustColumnWidths() {
		Enumeration<TableColumn> columns = getTableHeader().getColumnModel().getColumns();
		while (columns.hasMoreElements()) {
			TableColumn column = columns.nextElement();
			RecordTableColumn recordColumn = RecordTableColumn.values()[column.getModelIndex()];
			column.setMinWidth(recordColumn.getMinWidth());
			column.setPreferredWidth(recordColumn.getPreferredWidth());
		}
	}

	public Record getRecord() {
		return model.getRecord();
	}

	public void setRecord(Record record) {
		model.setRecord(record);
	}

}
