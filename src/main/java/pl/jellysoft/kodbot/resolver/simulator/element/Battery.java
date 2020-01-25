package pl.jellysoft.kodbot.resolver.simulator.element;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
// TODO change to @Value when Element changed to be interface
public class Battery extends Element {

    public static int LOW_AMOUNT = 25;
    public static int MEDIUM_AMOUNT = 50;
    public static int HIGH_AMOUNT = 75;

    private final int batteryAmount;

}