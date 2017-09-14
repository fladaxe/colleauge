package de.bflader.clol.journal.view;

import java.util.Date;

import de.bflader.clol.common.game.Role;

public enum JournalTableColumns {
	CREATED("Created", Date.class, 80,80),
	PLAYED_CHAMPION("Played", String.class, 100,100),
	ROLE("Role", Role.class, 80,80),
	OPPONENT_CHAMPION("Opponent", String.class, 100,100),
	TEXT("Text", String.class, 200,4000);

	private String name;
	private Class<?> type;
	private int minWidth;
	private int preferredWidth;

	private JournalTableColumns(String name, Class<?> type, int minWidth, int preferredWidth) {
		this.name = name;
		this.type = type;
		this.minWidth = minWidth;
		this.preferredWidth = preferredWidth;
	}

	public String getName() {
		return name;
	}

	public Class<?> getType() {
		return type;
	}

	public int getMinWidth() {
		return minWidth;
	}

	public int getPreferredWidth() {
		return preferredWidth;
	}

}