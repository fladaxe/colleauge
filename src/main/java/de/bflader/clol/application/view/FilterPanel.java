package de.bflader.clol.application.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.bflader.clol.common.game.RiotApiHelper;
import de.bflader.clol.common.game.Role;
import de.bflader.clol.common.gui.RatingComboBox;
import de.bflader.clol.common.gui.UIHelper;

public class FilterPanel extends JPanel {

	private static final long serialVersionUID = -7028981615507222577L;

	private static final Logger LOGGER = LogManager.getLogger(FilterPanel.class);

	public RatingComboBox ratingCbb = new RatingComboBox();
	public JComboBox<String> playedCbb = new JComboBox<>();
	public JComboBox<Role> roleCbb = new JComboBox<>(Role.values());
	public JComboBox<String> opponentCbb = new JComboBox<>();

	public FilterPanel() {
		super(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = UIHelper.DEFAULT_COMPONENT_INSETS;

		c.weightx = 1;
		c.gridx = 0;
		add(new JPanel(), c);

		c.weightx = 0;
		c.gridx++;
		add(new JLabel("Rating:"), c);
		c.gridx++;
		add(ratingCbb, c);

		c.gridx++;
		add(new JLabel("Played:"), c);
		c.gridx++;
		add(opponentCbb, c);

		c.gridx++;
		add(new JLabel("Role:"), c);
		c.gridx++;
		add(roleCbb, c);

		c.gridx++;
		add(new JLabel("Opponent:"), c);
		c.gridx++;
		add(playedCbb, c);

		c.weightx = 1;
		c.gridx++;
		add(new JPanel(), c);
	}

	public void udpateChampions() {
		List<String> champs = RiotApiHelper.getChampions();
		champs.add(0, "- Any -");
		opponentCbb.setModel(new DefaultComboBoxModel<>(champs.toArray(new String[champs.size()])));
		playedCbb.setModel(new DefaultComboBoxModel<>(champs.toArray(new String[champs.size()])));
		LOGGER.debug("Champions updated.");
	}
}
