package pl.jellysoft.kodbot.resolver.simulator;

import lombok.Value;
import lombok.With;
import pl.jellysoft.kodbot.model.BotDirection;

@Value
@With
class Position {

    private final int row;
    private final int col;

    static Position getNextPosition(Position position, BotDirection botDirection) {
        if (botDirection == BotDirection.BOTTOM_RIGHT) {
            return position.withCol(position.getCol() + 1);
        } else if (botDirection == BotDirection.BOTTOM_LEFT) {
            return position.withRow(position.getRow() - 1);
        } else if (botDirection == BotDirection.TOP_LEFT) {
            return position.withCol(position.getCol() - 1);
        } else if (botDirection == BotDirection.TOP_RIGHT) {
            return position.withRow(position.getRow() + 1);
        } else {
            throw new IllegalArgumentException("Unknown bot direction");
        }
    }

}
