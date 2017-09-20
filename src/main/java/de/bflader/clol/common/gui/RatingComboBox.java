package de.bflader.clol.common.gui;

import javax.swing.JComboBox;

public class RatingComboBox extends JComboBox<Integer> {

	private static final long serialVersionUID = -1404574869909750884L;

	public RatingComboBox() {
		for (int i = 0; i < 11; i++) {
			addItem(i);
		}
		setRenderer(new RatingRenderer());
	}
}
