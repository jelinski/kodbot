package pl.yeloon.magisterium.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.yeloon.magisterium.controller.bean.MapGalleryDTO;
import pl.yeloon.magisterium.model.Map;
import pl.yeloon.magisterium.model.MapUserScore;
import pl.yeloon.magisterium.resolver.statistic.MapRankingInfoDTO;
import pl.yeloon.magisterium.service.MapService;
import pl.yeloon.magisterium.service.RankService;
import pl.yeloon.magisterium.util.SecurityUtils;

@Controller
public class PlayController {

	private static final Logger logger = LoggerFactory.getLogger(PlayController.class);

	@Autowired
	private MapService mapService;

	@Autowired
	private RankService rankService;

	@RequestMapping(value = "/play", method = RequestMethod.GET)
	public String play(Locale locale, Model model) {
		logger.info("PlayController. Locale is {}.", locale);

		List<Map> maps = mapService.getAllMaps();
		List<MapGalleryDTO> mapGalleryList = new ArrayList<MapGalleryDTO>();
		List<Integer> clearedMapIndexes = new ArrayList<Integer>();

		Integer userId = SecurityUtils.getLoggedInUserId();
        if (userId != null) {
			List<MapUserScore> userMapScore = rankService.getUserMapUserScore(userId);
			for (MapUserScore mapUserScore : userMapScore) {
				MapGalleryDTO mapGallery = new MapGalleryDTO();
				mapGallery.setFinished(true);
				mapGallery.setUserScore(mapUserScore.getCommandCounter());
				mapGallery.setUserBatteryLevel(mapUserScore.getBatteryLevel());
				int mapId = mapUserScore.getMapId();
				Map map = getMapById(maps, mapId);
				mapGallery.setId(map.getId());
				mapGallery.setGameUrl(createGameUrl(map.getKey()));
				mapGallery.setImageUrl(createImageUrl(map.getKey()));
				mapGallery.setBestBatteryLevel(map.getBestBatteryLevel());
				mapGallery.setBestScore(map.getBestCommandCounter());
				clearedMapIndexes.add(mapUserScore.getMapId());
				mapGalleryList.add(mapGallery);

				List<MapUserScore> mapScores = rankService.getMapScores(mapGallery.getId());
				MapRankingInfoDTO mapRankingInfo = new MapRankingInfoDTO();
				mapGallery.setMapRankingInfo(mapRankingInfo);
				mapRankingInfo.setTotal(mapScores.size());
				for (int l = 0; l < mapScores.size(); l++) {
					if (mapScores.get(l).getUserId() == userId) {
						mapRankingInfo.setUserPosition(l + 1);
						break;
					}
				}

			}
		}

		int j = 0;
		int i = 0;
		while (i < MapService.FORWARD_MAP_PEEK && j < maps.size()) {
			Map map = maps.get(j);
			if (!clearedMapIndexes.contains(map.getId())) {
				MapGalleryDTO mapGallery = new MapGalleryDTO();
				mapGallery.setBestBatteryLevel(map.getBestBatteryLevel());
				mapGallery.setBestScore(map.getBestCommandCounter());
				mapGallery.setGameUrl(createGameUrl(map.getKey()));
				mapGallery.setImageUrl(createImageUrl(map.getKey()));
				mapGallery.setId(map.getId());
				mapGalleryList.add(mapGallery);
				i++;
			}
			j++;
		}

		Collections.sort(mapGalleryList);

		model.addAttribute("mapGalleryList", mapGalleryList);
		model.addAttribute("blockedMapCount", maps.size() - mapGalleryList.size());

		return "play";
	}

	private String createGameUrl(String key) {
		return key;
	}

	private String createImageUrl(String key) {
		return key;
	}

	private Map getMapById(List<Map> maps, int mapId) {
		for (int i = 0; i < maps.size(); i++) {
			if (maps.get(i).getId() == mapId)
				return maps.get(i);
		}
		return null;
	}
}
