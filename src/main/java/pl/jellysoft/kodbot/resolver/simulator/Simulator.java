package pl.jellysoft.kodbot.resolver.simulator;

import io.vavr.collection.List;
import io.vavr.control.Either;
import pl.jellysoft.kodbot.resolver.evaluator.ActionType;
import pl.jellysoft.kodbot.resolver.simulator.element.Battery;
import pl.jellysoft.kodbot.resolver.simulator.element.Element;
import pl.jellysoft.kodbot.resolver.simulator.element.HeavyBox;
import pl.jellysoft.kodbot.resolver.simulator.element.LightBox;

import java.util.Optional;

import static io.vavr.collection.List.ofAll;

public class Simulator {

    public static Either<String, SimulatorResult> simulate(Iterable<ActionType> actions, SimulationContext initialContext) {
        return ofAll(actions)
                .foldLeft(Either.<String, SimulationContext>right(initialContext), (acc, action) ->
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
