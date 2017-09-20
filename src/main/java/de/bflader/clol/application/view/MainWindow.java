package de.bflader.clol.application.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import de.bflader.clol.journal.view.FilterPanel;
import de.bflader.clol.journal.view.JournalPanel;

public class MainWindow {

	public JFrame frame = new JFrame();
	public FilterPanel filterPanel = new FilterPanel();
	public JournalPanel journalPanel = new JournalPanel();
	public StatusPanel statusPanel = new StatusPanel();
	public ControlPanel controlPanel = new ControlPanel();

	public MainWindow() {
		frame.setTitle("CLoL");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.add(filterPanel, BorderLayout.NORTH);
		frame.add(journalPanel, BorderLayout.CENTER);
		frame.add(controlPanel, BorderLayout.EAST);
		frame.add(statusPanel, BorderLayout.SOUTH);
		frame.setMinimumSize(new Dimension(800, 600));
		frame.pack();
		frame.setLocationRelativeTo(null);
	}

}
