package pl.jellysoft.kodbot.resolver.simulator;

import io.vavr.collection.Array;
import io.vavr.collection.List;
import io.vavr.control.Either;
import pl.jellysoft.kodbot.controller.bean.DataRow;
import pl.jellysoft.kodbot.controller.bean.MapBean;
import pl.jellysoft.kodbot.resolver.evaluator.ActionType;
import pl.jellysoft.kodbot.resolver.simulator.element.Battery;
import pl.jellysoft.kodbot.resolver.simulator.element.Element;
import pl.jellysoft.kodbot.resolver.simulator.element.ElementType;
import pl.jellysoft.kodbot.resolver.simulator.element.HeavyBox;
import pl.jellysoft.kodbot.resolver.simulator.element.LightBox;
import pl.jellysoft.kodbot.resolver.simulator.element.SpikedBox;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

import static io.vavr.collection.List.ofAll;
import static io.vavr.control.Either.right;

public class Simulator {

    public static Either<String, SimulatorResult> simulate(Iterable<ActionType> actions, MapBean mapBean) {
        return ofAll(actions)
                .foldLeft(setupSimulationContext(mapBean), (acc, action) ->
                        acc.flatMap(simulationContext -> {
                            if (action == ActionType.MOVE) {// TODO Get rid of wraps by rearranging the code
                                return wrap(move(simulationContext));
                            } else if (action == ActionType.JUMP) {
                                return wrap(jump(simulationContext));
                            } else if (action == ActionType.TURN_LEFT) {
                                return wrap(turnLeft(simulationContext));
                            } else if (action == ActionType.TURN_RIGHT) {
                                return wrap(turnRight(simulationContext));
                            } else {
                                return Either.<String, SimulationContext>left("Nieznany rodzaj otrzymanej akcji");
                            }
                        })
                )
                .map(simulationContext -> new SimulatorResult(simulationContext.getBatteryLevel(), checkIfWin(simulationContext)));
    }

    private static SimulationContext move(SimulationContext simulationContext) {
        SimulationContext result = simulationContext;
        if (simulationContext.getBatteryLevel() >= ActionType.MOVE.getBatteryCost()) {
            result = result.withBatteryLevel(result.getBatteryLevel() - ActionType.MOVE.getBatteryCost());
            Position nextPosition = getNextPositionInCurrentDirection(simulationContext);
            if (validateMove(nextPosition.row, nextPosition.col, simulationContext)) {
                result = result.withBotRow(nextPosition.row).withBotCol(nextPosition.col);
                result = checkAndPickupItems(result);
            }
        }
        return result;
    }

    private static SimulationContext jump(SimulationContext simulationContext) {
        SimulationContext result = simulationContext;
        if (simulationContext.getBatteryLevel() >= ActionType.JUMP.getBatteryCost()) {
            result = result.withBatteryLevel(result.getBatteryLevel() - ActionType.JUMP.getBatteryCost());
            Position nextPosition = getNextPositionInCurrentDirection(simulationContext);
            if (validateJump(nextPosition.row, nextPosition.col, simulationContext)) {
                result = result.withBotRow(nextPosition.row).withBotCol(nextPosition.col);
                result = checkAndPickupItems(result);
            }
        }
        return result;
    }

    private static SimulationContext turnLeft(SimulationContext simulationContext) {
        return Optional.of(simulationContext)
                .filter(sm -> sm.getBatteryLevel() >= ActionType.TURN_LEFT.getBatteryCost())
                .map(sm -> sm.withBatteryLevel(sm.getBatteryLevel() - ActionType.TURN_LEFT.getBatteryCost())
                        .withBotDirection((sm.getBotDirection() + 3) % 4))
                .orElse(simulationContext);
    }

    private static SimulationContext turnRight(SimulationContext simulationContext) {
        return Optional.of(simulationContext)
                .filter(sm -> sm.getBatteryLevel() >= ActionType.TURN_RIGHT.getBatteryCost())
                .map(sm -> sm
                        .withBatteryLevel(sm.getBatteryLevel() - ActionType.TURN_RIGHT.getBatteryCost())
                        .withBotDirection((sm.getBotDirection() + 1) % 4))
                .orElse(simulationContext);
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

    private static Either<String, SimulationContext> setupSimulationContext(MapBean mapBean) {
        return createElementsBoard(mapBean.getData()).map(elements ->
                SimulationContext.builder()
                        .elements(elements)
                        .batteryLevel(mapBean.getBatteryLevel())
                        .botCol(mapBean.getBotPositionCol())
                        .botRow(mapBean.getBotPositionRow())
                        .botDirection(mapBean.getBotRotation())
                        .batteryCount(countBatteries(mapBean.getData()))
                        .build());
    }

    private static Position getNextPositionInCurrentDirection(SimulationContext simulationContext) {
        int newRow = simulationContext.getBotRow();
        int newCol = simulationContext.getBotCol();
        // TODO why simulationContext cant use Enum directly?
        if (simulationContext.getBotDirection() == BotDirection.BOTTOM_RIGHT.getId()) {
            newCol++;
        } else if (simulationContext.getBotDirection() == BotDirection.BOTTOM_LEFT.getId()) {
            newRow--;
        } else if (simulationContext.getBotDirection() == BotDirection.TOP_LEFT.getId()) {
            newCol--;
        } else if (simulationContext.getBotDirection() == BotDirection.TOP_RIGHT.getId()) {
            newRow++;
        }
        return new Position(newRow, newCol);
    }

    private static boolean checkIfWin(SimulationContext simulationContext) {
        return simulationContext.getBatteryCount() == 0;
    }

    private static boolean validateMove(int row, int col, SimulationContext simulationContext) {
        if (validatePosition(row, col)) {
            int currentHeight = getStandableHeight(simulationContext.getBotRow(), simulationContext.getBotCol(), simulationContext);
            int destinationHeight = getStandableHeight(row, col, simulationContext);
            if (currentHeight == destinationHeight) {
                List<Element> stack = simulationContext.getElements().get(row).get(col);
                if (stack.size() == destinationHeight) {
                    return true;
                } else {
                    return isPickupable(simulationContext.getElements().get(row).get(col).peek());
                }
            }
        }
        return false;
    }

    private static boolean validateJump(int row, int col, SimulationContext simulationContext) {
        if (validatePosition(row, col)) {
            int currentHeight = getStandableHeight(simulationContext.getBotRow(), simulationContext.getBotCol(), simulationContext);
            int destinationHeight = getStandableHeight(row, col, simulationContext);
            if (Math.abs(currentHeight - destinationHeight) == 1) {
                List<Element> stack = simulationContext.getElements().get(row).get(col);
                if (stack.size() == destinationHeight) {
                    return true;
                } else {
                    return isPickupable(simulationContext.getElements().get(row).get(col).peek());
                }
            }
        }
        return false;
    }

    private static boolean validatePosition(int row, int col) {
        return row >= 0 && row < 10 && col >= 0 && col < 10;
    }

    private static int getStandableHeight(int row, int col, SimulationContext simulationContext) {
        List<Element> stack = simulationContext.getElements().get(row).get(col);
        int height = 0;
        for (Element element : stack) {
            if (isStandable(element)) {
                height++;
            } else {
                break;
            }
        }
        return height;
    }

    private static SimulationContext checkAndPickupItems(SimulationContext simulationContext) {
        SimulationContext result = simulationContext;
        Element element = simulationContext.getElements()
                .get(simulationContext.getBotRow())
                .get(simulationContext.getBotCol())
                .peek();
        if (isPickupable(element)) {
            result = result.withElements(result.getElements().update(simulationContext.getBotRow(), cols -> cols.update(simulationContext.getBotCol(), List::pop)));
            if (element instanceof Battery) {
                result = result.withBatteryLevel(Math.min(100, result.getBatteryLevel() + ((Battery) element).getBatteryAmount()))
                        .withBatteryCount(result.getBatteryCount() - 1);
            }
        }
        return result;
    }

    private static boolean isPickupable(Element element) {
        return element instanceof Battery;
    }

    private static boolean isStandable(Element element) {
        return (element instanceof HeavyBox || element instanceof LightBox);
    }

    private static Either<String, SimulationContext> wrap(SimulationContext simulationContext) {
        return Either.right(simulationContext);
    }

    // TODO use Lombok magic here
    private static class Position {
        private final int row;
        private final int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

}
