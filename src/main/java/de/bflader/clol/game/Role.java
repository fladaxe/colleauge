package de.bflader.clol.game;

public enum Role {
	ANY("- Any -"),
	TOP("Top"),
	JUNGLE("Jungle"),
	MID("Mid"),
	BOTTOM("Bottom"),
	SUPPORT("Support");

	private String desc;

	private Role(String desc) {
		this.desc = desc;
	}

	public String toString() {
		return desc;
	}
}
