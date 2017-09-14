package de.bflader.clol.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.stream.Stream;

import javax.xml.bind.JAXBException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.bflader.clol.application.Config;
import de.bflader.clol.common.XmlUtil;
import de.bflader.clol.record.Record;

public class Persistence {

	private static final Logger LOGGER = LogManager.getLogger(Persistence.class);

	private static final String BASE_PATH = Paths.get(System.getProperty("user.home"), ".clol").toString();
	private static final String RECORDS_PATH = Paths.get(BASE_PATH, "records").toString();
	private static final String CONFIG_PATH = Paths.get(BASE_PATH, "config.properties").toString();

	public static void prepareFolder() {
		prepare(BASE_PATH);
		prepare(RECORDS_PATH);
	}

	private static void prepare(String path) {
		if (!Files.exists(Paths.get(path))) {
			try {
				Files.createDirectories(Paths.get(path));
			} catch (IOException e) {
				throw new RuntimeException("Failed to create directories: " + path, e);
			}
		}
	}

	public static Properties loadConfig() {
		Properties props = new Properties(Config.createDefaultConfig());
		Path path = Paths.get(CONFIG_PATH);
		if (Files.exists(path)) {
			try (InputStream is = Files.newInputStream(path)) {
				props.load(is);
			} catch (IOException e) {
				LOGGER.warn("Failed to load properties!", e);
			}
		}
		return props;
	}

	public static List<Record> loadRecords() {
		List<Record> records = new ArrayList<>();
		try (Stream<Path> files = Files.list(Paths.get(RECORDS_PATH))) {
			files.forEach(new Consumer<Path>() {
				@Override
				public void accept(Path path) {
					if (!path.toString().endsWith(".xml")) {
						return;
					}
					try {
						Record record = XmlUtil.unmarshall(path.toFile(), Record.class);
						record.setFilePath(path);
						records.add(record);
					} catch (JAXBException e) {
						LOGGER.error("Failed to unmarshall " + path, e);
					}
				}
			});
		} catch (IOException e) {
			LOGGER.error("Failed to list files in " + RECORDS_PATH, e);
		}
		LOGGER.debug("Loaded " + records.size() + " records");
		return records;
	}

	public static void saveRecord(Record record) {
		try {
			Path path = record.getFilePath();
			if (path == null) {
				path = Paths.get(RECORDS_PATH, generateFilename(record));
			}
			String xmlString = XmlUtil.convertToXml(record, Record.class);
			Files.deleteIfExists(path);
			try (OutputStream os = Files.newOutputStream(path)) {
				os.write(xmlString.getBytes());
				LOGGER.debug("Saved record: " + record.getName());
			}
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Failed to create filename: " + record.getName(), e);
		} catch (IOException e) {
			LOGGER.error("Excpetion during save process for record " + record.getName(), e);
		} catch (JAXBException e) {
			LOGGER.error("Failed to marshall record " + record.getName(), e);
		}
	}

	private static String generateFilename(Record record) {
		return DigestUtils.sha1Hex(record.getName() + record.getCreated().getTime()) + ".xml";
	}

	public static void deleteRecord(Record record) {
		if (record == null || record.getFilePath() == null) {
			return;
		}
		Path path = record.getFilePath();
		try {
			Files.deleteIfExists(path);
			LOGGER.debug("Deleted record: " + record.getName());
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

}
