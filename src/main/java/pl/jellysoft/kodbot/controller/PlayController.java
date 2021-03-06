package pl.jellysoft.kodbot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.jellysoft.kodbot.model.GameMap;
import pl.jellysoft.kodbot.model.MapGalleryDTO;
import pl.jellysoft.kodbot.service.MapService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PlayController {

    private final MapService mapService;

    @GetMapping("/play")
    public String play(Model model) {
        List<GameMap> gameMaps = mapService.getAllMaps();
        List<MapGalleryDTO> mapGalleryList = new ArrayList<>();

        for (GameMap gameMap : gameMaps) {
            MapGalleryDTO mapGallery = MapGalleryDTO.builder()
                    .gameUrl(gameMap.getKey())
                    .imageUrl(gameMap.getKey())
                    .build();
            mapGalleryList.add(mapGallery);
        }

        Collections.sort(mapGalleryList);

        model.addAttribute("mapGalleryList", mapGalleryList);

        return "play";
    }

}
