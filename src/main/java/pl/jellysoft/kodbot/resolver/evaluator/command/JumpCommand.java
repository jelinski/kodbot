package pl.jellysoft.kodbot.resolver.evaluator.command;

public class JumpCommand implements Command {

    @Override
    public <R> R accept(CommandVisitor<R> visitor) {
        return visitor.visit(this);
    }

}
