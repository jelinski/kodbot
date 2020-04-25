package pl.jellysoft.kodbot.resolver;

import lombok.Getter;
import pl.jellysoft.kodbot.resolver.evaluator.ActionType;
import pl.jellysoft.kodbot.resolver.statistic.MapUserScoreDTO;
import pl.jellysoft.kodbot.resolver.statistic.StatisticDTO;

import java.util.List;

@Getter
public class ResolverWinResponse extends ResolverOkResponse {

    private final StatisticDTO newStatistics;
    private final MapUserScoreDTO newMapUserScore;
    private final String nextMapKey;

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

}
