package pl.jellysoft.kodbot.resolver.parser.antlr;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link KodbotParser}.
 */
public interface KodbotListener extends ParseTreeListener {

    /**
     * Enter a parse tree produced by {@link KodbotParser#increment}.
     *
     * @param ctx the parse tree
     */
    void enterIncrement(KodbotParser.IncrementContext ctx);

    /**
     * Exit a parse tree produced by {@link KodbotParser#increment}.
     *
     * @param ctx the parse tree
     */
    void exitIncrement(KodbotParser.IncrementContext ctx);

    /**
     * Enter a parse tree produced by {@link KodbotParser#turnLeft}.
     *
     * @param ctx the parse tree
     */
    void enterTurnLeft(KodbotParser.TurnLeftContext ctx);

    /**
     * Exit a parse tree produced by {@link KodbotParser#turnLeft}.
     *
     * @param ctx the parse tree
     */
    void exitTurnLeft(KodbotParser.TurnLeftContext ctx);

    /**
     * Enter a parse tree produced by {@link KodbotParser#functionDef}.
     *
     * @param ctx the parse tree
     */
    void enterFunctionDef(KodbotParser.FunctionDefContext ctx);

    /**
     * Exit a parse tree produced by {@link KodbotParser#functionDef}.
     *
     * @param ctx the parse tree
     */
    void exitFunctionDef(KodbotParser.FunctionDefContext ctx);

    /**
     * Enter a parse tree produced by {@link KodbotParser#assignWithSubtraction}.
     *
     * @param ctx the parse tree
     */
    void enterAssignWithSubtraction(KodbotParser.AssignWithSubtractionContext ctx);

    /**
     * Exit a parse tree produced by {@link KodbotParser#assignWithSubtraction}.
     *
     * @param ctx the parse tree
     */
    void exitAssignWithSubtraction(KodbotParser.AssignWithSubtractionContext ctx);

    /**
     * Enter a parse tree produced by {@link KodbotParser#assign}.
     *
     * @param ctx the parse tree
     */
    void enterAssign(KodbotParser.AssignContext ctx);

    /**
     * Exit a parse tree produced by {@link KodbotParser#assign}.
     *
     * @param ctx the parse tree
     */
    void exitAssign(KodbotParser.AssignContext ctx);

    /**
     * Enter a parse tree produced by {@link KodbotParser#var}.
     *
     * @param ctx the parse tree
     */
    void enterVar(KodbotParser.VarContext ctx);

    /**
     * Exit a parse tree produced by {@link KodbotParser#var}.
     *
     * @param ctx the parse tree
     */
    void exitVar(KodbotParser.VarContext ctx);

    /**
     * Enter a parse tree produced by {@link KodbotParser#block}.
     *
     * @param ctx the parse tree
     */
    void enterBlock(KodbotParser.BlockContext ctx);

    /**
     * Exit a parse tree produced by {@link KodbotParser#block}.
     *
     * @param ctx the parse tree
     */
    void exitBlock(KodbotParser.BlockContext ctx);

    /**
     * Enter a parse tree produced by {@link KodbotParser#expr}.
     *
     * @param ctx the parse tree
     */
    void enterExpr(KodbotParser.ExprContext ctx);

    /**
     * Exit a parse tree produced by {@link KodbotParser#expr}.
     *
     * @param ctx the parse tree
     */
    void exitExpr(KodbotParser.ExprContext ctx);

    /**
     * Enter a parse tree produced by {@link KodbotParser#assignWithAddition}.
     *
     * @param ctx the parse tree
     */
    void enterAssignWithAddition(KodbotParser.AssignWithAdditionContext ctx);

    /**
     * Exit a parse tree produced by {@link KodbotParser#assignWithAddition}.
     *
     * @param ctx the parse tree
     */
    void exitAssignWithAddition(KodbotParser.AssignWithAdditionContext ctx);

    /**
     * Enter a parse tree produced by {@link KodbotParser#repeat}.
     *
     * @param ctx the parse tree
     */
    void enterRepeat(KodbotParser.RepeatContext ctx);

    /**
     * Exit a parse tree produced by {@link KodbotParser#repeat}.
     *
     * @param ctx the parse tree
     */
    void exitRepeat(KodbotParser.RepeatContext ctx);

    /**
     * Enter a parse tree produced by {@link KodbotParser#stat}.
     *
     * @param ctx the parse tree
     */
    void enterStat(KodbotParser.StatContext ctx);

    /**
     * Exit a parse tree produced by {@link KodbotParser#stat}.
     *
     * @param ctx the parse tree
     */
    void exitStat(KodbotParser.StatContext ctx);

    /**
     * Enter a parse tree produced by {@link KodbotParser#jump}.
     *
     * @param ctx the parse tree
     */
    void enterJump(KodbotParser.JumpContext ctx);

    /**
     * Exit a parse tree produced by {@link KodbotParser#jump}.
     *
     * @param ctx the parse tree
     */
    void exitJump(KodbotParser.JumpContext ctx);

    /**
     * Enter a parse tree produced by {@link KodbotParser#main}.
     *
     * @param ctx the parse tree
     */
    void enterMain(KodbotParser.MainContext ctx);

    /**
     * Exit a parse tree produced by {@link KodbotParser#main}.
     *
     * @param ctx the parse tree
     */
    void exitMain(KodbotParser.MainContext ctx);

    /**
     * Enter a parse tree produced by {@link KodbotParser#functionCall}.
     *
     * @param ctx the parse tree
     */
    void enterFunctionCall(KodbotParser.FunctionCallContext ctx);

    /**
     * Exit a parse tree produced by {@link KodbotParser#functionCall}.
     *
     * @param ctx the parse tree
     */
    void exitFunctionCall(KodbotParser.FunctionCallContext ctx);

    /**
     * Enter a parse tree produced by {@link KodbotParser#start}.
     *
     * @param ctx the parse tree
     */
    void enterStart(KodbotParser.StartContext ctx);

    /**
     * Exit a parse tree produced by {@link KodbotParser#start}.
     *
     * @param ctx the parse tree
     */
    void exitStart(KodbotParser.StartContext ctx);

    /**
     * Enter a parse tree produced by {@link KodbotParser#turnRight}.
     *
     * @param ctx the parse tree
     */
    void enterTurnRight(KodbotParser.TurnRightContext ctx);

    /**
     * Exit a parse tree produced by {@link KodbotParser#turnRight}.
     *
     * @param ctx the parse tree
     */
    void exitTurnRight(KodbotParser.TurnRightContext ctx);

    /**
     * Enter a parse tree produced by {@link KodbotParser#move}.
     *
     * @param ctx the parse tree
     */
    void enterMove(KodbotParser.MoveContext ctx);

    /**
     * Exit a parse tree produced by {@link KodbotParser#move}.
     *
     * @param ctx the parse tree
     */
    void exitMove(KodbotParser.MoveContext ctx);

    /**
     * Enter a parse tree produced by {@link KodbotParser#decrement}.
     *
     * @param ctx the parse tree
     */
    void enterDecrement(KodbotParser.DecrementContext ctx);

    /**
     * Exit a parse tree produced by {@link KodbotParser#decrement}.
     *
     * @param ctx the parse tree
     */
    void exitDecrement(KodbotParser.DecrementContext ctx);

}
