package figure;

import enums.Color;
import enums.ErrorCode;
import enums.Position;
import game.Player;

public class Knight extends Figure {

    public static final String KNIGHT_SIGN = " N ";

    public Knight(Position position, Color color) {
        super(position, color);
    }

    @Override
    public void printFigure() {
        System.out.print(color.getColor() + KNIGHT_SIGN);
    }

    @Override
    public ErrorCode canMove(Position toPosition, Player player, Figure[][] board) {
        //ToDo
        return ErrorCode.Success;
    }
}
