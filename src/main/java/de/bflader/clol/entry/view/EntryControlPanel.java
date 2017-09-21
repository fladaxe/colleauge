package de.bflader.clol.entry.view;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.bflader.clol.common.gui.UIHelper;

public class EntryControlPanel extends JPanel {

	private static final long serialVersionUID = -8975071090162245440L;

	public JButton newButton = new JButton("New");
	public JButton editButton = new JButton("Edit");
	public JButton deleteButton = new JButton("Delete");

	public EntryControlPanel() {
		super(new FlowLayout(FlowLayout.CENTER));

		newButton.setToolTipText("Create a new entry.");
		newButton.setIcon(UIHelper.getIcon("note_add.png"));

		editButton.setToolTipText("Create a new entry.");
		editButton.setIcon(UIHelper.getIcon("note_edit.png"));

		deleteButton.setToolTipText("Create a new entry.");
		deleteButton.setIcon(UIHelper.getIcon("note_delete.png"));

		add(newButton);
		add(editButton);
		add(deleteButton);
	}
}
