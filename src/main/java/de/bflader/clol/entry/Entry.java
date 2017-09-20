package de.bflader.clol.entry;

import java.util.Date;

import de.bflader.clol.common.game.Role;

public class Entry {

	public static String ANY_CHAMPION = "- Any -";

	private Date created = new Date();
	private String opponentChampion = ANY_CHAMPION;
	private String playedChampion = ANY_CHAMPION;
	private int rating = 5;
	private Role role = Role.ANY;
	private String text;

	public Date getCreated() {
		return created;
	}

	public String getOpponentChampion() {
		return opponentChampion;
	}

	public String getPlayedChampion() {
		return playedChampion;
	}

	public int getRating() {
		return rating;
	}

	public Role getRole() {
		return role;
	}

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
}
