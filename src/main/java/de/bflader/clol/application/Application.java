package de.bflader.clol.application;

import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.bflader.clol.application.control.MainWindowController;
import de.bflader.clol.application.view.MainWindow;
import de.bflader.clol.common.gui.UIHelper;
import de.bflader.clol.journal.Journal;
import de.bflader.clol.persistence.Persistence;
import javafx.collections.ObservableList;

public class Application implements Runnable {

	private static final Logger LOGGER = LogManager.getLogger(Application.class);

	@Override
	public void run() {
		setLookAndFeel();
		Persistence.prepareFolder();
		MainWindow window = new MainWindow();
		MainWindowController controller = new MainWindowController(window);
		window.frame.setVisible(true);
		new SwingWorker<ObservableList<Journal>, Void>() {

			@Override
			protected ObservableList<Journal> doInBackground() throws Exception {
				window.statusPanel.setStatus("Loading diaries", UIHelper.getIcon("report.png"));
				return Persistence.loadJournals();
			}

			@Override
			protected void done() {
				try {
					controller.setJournals(get());
					window.statusPanel.setStatus(null, null);
				} catch (InterruptedException | ExecutionException e) {
					LOGGER.warn("Failed to load journals.", e);
				}
			}
		}.execute();
	}

	private static void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			LOGGER.debug("LookAndFeel set successfully.");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			LOGGER.warn("Failed to set LookAndFeel.", e);
		}
	}
}
