package pl.jellysoft.kodbot.resolver.evaluator.command;

import lombok.Value;

import java.util.List;

@Value
public class MainBlock implements Command {

    private final List<Command> commands;

    @Override
    public <R> R accept(CommandVisitor<R> visitor) {
        return visitor.visit(this);
    }

}
