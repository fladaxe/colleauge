package de.bflader.clol.journal;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import de.bflader.clol.entry.JournalEntry;

@XmlRootElement
public class Journal extends Observable {

	private String name;
	private List<JournalEntry> entries = new ArrayList<>();
	private Path filePath;
	private Date created = new Date();

	public void addEntry(JournalEntry entry) {
		if (!entries.contains(entry)) {
			entries.add(entry);
			setChanged();
			notifyObservers();
		}
	}

	public void remove(JournalEntry entry) {
		if (entries.remove(entry)) {
			setChanged();
			notifyObservers();
		}
	}

	public void setName(String name) {
		if (!name.equals(this.name)) {
			this.name = name;
			setChanged();
			notifyObservers();
		}
	}

	public void setFilePath(Path path) {
		if (path.equals(path)) {
			this.filePath = path;
			setChanged();
			notifyObservers();
		}
	}

	@XmlElement(name = "entry")
	public List<JournalEntry> getEntries() {
		return entries;
	}

	@XmlElement
	public String getName() {
		return name;
	}

	@XmlTransient
	public Path getFilePath() {
		return filePath;
	}

	@XmlAttribute
	public Date getCreated() {
		return created;
	}

	@Override
	public String toString() {
		return name;
	}
}
