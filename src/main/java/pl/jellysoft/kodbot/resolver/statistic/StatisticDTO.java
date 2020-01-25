package pl.jellysoft.kodbot.resolver.statistic;

import lombok.Data;

@Data
public class StatisticDTO {

    private int overall;
    private int moveCount;
    private int jumpCount;
    private int turnLeftCount;
    private int turnRightCount;
    private int definedFunctionCount;
    private int calledFunctionCount;
    private int repeatCount;
    private int assignWithAdditionCount;
    private int assignWithSubtractionCount;
    private int assignCount;
    private int incrementCount;
    private int decrementCount;

}
