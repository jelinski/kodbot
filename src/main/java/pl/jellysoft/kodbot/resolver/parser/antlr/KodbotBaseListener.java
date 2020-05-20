package pl.jellysoft.kodbot.resolver.parser.antlr;

import lombok.Getter;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.commons.lang3.mutable.MutableInt;
import pl.jellysoft.kodbot.resolver.evaluator.command.AssignCommand;
import pl.jellysoft.kodbot.resolver.evaluator.command.AssignWithAdditionCommand;
import pl.jellysoft.kodbot.resolver.evaluator.command.AssignWithSubtractionCommand;
import pl.jellysoft.kodbot.resolver.evaluator.command.Block;
import pl.jellysoft.kodbot.resolver.evaluator.command.Command;
import pl.jellysoft.kodbot.resolver.evaluator.command.DecrementCommand;
import pl.jellysoft.kodbot.resolver.evaluator.command.FunctionBlock;
import pl.jellysoft.kodbot.resolver.evaluator.command.FunctionCallCommand;
import pl.jellysoft.kodbot.resolver.evaluator.command.IncrementCommand;
import pl.jellysoft.kodbot.resolver.evaluator.command.JumpCommand;
import pl.jellysoft.kodbot.resolver.evaluator.command.MainBlock;
import pl.jellysoft.kodbot.resolver.evaluator.command.MoveCommand;
import pl.jellysoft.kodbot.resolver.evaluator.command.RepeatBlock;
import pl.jellysoft.kodbot.resolver.evaluator.command.TurnLeftCommand;
import pl.jellysoft.kodbot.resolver.evaluator.command.TurnRightCommand;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class KodbotBaseListener implements KodbotListener {

    @Getter
    private final MutableInt commandCounter = new MutableInt(0);

    @Getter
    private final List<Command> commands = new ArrayList<>();

    private final Deque<Block> lastBlocksStack = new ArrayDeque<>();

    @Override
    public void enterMove(KodbotParser.MoveContext ctx) {
    }

    @Override
    public void exitMove(KodbotParser.MoveContext ctx) {
        addToCurrentBlock(new MoveCommand());
    }

    @Override
    public void enterJump(KodbotParser.JumpContext ctx) {
    }

    @Override
    public void exitJump(KodbotParser.JumpContext ctx) {
        addToCurrentBlock(new JumpCommand());
    }

    @Override
    public void enterTurnLeft(KodbotParser.TurnLeftContext ctx) {
    }

    @Override
    public void exitTurnLeft(KodbotParser.TurnLeftContext ctx) {
        addToCurrentBlock(new TurnLeftCommand());
    }

    @Override
    public void enterTurnRight(KodbotParser.TurnRightContext ctx) {
    }

    @Override
    public void exitTurnRight(KodbotParser.TurnRightContext ctx) {
        addToCurrentBlock(new TurnRightCommand());
    }

    @Override
    public void enterAssign(KodbotParser.AssignContext ctx) {
    }

    @Override
    public void exitAssign(KodbotParser.AssignContext ctx) {
        AssignCommand assign = new AssignCommand(
                ctx.ID().getText(),
                ctx.var().getText());
        addToCurrentBlock(assign);
    }

    @Override
    public void enterAssignWithAddition(KodbotParser.AssignWithAdditionContext ctx) {
    }

    @Override
    public void exitAssignWithAddition(KodbotParser.AssignWithAdditionContext ctx) {
        AssignWithAdditionCommand assignWithAddition = new AssignWithAdditionCommand(
                ctx.ID().getText(),
                ctx.firstRight.getText(),
                ctx.secondRight.getText());
        addToCurrentBlock(assignWithAddition);
    }

    @Override
    public void enterAssignWithSubtraction(KodbotParser.AssignWithSubtractionContext ctx) {
    }

    @Override
    public void exitAssignWithSubtraction(KodbotParser.AssignWithSubtractionContext ctx) {
        AssignWithSubtractionCommand assignWithSubtraction = new AssignWithSubtractionCommand(
                ctx.ID().getText(),
                ctx.firstRight.getText(),
                ctx.secondRight.getText());
        addToCurrentBlock(assignWithSubtraction);
    }

    @Override
    public void enterIncrement(KodbotParser.IncrementContext ctx) {
    }

    @Override
    public void exitIncrement(KodbotParser.IncrementContext ctx) {
        IncrementCommand increment = new IncrementCommand(ctx.INCREMENT_ID().getText().substring(0, 1));
        addToCurrentBlock(increment);
    }

    @Override
    public void enterDecrement(KodbotParser.DecrementContext ctx) {
    }

    @Override
    public void exitDecrement(KodbotParser.DecrementContext ctx) {
        DecrementCommand decrement = new DecrementCommand(ctx.DECREMENT_ID().getText().substring(0, 1));
        addToCurrentBlock(decrement);
    }

    @Override
    public void enterRepeat(KodbotParser.RepeatContext ctx) {
        lastBlocksStack.addLast(new RepeatBlock(ctx.var().getText()));
    }

    @Override
    public void exitRepeat(KodbotParser.RepeatContext ctx) {
        RepeatBlock repeat = (RepeatBlock) lastBlocksStack.pollLast();
        addToCurrentBlock(repeat);
    }

    @Override
    public void enterFunctionDef(KodbotParser.FunctionDefContext ctx) {
        lastBlocksStack.add(new FunctionBlock(ctx.ID().getText()));
    }

    @Override
    public void exitFunctionDef(KodbotParser.FunctionDefContext ctx) {
        FunctionBlock function = (FunctionBlock) lastBlocksStack.pollLast();
        addToCurrentBlock(function);
    }

    @Override
    public void enterFunctionCall(KodbotParser.FunctionCallContext ctx) {
    }

    @Override
    public void exitFunctionCall(KodbotParser.FunctionCallContext ctx) {
        FunctionCallCommand functionCall = new FunctionCallCommand(ctx.ID().getText());
        addToCurrentBlock(functionCall);
    }

    @Override
    public void enterMain(KodbotParser.MainContext ctx) {
        lastBlocksStack.addLast(new MainBlock());
    }

    @Override
    public void exitMain(KodbotParser.MainContext ctx) {
        MainBlock main = (MainBlock) lastBlocksStack.pollLast();
        addToCurrentBlock(main);
    }

    private void addToCurrentBlock(Command command) {
        Block currentBlock = lastBlocksStack.peekLast();
        if (currentBlock != null) {
            List<Command> commands = currentBlock.getCommands();
            if (commands == null) {
                commands = new ArrayList<>();
            }
            commands.add(command);
        } else {
            commands.add(command);
        }
        commandCounter.increment();
    }

    @Override
    public void enterBlock(KodbotParser.BlockContext ctx) {
    }

    @Override
    public void exitBlock(KodbotParser.BlockContext ctx) {
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
    }

    @Override
    public void enterExpr(KodbotParser.ExprContext ctx) {
    }

    @Override
    public void exitExpr(KodbotParser.ExprContext ctx) {
    }

    @Override
    public void enterStart(KodbotParser.StartContext ctx) {
    }

    @Override
    public void exitStart(KodbotParser.StartContext ctx) {
    }

    @Override
    public void enterStat(KodbotParser.StatContext ctx) {
    }

    @Override
    public void exitStat(KodbotParser.StatContext ctx) {
    }

    @Override
    public void enterVar(KodbotParser.VarContext ctx) {
    }

    @Override
    public void exitVar(KodbotParser.VarContext ctx) {
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
    }

    @Override
    public void visitTerminal(TerminalNode node) {
    }

}
