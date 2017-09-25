package de.bflader.clol.application.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import de.bflader.clol.entry.table.EntryTable;
import de.bflader.clol.entry.view.EntryControlPanel;
import de.bflader.clol.journal.view.JournalControlPanel;

public class MainWindow {

	public JFrame frame = new JFrame();
	public FilterPanel filterPanel = new FilterPanel();
	public EntryControlPanel entryControlPanel = new EntryControlPanel();
	public EntryTable entryTable = new EntryTable();
	public StatusPanel statusPanel = new StatusPanel();
	public JournalControlPanel journalControlPanel = new JournalControlPanel();

	public MainWindow() {
		JPanel entryPanel = new JPanel(new BorderLayout());
		entryPanel.add(filterPanel, BorderLayout.NORTH);
		entryPanel.add(new JScrollPane(entryTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
		entryPanel.add(entryControlPanel, BorderLayout.SOUTH);
		frame.setTitle("CLoL");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.add(entryPanel, BorderLayout.CENTER);
		frame.add(journalControlPanel, BorderLayout.EAST);
		frame.add(statusPanel, BorderLayout.SOUTH);
		frame.setMinimumSize(new Dimension(1024, 768));
		frame.pack();
		frame.setLocationRelativeTo(null);
	}

}
