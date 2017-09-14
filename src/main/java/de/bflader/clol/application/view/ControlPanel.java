package de.bflader.clol.application.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import de.bflader.clol.common.gui.UIHelper;
import de.bflader.clol.journal.Journal;
import de.bflader.clol.journal.view.JournalComboBoxModel;
import javafx.collections.ObservableList;

public class ControlPanel extends JPanel implements Observer {

	private static final long serialVersionUID = 2563392097558724881L;

	public JComboBox<Journal> journalCbb = new JComboBox<>();
	public JButton newButton = new JButton("New");
	public JButton renameButton = new JButton("Rename");
	public JButton deleteButton = new JButton("Delete");
	public JButton quitButton = new JButton("Quit");
	public JournalComboBoxModel model = new JournalComboBoxModel();

	public ControlPanel() {
		super(new GridBagLayout());

		journalCbb.setModel(model);
		Dimension d = journalCbb.getPreferredSize();
		d.width = 150;
		journalCbb.setMinimumSize(d);
		journalCbb.setPreferredSize(d);

		newButton.setToolTipText("Create a new journal.");
		newButton.setIcon(UIHelper.getIcon("report_add.png"));
		newButton.setHorizontalAlignment(SwingConstants.LEFT);

		renameButton.setToolTipText("Rename the currently selected journal.");
		renameButton.setIcon(UIHelper.getIcon("report_edit.png"));
		renameButton.setHorizontalAlignment(SwingConstants.LEFT);

		deleteButton.setToolTipText("Delete the currently selected journal.");
		deleteButton.setIcon(UIHelper.getIcon("report_delete.png"));
		deleteButton.setHorizontalAlignment(SwingConstants.LEFT);

		quitButton.setToolTipText("Quit the application.");
		quitButton.setIcon(UIHelper.getIcon("door_in.png"));

		setup();
	}

	public void setup() {

		GridBagConstraints c = new GridBagConstraints();
		c.insets = UIHelper.DEFAULT_COMPONENT_INSETS;
		c.fill = GridBagConstraints.HORIZONTAL;

		c.gridy = 0;
		c.gridx = 0;
		c.gridwidth = 3;
		add(journalCbb, c);

		c.gridwidth = 1;
		c.gridheight = 4;
		c.weightx = 1;
		c.weighty = 1;
		c.gridy++;
		add(new JPanel(), c);

		c.gridheight = 4;
		c.gridx = 2;
		add(new JPanel(), c);

		c.gridheight = 1;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 1;
		add(newButton, c);

		c.gridy++;
		add(renameButton, c);

		c.gridy++;
		add(deleteButton, c);

		c.weighty = 1;
		c.gridy++;
		add(new JPanel(), c);

		c.weighty = 0;
		c.gridwidth = 3;
		c.gridy++;
		c.gridx = 0;
		add(quitButton, c);
	}

	@Override
	public void update(Observable o, Object arg) {

	}

	public void setJournals(ObservableList<Journal> journals) {
		model.setJournals(journals);
	}
}
