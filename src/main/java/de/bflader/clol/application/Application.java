package de.bflader.clol.application;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.bflader.clol.common.Role;
import de.bflader.clol.common.UIHelper;
import de.bflader.clol.entry.Entry;
import de.bflader.clol.record.Record;
import de.bflader.clol.record.RecordPanel;

public class Application implements Runnable {

	private static final Logger LOG = LogManager.getLogger(Application.class);

	private JLabel status = new JLabel();

	@Override
	public void run() {
		setLookAndFeel();
		setupAndShowGui();
		doBackgroundLoading();
	}

	public void setupAndShowGui() {
		JFrame frame = new JFrame();
		frame.setTitle("CLoL");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setMinimumSize(new Dimension(800, 600));
		frame.setLocationRelativeTo(null);

		Record record = new Record();
		record.addEntry(new Entry());
		record.addEntry(new Entry());
		record.addEntry(new Entry());
		record.addEntry(new Entry());
		frame.add(new RecordPanel(record), BorderLayout.CENTER);

		JPanel controlPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = UIHelper.DEFAULT_COMPONENT_INSETS;
		controlPanel.add(new JComboBox<>(Role.values()), c);
		c.gridy++;
		controlPanel.add(new JButton("New"), c);
		c.gridy++;
		controlPanel.add(new JButton("Delete"), c);
		c.gridy++;
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 1;
		controlPanel.add(new JPanel(), c);
		c.gridy++;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 0;
		controlPanel.add(new JButton("Quit"), c);

		frame.add(controlPanel, BorderLayout.EAST);

		JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		statusPanel.add(status);
		statusPanel.getInsets().set(6, 6, 6, 6);
		statusPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6),
				BorderFactory.createEtchedBorder()));
		frame.add(statusPanel, BorderLayout.SOUTH);

		frame.pack();
		frame.setVisible(true);
	}

	private void doBackgroundLoading() {
		setStatus("Loading records.", UIHelper.getIcon("database_refresh.png"));

	}

	public static void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			LOG.warn("Failed to set Look and Feel!", e);
		}
	}

	public void setStatus(String message, Icon icon) {
		status.setText(message);
		status.setIcon(icon);
	}

}
