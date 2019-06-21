package figure;

import enums.Position;
import game.Board;
import game.Player;

public class Pawn extends Figure {

    private static final String WHITE_FIGURE = Board.WHITE_FIGURE;

    private static final int ONE_MOVE = 1;
    private static final int TWO_MOVES = 2;

    private static final String PAWN_SIGN = " P ";

    private boolean firstMove;

    public Pawn(Position position, String color) {
        super(position, color);
        firstMove = true;
    }

    @Override
    public void printFigure() {
        System.out.print(color + PAWN_SIGN);
    }

    @Override
    public int canMove(Position toPosition, Player player, Figure[][] board) {
        if (color.equals(WHITE_FIGURE)) {
            return canMoveWithWhitePawn(toPosition, player, board);
        }
        return canMoveWithBlackPawn(toPosition, player, board);
    }

    private int canMoveWithWhitePawn(Position toPosition, Player player, Figure[][] board) {
        if (tryOneWhiteMoveForward(toPosition)) {
            if (isThereFigureOn(toPosition, board)) {
                System.out.print(INVALID_MOVE_MESSAGE);
                return ERROR_CODE_TRY_AGAIN;
            }
            return ERROR_CODE_SUCCESS;
        }

        if (tryWhiteDiagonalMove(toPosition) && isThereEnemyFigureOn(toPosition, player, board)) {
            return ERROR_CODE_SUCCESS;
        }

        if (tryTwoWhiteMovesForward(toPosition)) {
            if (isThereFigureOn(toPosition.getRow() + 1, toPosition.getColumn(), board)
                    || isThereFigureOn(toPosition, board)) {
                System.out.print(INVALID_MOVE_MESSAGE);
                return ERROR_CODE_TRY_AGAIN;
            }
            this.firstMove = false;
            return ERROR_CODE_SUCCESS;
        }

        System.out.print(INVALID_MOVE_MESSAGE);
        return ERROR_CODE_TRY_AGAIN;
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

    private int canMoveWithBlackPawn(Position toPosition, Player player, Figure[][] board) {
        if (tryOneBlackMoveForward(toPosition)) {
            if (isThereFigureOn(toPosition, board)) {
                System.out.print(INVALID_MOVE_MESSAGE);
                return ERROR_CODE_TRY_AGAIN;
            }
            return ERROR_CODE_SUCCESS;
        }

        if (tryBlackDiagonalMove(toPosition) && isThereEnemyFigureOn(toPosition, player, board)) {
            return ERROR_CODE_SUCCESS;
        }

        if (tryTwoBlackMovesForward(toPosition)) {
            if (isThereFigureOn(toPosition.getRow() - 1, toPosition.getColumn(), board)
                    || isThereFigureOn(toPosition, board)) {
                System.out.print(INVALID_MOVE_MESSAGE);
                return ERROR_CODE_TRY_AGAIN;
            }
            this.firstMove = false;
            return ERROR_CODE_SUCCESS;
        }

        System.out.print(INVALID_MOVE_MESSAGE);
        return ERROR_CODE_TRY_AGAIN;
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
