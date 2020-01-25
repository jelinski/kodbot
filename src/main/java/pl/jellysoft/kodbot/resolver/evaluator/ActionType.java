package pl.jellysoft.kodbot.resolver.evaluator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ActionType {

    MOVE(5),
    JUMP(10),
    TURN_LEFT(5),
    TURN_RIGHT(5);

    private final int batteryCost;

}
