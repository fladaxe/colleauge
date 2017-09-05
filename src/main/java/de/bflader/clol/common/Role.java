package de.bflader.clol.common;

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
