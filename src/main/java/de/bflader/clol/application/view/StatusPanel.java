package de.bflader.clol.application.view;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import org.apache.commons.lang3.StringUtils;

import de.bflader.clol.common.gui.UIHelper;

public class StatusPanel extends JPanel {

	private static final long serialVersionUID = -2291719392745446909L;

	private JLabel label = new JLabel(" ");

	public StatusPanel() {
		super(new FlowLayout(FlowLayout.RIGHT));
		add(label);
		Border outer = BorderFactory.createEmptyBorder(3, 3, 3, 3);
		Border inner = BorderFactory.createEtchedBorder();
		setBorder(BorderFactory.createCompoundBorder(outer, inner));
	}

	public void setStatus(String message, Icon icon) {
		if (StringUtils.isBlank(message) && icon == null) {
			message = "Ready";
			icon = UIHelper.getIcon("tick.png");
		}
		label.setText(message);
		label.setIcon(icon);
	}
}
