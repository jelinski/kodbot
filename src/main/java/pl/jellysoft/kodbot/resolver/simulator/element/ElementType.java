package pl.jellysoft.kodbot.resolver.simulator.element;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ElementType {

    HEAVY_BOX(1),
    LIGHT_BOX(2),
    SPIKED_BOX(3),
    BATTERY_LOW(100),
    BATTERY_MEDIUM(101),
    BATTERY_HIGH(102);

    private final int id;

    public ElementType getTypeById(int id) {
        for (ElementType e : ElementType.values()) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

}
