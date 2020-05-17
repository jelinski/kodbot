package pl.jellysoft.kodbot.resolver.evaluator.command;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class FunctionBlock extends Block {

    public static final String KEYWORD = "function";
    public static final String NAME_KEYWORD = "name";
    public static final String COMMANDS_KEYWORD = "commands";

    private final String name;

}
