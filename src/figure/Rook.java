package figure;

import enums.Color;
import enums.ErrorCode;
import enums.Position;
import game.Player;

public class Rook extends Figure {

    public static final String ROOK_SIGN = " R ";

    public Rook(Position position, Color color) {
        super(position, color);
    }

    @Override
    public void printFigure() {
        System.out.print(color.getColor() + ROOK_SIGN);
    }

    @Override
    public ErrorCode canMove(Position toPosition, Player player, Figure[][] board) {
        //ToDo
        return ErrorCode.Success;
    }
}
