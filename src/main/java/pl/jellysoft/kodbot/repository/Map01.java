package pl.jellysoft.kodbot.repository;

import pl.jellysoft.kodbot.model.BotDirection;
import pl.jellysoft.kodbot.model.GameMap;


final class Map01 {

    static GameMap map01() {
        return new GameMap(
                "0197b66c0c6670bdc518e52e51c00c32",
                5,
                2,
                BotDirection.BOTTOM_RIGHT,
                100,
                "map1_slide1|map1_slide2|map1_slide3|map1_slide4|map1_slide5|map1_slide6|map1_slide7|map1_slide8|map1_slide9|map1_slide10|map1_slide11",
                "[102 5 7]"
        );
    }

}
