package pl.jellysoft.kodbot.resolver.evaluator.command;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class FunctionBlock extends Block {

    public static final String KEYWORD = "function";
    public static final String NAME_KEYWORD = "name";
    public static final String COMMANDS_KEYWORD = "commands";

    private String name;

}
