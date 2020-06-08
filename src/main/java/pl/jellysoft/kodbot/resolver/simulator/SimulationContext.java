package pl.jellysoft.kodbot.resolver.simulator;

import io.vavr.collection.Array;
import io.vavr.collection.List;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import pl.jellysoft.kodbot.resolver.simulator.element.Element;

@Value
@Builder
@With
class SimulationContext {

    private final Array<Array<List<Element>>> elements;
    private final int batteryCount;
    private final int batteryLevel;
    private final int botRow;
    private final int botCol;
    private final int botDirection;

}
