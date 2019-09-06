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

        if (isThereOwnFigureOn(toPosition, player, board)) {
            return ErrorCode.TryAgain;
        }

        if (position.getRow() == toPosition.getRow()
                && thePathIsClearByColumn(toPosition, board)) {
            return ErrorCode.Success;
        }

        if (position.getColumn() == toPosition.getColumn()
                && thePathIsClearByRow(toPosition, board)) {
            return ErrorCode.Success;
        }

        return ErrorCode.TryAgain;
    }

    private boolean thePathIsClearByColumn(Position toPosition, Figure[][] board) {
        int row = position.getRow();
        int fromColumn = Math.min(position.getColumn(), toPosition.getColumn());
        int toColumn = Math.max(position.getColumn(), toPosition.getColumn());

        ++fromColumn;
        for (int i = fromColumn; i < toColumn; ++i) {
            if (board[row][i] != null) {
                return false;
            }
        }

        return true;
    }

    private boolean thePathIsClearByRow(Position toPosition, Figure[][] board) {
        int column = position.getColumn();
        int fromRow = Math.min(position.getRow(), toPosition.getRow());
        int toRow = Math.max(position.getRow(), toPosition.getRow());

        ++fromRow;
        for (int i = fromRow; i < toRow; ++i) {
            if (board[i][column] != null) {
                return false;
            }
        }

        return true;
    }
}
