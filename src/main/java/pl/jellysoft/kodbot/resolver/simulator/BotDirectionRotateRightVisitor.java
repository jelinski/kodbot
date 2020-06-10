package pl.jellysoft.kodbot.resolver.simulator;

public class BotDirectionRotateRightVisitor implements BotDirectionVisitor<BotDirection> {

    static BotDirection rotateRight(BotDirection botDirection) {
        return botDirection.accept(new BotDirectionRotateRightVisitor());
    }

    @Override
    public BotDirection visitBottomRight() {
        return BotDirection.BOTTOM_LEFT;
    }

    @Override
    public BotDirection visitBottomLeft() {
        return BotDirection.TOP_LEFT;
    }

    @Override
    public BotDirection visitTopLeft() {
        return BotDirection.TOP_RIGHT;
    }

    @Override
    public BotDirection visitTopRight() {
        return BotDirection.BOTTOM_RIGHT;
    }

}
