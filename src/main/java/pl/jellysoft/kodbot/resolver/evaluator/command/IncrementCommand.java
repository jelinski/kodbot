package pl.jellysoft.kodbot.resolver.evaluator.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// TODO replace with @Data afer making Command an interface
public class IncrementCommand extends Command {

    public static final String KEYWORD = "increment";
    public static final String VARIABLE_KEYWORD = "variable";

    private String variable;

}
