package pl.jellysoft.kodbot.resolver.evaluator.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RepeatBlock extends Block {

    public static final String KEYWORD = "repeat";
    public static final String COUNT_KEYWORD = "count";
    public static final String COMMANDS_KEYWORD = "commands";

    private String count;

}
