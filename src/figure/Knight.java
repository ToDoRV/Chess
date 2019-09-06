package figure;

import enums.Color;
import enums.ErrorCode;
import enums.Position;
import game.Player;

import static java.lang.Math.abs;

public class Knight extends Figure {

    public static final String KNIGHT_SIGN = " N ";

    public static final String TRY_TO_GET_OWN_FIGURE_MESSAGE =
            Color.Red.getColor() + "You try to get your own figure!" + Color.ResetColor.getColor();

    public Knight(Position position, Color color) {
        super(position, color);
    }

    @Override
    public void printFigure() {
        System.out.print(color.getColor() + KNIGHT_SIGN);
    }

    @Override
    public ErrorCode canMove(Position toPosition, Player player, Figure[][] board) {
        if (isThereOwnFigureOn(toPosition, player, board)) {
            System.out.println(TRY_TO_GET_OWN_FIGURE_MESSAGE);
            return ErrorCode.TryAgain;
        }

        int differenceInRows = abs(toPosition.getRow() - position.getRow());
        int differenceInColumns = abs(toPosition.getColumn() - position.getColumn());
        if (differenceInRows == 2 && differenceInColumns == 1
                || differenceInRows == 1 && differenceInColumns == 2) {
            return ErrorCode.Success;
        }

        return ErrorCode.TryAgain;
    }
}
