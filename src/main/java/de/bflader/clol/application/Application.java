package de.bflader.clol.application;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.bflader.clol.application.view.ControlPanel;
import de.bflader.clol.application.view.StatusPanel;
import de.bflader.clol.common.UIHelper;
import de.bflader.clol.persistence.Persistence;
import de.bflader.clol.record.Record;
import de.bflader.clol.record.RecordPanel;

public class Application extends WindowAdapter implements Runnable {

	private static final Logger LOG = LogManager.getLogger(Application.class);

	private RecordPanel recordPanel = new RecordPanel();
	private StatusPanel statusPanel = new StatusPanel();
	private ControlPanel controlPanel = new ControlPanel();
	private List<Record> records = new ArrayList<>();
	private Properties config;

	@Override
	public void run() {
		setLookAndFeel();
		setupWindow();
		doBackgroundLoading();
	}

	public void setupWindow() {
		JFrame frame = new JFrame();
		frame.setTitle("CLoL");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setMinimumSize(new Dimension(800, 600));
		frame.setLocationRelativeTo(null);
		frame.addWindowListener(this);
		frame.add(recordPanel, BorderLayout.CENTER);
		frame.add(controlPanel, BorderLayout.EAST);
		frame.add(statusPanel, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		Persistence.saveRecords(records);
		Persistence.saveConfig(config);
	}

	private void doBackgroundLoading() {
		Persistence.prepareFolder();
		setStatus("Loading config.", UIHelper.getIcon("database_refresh.png"));
		config = Persistence.loadConfig();
		setStatus("Loading records.", UIHelper.getIcon("database_refresh.png"));
		records = Persistence.loadRecords();
		controlPanel.setRecords(records);
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
		statusPanel.setStatus(message, icon);
	}

}
