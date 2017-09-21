package de.bflader.clol.application.control;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.bflader.clol.application.view.MainWindow;
import de.bflader.clol.common.gui.UIHelper;
import de.bflader.clol.entry.Entry;
import de.bflader.clol.entry.view.EntryEditor;
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
		window.journalControlPanel.journalCbb.addActionListener(this::onJournalSelectionChanged);
		window.journalControlPanel.newButton.addActionListener(this::onNewjournal);
		window.journalControlPanel.deleteButton.addActionListener(this::onDeletejournal);
		window.journalControlPanel.quitButton.addActionListener(this::onQuit);
		window.journalControlPanel.renameButton.addActionListener(this::onRenamejournal);
		window.entryControlPanel.newButton.addActionListener(this::onNewEntry);
		window.entryControlPanel.editButton.addActionListener(this::onEditEntry);
		window.entryControlPanel.deleteButton.addActionListener(this::onDeleteEntry);
		window.entryTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (me.getClickCount() == 2) {
					onEditEntry(null);
				}
			}
		});

		String aCommmand = "ESCAPE";
		window.frame.getRootPane().getInputMap().put(null, aCommmand);
		window.frame.getRootPane().getActionMap().put(aCommmand, new AbstractAction() {
			private static final long serialVersionUID = -5089207966171435499L;

			@Override
			public void actionPerformed(ActionEvent e) {
				onQuit(null);
			}

		});
	}

	private void onJournalSelectionChanged(ActionEvent e) {
		LOGGER.debug("Selection changed!");
		window.entryTable.model.setJournal(window.journalControlPanel.model.getSelectedItem());
	}

	private void onNewjournal(ActionEvent e) {
		String input = UIHelper.getStringFromUser("New journal name:", window.frame);
		if (StringUtils.isNotBlank(input)) {
			Journal journal = new Journal();
			journal.setName(input);
			journals.add(journal);
			window.journalControlPanel.model.setSelectedItem(journal);
		}
	}

	private void onDeletejournal(ActionEvent e) {
		Journal journal = window.journalControlPanel.model.getSelectedItem();
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
			Journal journal = window.journalControlPanel.model.getSelectedItem();
			journal.setName(input);
			window.journalControlPanel.model.fireChangeEvent(null);
		}

	}

	private void onNewEntry(ActionEvent e) {
		Journal journal = window.journalControlPanel.model.getSelectedItem();
		if (journal != null) {
			Entry entry = new Entry();
			EntryEditor editor = new EntryEditor(window.frame);
			editor.setValuesFrom(entry);
			editor.setModal(true);
			editor.setVisible(true);
			if (editor.exitedWithSave()) {
				editor.writeToEntry(entry);
				journal.addEntry(entry);
				window.entryTable.model.fireTableDataChanged();
			}
		}
	}

	private void onEditEntry(ActionEvent e) {
		int selectedRow = window.entryTable.getSelectedRow();
		if (selectedRow > -1) {
			int modelIndex = window.entryTable.convertRowIndexToModel(selectedRow);
			Entry entry = window.entryTable.model.getEntry(modelIndex);
			EntryEditor editor = new EntryEditor(window.frame);
			editor.setValuesFrom(entry);
			editor.setModal(true);
			editor.setVisible(true);
			if (editor.exitedWithSave()) {
				editor.writeToEntry(entry);
				window.entryTable.model.fireTableDataChanged();
			}
		}
	}

	private void onDeleteEntry(ActionEvent e) {
		int selectedRow = window.entryTable.getSelectedRow();
		if (selectedRow > -1) {
			int modelIndex = window.entryTable.convertRowIndexToModel(selectedRow);
			Entry entry = window.entryTable.model.getEntry(modelIndex);
			window.journalControlPanel.model.getSelectedItem().remove(entry);
		}
	}

	private void onQuit(ActionEvent e) {
		try {
			if (journals != null) {
				Persistence.savejournals(journals);
			}
		} catch (Exception ex) {
			LOGGER.error("Something went wrong while saving journals.", ex);
		}
		window.frame.dispose();
	}

	@Override
	public void windowClosing(WindowEvent e) {
		onQuit(null);
	}

	public void setJournals(ObservableList<Journal> journals) {
		window.journalControlPanel.setJournals(journals);
		this.journals = journals;
	}

}
