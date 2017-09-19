package de.bflader.clol.entry;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import de.bflader.clol.common.game.Role;

public class EntryEditor extends JDialog {

	private static final long serialVersionUID = -7928758440802639859L;

	private EntryPanel entryPanel = new EntryPanel();
	private JButton saveButton = new JButton("Save");
	private JButton cancelButton = new JButton("Cancel");
	private boolean exitedWithSave = false;

	public EntryEditor(Frame parent) {
		super(parent);
		setLayout(new BorderLayout());
		setMinimumSize(new Dimension(600, 400));
		setLocationRelativeTo(parent);
		saveButton.addActionListener(this::onSaveButtonClick);
		cancelButton.addActionListener(this::onCancelButtonClick);
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(cancelButton);
		buttonPanel.add(saveButton);
		add(entryPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		pack();
	}

	private void onSaveButtonClick(ActionEvent e) {
		exitedWithSave = true;
		dispose();
	}

	private void onCancelButtonClick(ActionEvent e) {
		dispose();
	}

	public void setValuesFrom(Entry entry) {
		if (entry != null) {
			entryPanel.playedChampion.setSelectedItem(entry.getPlayedChampion());
			entryPanel.opponentChampion.setSelectedItem(entry.getOpponentChampion());
			entryPanel.role.setSelectedItem(entry.getRole());
			entryPanel.textArea.setText(entry.getText());
		}
	}

	public void writeToEntry(Entry entry) {
		entry.setOpponentChampion((String) entryPanel.opponentChampion.getSelectedItem());
		entry.setPlayedChampion((String) entryPanel.playedChampion.getSelectedItem());
		entry.setRole((Role) entryPanel.role.getSelectedItem());
		entry.setText(entryPanel.textArea.getText());
	}

	public boolean exitedWithSave() {
		return exitedWithSave;
	}

}
