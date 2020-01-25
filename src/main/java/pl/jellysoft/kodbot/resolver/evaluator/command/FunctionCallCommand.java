package pl.jellysoft.kodbot.resolver.evaluator.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// TODO replace with @Data afer making Command an interface
public class FunctionCallCommand extends Command {

    public static final String KEYWORD = "functionCall";
    public static final String NAME_KEYWORD = "name";

    private String name;

}
