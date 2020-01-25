package pl.jellysoft.kodbot.resolver.simulator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BotDirection {

    BOTTOM_RIGHT(0),
    BOTTOM_LEFT(1),
    TOP_LEFT(2),
    TOP_RIGHT(3);

    private final int id;

}
