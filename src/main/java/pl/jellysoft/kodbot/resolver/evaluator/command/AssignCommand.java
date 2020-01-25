package pl.jellysoft.kodbot.resolver.evaluator.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// TODO replace with @Data afer making Command an interface
public class AssignCommand extends Command {

    public static final String KEYWORD = "assign";
    public static final String LEFT_OPERAND_KEYWORD = "leftOperand";
    public static final String RIGHT_OPERAND_KEYWORD = "rightOperand";

    private String leftOperand;
    private String rightOperand;

}
