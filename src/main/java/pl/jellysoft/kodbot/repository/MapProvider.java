package pl.jellysoft.kodbot.repository;

import org.apache.commons.lang3.ArrayUtils;
import pl.jellysoft.kodbot.model.GameMap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;
import static pl.jellysoft.kodbot.repository.Map01.map01;
import static pl.jellysoft.kodbot.repository.Map02.map02;
import static pl.jellysoft.kodbot.repository.Map03.map03;
import static pl.jellysoft.kodbot.repository.Map04.map04;
import static pl.jellysoft.kodbot.repository.Map05.map05;
import static pl.jellysoft.kodbot.repository.Map06.map06;
import static pl.jellysoft.kodbot.repository.Map07.map07;
import static pl.jellysoft.kodbot.repository.Map08.map08;
import static pl.jellysoft.kodbot.repository.Map09.map09;
import static pl.jellysoft.kodbot.repository.Map10.map10;
import static pl.jellysoft.kodbot.repository.Map11.map11;
import static pl.jellysoft.kodbot.repository.Map12.map12;
import static pl.jellysoft.kodbot.repository.Map13.map13;
import static pl.jellysoft.kodbot.repository.Map14.map14;
import static pl.jellysoft.kodbot.repository.Map15.map15;
import static pl.jellysoft.kodbot.repository.Map16.map16;
import static pl.jellysoft.kodbot.repository.Map17.map17;
import static pl.jellysoft.kodbot.repository.Map18.map18;
import static pl.jellysoft.kodbot.repository.Map19.map19;
import static pl.jellysoft.kodbot.repository.Map20.map20;
import static pl.jellysoft.kodbot.repository.Map21.map21;

public class MapProvider {

    private static final GameMap[] GAME_MAPS = new GameMap[]{
            map01(), map02(), map03(), map04(), map05(), map06(), map07(), map08(), map09(), map10(),
            map11(), map12(), map13(), map14(), map15(), map16(), map17(), map18(), map19(), map20(),
            map21()
    };

    private static final Map<String, GameMap> keyToGameMapMapping =
            Arrays.stream(GAME_MAPS)
                    .collect(toMap(GameMap::getKey, Function.identity()));

    public GameMap getMapByKey(String key) {
        return keyToGameMapMapping.get(key);
    }

    public List<GameMap> getAllMaps() {
        return Arrays.asList(GAME_MAPS);
    }

    public String getNextGameMapKey(GameMap currentMap) {
        int nextMapIndex = ArrayUtils.indexOf(GAME_MAPS, currentMap) + 1;
        return GAME_MAPS.length > nextMapIndex ? GAME_MAPS[nextMapIndex].getKey() : null;
    }

}
