package pl.jellysoft.kodbot.model;

public class BotDirectionRotateLeftVisitor implements BotDirectionVisitor<BotDirection> {

    public static BotDirection rotateLeft(BotDirection botDirection) {
        return botDirection.accept(new BotDirectionRotateLeftVisitor());
    }

    @Override
    public BotDirection visitBottomRight() {
        return BotDirection.TOP_RIGHT;
    }

    @Override
    public BotDirection visitBottomLeft() {
        return BotDirection.BOTTOM_RIGHT;
    }

    @Override
    public BotDirection visitTopLeft() {
        return BotDirection.BOTTOM_LEFT;
    }

    @Override
    public BotDirection visitTopRight() {
        return BotDirection.TOP_LEFT;
    }

}
