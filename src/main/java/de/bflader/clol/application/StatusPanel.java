package de.bflader.clol.application;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class StatusPanel extends JPanel {

	private static final long serialVersionUID = -2291719392745446909L;

	private JLabel label = new JLabel();

	public StatusPanel() {
		super(new FlowLayout(FlowLayout.RIGHT));
		add(label);
		getInsets().set(6, 6, 6, 6);
		Border outer = BorderFactory.createEmptyBorder(6, 6, 6, 6);
		Border inner = BorderFactory.createEtchedBorder();
		setBorder(BorderFactory.createCompoundBorder(outer, inner));
	}

	public void setStatus(String message, Icon icon) {
		label.setText(message);
		label.setIcon(icon);
	}
}
