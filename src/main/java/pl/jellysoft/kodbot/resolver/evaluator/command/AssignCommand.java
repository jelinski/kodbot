package pl.jellysoft.kodbot.resolver.evaluator.command;

import lombok.Value;

@Value
public class AssignCommand implements Command {

    private final String leftOperand;
    private final String rightOperand;

    @Override
    public <R> R accept(CommandVisitor<R> visitor) {
        return visitor.visit(this);
    }

}
