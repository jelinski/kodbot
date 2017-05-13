package pl.yeloon.magisterium.service;

import java.util.List;

import pl.yeloon.magisterium.controller.bean.MapRankDTO;
import pl.yeloon.magisterium.controller.bean.OverallRankDTO;
import pl.yeloon.magisterium.model.MapUserScore;
import pl.yeloon.magisterium.resolver.statistic.MapUserScoreDTO;

public interface RankService {

	List<MapUserScore> getUserMapUserScore(int userId);

	List<MapUserScore> getMapScores(int mapId);

	MapUserScore getMapScores(int mapId, int userId);

	void saveMapUserScore(MapUserScore mapUserScore);

	boolean isBetter(MapUserScoreDTO score1, MapUserScoreDTO score2);

	boolean isBetter(MapUserScore score1, MapUserScore score2);

	boolean isBetter(MapUserScore score1, MapUserScoreDTO score2);
	
	List<MapRankDTO> getMapRank(int mapId);

	List<OverallRankDTO> getOverallRank(); 

}
