package pl.jellysoft.kodbot.resolver.statistic;

import lombok.Value;

@Value
public class MapUserScoreDTO {

    private final Integer batteryLevel;
    private final Integer commandCounter;

}
