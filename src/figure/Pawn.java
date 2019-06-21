package figure;

import enums.Color;
import enums.ErrorCode;
import enums.Position;
import game.Player;

public class Pawn extends Figure {

    private static final int ONE_MOVE = 1;
    private static final int TWO_MOVES = 2;

    private static final String PAWN_SIGN = " P ";

    private boolean firstMove;

    public Pawn(Position position, Color color) {
        super(position, color);
        firstMove = true;
    }

    @Override
    public void printFigure() {
        System.out.print(color.getColor() + PAWN_SIGN);
    }

    @Override
    public ErrorCode canMove(Position toPosition, Player player, Figure[][] board) {
        if (color.equals(Color.WhiteFigure)) {
            return canMoveWithWhitePawn(toPosition, player, board);
        }
        return canMoveWithBlackPawn(toPosition, player, board);
    }

    private ErrorCode canMoveWithWhitePawn(Position toPosition, Player player, Figure[][] board) {
        if (tryOneWhiteMoveForward(toPosition)) {
            if (isThereFigureOn(toPosition, board)) {
                System.out.print(INVALID_MOVE_MESSAGE);
                return ErrorCode.TryAgain;
            }
            this.firstMove = false;
            return ErrorCode.Success;
        }

        if (tryWhiteDiagonalMove(toPosition) && isThereEnemyFigureOn(toPosition, player, board)) {
            this.firstMove = false;
            return ErrorCode.Success;
        }

        if (tryTwoWhiteMovesForward(toPosition)) {
            if (isThereFigureOn(toPosition.getRow() + 1, toPosition.getColumn(), board)
                    || isThereFigureOn(toPosition, board)) {
                System.out.print(INVALID_MOVE_MESSAGE);
                return ErrorCode.TryAgain;
            }
            this.firstMove = false;
            return ErrorCode.Success;
        }

        System.out.print(INVALID_MOVE_MESSAGE);
        return ErrorCode.TryAgain;
    }

    private boolean tryOneWhiteMoveForward(Position toPosition) {
        return position.getRow() - ONE_MOVE == toPosition.getRow()
                && position.getColumn() == toPosition.getColumn();
    }

    private boolean tryWhiteDiagonalMove(Position toPosition) {
        return position.getRow() - ONE_MOVE == toPosition.getRow()
                && ((position.getColumn() + ONE_MOVE == toPosition.getColumn())
                || (position.getColumn() - ONE_MOVE == toPosition.getColumn()));
    }

    private boolean tryTwoWhiteMovesForward(Position toPosition) {
        return firstMove
                && position.getRow() - TWO_MOVES == toPosition.getRow()
                && position.getColumn() == toPosition.getColumn();
    }

    private ErrorCode canMoveWithBlackPawn(Position toPosition, Player player, Figure[][] board) {
        if (tryOneBlackMoveForward(toPosition)) {
            if (isThereFigureOn(toPosition, board)) {
                System.out.print(INVALID_MOVE_MESSAGE);
                return ErrorCode.TryAgain;
            }
            return ErrorCode.Success;
        }

        if (tryBlackDiagonalMove(toPosition) && isThereEnemyFigureOn(toPosition, player, board)) {
            return ErrorCode.Success;
        }

        if (tryTwoBlackMovesForward(toPosition)) {
            if (isThereFigureOn(toPosition.getRow() - ONE_MOVE, toPosition.getColumn(), board)
                    || isThereFigureOn(toPosition, board)) {
                System.out.print(INVALID_MOVE_MESSAGE);
                return ErrorCode.TryAgain;
            }
            this.firstMove = false;
            return ErrorCode.Success;
        }

        System.out.print(INVALID_MOVE_MESSAGE);
        return ErrorCode.TryAgain;
    }

    private boolean tryOneBlackMoveForward(Position toPosition) {
        return position.getRow() + ONE_MOVE == toPosition.getRow()
                && position.getColumn() == toPosition.getColumn();
    }

    private boolean tryBlackDiagonalMove(Position toPosition) {
        return position.getRow() + ONE_MOVE == toPosition.getRow()
                && ((position.getColumn() + ONE_MOVE == toPosition.getColumn())
                || (position.getColumn() - ONE_MOVE == toPosition.getColumn()));
    }

    private boolean tryTwoBlackMovesForward(Position toPosition) {
        return firstMove
                && position.getRow() + TWO_MOVES == toPosition.getRow()
                && position.getColumn() == toPosition.getColumn();
    }

    private boolean isThereFigureOn(Position position, Figure[][] board) {
        return board[position.getRow()][position.getColumn()] != null;
    }

    private boolean isThereFigureOn(int row, int column, Figure[][] board) {
        return board[row][column] != null;
    }

    private boolean isThereEnemyFigureOn(Position position, Player player, Figure[][] board) {
        return isThereFigureOn(position, board)
                && !board[position.getRow()][position.getColumn()].getColor().equals(player.getColorOfFigures());
    }
}
