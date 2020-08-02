package pl.jellysoft.kodbot.repository;

import pl.jellysoft.kodbot.model.GameMap;
import pl.jellysoft.kodbot.resolver.simulator.BotDirection;


final class Map10 {

    static GameMap map10() {
        return new GameMap(
                "106f2cad6348f29698a6e435efb9523a",
                1,
                0,
                BotDirection.BOTTOM_RIGHT,
                50,
                null,
                "[1 1 4] [1 2 4] [1 3 4] [1 4 4] [1 4 5] [1 4 6] [1 4 7] [1 4 8] [1 5 1] [1 5 2] [1 5 3] [1 5 4] [1 5 5] [1 6 5] [1 7 5] [1 8 5] [100 4 4] [100 4 5] [100 5 4] [100 5 5] [101 1 4] [101 4 8] [101 5 1] [101 8 5]"
        );
    }

}
