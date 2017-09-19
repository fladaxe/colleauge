package de.bflader.clol.application.control;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.bflader.clol.application.view.MainWindow;
import de.bflader.clol.common.gui.UIHelper;
import de.bflader.clol.entry.Entry;
import de.bflader.clol.entry.EntryEditor;
import de.bflader.clol.journal.Journal;
import de.bflader.clol.persistence.Persistence;
import javafx.collections.ObservableList;

public class MainWindowController extends WindowAdapter {

	private static final Logger LOGGER = LogManager.getLogger(MainWindowController.class);

	private MainWindow window;
	private ObservableList<Journal> journals;

	public MainWindowController(MainWindow window) {
		this.window = window;
		linkActions();
	}

	public void linkActions() {
		window.frame.addWindowListener(this);
		window.controlPanel.journalCbb.addActionListener(this::onJournalSelectionChanged);
		window.controlPanel.newButton.addActionListener(this::onNewjournal);
		window.controlPanel.deleteButton.addActionListener(this::onDeletejournal);
		window.controlPanel.quitButton.addActionListener(this::onQuit);
		window.controlPanel.renameButton.addActionListener(this::onRenamejournal);
		window.journalPanel.newButton.addActionListener(this::onNewEntry);
		window.journalPanel.editButton.addActionListener(this::onEditEntry);
		window.journalPanel.deleteButton.addActionListener(this::onDeleteEntry);
		window.journalPanel.table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (me.getClickCount() == 2) {
					onEditEntry(null);
				}
			}
		});
	}

	private void onJournalSelectionChanged(ActionEvent e) {
		LOGGER.debug("Selection changed!");
		window.journalPanel.setJournal(window.controlPanel.model.getSelectedItem());
	}

	private void onNewjournal(ActionEvent e) {
		String input = UIHelper.getStringFromUser("New journal name:", window.frame);
		if (StringUtils.isNotBlank(input)) {
			Journal journal = new Journal();
			journal.setName(input);
			journals.add(journal);
			window.controlPanel.model.setSelectedItem(journal);
		}
	}

	private void onDeletejournal(ActionEvent e) {
		Journal journal = window.controlPanel.model.getSelectedItem();
		if (journal != null) {
			if (UIHelper.getConfirmationFromUser("Delete '" + journal.getName() + "'?", window.frame)) {
				Persistence.deletejournal(journal);
				journals.remove(journal);
			}
		}
	}

	private void onRenamejournal(ActionEvent e) {
		String input = UIHelper.getStringFromUser("New journal name:", window.frame);
		if (input != null) {
			Journal journal = window.controlPanel.model.getSelectedItem();
			journal.setName(input);
			window.controlPanel.model.fireChangeEvent(null);
		}

	}

	private void onNewEntry(ActionEvent e) {
		Journal journal = window.controlPanel.model.getSelectedItem();
		if(journal != null){
			Entry entry = new Entry();
			EntryEditor editor = new EntryEditor(window.frame);
			editor.setValuesFrom(entry);
			editor.setModal(true);
			editor.setVisible(true);
			if(editor.exitedWithSave()){
				editor.writeToEntry(entry);
				journal.addEntry(entry);
				window.journalPanel.table.model.fireTableDataChanged();
			}
		}
	}

	private void onEditEntry(ActionEvent e) {
		int selectedRow = window.journalPanel.table.getSelectedRow();
		if (selectedRow > -1) {
			int modelIndex = window.journalPanel.table.convertRowIndexToModel(selectedRow);
			Entry entry = window.journalPanel.table.model.getEntry(modelIndex);
			EntryEditor editor = new EntryEditor(window.frame);
			editor.setValuesFrom(entry);
			editor.setModal(true);
			editor.setVisible(true);
			if(editor.exitedWithSave()){
				editor.writeToEntry(entry);
				window.journalPanel.table.model.fireTableDataChanged();
			}
		}
	}

	private void onDeleteEntry(ActionEvent e) {
		int selectedRow = window.journalPanel.table.getSelectedRow();
		if (selectedRow > -1) {
			int modelIndex = window.journalPanel.table.convertRowIndexToModel(selectedRow);
			Entry entry = window.journalPanel.table.model.getEntry(modelIndex);
			window.controlPanel.model.getSelectedItem().remove(entry);
		}
	}

	private void onQuit(ActionEvent e) {
		Persistence.savejournals(journals);
		window.frame.dispose();
	}

	@Override
	public void windowClosing(WindowEvent e) {
		onQuit(null);
	}

	public void setJournals(ObservableList<Journal> journals) {
		window.controlPanel.setJournals(journals);
		this.journals = journals;
	}

}
