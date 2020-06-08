package pl.jellysoft.kodbot.resolver.simulator;

import lombok.Value;

@Value
public class SimulatorResult {

    private final int batteryLevel;
    private final boolean userWon;

}
