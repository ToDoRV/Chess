package figure;

import enums.Color;
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
    public int canMove(Position toPosition, Player player, Figure[][] board) {
        //ToDo
        return ERROR_CODE_SUCCESS;
    }
}
