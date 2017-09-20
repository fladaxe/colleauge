package de.bflader.clol.journal.view;

import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import de.bflader.clol.common.game.RiotApiHelper;

public class FilterPanel extends JPanel {

	private static final long serialVersionUID = -7028981615507222577L;

	public JComboBox<String> opponentCbb = new JComboBox<>();
	public JComboBox<String> playedCbb = new JComboBox<>();

	public FilterPanel() {
		super(new GridBagLayout());
		add(opponentCbb);
		add(playedCbb);
	}

	public void udpateChampions() {
		List<String> champs = RiotApiHelper.getChampions();
		champs.add(0, "- Any -");
		opponentCbb.setModel(new DefaultComboBoxModel<>(champs.toArray(new String[champs.size()])));
		playedCbb.setModel(new DefaultComboBoxModel<>(champs.toArray(new String[champs.size()])));
	}
}
