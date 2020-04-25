package pl.jellysoft.kodbot.resolver.parser.antlr;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import pl.jellysoft.kodbot.resolver.evaluator.command.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class KodbotBaseListener implements KodbotListener {

    private List<Command> commands;
    private Deque<Block> lastBlocksStack;
    private int commandCounter;

    public KodbotBaseListener() {
        this.commands = new ArrayList<>();
        lastBlocksStack = new ArrayDeque<>();
        commandCounter = 0;
    }

    @Override
    public void enterFunctionDef(KodbotParser.FunctionDefContext ctx) {
        FunctionBlock function = new FunctionBlock();
        lastBlocksStack.add(function);
    }

    @Override
    public void enterMain(KodbotParser.MainContext ctx) {
        MainBlock main = new MainBlock();
        lastBlocksStack.addLast(main);
    }

    @Override
    public void enterRepeat(KodbotParser.RepeatContext ctx) {
        RepeatBlock repeat = new RepeatBlock();
        lastBlocksStack.addLast(repeat);
    }

    @Override
    public void exitAssign(KodbotParser.AssignContext ctx) {
        AssignCommand assign = new AssignCommand();
        assign.setLeftOperand(ctx.ID().getText());
        assign.setRightOperand(ctx.var().getText());
        addToCurrentBlock(assign);
    }

    @Override
    public void exitAssignWithAddition(KodbotParser.AssignWithAdditionContext ctx) {
        AssignWithAdditionCommand assignWithAddition = new AssignWithAdditionCommand();
        assignWithAddition.setLeftOperand(ctx.ID().getText());
        assignWithAddition.setFirstRightOperand(ctx.firstRight.getText());
        assignWithAddition.setSecondRightOperand(ctx.secondRight.getText());
        addToCurrentBlock(assignWithAddition);
    }

    @Override
    public void exitAssignWithSubtraction(KodbotParser.AssignWithSubtractionContext ctx) {
        AssignWithSubtractionCommand assignWithSubtraction = new AssignWithSubtractionCommand();
        assignWithSubtraction.setLeftOperand(ctx.ID().getText());
        assignWithSubtraction.setFirstRightOperand(ctx.firstRight.getText());
        assignWithSubtraction.setSecondRightOperand(ctx.secondRight.getText());
        addToCurrentBlock(assignWithSubtraction);
    }

    @Override
    public void exitDecrement(KodbotParser.DecrementContext ctx) {
        DecrementCommand decrement = new DecrementCommand();
        decrement.setVariable(ctx.DECREMENT_ID().getText().substring(0, 1));
        addToCurrentBlock(decrement);
    }

    @Override
    public void exitFunctionCall(KodbotParser.FunctionCallContext ctx) {
        FunctionCallCommand functionCall = new FunctionCallCommand();
        functionCall.setName(ctx.ID().getText());
        addToCurrentBlock(functionCall);
    }

    @Override
    public void exitFunctionDef(KodbotParser.FunctionDefContext ctx) {
        FunctionBlock function = (FunctionBlock) lastBlocksStack.pollLast();
        function.setName(ctx.ID().getText());
        addToCurrentBlock(function);
    }

    @Override
    public void exitIncrement(KodbotParser.IncrementContext ctx) {
        IncrementCommand increment = new IncrementCommand();
        increment.setVariable(ctx.INCREMENT_ID().getText().substring(0, 1));
        addToCurrentBlock(increment);
    }

    @Override
    public void exitJump(KodbotParser.JumpContext ctx) {
        addToCurrentBlock(new JumpCommand());
    }

    @Override
    public void exitMain(KodbotParser.MainContext ctx) {
        MainBlock main = (MainBlock) lastBlocksStack.pollLast();
        addToCurrentBlock(main);
    }

    @Override
    public void exitMove(KodbotParser.MoveContext ctx) {
        addToCurrentBlock(new MoveCommand());
    }

    @Override
    public void exitRepeat(KodbotParser.RepeatContext ctx) {
        RepeatBlock repeat = (RepeatBlock) lastBlocksStack.pollLast();
        repeat.setCount(ctx.var().getText());
        addToCurrentBlock(repeat);
    }

    @Override
    public void exitTurnLeft(KodbotParser.TurnLeftContext ctx) {
        addToCurrentBlock(new TurnLeftCommand());
    }

    @Override
    public void exitTurnRight(KodbotParser.TurnRightContext ctx) {
        addToCurrentBlock(new TurnRightCommand());
    }

    public List<Command> getCommands() {
        return commands;
    }

    private void addToCurrentBlock(Command command) {
        Block currentBlock = lastBlocksStack.peekLast();
        if (currentBlock != null) {
            List<Command> commands = currentBlock.getCommands();
            if (commands == null)
                commands = new ArrayList<>();
            commands.add(command);
        } else {
            this.commands.add(command);
        }
        commandCounter++;
    }

    // NOT IMPLEMENTED METHODS

    @Override
    public void visitErrorNode(ErrorNode node) {
    }

    @Override
    public void visitTerminal(TerminalNode node) {
    }

    @Override
    public void enterAssign(KodbotParser.AssignContext ctx) {
    }

    @Override
    public void enterAssignWithAddition(KodbotParser.AssignWithAdditionContext ctx) {
    }

    @Override
    public void enterAssignWithSubtraction(KodbotParser.AssignWithSubtractionContext ctx) {
    }

    @Override
    public void enterBlock(KodbotParser.BlockContext ctx) {
    }

    @Override
    public void enterDecrement(KodbotParser.DecrementContext ctx) {
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
    }

    @Override
    public void enterExpr(KodbotParser.ExprContext ctx) {
    }

    @Override
    public void enterFunctionCall(KodbotParser.FunctionCallContext ctx) {
    }

    @Override
    public void enterIncrement(KodbotParser.IncrementContext ctx) {
    }

    @Override
    public void enterJump(KodbotParser.JumpContext ctx) {
    }

    @Override
    public void enterMove(KodbotParser.MoveContext ctx) {
    }

    @Override
    public void enterStart(KodbotParser.StartContext ctx) {
    }

    @Override
    public void enterStat(KodbotParser.StatContext ctx) {
    }

    @Override
    public void enterTurnLeft(KodbotParser.TurnLeftContext ctx) {
    }

    @Override
    public void enterTurnRight(KodbotParser.TurnRightContext ctx) {
    }

    @Override
    public void enterVar(KodbotParser.VarContext ctx) {
    }

    @Override
    public void exitBlock(KodbotParser.BlockContext ctx) {
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
    }

    @Override
    public void exitExpr(KodbotParser.ExprContext ctx) {
    }

    @Override
    public void exitStart(KodbotParser.StartContext ctx) {
    }

    @Override
    public void exitStat(KodbotParser.StatContext ctx) {
    }

    @Override
    public void exitVar(KodbotParser.VarContext ctx) {
    }

    public int getCommandsCounter() {
        return commandCounter;
    }

}
