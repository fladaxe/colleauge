package de.bflader.clol.application;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.bflader.clol.application.view.RecordControlPanel;
import de.bflader.clol.application.view.StatusPanel;
import de.bflader.clol.common.UIHelper;
import de.bflader.clol.entry.Entry;
import de.bflader.clol.persistence.Persistence;
import de.bflader.clol.record.Record;
import de.bflader.clol.record.RecordPanel;

public class Application extends WindowAdapter implements Runnable {

	private static final Logger LOGGER = LogManager.getLogger(Application.class);

	private JFrame frame = new JFrame();
	private RecordPanel recordPanel = new RecordPanel();
	private StatusPanel statusPanel = new StatusPanel();
	private RecordControlPanel controlPanel = new RecordControlPanel();
	private List<Record> records = new ArrayList<>();
	private Properties config;

	@Override
	public void run() {
		setLookAndFeel();
		setupWindow();
		setupControl();
		doBackgroundLoading();
	}

	private void setupWindow() {
		frame.setTitle("CLoL");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setMinimumSize(new Dimension(800, 600));
		frame.setLocationRelativeTo(null);
		frame.add(recordPanel, BorderLayout.CENTER);
		frame.add(controlPanel, BorderLayout.EAST);
		frame.add(statusPanel, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
	}

	private void setupControl() {
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				Persistence.saveConfig(config);
			}
		});

		controlPanel.addRecordSelectionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Record record = controlPanel.getSelectedRecord();
				if (record != null) {
					LOGGER.debug("Record selected: " + record.getName());
					recordPanel.setRecord(record);
				}
			}
		});

		controlPanel.setNewAction(new AbstractAction("New") {
			private static final long serialVersionUID = 3529717484073084693L;

			@Override
			public void actionPerformed(ActionEvent e) {
				String input = UIHelper.getStringFromUser("Insert record name:", frame);
				if (StringUtils.isNotBlank(input)) {
					Record record = new Record();
					record.setName(input);
					Persistence.saveRecord(record);
					records.add(record);
					controlPanel.setRecords(records);
					controlPanel.setSelectedRecord(record);
				}
			}
		});

		controlPanel.setDeleteAction(new AbstractAction("Delete") {
			private static final long serialVersionUID = -5736079911920773043L;

			@Override
			public void actionPerformed(ActionEvent e) {
				Record record = controlPanel.getSelectedRecord();
				if (record != null) {
					records.remove(record);
					Persistence.deleteRecord(record);
					controlPanel.setRecords(records);
				}
			}
		});

		controlPanel.setQuitAction(new AbstractAction("Quit") {
			private static final long serialVersionUID = 8421229576449878117L;

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		controlPanel.setRenameAction(new AbstractAction("Rename") {
			private static final long serialVersionUID = -3068524863925574218L;

			@Override
			public void actionPerformed(ActionEvent e) {
				Record record = controlPanel.getSelectedRecord();
				if (record != null) {
					String input = UIHelper.getStringFromUser("Insert new record name:", frame);
					if (StringUtils.isNotBlank(input)) {
						record.setName(input);
						Persistence.saveRecord(record);
						controlPanel.setRecords(records);
					}
				}
			}
		});

		recordPanel.setNewEntryAction(new AbstractAction("New") {
			private static final long serialVersionUID = 170375134195055371L;

			@Override
			public void actionPerformed(ActionEvent e) {
				Record record = controlPanel.getSelectedRecord();
				if (record != null) {
					// JDialog dialog = new JDialog(frame);
					// Entry entry = new Entry();
					// dialog.add(new EntryPanel(entry));
					// dialog.setModal(true);
					// dialog.pack();
					// dialog.setLocationRelativeTo(frame);
					// dialog.setVisible(true);
					record.addEntry(new Entry());
					LOGGER.debug("New entry added.");
					Persistence.saveRecord(record);
				}
			}
		});
	}

	private void doBackgroundLoading() {
		Persistence.prepareFolder();
		statusPanel.setStatus("Loading config.", UIHelper.getIcon("database_refresh.png"));
		config = Persistence.loadConfig();
		statusPanel.setStatus("Loading records.", UIHelper.getIcon("database_refresh.png"));
		records = Persistence.loadRecords();
		controlPanel.setRecords(records);
		statusPanel.setStatus(null, null);
	}

	private static void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			LOGGER.warn("Failed to set Look and Feel!", e);
		}
	}
}
