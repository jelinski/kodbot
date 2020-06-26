package pl.jellysoft.kodbot.resolver.evaluator.command;

import lombok.Value;

@Value
public final class AssignWithSubtractionCommand implements Command {

    private final String leftOperand;
    private final String firstRightOperand;
    private final String secondRightOperand;

    @Override
    public <R> R accept(CommandVisitor<R> visitor) {
        return visitor.visit(this);
    }

}
