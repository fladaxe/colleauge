package de.bflader.clol.record;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import de.bflader.clol.entry.Entry;

public class Record {

	@XmlAttribute
	private String name;

	@XmlElement(name = "entry")
	private List<Entry> entries;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Entry> getEntries() {
		return entries;
	}

	public void addEntry(Entry entry) {
		entries.add(entry);
	}
}
