package figure;

import enums.Color;
import enums.ErrorCode;
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
    public ErrorCode canMove(Position toPosition, Player player, Figure[][] board) {
        ErrorCode errorCode;
        Rook rook = new Rook(position, color);
        errorCode = rook.canMove(toPosition, player, board);

        if (errorCode.equals(ErrorCode.Success)) {
            return ErrorCode.Success;
        }

        Bishop bishop = new Bishop(position, color);
        errorCode = bishop.canMove(toPosition, player, board);

        if (errorCode.equals(ErrorCode.Success)) {
            return ErrorCode.Success;
        }

        return ErrorCode.TryAgain;
    }
}
