package pl.jellysoft.kodbot.resolver.evaluator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@Getter
@RequiredArgsConstructor
public enum ActionType {

    MOVE(5, ActionTypeVisitor::visitMove),
    JUMP(10, ActionTypeVisitor::visitJump),
    TURN_LEFT(5, ActionTypeVisitor::visitTurnLeft),
    TURN_RIGHT(5, ActionTypeVisitor::visitTurnRight);

    private final int batteryCost;
    private final Function<ActionTypeVisitor<?>, ?> dispatcher;

    @SuppressWarnings("unchecked")
    public <T> T accept(ActionTypeVisitor<T> visitor) {
        return (T) dispatcher.apply(visitor);
    }

}
