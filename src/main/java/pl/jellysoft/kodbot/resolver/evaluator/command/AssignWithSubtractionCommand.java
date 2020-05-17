package pl.jellysoft.kodbot.resolver.evaluator.command;

import lombok.Value;

@Value
public final class AssignWithSubtractionCommand implements Command {

    public static final String KEYWORD = "assignWithSubtraction";
    public static final String LEFT_OPERAND_KEYWORD = "leftOperand";
    public static final String FIRST_RIGHT_OPERAND_KEYWORD = "firstRightOperand";
    public static final String SECOND_RIGHT_OPERAND_KEYWORD = "secondRightOperand";

    private final String leftOperand;
    private final String firstRightOperand;
    private final String secondRightOperand;

}
