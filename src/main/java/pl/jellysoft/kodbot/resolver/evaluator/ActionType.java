package pl.jellysoft.kodbot.resolver.evaluator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@Getter
@RequiredArgsConstructor
public enum ActionType {

    MOVE(ActionTypeVisitor::visitMove),
    JUMP(ActionTypeVisitor::visitJump),
    TURN_LEFT(ActionTypeVisitor::visitTurnLeft),
    TURN_RIGHT(ActionTypeVisitor::visitTurnRight);

    private final Function<ActionTypeVisitor<?>, ?> dispatcher;

    @SuppressWarnings("unchecked")
    public <T> T accept(ActionTypeVisitor<T> visitor) {
        return (T) dispatcher.apply(visitor);
    }

}
