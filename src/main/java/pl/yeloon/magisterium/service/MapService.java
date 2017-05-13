package pl.yeloon.magisterium.service;

import java.util.List;

import pl.yeloon.magisterium.controller.bean.MapBean;
import pl.yeloon.magisterium.model.Map;
import pl.yeloon.magisterium.model.MapAccessToken;

public interface MapService {
	static final int FORWARD_MAP_PEEK = 3;
	static final String MAP_SLIDES_DIRECTORY = "/resources/images/game/map_slides/";
	
	MapBean getMapBeanByKey(String key);
	Map getMapByKey(String key);
	String generateAccessToken(int mapId, int userId);
	List<Map> getAllMaps();
	Map getMap(int mapId);
	MapAccessToken getAccessToken(String accessToken);
	void saveMap(Map map);
	MapBean createMapBeanFromMap(Map map);

}
