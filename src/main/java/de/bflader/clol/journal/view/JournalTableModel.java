package de.bflader.clol.journal.view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.bflader.clol.entry.Entry;
import de.bflader.clol.journal.Journal;

public class JournalTableModel extends AbstractTableModel implements Observer {

	private static final long serialVersionUID = -2926095521089177791L;

	private static final Logger LOGGER = LogManager.getLogger(JournalTableModel.class);

	private Journal journal;

	@Override
	public int getRowCount() {
		if (getJournal() == null) {
			return 0;
		}
		return getJournal().getEntries().size();
	}

	@Override
	public int getColumnCount() {
		return JournalTableColumns.values().length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return JournalTableColumns.values()[columnIndex].getName();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return JournalTableColumns.values()[columnIndex].getType();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (getJournal() == null || getJournal().getEntries().isEmpty()) {
			return null;
		}
		Entry entry = getJournal().getEntries().get(rowIndex);
		JournalTableColumns column = JournalTableColumns.values()[columnIndex];
		switch (column) {
		case CREATED:
			return entry.getCreated();
		case RATING:
			return entry.getRating();
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

	public Journal getJournal() {
		return journal;
	}

	public void setJournal(Journal journal) {
		if (this.journal != null) {
			this.journal.deleteObserver(this);
		}
		this.journal = journal;
		if (this.journal != null) {
			this.journal.addObserver(this);
		}
		fireTableDataChanged();
		LOGGER.debug("Table changed.");
	}

	@Override
	public void update(Observable o, Object arg) {
		fireTableDataChanged();
	}

	public Entry getEntry(int index) {
		return journal.getEntries().get(index);
	}
	
	public int getIndex(Entry entry){
		return journal.getEntries().indexOf(entry);
	}

}
