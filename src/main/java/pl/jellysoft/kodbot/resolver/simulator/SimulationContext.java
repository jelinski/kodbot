package pl.jellysoft.kodbot.resolver.simulator;

import io.vavr.collection.Array;
import io.vavr.collection.List;
import io.vavr.control.Either;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import pl.jellysoft.kodbot.controller.bean.DataRow;
import pl.jellysoft.kodbot.controller.bean.MapBean;
import pl.jellysoft.kodbot.resolver.simulator.element.Battery;
import pl.jellysoft.kodbot.resolver.simulator.element.Element;
import pl.jellysoft.kodbot.resolver.simulator.element.ElementType;
import pl.jellysoft.kodbot.resolver.simulator.element.HeavyBox;
import pl.jellysoft.kodbot.resolver.simulator.element.LightBox;
import pl.jellysoft.kodbot.resolver.simulator.element.SpikedBox;

import java.util.Collection;
import java.util.function.Predicate;

import static io.vavr.control.Either.right;

@Value
@Builder
@With
public class SimulationContext {

    private final Array<Array<List<Element>>> elements;
    private final int batteryCount;
    private final int batteryLevel;
    private final Position botPosition;
    private final BotDirection botDirection;

    public static Either<String, SimulationContext> setupSimulationContext(MapBean mapBean) {
        return createElementsBoard(mapBean.getData()).map(elements ->
                SimulationContext.builder()
                        .elements(elements)
                        .batteryLevel(mapBean.getBatteryLevel())
                        .botPosition(new Position(mapBean.getBotPositionRow(), mapBean.getBotPositionCol()))
                        .botDirection(BotDirection.fromId(mapBean.getBotRotation()))
                        .batteryCount(countBatteries(mapBean.getData()))
                        .build());
    }

    private static Either<String, Array<Array<List<Element>>>> createElementsBoard(Collection<DataRow> data) {
        Array<Array<List<Element>>> elements = Array.fill(10, () -> Array.fill(10, List::empty));
        return List.ofAll(data)
                .foldLeft(Either.right(elements), (acc, d) ->
                        acc.flatMap(e -> {
                            Element element;
                            int typeId = d.getType();
                            if (typeId == ElementType.HEAVY_BOX.getId()) {
                                element = new HeavyBox();
                            } else if (typeId == ElementType.LIGHT_BOX.getId()) {
                                element = new LightBox();
                            } else if (typeId == ElementType.SPIKED_BOX.getId()) {
                                element = new SpikedBox();
                            } else if (typeId == ElementType.BATTERY_LOW.getId()) {
                                element = new Battery(Battery.LOW_AMOUNT);
                            } else if (typeId == ElementType.BATTERY_MEDIUM.getId()) {
                                element = new Battery(Battery.MEDIUM_AMOUNT);
                            } else if (typeId == ElementType.BATTERY_HIGH.getId()) {
                                element = new Battery(Battery.HIGH_AMOUNT);
                            } else {
                                return Either.<String, Array<Array<List<Element>>>>left("Nieznany typ elementu podczas tworzenia mapy dla symulacji");
                            }
                            return right(e.update(d.getRow(), cols -> cols.update(d.getCol(), old -> old.push(element))));
                        }));
    }

    private static int countBatteries(Collection<DataRow> data) {
        List<Integer> batteryIds =
                List.of(ElementType.BATTERY_LOW, ElementType.BATTERY_MEDIUM, ElementType.BATTERY_HIGH)
                        .map(ElementType::getId);
        Predicate<Integer> isBatteryType = batteryIds::contains;
        return (int) data.stream()
                .map(DataRow::getType)
                .filter(isBatteryType)
                .count();
    }

}
