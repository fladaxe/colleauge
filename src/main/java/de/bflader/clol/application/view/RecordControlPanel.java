package de.bflader.clol.application.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.bflader.clol.common.UIHelper;
import de.bflader.clol.record.Record;

public class RecordControlPanel extends JPanel {

	private static final long serialVersionUID = 2563392097558724881L;

	private static final Logger LOGGER = LogManager.getLogger(RecordControlPanel.class);

	private JButton newButton = new JButton();
	private JButton renameButton = new JButton();
	private JButton deleteButton = new JButton();
	private JButton quitButton = new JButton();
	private JComboBox<Record> recordCbb = new JComboBox<>();

	private List<Record> records;

	public RecordControlPanel() {
		super(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = UIHelper.DEFAULT_COMPONENT_INSETS;
		Dimension d = recordCbb.getPreferredSize();
		d.width = 150;
		recordCbb.setMinimumSize(d);
		recordCbb.setPreferredSize(d);
		add(recordCbb, c);

		c.gridy++;
		add(newButton, c);

		c.gridy++;
		add(renameButton, c);

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
		this.records = records;
		refresh();
	}

	public void refresh() {
		int selectedIndex = recordCbb.getSelectedIndex();
		Object selectedItem = recordCbb.getSelectedItem();
		recordCbb.setModel(new DefaultComboBoxModel<Record>(records.toArray(new Record[records.size()])));
		recordCbb.setSelectedItem(selectedItem);
		if (recordCbb.getSelectedIndex() == -1 && selectedIndex < recordCbb.getModel().getSize()) {
			LOGGER.debug("Trying to set previous index: " + selectedIndex);
			recordCbb.setSelectedItem(recordCbb.getItemAt(selectedIndex));
		}
		if (recordCbb.getSelectedIndex() == -1 && recordCbb.getModel().getSize() > 0) {
			LOGGER.debug("Setting index to 0.");
			recordCbb.setSelectedItem(recordCbb.getItemAt(0));
		}
		LOGGER.debug("Final index: " + recordCbb.getSelectedIndex());
	}

	public void addRecordSelectionListener(ActionListener listener) {
		recordCbb.addActionListener(listener);
	}

	public void setQuitAction(Action action) {
		quitButton.setAction(action);
	}

	public void setDeleteAction(Action action) {
		deleteButton.setAction(action);
	}

	public void setNewAction(Action action) {
		newButton.setAction(action);
	}

	public void setRenameAction(Action action) {
		renameButton.setAction(action);
	}

	public Record getSelectedRecord() {
		return (Record) recordCbb.getSelectedItem();
	}

	public void setSelectedRecord(Record record) {
		recordCbb.setSelectedItem(record);
	}
}
