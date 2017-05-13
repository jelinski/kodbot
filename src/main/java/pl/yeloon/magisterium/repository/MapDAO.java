package pl.yeloon.magisterium.repository;

import java.util.List;

import pl.yeloon.magisterium.model.Map;
import pl.yeloon.magisterium.model.MapAccessToken;

public interface MapDAO {

	Map getMapByKey(String key);
	void saveAccessToken(MapAccessToken accessToken);
	MapAccessToken getAccessToken(int userId, int mapId);
	MapAccessToken getAccessToken(String accessToken);
	List<Map> getAllMaps();
	Map getMap(int mapId);
	void saveMap(Map map);
	
}
