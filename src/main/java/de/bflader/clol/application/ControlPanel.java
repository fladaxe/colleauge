package de.bflader.clol.application;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.bflader.clol.common.UIHelper;
import de.bflader.clol.record.Record;

public class ControlPanel extends JPanel implements Observer {

	private static final long serialVersionUID = 2563392097558724881L;

	private static final Logger LOGGER = LogManager.getLogger(ControlPanel.class);

	private JButton quitButton = new JButton("Quit");
	private JButton deleteButton = new JButton("Delete");
	private JButton newButton = new JButton("New");
	private JComboBox<Record> recordCbb = new JComboBox<>();

	public ControlPanel() {
		super(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = UIHelper.DEFAULT_COMPONENT_INSETS;
		add(recordCbb, c);

		c.gridy++;
		add(newButton, c);

		c.gridy++;
		add(deleteButton, c);

		c.fill = GridBagConstraints.BOTH;
		c.weighty = 1;
		c.gridy++;
		add(new JPanel(), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 0;
		c.gridy++;
		add(quitButton, c);
	}

	public void setRecords(List<Record> records) {
		int selectedIndex = recordCbb.getSelectedIndex();
		Object selectedItem = recordCbb.getSelectedItem();
		recordCbb.setModel(new DefaultComboBoxModel<Record>(records.toArray(new Record[records.size()])));
		recordCbb.setSelectedItem(selectedItem);
		if (recordCbb.getSelectedIndex() == -1 && selectedIndex < recordCbb.getModel().getSize()) {
			recordCbb.setSelectedIndex(selectedIndex);
		}
		if (recordCbb.getSelectedIndex() == -1 && recordCbb.getModel().getSize() > 0) {
			recordCbb.setSelectedIndex(0);
		}
		LOGGER.debug(recordCbb.getModel().getSize());
	}

	public void removeRecord(Record record) {
	}

	public void addRecord(Record record) {
		recordCbb.addItem(record);
	}

	public void setQuitAction(Action action) {
		quitButton.setAction(action);
	}

	public void setDeleteAction(Action action) {
		deleteButton.setAction(action);
	}

	public void setNewButton(Action action) {
		newButton.setAction(action);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}
}
