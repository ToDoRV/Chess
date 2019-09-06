package figure;

import enums.Color;
import enums.ErrorCode;
import enums.Position;
import game.Board;
import game.Player;

public class King extends Figure {

    public static final String KING_SIGN = " K ";

    public King(Position position, Color color) {
        super(position, color);
    }

    @Override
    public void printFigure() {
        System.out.print(color.getColor() + KING_SIGN);
    }

    @Override
    public ErrorCode canMove(Position toPosition, Player player, Figure[][] board) {

        if (isThereOwnFigureOn(toPosition, player, board)) {
            return ErrorCode.TryAgain;
        }

        if (Math.abs(toPosition.getRow() - position.getRow()) <= 1
                && Math.abs(toPosition.getColumn() - position.getColumn()) <= 1) {
            return ErrorCode.Success;
        }

        if (canMakeCastling(toPosition, player, board)) {
            return ErrorCode.Success;
        }

        return ErrorCode.TryAgain;
    }

    private boolean canMakeCastling(Position toPosition, Player player, Figure[][] board) {
        ErrorCode errorCode;
        if (color.equals(Color.WhiteFigure)) {
            errorCode = makeCastlingWithWhiteFigure(toPosition, player, board);
        } else {
            errorCode = canMakeCastlingWithBlackFigure(toPosition, player, board);
        }

        if (errorCode.equals(ErrorCode.Success)) {
            return true;
        }
        return false;
    }

    private ErrorCode makeCastlingWithWhiteFigure(Position toPosition, Player player, Figure[][] board) {
        if (position.equals(Position.E1) && toPosition.equals(Position.G1) && haveWhiteRookOnH1(board)) {
            return Board.moveFigure(Position.H1, Position.F1, player);
        }

        if (position.equals(Position.E1) && toPosition.equals(Position.C1) && haveWhiteRookOnA1(board)) {
            return Board.moveFigure(Position.A1, Position.D1, player);
        }

        return ErrorCode.TryAgain;
    }

    private boolean haveWhiteRookOnH1(Figure[][] board) {
        Figure rook = board[Position.H1.getRow()][Position.H1.getColumn()];
        return rook instanceof Rook
                && rook.getColor().equals(Color.WhiteFigure);
    }

    private boolean haveWhiteRookOnA1(Figure[][] board) {
        Figure rook = board[Position.A1.getRow()][Position.A1.getColumn()];
        return rook instanceof Rook
                && rook.getColor().equals(Color.WhiteFigure);
    }

    private ErrorCode canMakeCastlingWithBlackFigure(Position toPosition, Player player, Figure[][] board) {
        if (position.equals(Position.E8) && toPosition.equals(Position.G8) && haveBlackRookOnH8(board)) {
            return Board.moveFigure(Position.H8, Position.F8, player);
        }

        if (position.equals(Position.E8) && toPosition.equals(Position.C8) && haveBlackRookOnA8(board)) {
            return Board.moveFigure(Position.A8, Position.D8, player);
        }

        return ErrorCode.TryAgain;
    }

    private boolean haveBlackRookOnH8(Figure[][] board) {
        Figure rook = board[Position.H8.getRow()][Position.H8.getColumn()];
        return rook instanceof Rook
                && rook.getColor().equals(Color.BlackFigure);
    }

    private boolean haveBlackRookOnA8(Figure[][] board) {
        Figure rook = board[Position.A8.getRow()][Position.A8.getColumn()];
        return rook instanceof Rook
                && rook.getColor().equals(Color.BlackFigure);
    }
}
