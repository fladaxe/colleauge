package de.bflader.clol.entry.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.KeyboardFocusManager;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.bflader.clol.common.game.RiotApiHelper;
import de.bflader.clol.common.game.Role;
import de.bflader.clol.common.gui.UIHelper;

public class EntryPanel extends JPanel {

	private static final long serialVersionUID = 8129560510781148852L;

	public JComboBox<String> playedChampion = new JComboBox<>();;
	public JComboBox<String> opponentChampion = new JComboBox<>();
	public JComboBox<Role> role = new JComboBox<>(Role.values());
	public JTextField textArea = new JTextField();

	public EntryPanel() {
		super();
		setup();
	}

	private void setup() {
		List<String> champions = RiotApiHelper.getChampions();
		champions.add(0, "- Any -");
		playedChampion = new JComboBox<>(champions.toArray(new String[champions.size()]));
		opponentChampion = new JComboBox<>(champions.toArray(new String[champions.size()]));

		textArea.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
		textArea.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = UIHelper.DEFAULT_COMPONENT_INSETS;
		c.anchor = GridBagConstraints.LINE_END;

		c.gridheight = 2;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;
		add(new JPanel(), c);

		c.gridheight = 1;
		c.weightx = 0;
		c.gridx = 1;
		add(new JLabel("Played:"), c);
		c.gridx = 2;
		add(playedChampion, c);

		c.gridx = 1;
		c.gridy = 1;
		add(new JLabel("Opponent:"), c);
		c.gridx = 2;
		add(opponentChampion, c);

		c.gridx = 3;
		c.gridy = 0;
		add(new JLabel("Role:"), c);
		c.gridx = 4;
		add(role, c);

		c.weightx = 1;
		c.gridheight = 2;
		c.gridx++;
		c.gridx = 5;
		add(new JPanel(), c);

		c.gridwidth = 6;
		c.gridheight = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		add(textArea, c);
	}
}
