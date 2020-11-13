package pl.jellysoft.kodbot.repository;

import pl.jellysoft.kodbot.model.BotDirection;
import pl.jellysoft.kodbot.model.GameMap;


final class Map08 {

    static GameMap map08() {
        return new GameMap(
                "089b11ef5dac2d659666d11a64076a0d",
                5,
                5,
                BotDirection.BOTTOM_LEFT,
                100,
                null,
                "[1 3 3] [1 3 4] [1 3 5] [1 3 6] [1 3 7] [1 4 3] [1 4 5] [1 4 7] [1 5 3] [1 5 4] [2 5 5] [1 5 6] [1 5 7] [1 6 3] [1 6 5] [1 6 7] [1 7 3] [1 7 4] [1 7 5] [1 7 6] [1 7 7] [101 3 3] [101 3 5] [101 3 7]  [101 5 3] [101 5 7] [101 7 3] [101 7 5] [101 7 7]"
        );
    }

}
