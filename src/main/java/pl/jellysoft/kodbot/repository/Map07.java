package pl.jellysoft.kodbot.repository;

import pl.jellysoft.kodbot.model.GameMap;
import pl.jellysoft.kodbot.resolver.simulator.BotDirection;


final class Map07 {

    static GameMap map07() {
        return new GameMap(
                "07357fdaadfe8381db7f148d900d3165",
                1,
                4,
                BotDirection.BOTTOM_RIGHT,
                25,
                null,
                "[1 1 4] [1 1 5] [1 1 6] [1 1 7] [1 2 7] [1 3 7] [1 4 4] [1 4 5] [1 4 6] [1 4 7] [1 5 4] [1 5 4] [1 6 4] [1 6 4] [1 6 4] [1 7 4] [1 7 4] [1 7 4] [1 7 4] [100 1 7] [100 4 7] [100 4 4] [100 7 4]"
        );
    }

}
