package pl.jellysoft.kodbot.resolver.evaluator.command;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class RepeatBlock extends Block {

    public static final String KEYWORD = "repeat";
    public static final String COUNT_KEYWORD = "count";
    public static final String COMMANDS_KEYWORD = "commands";

    private final String count;

}
