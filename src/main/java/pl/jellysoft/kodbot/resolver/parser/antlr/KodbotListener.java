package pl.jellysoft.kodbot.resolver.parser.antlr;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link KodbotParser}.
 */
public interface KodbotListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link KodbotParser#increment}.
	 * @param ctx the parse tree
	 */
	void enterIncrement(@NotNull KodbotParser.IncrementContext ctx);
	/**
	 * Exit a parse tree produced by {@link KodbotParser#increment}.
	 * @param ctx the parse tree
	 */
	void exitIncrement(@NotNull KodbotParser.IncrementContext ctx);

	/**
	 * Enter a parse tree produced by {@link KodbotParser#turnLeft}.
	 * @param ctx the parse tree
	 */
	void enterTurnLeft(@NotNull KodbotParser.TurnLeftContext ctx);
	/**
	 * Exit a parse tree produced by {@link KodbotParser#turnLeft}.
	 * @param ctx the parse tree
	 */
	void exitTurnLeft(@NotNull KodbotParser.TurnLeftContext ctx);

	/**
	 * Enter a parse tree produced by {@link KodbotParser#functionDef}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDef(@NotNull KodbotParser.FunctionDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link KodbotParser#functionDef}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDef(@NotNull KodbotParser.FunctionDefContext ctx);

	/**
	 * Enter a parse tree produced by {@link KodbotParser#assignWithSubtraction}.
	 * @param ctx the parse tree
	 */
	void enterAssignWithSubtraction(@NotNull KodbotParser.AssignWithSubtractionContext ctx);
	/**
	 * Exit a parse tree produced by {@link KodbotParser#assignWithSubtraction}.
	 * @param ctx the parse tree
	 */
	void exitAssignWithSubtraction(@NotNull KodbotParser.AssignWithSubtractionContext ctx);

	/**
	 * Enter a parse tree produced by {@link KodbotParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssign(@NotNull KodbotParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link KodbotParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssign(@NotNull KodbotParser.AssignContext ctx);

	/**
	 * Enter a parse tree produced by {@link KodbotParser#var}.
	 * @param ctx the parse tree
	 */
	void enterVar(@NotNull KodbotParser.VarContext ctx);
	/**
	 * Exit a parse tree produced by {@link KodbotParser#var}.
	 * @param ctx the parse tree
	 */
	void exitVar(@NotNull KodbotParser.VarContext ctx);

	/**
	 * Enter a parse tree produced by {@link KodbotParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(@NotNull KodbotParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link KodbotParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(@NotNull KodbotParser.BlockContext ctx);

	/**
	 * Enter a parse tree produced by {@link KodbotParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(@NotNull KodbotParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link KodbotParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(@NotNull KodbotParser.ExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link KodbotParser#assignWithAddition}.
	 * @param ctx the parse tree
	 */
	void enterAssignWithAddition(@NotNull KodbotParser.AssignWithAdditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link KodbotParser#assignWithAddition}.
	 * @param ctx the parse tree
	 */
	void exitAssignWithAddition(@NotNull KodbotParser.AssignWithAdditionContext ctx);

	/**
	 * Enter a parse tree produced by {@link KodbotParser#repeat}.
	 * @param ctx the parse tree
	 */
	void enterRepeat(@NotNull KodbotParser.RepeatContext ctx);
	/**
	 * Exit a parse tree produced by {@link KodbotParser#repeat}.
	 * @param ctx the parse tree
	 */
	void exitRepeat(@NotNull KodbotParser.RepeatContext ctx);

	/**
	 * Enter a parse tree produced by {@link KodbotParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(@NotNull KodbotParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link KodbotParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(@NotNull KodbotParser.StatContext ctx);

	/**
	 * Enter a parse tree produced by {@link KodbotParser#jump}.
	 * @param ctx the parse tree
	 */
	void enterJump(@NotNull KodbotParser.JumpContext ctx);
	/**
	 * Exit a parse tree produced by {@link KodbotParser#jump}.
	 * @param ctx the parse tree
	 */
	void exitJump(@NotNull KodbotParser.JumpContext ctx);

	/**
	 * Enter a parse tree produced by {@link KodbotParser#main}.
	 * @param ctx the parse tree
	 */
	void enterMain(@NotNull KodbotParser.MainContext ctx);
	/**
	 * Exit a parse tree produced by {@link KodbotParser#main}.
	 * @param ctx the parse tree
	 */
	void exitMain(@NotNull KodbotParser.MainContext ctx);

	/**
	 * Enter a parse tree produced by {@link KodbotParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(@NotNull KodbotParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link KodbotParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(@NotNull KodbotParser.FunctionCallContext ctx);

	/**
	 * Enter a parse tree produced by {@link KodbotParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(@NotNull KodbotParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link KodbotParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(@NotNull KodbotParser.StartContext ctx);

	/**
	 * Enter a parse tree produced by {@link KodbotParser#turnRight}.
	 * @param ctx the parse tree
	 */
	void enterTurnRight(@NotNull KodbotParser.TurnRightContext ctx);
	/**
	 * Exit a parse tree produced by {@link KodbotParser#turnRight}.
	 * @param ctx the parse tree
	 */
	void exitTurnRight(@NotNull KodbotParser.TurnRightContext ctx);

	/**
	 * Enter a parse tree produced by {@link KodbotParser#move}.
	 * @param ctx the parse tree
	 */
	void enterMove(@NotNull KodbotParser.MoveContext ctx);
	/**
	 * Exit a parse tree produced by {@link KodbotParser#move}.
	 * @param ctx the parse tree
	 */
	void exitMove(@NotNull KodbotParser.MoveContext ctx);

	/**
	 * Enter a parse tree produced by {@link KodbotParser#decrement}.
	 * @param ctx the parse tree
	 */
	void enterDecrement(@NotNull KodbotParser.DecrementContext ctx);
	/**
	 * Exit a parse tree produced by {@link KodbotParser#decrement}.
	 * @param ctx the parse tree
	 */
	void exitDecrement(@NotNull KodbotParser.DecrementContext ctx);
}
