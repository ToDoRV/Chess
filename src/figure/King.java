package figure;

import enums.Color;
import enums.ErrorCode;
import enums.Position;
import game.Player;

public class King extends Figure {

    public static final String KING_SIGN = " K ";

    public King(Position position, Color color) {
        super(position, color);
    }

    @Override
    public void printFigure() {
        System.out.print(color.getColor() + KING_SIGN);
    }

    @Override
    public ErrorCode canMove(Position toPosition, Player player, Figure[][] board) {
        //ToDo
        return ErrorCode.Success;
    }
}
