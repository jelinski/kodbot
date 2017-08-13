package pl.yeloon.magisterium.controller;

import java.util.List;

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
    private MapService mapService;

	@Autowired
    private ResolverService resolverService;

	@RequestMapping(value = "/{mapKey}", method = RequestMethod.GET)
    public String help(@PathVariable String mapKey, Model model) {
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
                logger.error("Exception occurred while generating access token for a map", e);
			}

			model.addAttribute("mapKey", mapKey);
			return "game";
		}

	}

	@RequestMapping(value = "/resolve/code", method = RequestMethod.POST)
	@ResponseBody
	public ResolverResponse resolveCode(@RequestParam(value = "data") String code, @RequestParam String accessToken, @RequestParam String mapKey) {
		return resolve(code, accessToken, mapKey);
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

	private ResolverResponse resolve(String input, String accessToken, String mapKey) {
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
			return resolverService.resolve(input, userId, map);

		} else {
			return new ResolverErrorResponse("Niepoprawna wartość mapKey");
		}
	}

}
