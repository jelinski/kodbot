package pl.jellysoft.kodbot.resolver.simulator;

import com.google.common.collect.MoreCollectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum BotDirection {

    BOTTOM_RIGHT(0),
    BOTTOM_LEFT(1),
    TOP_LEFT(2),
    TOP_RIGHT(3);

    private final int id;

    static BotDirection fromId(int id) {
        return Arrays.stream(values())
                .filter(botDirection -> id == botDirection.getId())
                .collect(MoreCollectors.onlyElement());
    }

}
