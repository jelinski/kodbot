package pl.jellysoft.kodbot.service;

import lombok.RequiredArgsConstructor;
import pl.jellysoft.kodbot.model.DataRow;
import pl.jellysoft.kodbot.model.GameMap;
import pl.jellysoft.kodbot.model.MapBean;
import pl.jellysoft.kodbot.repository.MapProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
public class MapService {

    public static final String MAP_SLIDES_DIRECTORY = "/resources/images/game/map_slides/";
    private static final Pattern DATA_ROW_FORMAT_PATTERN = Pattern.compile("\\[([0-9]+)\\s([0-9]+)\\s([0-9]+)]");

    private final MapProvider mapProvider;

    private static List<DataRow> createDataRowList(String data) {
        List<DataRow> result = new ArrayList<>();

        Matcher matcher = DATA_ROW_FORMAT_PATTERN.matcher(data);
        while (matcher.find()) {
            Integer type = Integer.parseInt(matcher.group(1));
            Integer row = Integer.parseInt(matcher.group(2));
            Integer col = Integer.parseInt(matcher.group(3));
            result.add(new DataRow(type, row, col));
        }
        return result;
    }

    public List<GameMap> getAllMaps() {
        return mapProvider.getAllMaps();
    }

    public GameMap getMapByKey(String key) {
        return mapProvider.getMapByKey(key);
    }

    public MapBean getMapBeanByKey(String key) {
        GameMap gameMap = getMapByKey(key);
        return createMapBeanFromMap(gameMap);
    }

    public MapBean createMapBeanFromMap(GameMap gameMap) {
        return MapBean.builder()
                .botPositionCol(gameMap.getStartCol())
                .botPositionRow(gameMap.getStartRow())
                .botDirection(gameMap.getBotDirection())
                .batteryLevel(gameMap.getBatteryLevel())
                .data(createDataRowList(gameMap.getData()))
                .mapSlides(
                        ofNullable(gameMap.getMapSlides())
                                .filter(mapSlides -> !mapSlides.isEmpty())
                                .map(mapSlides -> mapSlides.split("\\|"))
                                .map(Arrays::asList)
                                .orElse(null))
                .build();
    }

    String getNextGameMapKey(GameMap gameMap) {
        return mapProvider.getNextGameMapKey(gameMap);
    }

}
