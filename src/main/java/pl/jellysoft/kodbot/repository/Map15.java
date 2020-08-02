package pl.jellysoft.kodbot.repository;

import pl.jellysoft.kodbot.model.GameMap;
import pl.jellysoft.kodbot.resolver.simulator.BotDirection;


final class Map15 {

    static GameMap map15() {
        return new GameMap(
                "6ecef5aba2c0e548862a0ff17b879e05",
                8,
                5,
                BotDirection.BOTTOM_LEFT,
                10,
                null,
                "[1 2 4] [1 2 5] [1 3 4] [1 3 5] [1 4 2] [1 4 3] [1 4 4] [1 4 5] [1 4 6] [1 4 7] [1 5 2] [1 5 3] [1 5 4] [1 5 5] [1 5 6] [1 5 7] [1 6 4] [1 6 5] [1 7 4] [1 7 5] [101 2 4] [101 5 2] [101 7 5] [101 4 7]  [1 4 4] [1 4 5] [1 5 4] [1 5 5] [3 2 5] [3 3 5] [3 4 2] [3 4 3] [3 6 4] [3 7 4] [3 5 6] [3 5 7]"
        );
    }

}
