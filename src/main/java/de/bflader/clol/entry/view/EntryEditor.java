package de.bflader.clol.entry.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import de.bflader.clol.common.game.Role;
import de.bflader.clol.entry.JournalEntry;

public class EntryEditor extends JDialog {

	private static final long serialVersionUID = -7928758440802639859L;

	private EntryPanel entryPanel = new EntryPanel();
	private JButton saveButton = new JButton("Save");
	private JButton cancelButton = new JButton("Cancel");
	private boolean exitedWithSave = false;

	public EntryEditor(Frame parent) {
		super(parent);
		setLayout(new BorderLayout());
		saveButton.addActionListener(this::onSaveButtonClick);
		cancelButton.addActionListener(this::onCancelButtonClick);
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(cancelButton);
		buttonPanel.add(saveButton);
		add(entryPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		pack();
		setResizable(false);
		setMinimumSize(new Dimension(500, getHeight()));
		setLocationRelativeTo(parent);
	}

	private void onSaveButtonClick(ActionEvent e) {
		exitedWithSave = true;
		dispose();
	}

	private void onCancelButtonClick(ActionEvent e) {
		dispose();
	}

	public void setValuesFrom(JournalEntry entry) {
		if (entry != null) {
			entryPanel.playedCbb.setSelectedItem(entry.getPlayedChampion());
			entryPanel.opponentCbb.setSelectedItem(entry.getOpponentChampion());
			entryPanel.roleCbb.setSelectedItem(entry.getRole());
			entryPanel.textArea.setText(entry.getText());
			entryPanel.ratingCbb.setSelectedItem(entry.getRating());
		}
	}

	public void writeToEntry(JournalEntry entry) {
		entry.setOpponentChampion((String) entryPanel.opponentCbb.getSelectedItem());
		entry.setPlayedChampion((String) entryPanel.playedCbb.getSelectedItem());
		entry.setRole((Role) entryPanel.roleCbb.getSelectedItem());
		entry.setText(entryPanel.textArea.getText());
		entry.setRating((int) entryPanel.ratingCbb.getSelectedItem());
	}

	public boolean exitedWithSave() {
		return exitedWithSave;
	}

}
