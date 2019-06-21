package figure;

import enums.Color;
import enums.Position;
import game.Player;

public class Queen extends Figure {

    public static final String QUEEN_SIGN = " Q ";

    public Queen(Position position, Color color) {
        super(position, color);
    }

    @Override
    public void printFigure() {
        System.out.print(color.getColor() + QUEEN_SIGN);
    }

    @Override
    public int canMove(Position toPosition, Player player, Figure[][] board) {
        //ToDo
        return ERROR_CODE_SUCCESS;
    }
}
