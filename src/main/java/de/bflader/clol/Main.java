package de.bflader.clol;

import javax.swing.SwingUtilities;

import de.bflader.clol.application.Application;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Application());
	}
}
