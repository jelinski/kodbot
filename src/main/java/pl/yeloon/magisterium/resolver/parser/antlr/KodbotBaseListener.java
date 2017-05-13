// Generated from C:\Users\yeloon\git\kodbotLang\Kodbot.g4 by ANTLR 4.1
package pl.yeloon.magisterium.resolver.parser.antlr;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import pl.yeloon.magisterium.resolver.evaluator.command.AssignCommand;
import pl.yeloon.magisterium.resolver.evaluator.command.AssignWithAdditionCommand;
import pl.yeloon.magisterium.resolver.evaluator.command.AssignWithSubtractionCommand;
import pl.yeloon.magisterium.resolver.evaluator.command.Block;
import pl.yeloon.magisterium.resolver.evaluator.command.Command;
import pl.yeloon.magisterium.resolver.evaluator.command.DecrementCommand;
import pl.yeloon.magisterium.resolver.evaluator.command.FunctionBlock;
import pl.yeloon.magisterium.resolver.evaluator.command.FunctionCallCommand;
import pl.yeloon.magisterium.resolver.evaluator.command.IncrementCommand;
import pl.yeloon.magisterium.resolver.evaluator.command.JumpCommand;
import pl.yeloon.magisterium.resolver.evaluator.command.MainBlock;
import pl.yeloon.magisterium.resolver.evaluator.command.MoveCommand;
import pl.yeloon.magisterium.resolver.evaluator.command.RepeatBlock;
import pl.yeloon.magisterium.resolver.evaluator.command.TurnLeftCommand;
import pl.yeloon.magisterium.resolver.evaluator.command.TurnRightCommand;

public class KodbotBaseListener implements KodbotListener {

	private List<Command> commands;
	private Deque<Block> lastBlocksStack;
	private int commandCounter;

	public KodbotBaseListener() {
		this.commands = new ArrayList<Command>();
		lastBlocksStack = new ArrayDeque<Block>();
		commandCounter=0;
	}

	@Override
	public void enterFunctionDef(@NotNull KodbotParser.FunctionDefContext ctx) {
		FunctionBlock function = new FunctionBlock();
		lastBlocksStack.add(function);
	}

	@Override
	public void enterMain(@NotNull KodbotParser.MainContext ctx) {
		MainBlock main = new MainBlock();
		lastBlocksStack.addLast(main);
	}

	@Override
	public void enterRepeat(@NotNull KodbotParser.RepeatContext ctx) {
		RepeatBlock repeat = new RepeatBlock();
		lastBlocksStack.addLast(repeat);
	}

	@Override
	public void exitAssign(@NotNull KodbotParser.AssignContext ctx) {
		AssignCommand assign = new AssignCommand();
		assign.setLeftOperand(ctx.ID().getText());
		assign.setRightOperand(ctx.var().getText());
		addToCurrentBlock(assign);
	}

	@Override
	public void exitAssignWithAddition(@NotNull KodbotParser.AssignWithAdditionContext ctx) {
		AssignWithAdditionCommand assignWithAddition = new AssignWithAdditionCommand();
		assignWithAddition.setLeftOperand(ctx.ID().getText());
		assignWithAddition.setFirstRightOperand(ctx.firstRight.getText());
		assignWithAddition.setSecondRightOperand(ctx.secondRight.getText());
		addToCurrentBlock(assignWithAddition);
	}

	@Override
	public void exitAssignWithSubtraction(@NotNull KodbotParser.AssignWithSubtractionContext ctx) {
		AssignWithSubtractionCommand assignWithSubtraction = new AssignWithSubtractionCommand();
		assignWithSubtraction.setLeftOperand(ctx.ID().getText());
		assignWithSubtraction.setFirstRightOperand(ctx.firstRight.getText());
		assignWithSubtraction.setSecondRightOperand(ctx.secondRight.getText());
		addToCurrentBlock(assignWithSubtraction);
	}

	@Override
	public void exitDecrement(@NotNull KodbotParser.DecrementContext ctx) {
		DecrementCommand decrement = new DecrementCommand();
		decrement.setVariable(ctx.DECREMENT_ID().getText().substring(0, 1));
		addToCurrentBlock(decrement);
	}

	@Override
	public void exitFunctionCall(@NotNull KodbotParser.FunctionCallContext ctx) {
		FunctionCallCommand functionCall = new FunctionCallCommand();
		functionCall.setName(ctx.ID().getText());
		addToCurrentBlock(functionCall);
	}

	@Override
	public void exitFunctionDef(@NotNull KodbotParser.FunctionDefContext ctx) {
		FunctionBlock function = (FunctionBlock) lastBlocksStack.pollLast();
		function.setName(ctx.ID().getText());
		addToCurrentBlock(function);
	}

	@Override
	public void exitIncrement(@NotNull KodbotParser.IncrementContext ctx) {
		IncrementCommand increment = new IncrementCommand();
		increment.setVariable(ctx.INCREMENT_ID().getText().substring(0, 1));
		addToCurrentBlock(increment);
	}

	@Override
	public void exitJump(@NotNull KodbotParser.JumpContext ctx) {
		addToCurrentBlock(new JumpCommand());
	}

	@Override
	public void exitMain(@NotNull KodbotParser.MainContext ctx) {
		MainBlock main = (MainBlock) lastBlocksStack.pollLast();
		addToCurrentBlock(main);
	}

	@Override
	public void exitMove(@NotNull KodbotParser.MoveContext ctx) {
		addToCurrentBlock(new MoveCommand());
	}

	@Override
	public void exitRepeat(@NotNull KodbotParser.RepeatContext ctx) {
		RepeatBlock repeat = (RepeatBlock) lastBlocksStack.pollLast();
		repeat.setCount(ctx.var().getText());
		addToCurrentBlock(repeat);
	}

	@Override
	public void exitTurnLeft(@NotNull KodbotParser.TurnLeftContext ctx) {
		addToCurrentBlock(new TurnLeftCommand());
	}

	@Override
	public void exitTurnRight(@NotNull KodbotParser.TurnRightContext ctx) {
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
				commands = new ArrayList<Command>();
			commands.add(command);
		} else {
			this.commands.add(command);
		}
		commandCounter++;
	}

	// NOT IMPLEMENTED METHODS

	@Override
	public void visitErrorNode(@NotNull ErrorNode node) {
	}

	@Override
	public void visitTerminal(@NotNull TerminalNode node) {
	}

	@Override
	public void enterAssign(@NotNull KodbotParser.AssignContext ctx) {
	}

	@Override
	public void enterAssignWithAddition(@NotNull KodbotParser.AssignWithAdditionContext ctx) {
	}

	@Override
	public void enterAssignWithSubtraction(@NotNull KodbotParser.AssignWithSubtractionContext ctx) {
	}

	@Override
	public void enterBlock(@NotNull KodbotParser.BlockContext ctx) {
	}

	@Override
	public void enterDecrement(@NotNull KodbotParser.DecrementContext ctx) {
	}

	@Override
	public void enterEveryRule(@NotNull ParserRuleContext ctx) {
	}

	@Override
	public void enterExpr(@NotNull KodbotParser.ExprContext ctx) {
	}

	@Override
	public void enterFunctionCall(@NotNull KodbotParser.FunctionCallContext ctx) {
	}

	@Override
	public void enterIncrement(@NotNull KodbotParser.IncrementContext ctx) {
	}

	@Override
	public void enterJump(@NotNull KodbotParser.JumpContext ctx) {
	}

	@Override
	public void enterMove(@NotNull KodbotParser.MoveContext ctx) {
	}

	@Override
	public void enterStart(@NotNull KodbotParser.StartContext ctx) {
	}

	@Override
	public void enterStat(@NotNull KodbotParser.StatContext ctx) {
	}

	@Override
	public void enterTurnLeft(@NotNull KodbotParser.TurnLeftContext ctx) {
	}

	@Override
	public void enterTurnRight(@NotNull KodbotParser.TurnRightContext ctx) {
	}

	@Override
	public void enterVar(@NotNull KodbotParser.VarContext ctx) {
	}

	@Override
	public void exitBlock(@NotNull KodbotParser.BlockContext ctx) {
	}

	@Override
	public void exitEveryRule(@NotNull ParserRuleContext ctx) {
	}

	@Override
	public void exitExpr(@NotNull KodbotParser.ExprContext ctx) {
	}

	@Override
	public void exitStart(@NotNull KodbotParser.StartContext ctx) {
	}

	@Override
	public void exitStat(@NotNull KodbotParser.StatContext ctx) {
	}

	@Override
	public void exitVar(@NotNull KodbotParser.VarContext ctx) {
	}

	public int getCommandsCounter() {
		return commandCounter;
	}

}