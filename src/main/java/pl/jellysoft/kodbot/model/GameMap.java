package pl.jellysoft.kodbot.model;

import lombok.Value;

@Value
public class GameMap {

    private final String key;
    private final Integer startRow;
    private final Integer startCol;
    private final BotDirection botDirection;
    private final Integer batteryLevel;
    private final String mapSlides;
    private final String data;

}
