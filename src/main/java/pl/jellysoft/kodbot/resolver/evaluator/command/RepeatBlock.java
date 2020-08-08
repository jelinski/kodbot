package pl.jellysoft.kodbot.resolver.evaluator.command;

import lombok.Value;

import java.util.List;

@Value
public class RepeatBlock implements Command {

    private final String count;
    private final List<Command> commands;

    @Override
    public <R> R accept(CommandVisitor<R> visitor) {
        return visitor.visit(this);
    }

}
