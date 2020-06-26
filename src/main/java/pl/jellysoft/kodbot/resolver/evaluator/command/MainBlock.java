package pl.jellysoft.kodbot.resolver.evaluator.command;

public class MainBlock extends Block {

    @Override
    public <R> R accept(CommandVisitor<R> visitor) {
        return visitor.visit(this);
    }

}
