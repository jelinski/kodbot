package pl.jellysoft.kodbot.resolver.simulator;

import io.vavr.control.Either;
import lombok.Value;
import org.apache.commons.lang3.mutable.MutableInt;
import pl.jellysoft.kodbot.controller.bean.DataRow;
import pl.jellysoft.kodbot.controller.bean.MapBean;
import pl.jellysoft.kodbot.resolver.evaluator.ActionType;
import pl.jellysoft.kodbot.resolver.simulator.element.Battery;
import pl.jellysoft.kodbot.resolver.simulator.element.Element;
import pl.jellysoft.kodbot.resolver.simulator.element.ElementType;
import pl.jellysoft.kodbot.resolver.simulator.element.HeavyBox;
import pl.jellysoft.kodbot.resolver.simulator.element.LightBox;
import pl.jellysoft.kodbot.resolver.simulator.element.SpikedBox;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static io.vavr.collection.List.ofAll;
import static io.vavr.control.Either.left;
import static io.vavr.control.Either.right;

public class Simulator {

    public static Either<String, SimulatorResult> simulate(List<ActionType> actions, MapBean mapBean) {
        return ofAll(actions)
                .foldLeft(setupMap(mapBean), (acc, action) ->
                        acc.flatMap(simulationContext -> {
                            if (action == ActionType.MOVE) {
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
                .map(simulationContext -> new SimulatorResult(simulationContext.batteryLevel, checkIfWin(simulationContext)));
    }

    private static SimulationContext move(SimulationContext simulationContext) {
        if (simulationContext.batteryLevel >= ActionType.MOVE.getBatteryCost()) {
            simulationContext.batteryLevel -= ActionType.MOVE.getBatteryCost();
            Position nextPosition = getNextPositionInCurrentDirection(simulationContext);
            if (validateMove(nextPosition.row, nextPosition.col, simulationContext)) {
                simulationContext.botRow = nextPosition.row;
                simulationContext.botCol = nextPosition.col;
                checkAndPickupItems(simulationContext);
            }
        }
        return simulationContext;
    }

    private static SimulationContext jump(SimulationContext simulationContext) {
        if (simulationContext.batteryLevel >= ActionType.JUMP.getBatteryCost()) {
            simulationContext.batteryLevel -= ActionType.JUMP.getBatteryCost();
            Position nextPosition = getNextPositionInCurrentDirection(simulationContext);
            if (validateJump(nextPosition.row, nextPosition.col, simulationContext)) {
                simulationContext.botRow = nextPosition.row;
                simulationContext.botCol = nextPosition.col;
                checkAndPickupItems(simulationContext);
            }
        }
        return simulationContext;
    }

    private static SimulationContext turnLeft(SimulationContext simulationContext) {
        if (simulationContext.batteryLevel >= ActionType.TURN_LEFT.getBatteryCost()) {
            simulationContext.batteryLevel -= ActionType.TURN_LEFT.getBatteryCost();
            simulationContext.botDirection += 3;
        }
        return simulationContext;
    }

    private static SimulationContext turnRight(SimulationContext simulationContext) {
        if (simulationContext.batteryLevel >= ActionType.TURN_RIGHT.getBatteryCost()) {
            simulationContext.batteryLevel -= ActionType.TURN_RIGHT.getBatteryCost();
            simulationContext.botDirection++;
        }
        return simulationContext;
    }

    private static Either<String, SimulationContext> setupMap(MapBean mapBean) {
        SimulationContext simulationContext = new SimulationContext();
        simulationContext.batteryLevel = mapBean.getBatteryLevel();
        simulationContext.botCol = mapBean.getBotPositionCol();
        simulationContext.botRow = mapBean.getBotPositionRow();
        simulationContext.botDirection = mapBean.getBotRotation();
        for (DataRow row : mapBean.getData()) {
            Element element;
            int typeId = row.getType();
            if (typeId == ElementType.HEAVY_BOX.getId()) {
                element = new HeavyBox();
            } else if (typeId == ElementType.LIGHT_BOX.getId()) {
                element = new LightBox();
            } else if (typeId == ElementType.SPIKED_BOX.getId()) {
                element = new SpikedBox();
            } else if (typeId == ElementType.BATTERY_LOW.getId()) {
                element = new Battery(Battery.LOW_AMOUNT);
                simulationContext.batteryCount.increment();
            } else if (typeId == ElementType.BATTERY_MEDIUM.getId()) {
                element = new Battery(Battery.MEDIUM_AMOUNT);
                simulationContext.batteryCount.increment();
            } else if (typeId == ElementType.BATTERY_HIGH.getId()) {
                element = new Battery(Battery.HIGH_AMOUNT);
                simulationContext.batteryCount.increment();
            } else {
                return left("Nieznany typ elementu podczas tworzenia mapy dla symulacji");
            }
            simulationContext.map.get(row.getRow()).get(row.getCol()).addLast(element);
        }
        return right(simulationContext);
    }

    private static Position getNextPositionInCurrentDirection(SimulationContext simulationContext) {
        simulationContext.botDirection = simulationContext.botDirection % 4;
        int newRow = simulationContext.botRow;
        int newCol = simulationContext.botCol;
        if (simulationContext.botDirection == BotDirection.BOTTOM_RIGHT.getId()) {
            newCol++;
        } else if (simulationContext.botDirection == BotDirection.BOTTOM_LEFT.getId()) {
            newRow--;
        } else if (simulationContext.botDirection == BotDirection.TOP_LEFT.getId()) {
            newCol--;
        } else if (simulationContext.botDirection == BotDirection.TOP_RIGHT.getId()) {
            newRow++;
        }
        return new Position(newRow, newCol);
    }

    private static boolean checkIfWin(SimulationContext simulationContext) {
        return simulationContext.batteryCount.intValue() == 0;
    }

    private static boolean validateMove(int row, int col, SimulationContext simulationContext) {
        if (validatePosition(row, col)) {
            int currentHeight = getStandableHeight(simulationContext.botRow, simulationContext.botCol, simulationContext);
            int destinationHeight = getStandableHeight(row, col, simulationContext);
            if (currentHeight == destinationHeight) {
                Deque<Element> stack = simulationContext.map.get(row).get(col);
                if (stack.size() == destinationHeight) {
                    return true;
                } else {
                    return isPickupable(simulationContext.map.get(row).get(col).peekLast());
                }
            }
        }
        return false;
    }

    private static boolean validateJump(int row, int col, SimulationContext simulationContext) {
        if (validatePosition(row, col)) {
            int currentHeight = getStandableHeight(simulationContext.botRow, simulationContext.botCol, simulationContext);
            int destinationHeight = getStandableHeight(row, col, simulationContext);
            if (Math.abs(currentHeight - destinationHeight) == 1) {
                Deque<Element> stack = simulationContext.map.get(row).get(col);
                if (stack.size() == destinationHeight) {
                    return true;
                } else {
                    return isPickupable(simulationContext.map.get(row).get(col).peekLast());
                }
            }
        }
        return false;
    }

    private static boolean validatePosition(int row, int col) {
        return row >= 0 && row < 10 && col >= 0 && col < 10;
    }

    private static int getStandableHeight(int row, int col, SimulationContext simulationContext) {
        Deque<Element> stack = simulationContext.map.get(row).get(col);
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

    private static void checkAndPickupItems(SimulationContext simulationContext) {
        Element element = simulationContext.map.get(simulationContext.botRow).get(simulationContext.botCol).peekLast();
        if (isPickupable(element)) {
            simulationContext.map.get(simulationContext.botRow).get(simulationContext.botCol).pollLast();
            if (element instanceof Battery) {
                simulationContext.batteryLevel += ((Battery) element).getBatteryAmount();
                if (simulationContext.batteryLevel > 100) {
                    simulationContext.batteryLevel = 100;
                }
                simulationContext.batteryCount.decrement();
            }
        }
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

    // TODO change to immutable structure
    private static class SimulationContext {
        private final List<List<Deque<Element>>> map = new ArrayList<>();
        private final MutableInt batteryCount = new MutableInt();
        private int batteryLevel;
        private int botRow;
        private int botCol;
        private int botDirection;

        SimulationContext() {
            for (int i = 0; i < 10; i++) {
                map.add(new ArrayList<>(10));
                for (int j = 0; j < 10; j++) {
                    map.get(i).add(new ArrayDeque<>());
                }
            }
        }

    }

    private static class Position {
        private final int row;
        private final int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    @Value
    public static class SimulatorResult {

        private final int batteryLevel;
        private final boolean userWon;

    }
}
