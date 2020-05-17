package pl.jellysoft.kodbot.resolver.evaluator.command;

import lombok.Value;

@Value
public class AssignCommand implements Command {

    public static final String KEYWORD = "assign";
    public static final String LEFT_OPERAND_KEYWORD = "leftOperand";
    public static final String RIGHT_OPERAND_KEYWORD = "rightOperand";

    private final String leftOperand;
    private final String rightOperand;

}
