package de.bflader.clol.application;

import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.commons.lang3.SystemUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.bflader.clol.application.control.MainWindowController;
import de.bflader.clol.application.view.MainWindow;
import de.bflader.clol.common.game.RiotApiHelper;
import de.bflader.clol.common.gui.UIHelper;
import de.bflader.clol.persistence.Persistence;

public class Application implements Runnable {

	private static final Logger LOGGER = LogManager.getLogger(Application.class);

	@Override
	public void run() {
		setLookAndFeel();
		MainWindow window = new MainWindow();
		MainWindowController controller = new MainWindowController(window);
		window.frame.setEnabled(false);
		window.frame.setVisible(true);
		new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				window.statusPanel.setStatus("Preparing folder", UIHelper.getIcon("report.png"));
				Persistence.prepareFolder();

				window.statusPanel.setStatus("Preparing Riot API", UIHelper.getIcon("report.png"));
				RiotApiHelper.init("");

				window.statusPanel.setStatus("Preparing champion list", UIHelper.getIcon("report.png"));
				RiotApiHelper.prepareChampions();
				window.filterPanel.udpateChampions();

				window.statusPanel.setStatus("Loading diaries", UIHelper.getIcon("report.png"));
				controller.setJournals(Persistence.loadJournals());

				window.statusPanel.setStatus(null, null);
				window.frame.setEnabled(true);
				return null;
			}
		}.execute();
	}

	private static void setLookAndFeel() {
		try {
			if (SystemUtils.IS_OS_WINDOWS) {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				LOGGER.debug("Windows detected -> SystemLookAndFeel");
			} else {
				for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
					if ("Nimbus".equals(info.getName())) {
						UIManager.setLookAndFeel(info.getClassName());
						LOGGER.debug("Nimbus-LookAndFeel set.");
						break;
					}
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			LOGGER.warn("Failed to set LookAndFeel.", e);
		}
	}
}
