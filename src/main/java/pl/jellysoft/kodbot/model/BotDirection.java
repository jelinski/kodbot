package pl.jellysoft.kodbot.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@Getter
@RequiredArgsConstructor
public enum BotDirection {

    BOTTOM_RIGHT(BotDirectionVisitor::visitBottomRight),
    BOTTOM_LEFT(BotDirectionVisitor::visitBottomLeft),
    TOP_LEFT(BotDirectionVisitor::visitTopLeft),
    TOP_RIGHT(BotDirectionVisitor::visitTopRight);

    private final Function<BotDirectionVisitor<?>, ?> dispatcher;

    @SuppressWarnings("unchecked")
    <T> T accept(BotDirectionVisitor<T> visitor) {
        return (T) dispatcher.apply(visitor);
    }

}
