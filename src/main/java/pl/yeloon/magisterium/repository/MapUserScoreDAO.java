package pl.yeloon.magisterium.repository;

import java.util.List;

import pl.yeloon.magisterium.controller.bean.MapRankDTO;
import pl.yeloon.magisterium.model.MapUserScore;

public interface MapUserScoreDAO {

	List<MapUserScore> getUserMapUserScore(int userId);
	List<MapUserScore> getMapScores(int mapId);
	MapUserScore getMapScores(int mapId, int userId);
	void saveMapUserScore(MapUserScore mapUserScore);
	List<MapUserScore> getMapRank(int mapId);

}
