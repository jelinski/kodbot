package pl.yeloon.magisterium.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.yeloon.magisterium.controller.bean.MapBean;
import pl.yeloon.magisterium.model.Map;
import pl.yeloon.magisterium.model.MapAccessToken;
import pl.yeloon.magisterium.resolver.ResolverErrorResponse;
import pl.yeloon.magisterium.resolver.ResolverResponse;
import pl.yeloon.magisterium.service.MapService;
import pl.yeloon.magisterium.service.ResolverService;
import pl.yeloon.magisterium.util.SecurityUtils;

@Controller
@RequestMapping(value = "/game")
public class GameController {

	private static final Logger logger = LoggerFactory.getLogger(GameController.class);

	@Autowired
	MapService mapService;

	@Autowired
	ResolverService resolverService;

	@RequestMapping(value = "/{mapKey}", method = RequestMethod.GET)
	public String help(@PathVariable("mapKey") String mapKey, Locale locale, Model model) {
		// Wyszukanie planszy po mapKey'u
		// Jesli nie znalazlo to strona 404 albo /play
		Map map = mapService.getMapByKey(mapKey);
		if (map == null) {
			logger.warn("Request for unexisting map was made with key: " + mapKey);
			return "redirect:/play";
		} else {
			try {
				Integer userId = SecurityUtils.getLoggedInUserId();
				if (userId != null) {
					String accessToken = mapService.generateAccessToken(map.getId(), userId);
					model.addAttribute("accessToken", accessToken);
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}

			model.addAttribute("mapKey", mapKey);
			return "game";
		}

	}

	@RequestMapping(value = "/resolve/json", method = RequestMethod.POST)
	@ResponseBody
	public ResolverResponse resolveJson(@RequestParam(value = "data") String json, @RequestParam(value = "accessToken") String accessToken, @RequestParam(value = "mapKey") String mapKey) {
		return resolve(json, false, accessToken, mapKey);
	}

	@RequestMapping(value = "/resolve/code", method = RequestMethod.POST)
	@ResponseBody
	public ResolverResponse resolveCode(@RequestParam(value = "data") String code, @RequestParam(value = "accessToken") String accessToken, @RequestParam(value = "mapKey") String mapKey) {
		return resolve(code, true, accessToken, mapKey);
	}

	@RequestMapping(value = "/fetchMap", method = RequestMethod.POST)
	@ResponseBody
	public MapBean fetchMap(@RequestParam("mapKey") String mapKey, HttpServletRequest request) {
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

	private ResolverResponse resolve(String input, boolean isCodeMode, String accessToken, String mapKey) {
		Map map = mapService.getMapByKey(mapKey);
		if (map != null) {
			Integer userId = null;
			if (accessToken != null && !accessToken.isEmpty()) {
				MapAccessToken mapAccessToken = mapService.getAccessToken(accessToken);
				if (mapAccessToken != null) {
					if (map.getId() == mapAccessToken.getMapId()) {
						userId = mapAccessToken.getUserId();
					}
				} else {
					return new ResolverErrorResponse("Niepoprawna wartosc accessTokenu");
				}
			}
			return resolverService.resolve(input, isCodeMode, userId, map);

		} else {
			return new ResolverErrorResponse("Niepoprawna wartość mapKey");
		}
	}

}
