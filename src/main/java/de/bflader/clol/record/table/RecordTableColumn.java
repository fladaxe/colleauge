package de.bflader.clol.record.table;

import java.util.Date;

import de.bflader.clol.game.Role;

public enum RecordTableColumn {
	CREATED("Created", Date.class, 100),
	PLAYED_CHAMPION("Played", String.class, 100),
	ROLE("Role", Role.class, 75),
	OPPONENT_CHAMPION("Opponent", String.class, 100),
	TEXT("Text", String.class, 150);

	private String name;
	private Class<?> type;
	private int width;

	private RecordTableColumn(String name, Class<?> type, int width) {
		this.name = name;
		this.type = type;
		this.width = width;
	}

	public String getName() {
		return name;
	}

	public Class<?> getType() {
		return type;
	}

	public int getWidth() {
		return width;
	}

}
