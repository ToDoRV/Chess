package figure;

import enums.Color;
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
    public int canMove(Position toPosition, Player player, Figure[][] board) {
        //ToDo
        return ERROR_CODE_SUCCESS;
    }
}
