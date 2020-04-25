package pl.jellysoft.kodbot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.jellysoft.kodbot.controller.bean.MapGalleryDTO;
import pl.jellysoft.kodbot.model.GameMap;
import pl.jellysoft.kodbot.service.MapService;

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
        List<MapGalleryDTO> mapGalleryList = new ArrayList<>();

        for (GameMap gameMap : gameMaps) {
            MapGalleryDTO mapGallery = MapGalleryDTO.builder()
                    .gameUrl(gameMap.getKey())
                    .imageUrl(gameMap.getKey()) // TODO why gameUrl is same as imageUrl?
                    .build();
            mapGalleryList.add(mapGallery);
        }

        Collections.sort(mapGalleryList);

        model.addAttribute("mapGalleryList", mapGalleryList);

        return "play";
    }

}
