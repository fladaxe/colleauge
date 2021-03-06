package de.bflader.clol.common.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.bflader.clol.persistence.Persistence;
import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.static_data.dto.Champion;
import net.rithms.riot.constant.Platform;

public class RiotApiHelper {

	private static final Logger LOGGER = LogManager.getLogger(RiotApiHelper.class);

	private static RiotApi api;
	private static List<String> champs = new ArrayList<>();

	public static void init(String key) {
		api = new RiotApi(new ApiConfig().setKey(key));
		try {
			api.getDataVersions(Platform.EUW);
			LOGGER.debug("Successfully tested RiotAPI.");
		} catch (RiotApiException e) {
			LOGGER.warn("Failed to test the RiotAPI: " + e.getMessage());
		}
	}

	public static void prepareChampions() {
		try {
			for (Champion champ : api.getDataChampionList(Platform.EUW).getData().values()) {
				champs.add(champ.getName());
			}
		} catch (RiotApiException e) {
			LOGGER.warn("Failed to get champion data: " + e.getMessage());
		}
		if (champs.isEmpty()) {
			champs.addAll(Persistence.loadChampionBackup());
		}
		if( champs.isEmpty()){
			champs.addAll(Persistence.loadChampionDefault());
		}
		Collections.sort(champs);
		Persistence.saveChampionBackup(champs);
		LOGGER.debug("Champions prepared.");
	}

	public static List<String> getChampions() {
		return new ArrayList<>(champs);
	}
}
