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
import static pl.jellysoft.kodbot.resolver.simulator.Position.getNextPosition;

public class Simulator {

    public static Either<String, SimulatorResult> simulate(Iterable<ActionType> actions, SimulationContext initialContext) {
        return ofAll(actions)
                .foldLeft(Either.<String, SimulationContext>right(initialContext), (acc, action) ->
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
                .map(simulationContext -> new SimulatorResult(simulationContext.getBatteryLevel(), checkIfWin(simulationContext)));
    }

    private static SimulationContext move(SimulationContext simulationContext) {
        SimulationContext result = simulationContext;
        if (simulationContext.getBatteryLevel() >= ActionType.MOVE.getBatteryCost()) {
            result = result.withBatteryLevel(result.getBatteryLevel() - ActionType.MOVE.getBatteryCost());
            Position nextPosition = getNextPosition(simulationContext.getBotPosition(), BotDirection.fromId(simulationContext.getBotDirection()));
            if (validateMove(nextPosition, simulationContext)) {
                result = result.withBotPosition(nextPosition);
                result = checkAndPickupItems(result);
            }
        }
        return result;
    }

    private static SimulationContext jump(SimulationContext simulationContext) {
        SimulationContext result = simulationContext;
        if (simulationContext.getBatteryLevel() >= ActionType.JUMP.getBatteryCost()) {
            result = result.withBatteryLevel(result.getBatteryLevel() - ActionType.JUMP.getBatteryCost());
            Position nextPosition = getNextPosition(simulationContext.getBotPosition(), BotDirection.fromId(simulationContext.getBotDirection()));
            if (validateJump(nextPosition, simulationContext)) {
                result = result.withBotPosition(nextPosition);
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

    private static boolean checkIfWin(SimulationContext simulationContext) {
        return simulationContext.getBatteryCount() == 0;
    }

    private static boolean validateMove(Position position, SimulationContext simulationContext) {
        if (validatePosition(position)) {
            int currentHeight = getStandableHeight(simulationContext.getBotPosition(), simulationContext);
            int destinationHeight = getStandableHeight(position, simulationContext);
            if (currentHeight == destinationHeight) {
                List<Element> stack = simulationContext.getElements().get(position.getRow()).get(position.getCol());
                if (stack.size() == destinationHeight) {
                    return true;
                } else {
                    return isPickupable(stack.peek());
                }
            }
        }
        return false;
    }

    private static boolean validateJump(Position position, SimulationContext simulationContext) {
        if (validatePosition(position)) {
            int currentHeight = getStandableHeight(simulationContext.getBotPosition(), simulationContext);
            int destinationHeight = getStandableHeight(position, simulationContext);
            if (Math.abs(currentHeight - destinationHeight) == 1) {
                List<Element> stack = simulationContext.getElements().get(position.getRow()).get(position.getCol());
                if (stack.size() == destinationHeight) {
                    return true;
                } else {
                    return isPickupable(stack.peek());
                }
            }
        }
        return false;
    }

    private static boolean validatePosition(Position position) {
        return position.getRow() >= 0 && position.getRow() < 10 && position.getCol() >= 0 && position.getCol() < 10;
    }

    private static int getStandableHeight(Position position, SimulationContext simulationContext) {
        List<Element> stack = simulationContext.getElements().get(position.getRow()).get(position.getCol());
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
        int botRow = simulationContext.getBotPosition().getRow();
        int botCol = simulationContext.getBotPosition().getCol();
        Element element = simulationContext.getElements()
                .get(botRow)
                .get(botCol)
                .peek();
        if (isPickupable(element)) {
            result = result.withElements(result.getElements().update(botRow, cols -> cols.update(botCol, List::pop)));
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

}
