package pl.jellysoft.kodbot.controller.bean;

import lombok.Builder;
import lombok.Value;
import pl.jellysoft.kodbot.resolver.simulator.BotDirection;

import java.util.List;

@Value
@Builder
public class MapBean {

    private final Integer botPositionRow;
    private final Integer botPositionCol;
    private final BotDirection botDirection;
    private final Integer batteryLevel;
    private final List<String> mapSlides;
    private final List<DataRow> data;

}
