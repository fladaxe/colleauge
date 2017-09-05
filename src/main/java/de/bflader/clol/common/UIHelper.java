package de.bflader.clol.common;

import java.awt.Insets;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UIHelper {

	private static final Logger LOGGER = LogManager.getLogger(UIHelper.class);

	public static Insets DEFAULT_COMPONENT_INSETS = new Insets(6, 6, 6, 6);

	private static Map<String, Icon> iconCache = new HashMap<>();

	public static Icon getIcon(String filename) {
		Icon icon = iconCache.get(filename);
		if (icon == null) {
			URL imgURL = UIHelper.class.getResource("/icons/" + filename);
			if (imgURL != null) {
				icon = new ImageIcon(imgURL);
				iconCache.put(filename, icon);
			} else {
				LOGGER.warn("Could not find Icon: " + filename);
			}
		}
		return icon;
	}
}
