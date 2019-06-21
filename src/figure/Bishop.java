package figure;

import enums.Color;
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
    public int canMove(Position toPosition, Player player, Figure[][] board) {
        //ToDo
        return ERROR_CODE_SUCCESS;
    }
}
