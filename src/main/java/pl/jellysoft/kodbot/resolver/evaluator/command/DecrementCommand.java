package pl.jellysoft.kodbot.resolver.evaluator.command;

import lombok.Value;

@Value
public final class DecrementCommand implements Command {

    public static final String KEYWORD = "decrement";
    public static final String VARIABLE_KEYWORD = "variable";

    private final String variable;

}
