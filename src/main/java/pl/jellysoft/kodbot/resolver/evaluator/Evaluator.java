package pl.jellysoft.kodbot.resolver.evaluator;

import pl.jellysoft.kodbot.resolver.evaluator.command.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Evaluator {

    private final int WATCHDOG_MAX = 1000;
    private int watchDog;
    private VariableContainer variables;
    private FunctionContainer functions;
    private List<ActionType> actions;
    private Deque<FunctionBlock> functionCallDeque;

    public Evaluator() {
        this.watchDog = WATCHDOG_MAX;
        this.variables = new VariableContainer();
        this.functions = new FunctionContainer();
        this.functionCallDeque = new ArrayDeque<FunctionBlock>();
        this.actions = new ArrayList<ActionType>();
    }

    private void process(List<Command> commands) throws EvaluatorException {
        watchDog--;
        if (watchDog == 0)
            throw new EvaluatorException("Zbyt wiele zagniezdzen");

        for (Command currentCommand : commands) {
            if (currentCommand instanceof MoveCommand) {
                moveAction();
            } else if (currentCommand instanceof TurnLeftCommand) {
                turnLeftAction();
            } else if (currentCommand instanceof TurnRightCommand) {
                turnRightAction();
            } else if (currentCommand instanceof JumpCommand) {
                jumpAction();
            } else if (currentCommand instanceof AssignCommand) {
                assignAction((AssignCommand) currentCommand);
            } else if (currentCommand instanceof AssignWithAdditionCommand) {
                assignWithAdditionAction((AssignWithAdditionCommand) currentCommand);
            } else if (currentCommand instanceof AssignWithSubtractionCommand) {
                assignWithSubtractionAction((AssignWithSubtractionCommand) currentCommand);
            } else if (currentCommand instanceof IncrementCommand) {
                incrementAction((IncrementCommand) currentCommand);
            } else if (currentCommand instanceof DecrementCommand) {
                decrementAction((DecrementCommand) currentCommand);
            } else if (currentCommand instanceof RepeatBlock) {
                repeatAction((RepeatBlock) currentCommand);
            } else if (currentCommand instanceof FunctionCallCommand) {
                functionCallAction((FunctionCallCommand) currentCommand);
            } else {
                throw new EvaluatorException("Nieznana komenda");
            }
        }

    }

    public EvaluatorResult evaluate(List<Command> commands) throws EvaluatorException {
        functions.setup(commands);
        process(functions.getMainFunction().getCommands());
        return new EvaluatorResult(actions);
    }

    private void moveAction() {
        actions.add(ActionType.MOVE);
    }

    private void jumpAction() {
        actions.add(ActionType.JUMP);
    }

    private void turnLeftAction() {
        actions.add(ActionType.TURN_LEFT);
    }

    private void turnRightAction() {
        actions.add(ActionType.TURN_RIGHT);
    }

    private void functionCallAction(FunctionCallCommand functionCallCommand) throws EvaluatorException {
        FunctionBlock function = functions.getByName(functionCallCommand.getName());
        if (!functionCallDeque.contains(function)) {
            functionCallDeque.addLast(function);
            process(function.getCommands());
            functionCallDeque.removeLast();
        } else {
            throw new EvaluatorException("Wykryto rekurencje. Rekurencja nie jest wspierana w grze KodBot.");
        }

    }

    private void repeatAction(RepeatBlock repeatBlock) throws EvaluatorException {
        Integer repeatCount = variables.getValue(repeatBlock.getCount());
        if (repeatCount < 0) {
            throw new EvaluatorException("Ujemna wartość powtórzeń: " + repeatBlock.getCount() + "  = " + repeatCount);
        } else {
            for (int i = 0; i < repeatCount; i++) {
                process(repeatBlock.getCommands());
            }
        }
    }

    private void assignAction(AssignCommand assignCommand) throws EvaluatorException {
        variables.setVariable(assignCommand.getLeftOperand(), assignCommand.getRightOperand());
    }

    private void assignWithSubtractionAction(AssignWithSubtractionCommand currentCommand) throws EvaluatorException {
        Integer firstRightOperand = variables.getValue(currentCommand.getFirstRightOperand());
        Integer secondRightOperand = variables.getValue(currentCommand.getSecondRightOperand());
        variables.setVariable(currentCommand.getLeftOperand(), String.valueOf(firstRightOperand - secondRightOperand));
    }

    private void assignWithAdditionAction(AssignWithAdditionCommand currentCommand) throws EvaluatorException {
        Integer firstRightOperand = variables.getValue(currentCommand.getFirstRightOperand());
        Integer secondRightOperand = variables.getValue(currentCommand.getSecondRightOperand());
        variables.setVariable(currentCommand.getLeftOperand(), String.valueOf(firstRightOperand + secondRightOperand));
    }

    private void decrementAction(DecrementCommand currentCommand) throws EvaluatorException {
        variables.setVariable(currentCommand.getVariable(), String.valueOf(variables.getValue(currentCommand.getVariable()) - 1));
    }

    private void incrementAction(IncrementCommand currentCommand) throws EvaluatorException {
        variables.setVariable(currentCommand.getVariable(), String.valueOf(variables.getValue(currentCommand.getVariable()) + 1));
    }

}
