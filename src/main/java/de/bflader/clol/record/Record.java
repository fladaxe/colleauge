package de.bflader.clol.record;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import de.bflader.clol.entry.Entry;

@XmlRootElement
public class Record extends Observable {

	private String name;
	private List<Entry> entries = new ArrayList<>();
	@XmlTransient
	private Path filePath;
	private Date created = new Date();

	public void addEntry(Entry entry) {
		if (entry == null) {
			return;
		}
		entries.add(entry);
		setChanged();
		notifyObservers();
	}

	public void remove(Entry entry) {
		if (entries.remove(entry)) {
			setChanged();
			notifyObservers();
		}
	}

	public void setName(String name) {
		if (name != null && name.equals(this.name)) {
			return;
		}
		this.name = name;
		setChanged();
		notifyObservers();
	}

	public void setFilePath(Path path) {
		this.filePath = path;
	}

	public List<Entry> getEntries() {
		return entries;
	}

	public String getName() {
		return name;
	}

	@XmlTransient
	public Path getFilePath() {
		return filePath;
	}

	public Date getCreated() {
		return created;
	}

	@Override
	public String toString() {
		return name;
	}
}
