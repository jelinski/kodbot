package pl.yeloon.magisterium.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.yeloon.magisterium.controller.bean.MapBean;
import pl.yeloon.magisterium.model.GameMap;
import pl.yeloon.magisterium.resolver.ResolverErrorResponse;
import pl.yeloon.magisterium.resolver.ResolverResponse;
import pl.yeloon.magisterium.service.MapService;
import pl.yeloon.magisterium.service.ResolverService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/game")
public class GameController {

	private static final Logger logger = LoggerFactory.getLogger(GameController.class);

	@Autowired
    private MapService mapService;

	@Autowired
    private ResolverService resolverService;

	@RequestMapping(value = "/{mapKey}", method = RequestMethod.GET)
    public String help(@PathVariable String mapKey, Model model) {
		GameMap gameMap = mapService.getMapByKey(mapKey);
		if (gameMap == null) {
			logger.warn("Request for unexisting map was made with key: " + mapKey);
			return "redirect:/play";
		} else {
			model.addAttribute("mapKey", mapKey);
			return "game";
		}
	}

	@RequestMapping(value = "/resolve/code", method = RequestMethod.POST)
	@ResponseBody
	public ResolverResponse resolveCode(@RequestParam(value = "data") String code, @RequestParam String mapKey) {
		GameMap gameMap = mapService.getMapByKey(mapKey);
		if (gameMap != null) {
			return resolverService.resolve(code, gameMap);
		} else {
			return new ResolverErrorResponse("Niepoprawna wartość mapKey");
		}
	}

	@RequestMapping(value = "/fetchMap", method = RequestMethod.POST)
	@ResponseBody
	public MapBean fetchMap(@RequestParam String mapKey, HttpServletRequest request) {
		MapBean mb = mapService.getMapBeanByKey(mapKey);
		List<String> mapSlidesUrls = mb.getMapSlides();
		if (mapSlidesUrls != null) {
			for (int i = 0; i < mapSlidesUrls.size(); i++) {
				// TODO dodac jeszcze suffix ze wzgledu na jezyk
				mapSlidesUrls.set(i, request.getContextPath() + MapService.MAP_SLIDES_DIRECTORY + mapSlidesUrls.get(i) + ".png");
			}
		}
		return mb;
	}

}
