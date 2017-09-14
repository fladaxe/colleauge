package de.bflader.clol.common;

import java.awt.Component;
import java.awt.Insets;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

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
			} else {
				LOGGER.warn("Could not find Icon: " + filename);
			}
		}
		return icon;
	}

	public static void setColumnWidths(JTable table, Integer... widths) {
		TableColumnModel columnModel = table.getColumnModel();
		int i = 0;
		for (Integer width : widths) {
			TableColumn column = columnModel.getColumn(i++);
			column.setPreferredWidth(width);
			column.setMinWidth(75);
		}
	}

	public static String getStringFromUser(String message, Component parent) {
		return JOptionPane.showInputDialog(parent, message);
	}
}
