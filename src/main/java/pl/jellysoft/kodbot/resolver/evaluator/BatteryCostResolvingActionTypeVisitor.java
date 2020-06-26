package pl.jellysoft.kodbot.resolver.evaluator;

public class BatteryCostResolvingActionTypeVisitor implements ActionTypeVisitor<Integer> {

    public static int batteryCostForAction(ActionType actionType) {
        return actionType.accept(new BatteryCostResolvingActionTypeVisitor());
    }

    @Override
    public Integer visitMove() {
        return 5;
    }

    @Override
    public Integer visitJump() {
        return 10;
    }

    @Override
    public Integer visitTurnLeft() {
        return 5;
    }

    @Override
    public Integer visitTurnRight() {
        return 5;
    }

}
