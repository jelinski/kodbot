package pl.jellysoft.kodbot.resolver.evaluator.command;

import lombok.Value;

@Value
public final class DecrementCommand implements Command {

    private final String variable;

    @Override
    public <R> R accept(CommandVisitor<R> visitor) {
        return visitor.visit(this);
    }

}
