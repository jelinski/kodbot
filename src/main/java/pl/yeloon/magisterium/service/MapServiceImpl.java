package pl.yeloon.magisterium.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.yeloon.magisterium.controller.bean.MapBean;
import pl.yeloon.magisterium.controller.bean.MapBean.DataRow;
import pl.yeloon.magisterium.model.GameMap;
import pl.yeloon.magisterium.repository.MapProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MapServiceImpl implements MapService {

	@Autowired
	private MapProvider mapProvider;

	@Override
	public List<GameMap> getAllMaps() {
		return mapProvider.getAllMaps();
	}

	@Override
	public GameMap getMapByKey(String key) {
		return mapProvider.getMapByKey(key);
	}

	@Override
	public MapBean getMapBeanByKey(String key) {
		GameMap gameMap = getMapByKey(key);
		return createMapBeanFromMap(gameMap);
	}

	@Override
	public MapBean createMapBeanFromMap(GameMap gameMap) {
		MapBean result = new MapBean();
		result.setBotPositionCol(gameMap.getStartCol());
		result.setBotPositionRow(gameMap.getStartRow());
		result.setBotRotation(gameMap.getBotDirection());
		result.setBatteryLevel(gameMap.getBatteryLevel());
		result.setData(createDataRowList(gameMap.getData()));
		String mapSlides = gameMap.getMapSlides();
		if (mapSlides != null && !mapSlides.isEmpty()) {
			result.setMapSlides(Arrays.asList(gameMap.getMapSlides().split("\\|")));
		}
		return result;
	}

	private List<MapBean.DataRow> createDataRowList(String data) {
		List<MapBean.DataRow> result = new ArrayList<>();
		Pattern pattern = Pattern.compile("\\[([0-9]+)\\s([0-9]+)\\s([0-9]+)\\]");
		Matcher matcher = pattern.matcher(data);
		while (matcher.find()) {
			Integer type = Integer.parseInt(matcher.group(1));
			Integer row = Integer.parseInt(matcher.group(2));
			Integer col = Integer.parseInt(matcher.group(3));
			result.add(new DataRow(type, row, col));
		}
		return result;
	}

	@Override
	public String getNextGameMapKey(GameMap gameMap) {
		return mapProvider.getNextGameMapKey(gameMap);
	}
}
