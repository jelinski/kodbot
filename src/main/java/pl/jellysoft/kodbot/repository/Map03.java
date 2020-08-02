package pl.jellysoft.kodbot.repository;

import pl.jellysoft.kodbot.model.GameMap;
import pl.jellysoft.kodbot.resolver.simulator.BotDirection;


final class Map03 {

    static GameMap map03() {
        return new GameMap(
                "035e7213f783038e4019fab444e85ec1",
                5,
                2,
                BotDirection.BOTTOM_RIGHT,
                100,
                "map3_slide1",
                "[3 5 4] [3 5 5] [102 5 7]"
        );
    }

}
