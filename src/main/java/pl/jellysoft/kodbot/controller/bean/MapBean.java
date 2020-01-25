package pl.jellysoft.kodbot.controller.bean;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class MapBean {

    private Integer botPositionRow;
    private Integer botPositionCol;
    private Integer botRotation;
    private Integer batteryLevel;
    private List<String> mapSlides;
    private List<DataRow> data;

}
