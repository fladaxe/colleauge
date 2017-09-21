package de.bflader.clol.journal.view;

import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.bflader.clol.journal.Journal;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;

public class JournalComboBoxModel extends AbstractListModel<Journal> implements ComboBoxModel<Journal> {
	private static final long serialVersionUID = -4189345454355465260L;

	private static final Logger LOGGER = LogManager.getLogger(JournalComboBoxModel.class);

	private ObservableList<Journal> journals = FXCollections.observableList(new ArrayList<>());
	private Journal selectedJournal;
	private int selectedIndex = -1;

	public JournalComboBoxModel() {
		super();
	}

	public void setJournals(ObservableList<Journal> journals) {
		this.journals.removeListener(this::fireChangeEvent);
		this.journals = journals;
		this.journals.addListener(this::fireChangeEvent);
		if (!journals.isEmpty()) {
			setSelectedItem(journals.get(0));
		}
		fireChangeEvent(null);
		LOGGER.debug("New list with " + journals.size() + " journals set.");
	}

	public void fireChangeEvent(Change<? extends Journal> c) {
		if (journals.contains(selectedJournal)) {
			selectedIndex = journals.indexOf(selectedJournal);
		} else {
			selectedIndex = Math.min(selectedIndex, journals.size() - 1);
			if (selectedIndex > -1) {
				selectedJournal = journals.get(selectedIndex);
			} else {
				selectedJournal = null;
			}
		}
		fireContentsChanged(this, -1, -1);
		LOGGER.debug("ComboBox updated.");
	}

	@Override
	public int getSize() {
		return journals.size();
	}

	@Override
	public Journal getElementAt(int index) {
		return journals.get(index);
	}

	@Override
	public void setSelectedItem(Object anItem) {
		if (journals.contains(anItem)) {
			selectedJournal = (Journal) anItem;
			fireContentsChanged(this, -1, -1);
		}
	}

	@Override
	public Journal getSelectedItem() {
		return selectedJournal;
	}
}