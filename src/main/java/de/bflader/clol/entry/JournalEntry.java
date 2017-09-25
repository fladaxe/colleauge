package de.bflader.clol.entry;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import de.bflader.clol.common.game.Role;

@XmlRootElement
public class JournalEntry {

	public static final String ANY_CHAMPION = "- Any -";

	private Date created = new Date();
	private String opponentChampion = ANY_CHAMPION;
	private String playedChampion = ANY_CHAMPION;
	private int rating = 5;
	private Role role = Role.ANY;
	private String text;

	@XmlAttribute
	public Date getCreated() {
		return created;
	}

	@XmlElement
	public String getOpponentChampion() {
		return opponentChampion;
	}

	@XmlElement
	public String getPlayedChampion() {
		return playedChampion;
	}

	@XmlElement
	public int getRating() {
		return rating;
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
	}

	public void setPlayedChampion(String playedChampion) {
		this.playedChampion = playedChampion;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "Entry: " + rating + " / " + playedChampion + " / " + role + " / " + opponentChampion;
	}
}
