package pl.jellysoft.kodbot.resolver.statistic;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@Builder
@With
public class StatisticDTO {

    @Builder.Default
    private final int moveCount = 0;

    @Builder.Default
    private final int jumpCount = 0;

    @Builder.Default
    private final int turnLeftCount = 0;

    @Builder.Default
    private final int turnRightCount = 0;

    @Builder.Default
    private final int definedFunctionCount = 0;

    @Builder.Default
    private final int calledFunctionCount = 0;

    @Builder.Default
    private final int repeatCount = 0;

    @Builder.Default
    private final int assignWithAdditionCount = 0;

    @Builder.Default
    private final int assignWithSubtractionCount = 0;

    @Builder.Default
    private final int assignCount = 0;

    @Builder.Default
    private final int incrementCount = 0;

    @Builder.Default
    private final int decrementCount = 0;

}
