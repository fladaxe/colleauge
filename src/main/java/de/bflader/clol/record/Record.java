package de.bflader.clol.record;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import de.bflader.clol.entry.Entry;

@XmlRootElement
public class Record extends Observable {

	private String name;

	@XmlElement(name = "entry")
	private List<Entry> entries = new ArrayList<>();

	@XmlAttribute
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name != null && name.equals(this.name)) {
			return;
		}
		this.name = name;
		setChanged();
		notifyObservers();
	}

	public List<Entry> getEntries() {
		return entries;
	}

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

	@Override
	public String toString() {
		return name;
	}
}
