package pl.jellysoft.kodbot.resolver.simulator;

import io.vavr.collection.List;
import lombok.RequiredArgsConstructor;
import pl.jellysoft.kodbot.resolver.evaluator.ActionType;
import pl.jellysoft.kodbot.resolver.evaluator.ActionTypeVisitor;
import pl.jellysoft.kodbot.resolver.simulator.element.Battery;
import pl.jellysoft.kodbot.resolver.simulator.element.Element;

import java.util.Optional;

import static io.vavr.collection.List.ofAll;
import static pl.jellysoft.kodbot.model.BotDirectionRotateLeftVisitor.rotateLeft;
import static pl.jellysoft.kodbot.model.BotDirectionRotateRightVisitor.rotateRight;
import static pl.jellysoft.kodbot.resolver.evaluator.ActionType.JUMP;
import static pl.jellysoft.kodbot.resolver.evaluator.ActionType.MOVE;
import static pl.jellysoft.kodbot.resolver.evaluator.ActionType.TURN_LEFT;
import static pl.jellysoft.kodbot.resolver.evaluator.ActionType.TURN_RIGHT;
import static pl.jellysoft.kodbot.resolver.evaluator.BatteryCostResolvingActionTypeVisitor.batteryCostForAction;
import static pl.jellysoft.kodbot.resolver.simulator.Position.getNextPosition;
import static pl.jellysoft.kodbot.resolver.simulator.Simulator.ActionTypeSimulationVisitor.simulateNextStep;

public class Simulator {

    public static SimulatorResult simulate(Iterable<ActionType> actions, SimulationContext initialContext) {
        SimulationContext result = ofAll(actions)
                .foldLeft((initialContext), (simulationContext, action) -> simulateNextStep(action, simulationContext));
        return new SimulatorResult(result.getBatteryLevel(), checkIfWin(result));
    }

    private static SimulationContext move(SimulationContext simulationContext) {
        SimulationContext result = simulationContext;
        if (simulationContext.getBatteryLevel() >= batteryCostForAction(MOVE)) {
            result = result.withBatteryLevel(result.getBatteryLevel() - batteryCostForAction(MOVE));
            Position nextPosition = getNextPosition(simulationContext.getBotPosition(), simulationContext.getBotDirection());
            if (validateMove(nextPosition, simulationContext)) {
                result = result.withBotPosition(nextPosition);
                result = checkAndPickupItems(result);
            }
        }
        return result;
    }

    private static SimulationContext jump(SimulationContext simulationContext) {
        SimulationContext result = simulationContext;
        if (simulationContext.getBatteryLevel() >= batteryCostForAction(JUMP)) {
            result = result.withBatteryLevel(result.getBatteryLevel() - batteryCostForAction(JUMP));
            Position nextPosition = getNextPosition(simulationContext.getBotPosition(), simulationContext.getBotDirection());
            if (validateJump(nextPosition, simulationContext)) {
                result = result.withBotPosition(nextPosition);
                result = checkAndPickupItems(result);
            }
        }
        return result;
    }

    private static SimulationContext turnLeft(SimulationContext simulationContext) {
        return Optional.of(simulationContext)
                .filter(sm -> sm.getBatteryLevel() >= batteryCostForAction(TURN_LEFT))
                .map(sm -> sm.withBatteryLevel(sm.getBatteryLevel() - batteryCostForAction(TURN_LEFT))
                        .withBotDirection(rotateLeft(sm.getBotDirection())))
                .orElse(simulationContext);
    }

    private static SimulationContext turnRight(SimulationContext simulationContext) {
        return Optional.of(simulationContext)
                .filter(sm -> sm.getBatteryLevel() >= batteryCostForAction(TURN_RIGHT))
                .map(sm -> sm
                        .withBatteryLevel(sm.getBatteryLevel() - batteryCostForAction(TURN_RIGHT))
                        .withBotDirection(rotateRight(sm.getBotDirection())))
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
                List<Element> stack = simulationContext.getElementsAtPosition(position);
                if (stack.size() == destinationHeight) {
                    return true;
                } else {
                    return stack.peek().isPickable();
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
                List<Element> stack = simulationContext.getElementsAtPosition(position);
                if (stack.size() == destinationHeight) {
                    return true;
                } else {
                    return stack.peek().isPickable();
                }
            }
        }
        return false;
    }

    private static boolean validatePosition(Position position) {
        return position.getRow() >= 0 && position.getRow() < 10 && position.getCol() >= 0 && position.getCol() < 10;
    }

    private static int getStandableHeight(Position position, SimulationContext simulationContext) {
        return simulationContext.getElementsAtPosition(position)
                .count(Element::isStandable);
    }

    private static SimulationContext checkAndPickupItems(SimulationContext simulationContext) {
        Position botPosition = simulationContext.getBotPosition();
        List<Element> elementsAtPosition = simulationContext.getElementsAtPosition(botPosition);
        if (elementsAtPosition.size() > 0) {
            Element element = elementsAtPosition.peek();
            if (element.isPickable()) {
                int botRow = botPosition.getRow();
                int botCol = botPosition.getCol();
                int batteryLevelDelta = element instanceof Battery ? ((Battery) element).getBatteryAmount() : 0;
                int newBatteryLevel = Math.min(100, simulationContext.getBatteryLevel() + batteryLevelDelta);
                int newBatteryCount = simulationContext.getBatteryCount() - (element instanceof Battery ? 1 : 0);
                return simulationContext
                        .withBatteryLevel(newBatteryLevel)
                        .withBatteryCount(newBatteryCount)
                        .withElements(simulationContext.getElements().update(botRow, cols -> cols.update(botCol, List::pop)));
            }
        }
        return simulationContext;
    }

    @RequiredArgsConstructor
    static class ActionTypeSimulationVisitor implements ActionTypeVisitor<SimulationContext> {

        private final SimulationContext simulationContext;

        static SimulationContext simulateNextStep(ActionType actionType, SimulationContext simulationContext) {
            return actionType.accept(new ActionTypeSimulationVisitor(simulationContext));
        }

        @Override
        public SimulationContext visitMove() {
            return move(simulationContext);
        }

        @Override
        public SimulationContext visitJump() {
            return jump(simulationContext);
        }

        @Override
        public SimulationContext visitTurnLeft() {
            return turnLeft(simulationContext);
        }

        @Override
        public SimulationContext visitTurnRight() {
            return turnRight(simulationContext);
        }
    }

}
