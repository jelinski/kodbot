package pl.jellysoft.kodbot.repository;

import pl.jellysoft.kodbot.model.GameMap;
import pl.jellysoft.kodbot.resolver.simulator.BotDirection;


final class Map04 {

    static GameMap map04() {
        return new GameMap(
                "0477fc4aade86292b60e8df852c03477",
                2,
                2,
                BotDirection.BOTTOM_RIGHT,
                50,
                null,
                "[3 3 4] [3 3 5] [3 4 3] [3 4 6] [3 5 3] [3 5 6] [3 6 4] [3 6 5] [102 2 7] [101 7 7] [100 7 2]"
        );
    }

}
