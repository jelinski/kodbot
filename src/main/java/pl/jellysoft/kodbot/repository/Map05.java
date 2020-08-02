package pl.jellysoft.kodbot.repository;

import pl.jellysoft.kodbot.model.GameMap;
import pl.jellysoft.kodbot.resolver.simulator.BotDirection;


final class Map05 {

    static GameMap map05() {
        return new GameMap(
                "05b6edf731febab98b0aed5594c08e69",
                3,
                3,
                BotDirection.BOTTOM_RIGHT,
                100,
                null,
                "[1 3 3] [1 3 4] [1 3 5] [1 3 6] [1 4 3] [1 4 6] [1 5 3] [1 5 6] [1 6 3] [1 6 4] [1 6 5] [1 6 6] [100 3 6] [102 6 3]"
        );
    }

}
