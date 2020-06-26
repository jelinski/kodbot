package pl.jellysoft.kodbot.resolver.evaluator.command;

import lombok.Value;

@Value
public class FunctionCallCommand implements Command {

    private final String name;

    @Override
    public <R> R accept(CommandVisitor<R> visitor) {
        return visitor.visit(this);
    }

}
