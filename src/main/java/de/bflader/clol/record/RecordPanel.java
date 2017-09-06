package de.bflader.clol.record;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Date;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import de.bflader.clol.common.UIHelper;
import de.bflader.clol.record.table.RecordTableModel;

public class RecordPanel extends JPanel {

	private static final long serialVersionUID = -8975071090162245440L;

	private RecordTableModel model;

	private JButton newButton = new JButton("New");
	private JButton editButton = new JButton("Edit");
	private JButton deleteButton = new JButton("Delete");

	public RecordPanel() {
		this(null);
	}

	public RecordPanel(Record record) {
		super(new GridBagLayout());
		model = new RecordTableModel(record);
		setup();

	}

	private void setup() {
		JTable table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		table.setDefaultRenderer(Date.class, new DateTableCellRenderer());
		table.getTableHeader().setReorderingAllowed(false);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = UIHelper.DEFAULT_COMPONENT_INSETS;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 5;
		add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), c);

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

	public void setRecord(Record record) {
		model.setRecord(record);
	}

	public Record getRecord() {
		return model.getRecord();
	}

	public void setNewEntryAction(Action action) {
		newButton.setAction(action);
	}

	public void setEditEntryAction(Action action) {
		editButton.setAction(action);
	}

	public void setRemoveAction(Action action) {
		deleteButton.setAction(action);
	}
}
