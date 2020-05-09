package pl.jellysoft.kodbot.controller;

import org.owasp.encoder.Encode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.jellysoft.kodbot.controller.bean.MapBean;
import pl.jellysoft.kodbot.model.GameMap;
import pl.jellysoft.kodbot.resolver.ResolverErrorResponse;
import pl.jellysoft.kodbot.resolver.ResolverResponse;
import pl.jellysoft.kodbot.service.MapService;
import pl.jellysoft.kodbot.service.ResolverService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/game")
public class GameController {

    @Autowired
    private MapService mapService;

    @Autowired
    private ResolverService resolverService;

    @GetMapping("/{mapKey}")
    public String help(@PathVariable String mapKey, Model model) {
        GameMap gameMap = mapService.getMapByKey(mapKey);
        if (gameMap == null) {
            return "redirect:/play";
        } else {
            model.addAttribute("mapKey", Encode.forHtml(mapKey));
            return "game";
        }
    }

    @PostMapping("/resolve/code")
    @ResponseBody
    public ResolverResponse resolveCode(@RequestParam String code, @RequestParam String mapKey) {
        GameMap gameMap = mapService.getMapByKey(mapKey);
        if (gameMap != null) {
            return resolverService.resolve(code, gameMap);
        } else {
            return new ResolverErrorResponse("Niepoprawna wartość mapKey");
        }
    }

    @PostMapping("/fetchMap")
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
