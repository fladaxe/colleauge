package de.bflader.clol.record;

import javax.swing.table.AbstractTableModel;

import de.bflader.clol.entry.Entry;

public class RecordTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -2926095521089177791L;

	private Record record;

	public RecordTableModel(Record record) {
		this.record = record;
	}

	@Override
	public int getRowCount() {
		if (getRecord() == null) {
			return 0;
		}
		return getRecord().getEntries().size();
	}

	@Override
	public int getColumnCount() {
		return RecordTableColumn.values().length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return RecordTableColumn.values()[columnIndex].getName();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return RecordTableColumn.values()[columnIndex].getType();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (getRecord() == null || getRecord().getEntries().isEmpty()) {
			return null;
		}
		Entry entry = getRecord().getEntries().get(rowIndex);
		RecordTableColumn column = RecordTableColumn.values()[columnIndex];
		switch (column) {
		case CREATED:
			return entry.getCreated();
		case OPPONENT_CHAMPION:
			return entry.getOpponentChampion();
		case PLAYED_CHAMPION:
			return entry.getPlayedChampion();
		case ROLE:
			return entry.getRole();
		case TEXT:
			return entry.getText();
		default:
			throw new RuntimeException("Unkown Column: " + column);
		}
	}

	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
		this.fireTableDataChanged();
	}

}
