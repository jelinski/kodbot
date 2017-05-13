package pl.yeloon.magisterium.resolver;

import java.util.List;

import pl.yeloon.magisterium.resolver.evaluator.ActionType;
import pl.yeloon.magisterium.resolver.statistic.MapRankingInfoDTO;
import pl.yeloon.magisterium.resolver.statistic.MapUserScoreDTO;
import pl.yeloon.magisterium.resolver.statistic.StatisticDTO;

public class ResolverWinResponse extends ResolverOkResponse {

	StatisticDTO oldStatistics;
	StatisticDTO newStatistics;
	MapUserScoreDTO oldMapUserScore;
	MapUserScoreDTO newMapUserScore;
	MapUserScoreDTO mapBestScore;
	MapRankingInfoDTO mapRankingInfo;
	String nextMapKey;

	public ResolverWinResponse(List<ActionType> actions, StatisticDTO oldStatistics, StatisticDTO newStatistics, MapUserScoreDTO oldMapUserScoreDTO, MapUserScoreDTO newMapUserScoreDTO, MapUserScoreDTO mapBestScore,
			MapRankingInfoDTO mapRankingInfo, String nextMapKey) {
		super(actions);
		this.oldStatistics = oldStatistics;
		this.newStatistics = newStatistics;
		this.oldMapUserScore = oldMapUserScoreDTO;
		this.newMapUserScore = newMapUserScoreDTO;
		this.mapBestScore = mapBestScore;
		this.mapRankingInfo = mapRankingInfo;
		this.nextMapKey = nextMapKey;
	}

	@Override
	public String getCode() {
		return "WIN";
	}

	public StatisticDTO getOldStatistics() {
		return oldStatistics;
	}

	public void setOldStatistics(StatisticDTO oldStatistics) {
		this.oldStatistics = oldStatistics;
	}

	public StatisticDTO getNewStatistics() {
		return newStatistics;
	}

	public void setNewStatistics(StatisticDTO newStatistics) {
		this.newStatistics = newStatistics;
	}

	public MapUserScoreDTO getOldMapUserScore() {
		return oldMapUserScore;
	}

	public void setOldMapUserScore(MapUserScoreDTO oldMapUserScore) {
		this.oldMapUserScore = oldMapUserScore;
	}

	public MapUserScoreDTO getNewMapUserScore() {
		return newMapUserScore;
	}

	public void setNewMapUserScore(MapUserScoreDTO newMapUserScore) {
		this.newMapUserScore = newMapUserScore;
	}

	@Override
	public String toString() {
		return "ResolverWinResponse [oldStatistics=" + oldStatistics + ", newStatistics=" + newStatistics + ", oldMapUserScore=" + oldMapUserScore + ", newMapUserScore=" + newMapUserScore + ", mapBestScore=" + mapBestScore
				+ ", mapRankingInfo=" + mapRankingInfo + ", nextMapKey=" + nextMapKey + "]";
	}

	public MapUserScoreDTO getMapBestScore() {
		return mapBestScore;
	}

	public void setMapBestScore(MapUserScoreDTO mapBestScore) {
		this.mapBestScore = mapBestScore;
	}

	public MapRankingInfoDTO getMapRankingInfo() {
		return mapRankingInfo;
	}

	public void setMapRankingInfo(MapRankingInfoDTO mapRankingInfo) {
		this.mapRankingInfo = mapRankingInfo;
	}

	public String getNextMapKey() {
		return nextMapKey;
	}

	public void setNextMapKey(String nextMapKey) {
		this.nextMapKey = nextMapKey;
	}

}
