package pl.yeloon.magisterium.resolver;

import pl.yeloon.magisterium.resolver.evaluator.ActionType;
import pl.yeloon.magisterium.resolver.statistic.MapUserScoreDTO;
import pl.yeloon.magisterium.resolver.statistic.StatisticDTO;

import java.util.List;

public class ResolverWinResponse extends ResolverOkResponse {

	private StatisticDTO newStatistics;
	private MapUserScoreDTO newMapUserScore;

	private String nextMapKey;

	public ResolverWinResponse(List<ActionType> actions, StatisticDTO newStatistics, MapUserScoreDTO newMapUserScoreDTO, String nextMapKey) {
		super(actions);
		this.newStatistics = newStatistics;
		this.newMapUserScore = newMapUserScoreDTO;
		this.nextMapKey = nextMapKey;
	}

	@Override
	public String getCode() {
		return "WIN";
	}

	public StatisticDTO getNewStatistics() {
		return newStatistics;
	}

	public void setNewStatistics(StatisticDTO newStatistics) {
		this.newStatistics = newStatistics;
	}

	public MapUserScoreDTO getNewMapUserScore() {
		return newMapUserScore;
	}

	public void setNewMapUserScore(MapUserScoreDTO newMapUserScore) {
		this.newMapUserScore = newMapUserScore;
	}

	public String getNextMapKey() {
		return nextMapKey;
	}

	public void setNextMapKey(String nextMapKey) {
		this.nextMapKey = nextMapKey;
	}

}
