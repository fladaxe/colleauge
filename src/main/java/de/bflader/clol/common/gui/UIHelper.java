package de.bflader.clol.common.gui;

import java.awt.Component;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UIHelper {

	private static final Logger LOGGER = LogManager.getLogger(UIHelper.class);

	public static final Insets DEFAULT_COMPONENT_INSETS = new Insets(3, 3, 3, 3);

	private static Map<String, Icon> iconCache = new HashMap<>();

	public static Icon getIcon(String filename) {
		Icon icon = iconCache.get(filename);
		if (icon == null) {
			URL imgURL = UIHelper.class.getResource("/icons/" + filename);
			if (imgURL != null) {
				icon = new ImageIcon(imgURL);
				iconCache.put(filename, icon);
				LOGGER.debug("Icon loaded into cache: " + filename);
			} else {
				LOGGER.warn("Could not find Icon: " + filename);
			}
		}
		return icon;
	}

	public static String getStringFromUser(String message, Component parent) {
		return JOptionPane.showInputDialog(parent, message);
	}

	public static boolean getConfirmationFromUser(String message, Component parent) {
		return JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(parent, message, "Confirmation",
				JOptionPane.OK_CANCEL_OPTION);
	}

	private static BufferedImage ratingImage = null;

	public static ImageIcon getRatingImage(int rating) {
		if (ratingImage == null) {
			InputStream is = UIHelper.class.getClassLoader().getResourceAsStream("rating.png");
			try {
				ratingImage = ImageIO.read(is);
				LOGGER.debug("Rating image loaded.");
			} catch (IOException e) {
				LOGGER.error("Failed to load rating image.", e);
			}
		}
		return new ImageIcon(ratingImage.getSubimage(0, rating * 15, 100, 15));
	}
}
