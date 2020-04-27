package pl.jellysoft.kodbot.resolver.evaluator.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FunctionBlock extends Block {

    public static final String KEYWORD = "function";
    public static final String NAME_KEYWORD = "name";
    public static final String COMMANDS_KEYWORD = "commands";

    private String name;

    @Override
    public boolean equals(Object obj) { // TODO make use of Lombok
        return obj instanceof FunctionBlock && ((FunctionBlock) obj).name.equals(this.name);
    }

}
