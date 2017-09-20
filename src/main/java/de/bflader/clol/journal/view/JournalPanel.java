package de.bflader.clol.journal.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import de.bflader.clol.common.gui.UIHelper;
import de.bflader.clol.journal.Journal;

public class JournalPanel extends JPanel {

	private static final long serialVersionUID = -8975071090162245440L;

	public JournalTable table = new JournalTable();
	public JButton newButton = new JButton("New");
	public JButton editButton = new JButton("Edit");
	public JButton deleteButton = new JButton("Delete");

	public JournalPanel() {
		super(new GridBagLayout());

		newButton.setToolTipText("Create a new entry.");
		newButton.setIcon(UIHelper.getIcon("note_add.png"));

		editButton.setToolTipText("Create a new entry.");
		editButton.setIcon(UIHelper.getIcon("note_edit.png"));

		deleteButton.setToolTipText("Create a new entry.");
		deleteButton.setIcon(UIHelper.getIcon("note_delete.png"));

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = UIHelper.DEFAULT_COMPONENT_INSETS;

		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 5;
		add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),
				c);

		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		c.fill = GridBagConstraints.NONE;
		c.gridy = 1;
		c.weighty = 0;

		c.gridx = 0;
		add(new JPanel(), c);

		c.weightx = 0;
		c.gridx++;
		add(newButton, c);

		c.gridx++;
		add(editButton, c);

		c.gridx++;
		add(deleteButton, c);

		c.weightx = 1;
		c.gridx++;
		add(new JPanel(), c);
	}

	public void setJournal(Journal journal) {
		table.model.setJournal(journal);
	}
}
