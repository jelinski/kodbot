package pl.jellysoft.kodbot.resolver.simulator;

import com.google.common.collect.MoreCollectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.function.Function;

@Getter
@RequiredArgsConstructor
public enum BotDirection {

    BOTTOM_RIGHT(0, BotDirectionVisitor::visitBottomRight),
    BOTTOM_LEFT(1, BotDirectionVisitor::visitBottomLeft),
    TOP_LEFT(2, BotDirectionVisitor::visitTopLeft),
    TOP_RIGHT(3, BotDirectionVisitor::visitTopRight);

    private final int id;
    private final Function<BotDirectionVisitor<?>, ?> dispatcher;

    static BotDirection fromId(int id) {
        return Arrays.stream(values())
                .filter(botDirection -> id == botDirection.getId())
                .collect(MoreCollectors.onlyElement());
    }

    @SuppressWarnings("unchecked")
    <T> T accept(BotDirectionVisitor<T> visitor) {
        return (T) dispatcher.apply(visitor);
    }

}
