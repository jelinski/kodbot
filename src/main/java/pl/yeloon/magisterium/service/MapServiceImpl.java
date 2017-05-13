package pl.yeloon.magisterium.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.yeloon.magisterium.controller.bean.MapBean;
import pl.yeloon.magisterium.controller.bean.MapBean.DataRow;
import pl.yeloon.magisterium.model.Map;
import pl.yeloon.magisterium.model.MapAccessToken;
import pl.yeloon.magisterium.repository.MapDAO;

@Service
public class MapServiceImpl implements MapService {

	private SecureRandom random = new SecureRandom();

	@Autowired
	MapDAO mapDAO;

	@Override
	public List<Map> getAllMaps() {
		return mapDAO.getAllMaps();
	}

	@Override
	public Map getMapByKey(String key) {
		return mapDAO.getMapByKey(key);
	}

	@Transactional
	@Override
	public void saveMap(Map map) {
		mapDAO.saveMap(map);
	}

	@Override
	public MapBean getMapBeanByKey(String key) {
		MapBean result = new MapBean();
		Map map = getMapByKey(key);
		result.setBotPositionCol(map.getStartCol());
		result.setBotPositionRow(map.getStartRow());
		result.setBotRotation(map.getBotDirection());
		result.setBatteryLevel(map.getBatteryLevel());
		result.setData(createDataRowList(map.getData()));
		String mapSlides = map.getMapSlides();
		if (mapSlides != null && !mapSlides.isEmpty()) {
			result.setMapSlides(Arrays.asList(map.getMapSlides().split("\\|")));
		}
		return result;
	}

	@Override
	public MapBean createMapBeanFromMap(Map map) {
		MapBean result = new MapBean();
		result.setBotPositionCol(map.getStartCol());
		result.setBotPositionRow(map.getStartRow());
		result.setBotRotation(map.getBotDirection());
		result.setBatteryLevel(map.getBatteryLevel());
		result.setData(createDataRowList(map.getData()));
		String mapSlides = map.getMapSlides();
		if (mapSlides != null && !mapSlides.isEmpty()) {
			result.setMapSlides(Arrays.asList(map.getMapSlides().split("\\|")));
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
	@Transactional
	public String generateAccessToken(int mapId, int userId) {
		String accessToken = new BigInteger(130, random).toString(32);

		MapAccessToken mapAccessToken = mapDAO.getAccessToken(userId, mapId);
		if (mapAccessToken == null) {
			mapAccessToken = new MapAccessToken(userId, mapId, accessToken); // utworz nowy wpis
		} else {
			mapAccessToken.setAccessToken(accessToken); // nadpisz tylko access token
		}

		mapDAO.saveAccessToken(mapAccessToken);
		return accessToken;
	}

	@Override
	public MapAccessToken getAccessToken(String accessToken) {
		return mapDAO.getAccessToken(accessToken);
	}

	@Override
	public Map getMap(int mapId) {
		return mapDAO.getMap(mapId);
	}
}
