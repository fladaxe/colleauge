package de.bflader.clol.application;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.bflader.clol.common.UIHelper;
import de.bflader.clol.entry.EntryPanel;

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
		frame.setMinimumSize(new Dimension(600, 400));
		frame.setLocationRelativeTo(null);

		EntryPanel entryPanel = new EntryPanel();
		frame.add(entryPanel, BorderLayout.CENTER);

		JPanel stausPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		stausPanel.add(status);
		frame.add(stausPanel, BorderLayout.SOUTH);

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
