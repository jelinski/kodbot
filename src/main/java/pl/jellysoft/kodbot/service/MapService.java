package pl.jellysoft.kodbot.service;

import pl.jellysoft.kodbot.controller.bean.MapBean;
import pl.jellysoft.kodbot.model.GameMap;

import java.util.List;

// TODO get rid of interface...
public interface MapService {

	String MAP_SLIDES_DIRECTORY = "/resources/images/game/map_slides/";

	MapBean getMapBeanByKey(String key);

	GameMap getMapByKey(String key);

	List<GameMap> getAllMaps();

	MapBean createMapBeanFromMap(GameMap gameMap);

	String getNextGameMapKey(GameMap gameMap);
}
