package de.bflader.clol.entry;

import java.util.Date;
import java.util.Observable;

import javax.xml.bind.annotation.XmlElement;

import de.bflader.clol.game.Role;

public class Entry extends Observable {

	@XmlElement
	private Date created = new Date();
	private String opponentChampion;
	private String playedChampion;
	private Role role = Role.ANY;
	private String text = "Extrem langer Text. Extrem langer Text. Extrem langer Text. Extrem langer Text. Extrem langer Text. Extrem langer Text. Extrem langer Text. ";

	@XmlElement
	public String getOpponentChampion() {
		return opponentChampion;
	}

	@XmlElement
	public String getPlayedChampion() {
		return playedChampion;
	}

	@XmlElement
	public Role getRole() {
		return role;
	}

	@XmlElement
	public String getText() {
		return text;
	}

	public void setOpponentChampion(String opponentChampion) {
		this.opponentChampion = opponentChampion;
		setChanged();
		notifyObservers();
	}

	public void setPlayedChampion(String playedChampion) {
		this.playedChampion = playedChampion;
		setChanged();
		notifyObservers();
	}

	public void setRole(Role role) {
		this.role = role;
		setChanged();
		notifyObservers();
	}

	public void setText(String text) {
		this.text = text;
		setChanged();
		notifyObservers();
	}

	public Date getCreated() {
		return created;
	}
}
