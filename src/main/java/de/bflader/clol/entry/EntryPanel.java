package de.bflader.clol.entry;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.KeyboardFocusManager;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import de.bflader.clol.common.game.Role;
import de.bflader.clol.common.gui.UIHelper;

public class EntryPanel extends JPanel {

	private static final long serialVersionUID = 8129560510781148852L;

	private Entry entry;

	private JComboBox<String> playedChampion = new JComboBox<>();;
	private JComboBox<String> opponentChampion = new JComboBox<>();
	private JComboBox<Role> role = new JComboBox<>(Role.values());
	private JTextArea textArea = new JTextArea();

	public EntryPanel() {
		this(null);
	}

	public EntryPanel(Entry entry) {
		super();
		setup();
		setEntry(entry);
	}

	private void setup() {
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textArea.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
		textArea.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = UIHelper.DEFAULT_COMPONENT_INSETS;
		int x = 1;
		c.gridy = 0;

		c.gridx = x++;
		add(new JLabel("Role"), c);
		c.gridx = x++;
		add(role, c);
		c.gridx = x++;
		add(new JLabel("Played"), c);
		c.gridx = x++;
		add(playedChampion, c);
		c.gridx = x++;
		add(new JLabel("Opponent"), c);
		c.gridx = x++;
		add(opponentChampion, c);
		c.gridx = x++;

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = x;
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		add(scrollPane, c);
	}

	public Entry getEntry() {
		return entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
		if (entry != null) {
			playedChampion.setSelectedItem(entry.getPlayedChampion());
			opponentChampion.setSelectedItem(entry.getOpponentChampion());
			role.setSelectedItem(entry.getRole());
			textArea.setText(entry.getText());
		}
	}
}
