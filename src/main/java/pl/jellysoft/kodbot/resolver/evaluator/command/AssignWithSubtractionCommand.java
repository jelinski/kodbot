package pl.jellysoft.kodbot.resolver.evaluator.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// TODO replace with @Data afer making Command an interface
public final class AssignWithSubtractionCommand extends Command {

    public static final String KEYWORD = "assignWithSubtraction";
    public static final String LEFT_OPERAND_KEYWORD = "leftOperand";
    public static final String FIRST_RIGHT_OPERAND_KEYWORD = "firstRightOperand";
    public static final String SECOND_RIGHT_OPERAND_KEYWORD = "secondRightOperand";

    private String leftOperand;
    private String firstRightOperand;
    private String secondRightOperand;

}
