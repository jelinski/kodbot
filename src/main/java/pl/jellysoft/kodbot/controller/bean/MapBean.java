package pl.jellysoft.kodbot.controller.bean;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class MapBean {

    private final Integer botPositionRow;
    private final Integer botPositionCol;
    private final Integer botRotation;
    private final Integer batteryLevel;
    private final List<String> mapSlides;
    private final List<DataRow> data;

}
