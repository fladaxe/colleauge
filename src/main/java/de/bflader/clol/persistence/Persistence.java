package de.bflader.clol.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

import javax.xml.bind.JAXBException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.bflader.clol.common.XmlUtil;
import de.bflader.clol.journal.Journal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Persistence {

	private static final Logger LOGGER = LogManager.getLogger(Persistence.class);

	private static final String BASE_PATH = Paths.get(System.getProperty("user.home"), ".clol").toString();
	private static final String JOURNALS_PATH = Paths.get(BASE_PATH, "journals").toString();
	private static final String CONFIG_PATH = Paths.get(BASE_PATH, "config.properties").toString();
	private static final String CHAMP_BACKUP_PATH = Paths.get(BASE_PATH, "champions.txt").toString();

	public static void prepareFolder() {
		try {
			Files.createDirectories(Paths.get(BASE_PATH));
			Files.createDirectories(Paths.get(JOURNALS_PATH));
		} catch (IOException e) {
			throw new RuntimeException("Failed to create directories.", e);
		}
		LOGGER.debug("Folder under " + BASE_PATH + " prepared.");
	}

	public static ObservableList<Journal> loadJournals() {
		ObservableList<Journal> journals = FXCollections.observableList(new ArrayList<>());
		try (Stream<Path> files = Files.list(Paths.get(JOURNALS_PATH))) {
			files.forEach(path -> {
				if (path.toString().endsWith(".xml")) {
					try {
						Journal journal = XmlUtil.unmarshall(path.toFile(), Journal.class);
						journal.setFilePath(path);
						journals.add(journal);
					} catch (JAXBException e) {
						LOGGER.error("Failed to unmarshall " + path, e);
					}
				}
			});
		} catch (IOException e) {
			LOGGER.error("Failed to list files in " + JOURNALS_PATH, e);
		}
		LOGGER.debug("Loaded " + journals.size() + " journals");
		return journals;
	}

	public static void savejournals(List<Journal> journals) {
		for (Journal journal : journals) {
			savejournal(journal);
		}
		LOGGER.debug("Saved " + journals.size() + " journals.");
	}

	public static void savejournal(Journal journal) {
		try {
			Path path = journal.getFilePath();
			if (path == null) {
				path = Paths.get(JOURNALS_PATH, generateFilename(journal));
			}
			String xmlString = XmlUtil.convertToXml(journal, Journal.class);
			Files.deleteIfExists(path);
			try (OutputStream os = Files.newOutputStream(path)) {
				os.write(xmlString.getBytes());
				LOGGER.debug("Saved journal: " + journal.getName());
			}
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Failed to create filename: " + journal.getName(), e);
		} catch (IOException e) {
			LOGGER.error("Excpetion during save process for journal " + journal.getName(), e);
		} catch (JAXBException e) {
			LOGGER.error("Failed to marshall journal " + journal.getName(), e);
		}
	}

	private static String generateFilename(Journal journal) {
		return DigestUtils.sha1Hex(journal.getName() + journal.getCreated().getTime()) + ".xml";
	}

	public static void deletejournal(Journal journal) {
		if (journal == null || journal.getFilePath() == null) {
			return;
		}
		Path path = journal.getFilePath();
		try {
			Files.deleteIfExists(path);
			LOGGER.debug("Deleted journal: " + journal.getName());
		} catch (IOException e) {
			LOGGER.warn("Failed to delete file: " + path, e);
		}
	}

	public static void saveConfig(Properties config) {
		try (OutputStream os = Files.newOutputStream(Paths.get(CONFIG_PATH))) {
			config.store(os, null);
		} catch (IOException e) {
			LOGGER.error("Failed to save config.", e);
		}
	}

	public static List<String> loadChampionBackup() {
		List<String> champs = new ArrayList<>();
		try {
			champs.addAll(Files.readAllLines(Paths.get(CHAMP_BACKUP_PATH)));
			LOGGER.debug("Loaded champions from backup.");
		} catch (IOException e) {
			LOGGER.warn("No champion backup found!");
		}
		return champs;
	}

	public static void saveChampionBackup(List<String> champs) {
		try {
			Files.deleteIfExists(Paths.get(CHAMP_BACKUP_PATH));
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(CHAMP_BACKUP_PATH))) {
				for (String champ : champs) {
					writer.write(champ + System.getProperty("line.separator"));
				}
			}
			LOGGER.debug("Saved new champion backup.");
		} catch (IOException e) {
			LOGGER.error("Failed to save champion backup!", e);
		}
	}

	public static List<String> loadChampionDefault() {
		List<String> champs = new ArrayList<>();
		try (InputStream is = Persistence.class.getClassLoader().getResourceAsStream("champions.txt")) {
			try (InputStreamReader isr = new InputStreamReader(is)) {
				try (BufferedReader br = new BufferedReader(isr)) {
					while (br.ready()) {
						champs.add(br.readLine());
					}
				}
			}
		} catch (IOException e) {
			LOGGER.error("Failed to load default champion resource!", e);
		}
		return champs;
	}

}
