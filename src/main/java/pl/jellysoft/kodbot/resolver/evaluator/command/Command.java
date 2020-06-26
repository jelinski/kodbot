package pl.jellysoft.kodbot.resolver.evaluator.command;

public interface Command {

    <R> R accept(CommandVisitor<R> visitor);

}
