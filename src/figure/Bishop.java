package figure;

import enums.Color;
import enums.ErrorCode;
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
    public ErrorCode canMove(Position toPosition, Player player, Figure[][] board) {

        if (isThereOwnFigureOn(toPosition, player, board)) {
            return ErrorCode.TryAgain;
        }

        if (Math.abs(toPosition.getRow() - position.getRow()) == Math.abs(toPosition.getColumn() - position.getColumn())
                && thePathIsClearDiagonally(toPosition, board)) {
            return ErrorCode.Success;
        }

        return ErrorCode.TryAgain;
    }

    private boolean thePathIsClearDiagonally(Position toPosition, Figure[][] board) {

        int fromRow;
        int toRow;
        int column;

        if (position.getRow() < toPosition.getRow()) {
            fromRow = position.getRow();
            toRow = toPosition.getRow();
            column = position.getColumn();
        } else {
            fromRow = toPosition.getRow();
            toRow = position.getRow();
            column = toPosition.getColumn();
        }

        if (toPosition.getRow() + toPosition.getColumn() == position.getRow() + position.getColumn()) {
            return isTheOppositeDiagonalClear(fromRow, toRow, column, board);
        }

        return isTheDiagonalClear(fromRow, toRow, column, board);
    }

    private boolean isTheDiagonalClear(int fromRow, int toRow, int column, Figure[][] board) {
        ++fromRow;
        ++column;

        for (int i = fromRow; i < toRow; ++i) {
            if(board[i][column] != null) {
                return false;
            }
            ++column;
        }

        return true;
    }

    private boolean isTheOppositeDiagonalClear(int fromRow, int toRow, int column, Figure[][] board) {
        ++fromRow;
        --column;

        for (int i = fromRow; i < toRow; ++i) {
            if(board[i][column] != null) {
                return false;
            }
            --column;
        }

        return true;
    }
}
