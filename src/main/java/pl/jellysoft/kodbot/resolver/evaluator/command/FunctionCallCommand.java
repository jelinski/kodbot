package pl.jellysoft.kodbot.resolver.evaluator.command;

import lombok.Value;

@Value
public class FunctionCallCommand implements Command {

    public static final String KEYWORD = "functionCall";
    public static final String NAME_KEYWORD = "name";

    private final String name;

}
