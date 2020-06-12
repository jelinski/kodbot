package pl.jellysoft.kodbot.resolver.simulator.element;

import lombok.Value;

@Value
public class Battery implements Element {

    public static final int LOW_AMOUNT = 25;
    public static final int MEDIUM_AMOUNT = 50;
    public static final int HIGH_AMOUNT = 75;

    private final int batteryAmount;

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    public boolean isStandable() {
        return false;
    }

}
