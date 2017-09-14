package de.bflader.clol.entry;

import java.util.Date;

import de.bflader.clol.common.game.Role;

public class Entry {

	private Date created = new Date();
	private String opponentChampion;
	private String playedChampion;
	private Role role = Role.ANY;
	private String text;

	public String getOpponentChampion() {
		return opponentChampion;
	}

	public String getPlayedChampion() {
		return playedChampion;
	}

	public Role getRole() {
		return role;
	}

	public String getText() {
		return text;
	}

	public Date getCreated() {
		return created;
	}

	public void setOpponentChampion(String opponentChampion) {
		this.opponentChampion = opponentChampion;
	}

	public void setPlayedChampion(String playedChampion) {
		this.playedChampion = playedChampion;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setText(String text) {
		this.text = text;
	}
}
