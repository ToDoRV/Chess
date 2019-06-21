package figure;

import enums.Color;
import enums.ErrorCode;
import enums.Position;
import game.Player;

public class Bishop extends Figure {

    public static final String BISHOP_SIGN = " B ";

    public Bishop(Position position, Color color) {
        super(position, color);
    }

    @Override
    public void printFigure() {
        System.out.print(color.getColor() + BISHOP_SIGN);
    }

    @Override
    public ErrorCode canMove(Position toPosition, Player player, Figure[][] board) {
        //ToDo
        return ErrorCode.Success;
    }
}
