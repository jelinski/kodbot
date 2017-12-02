package pl.yeloon.magisterium.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.yeloon.magisterium.controller.bean.MapGalleryDTO;
import pl.yeloon.magisterium.model.GameMap;
import pl.yeloon.magisterium.service.MapService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Controller
public class PlayController {

	private static final Logger logger = LoggerFactory.getLogger(PlayController.class);

	@Autowired
	private MapService mapService;

	@RequestMapping(value = "/play", method = RequestMethod.GET)
	public String play(Locale locale, Model model) {
		logger.info("PlayController. Locale is {}.", locale);

		List<GameMap> gameMaps = mapService.getAllMaps();
		List<MapGalleryDTO> mapGalleryList = new ArrayList<MapGalleryDTO>();

		for (GameMap gameMap : gameMaps) {
			MapGalleryDTO mapGallery = new MapGalleryDTO();
			mapGallery.setGameUrl(gameMap.getKey());
			mapGallery.setImageUrl(gameMap.getKey());
			mapGalleryList.add(mapGallery);
		}

		Collections.sort(mapGalleryList);

		model.addAttribute("mapGalleryList", mapGalleryList);

		return "play";
	}

}
