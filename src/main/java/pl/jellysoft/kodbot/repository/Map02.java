package pl.jellysoft.kodbot.repository;

import pl.jellysoft.kodbot.model.BotDirection;
import pl.jellysoft.kodbot.model.GameMap;


final class Map02 {

    static GameMap map02() {
        return new GameMap(
                "02b935e6bc094f8da1b852ed619183bd",
                5,
                2,
                BotDirection.BOTTOM_RIGHT,
                100,
                "map2_slide1",
                "[1 5 4] [1 5 5] [102 5 7]"
        );
    }

}
