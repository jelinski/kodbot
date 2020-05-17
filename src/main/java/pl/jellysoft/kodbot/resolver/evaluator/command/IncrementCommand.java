package pl.jellysoft.kodbot.resolver.evaluator.command;

import lombok.Value;

@Value
public class IncrementCommand implements Command {

    public static final String KEYWORD = "increment";
    public static final String VARIABLE_KEYWORD = "variable";

    private final String variable;

}
