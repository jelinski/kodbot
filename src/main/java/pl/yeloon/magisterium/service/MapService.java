package pl.yeloon.magisterium.service;

import pl.yeloon.magisterium.controller.bean.MapBean;
import pl.yeloon.magisterium.model.GameMap;

import java.util.List;

public interface MapService {

	String MAP_SLIDES_DIRECTORY = "/resources/images/game/map_slides/";

	MapBean getMapBeanByKey(String key);

	GameMap getMapByKey(String key);

	List<GameMap> getAllMaps();

	MapBean createMapBeanFromMap(GameMap gameMap);

	String getNextGameMapKey(GameMap gameMap);
}
