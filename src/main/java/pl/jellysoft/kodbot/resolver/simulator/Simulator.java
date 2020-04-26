package pl.jellysoft.kodbot.resolver.simulator;

import pl.jellysoft.kodbot.controller.bean.DataRow;
import pl.jellysoft.kodbot.controller.bean.MapBean;
import pl.jellysoft.kodbot.resolver.evaluator.ActionType;
import pl.jellysoft.kodbot.resolver.simulator.element.*;

import java.util.*;

public class Simulator {

    private final List<List<Deque<Element>>> map;
    private int batteryLevel;
    private int botRow;
    private int botCol;
    private int botDirection;
    private int batteryNumber;

    public Simulator() {
        batteryNumber = 0;
        map = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            map.add(new ArrayList<>(10));
            for (int j = 0; j < 10; j++) {
                map.get(i).add(new ArrayDeque<>());
            }
        }
    }

    public SimulatorResult simulate(List<ActionType> actions, MapBean mapBean) throws SimulatorException {
        setupMap(mapBean);
        for (ActionType action : actions) {

            if (action == ActionType.MOVE) {
                move();
            } else if (action == ActionType.JUMP) {
                jump();
            } else if (action == ActionType.TURN_LEFT) {
                turnLeft();
            } else if (action == ActionType.TURN_RIGHT) {
                turnRight();
            } else {
                throw new SimulatorException("Nieznany rodzaj otrzymanej akcji");
            }
        }
        if (checkIfWin()) {
            return new SimulatorResult(batteryLevel, true);
        }
        return new SimulatorResult(batteryLevel, false);
    }

    private void move() throws SimulatorException {
        if (batteryLevel >= ActionType.MOVE.getBatteryCost()) {
            batteryLevel -= ActionType.MOVE.getBatteryCost();
            Position nextPosition = getNextPositionInCurrentDirection();
            if (validateMove(nextPosition.row, nextPosition.col)) {
                botRow = nextPosition.row;
                botCol = nextPosition.col;
                checkAndPickupItems();
            }

        }
    }

    private void jump() throws SimulatorException {
        if (batteryLevel >= ActionType.JUMP.getBatteryCost()) {
            batteryLevel -= ActionType.JUMP.getBatteryCost();
            Position nextPosition = getNextPositionInCurrentDirection();
            if (validateJump(nextPosition.row, nextPosition.col)) {
                botRow = nextPosition.row;
                botCol = nextPosition.col;
                checkAndPickupItems();
            }
        }
    }

    private void turnLeft() {
        if (batteryLevel >= ActionType.TURN_LEFT.getBatteryCost()) {
            batteryLevel -= ActionType.TURN_LEFT.getBatteryCost();
            botDirection += 3;
        }
    }

    private void turnRight() {
        if (batteryLevel >= ActionType.TURN_RIGHT.getBatteryCost()) {
            batteryLevel -= ActionType.TURN_RIGHT.getBatteryCost();
            botDirection++;
        }
    }

    private void setupMap(MapBean mapBean) throws SimulatorException {
        batteryLevel = mapBean.getBatteryLevel();
        botCol = mapBean.getBotPositionCol();
        botRow = mapBean.getBotPositionRow();
        botDirection = mapBean.getBotRotation();
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
                batteryNumber++;
            } else if (typeId == ElementType.BATTERY_MEDIUM.getId()) {
                element = new Battery(Battery.MEDIUM_AMOUNT);
                batteryNumber++;
            } else if (typeId == ElementType.BATTERY_HIGH.getId()) {
                element = new Battery(Battery.HIGH_AMOUNT);
                batteryNumber++;
            } else {
                throw new SimulatorException("Nieznany typ elementu podczas tworzenia mapy dla symulacji");
            }
            map.get(row.getRow()).get(row.getCol()).addLast(element);
        }
    }

    private Position getNextPositionInCurrentDirection() {
        botDirection = botDirection % 4;
        int newRow = botRow;
        int newCol = botCol;
        if (botDirection == BotDirection.BOTTOM_RIGHT.getId()) {
            newCol++;
        } else if (botDirection == BotDirection.BOTTOM_LEFT.getId()) {
            newRow--;
        } else if (botDirection == BotDirection.TOP_LEFT.getId()) {
            newCol--;
        } else if (botDirection == BotDirection.TOP_RIGHT.getId()) {
            newRow++;
        }
        return new Position(newRow, newCol);
    }

    private boolean checkIfWin() {
        return batteryNumber == 0;
    }

    private boolean validateMove(int row, int col) throws SimulatorException {
        if (validatePosition(row, col)) {
            int currentHeight = getStandableHeight(botRow, botCol);
            int destinationHeight = getStandableHeight(row, col);
            if (currentHeight == destinationHeight) {
                Deque<Element> stack = map.get(row).get(col);
                if (stack.size() == destinationHeight) {
                    return true; //idziemy na kafelek/bloczek na ktorym juz nic nie stoi
                } else if (isPickupable(map.get(row).get(col).peekLast())) {
                    return true; //jesli cos na nim stoi, to musi to byc do zebrania np. bateria
                }
            }
        }
        return false;
    }

    private boolean validateJump(int row, int col) throws SimulatorException {
        if (validatePosition(row, col)) {
            int currentHeight = getStandableHeight(botRow, botCol);
            int destinationHeight = getStandableHeight(row, col);
            if (Math.abs(currentHeight - destinationHeight) == 1) {
                Deque<Element> stack = map.get(row).get(col);
                if (stack.size() == destinationHeight) {
                    return true; //tam gdzie chcemy skoczyc nic nie stoi
                } else if (isPickupable(map.get(row).get(col).peekLast())) {
                    return true; //jesli jednak cos stoi to musi byc do podniesienia
                }
            }
        }
        return false;
    }

    private boolean validatePosition(int row, int col) {
        return row >= 0 && row < 10 && col >= 0 && col < 10;
    }

    private int getStandableHeight(int row, int col) throws SimulatorException {
        Deque<Element> stack = map.get(row).get(col);
        int height = 0;
        for (Element element : stack) {
            if (isStandable(element)) {
                height++;
            } else {
                break;
            }
        }
        if ((stack.size() - height) > 1) {
            throw new SimulatorException("Istnieje wiecej niz jeden element non-standable na wierzchu. [ROW,COL]=" + row + "," + col);
        }
        return height;
    }

    private void checkAndPickupItems() {
        Element element = map.get(botRow).get(botCol).peekLast();
        if (isPickupable(element)) {
            map.get(botRow).get(botCol).pollLast();
            if (element instanceof Battery) {
                batteryLevel += ((Battery) element).getBatteryAmount();
                if (batteryLevel > 100)
                    batteryLevel = 100;
                batteryNumber--;
            }
        }
    }

    private boolean isPickupable(Element element) {
        return element instanceof Battery;
    }

    private boolean isStandable(Element element) {
        return (element instanceof HeavyBox || element instanceof LightBox);
    }

    private static class Position {
        private final int row;
        private final int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static class SimulatorResult {
        private int batteryLevel;
        private boolean userWon;

        public SimulatorResult(int batteryLevel, boolean userWon) {
            this.batteryLevel = batteryLevel;
            this.userWon = userWon;
        }

        public int getBatteryLevel() {
            return batteryLevel;
        }

        public void setBatteryLevel(int batteryLevel) {
            this.batteryLevel = batteryLevel;
        }

        public boolean isUserWon() {
            return userWon;
        }

        public void setUserWon(boolean userWon) {
            this.userWon = userWon;
        }

    }
}
