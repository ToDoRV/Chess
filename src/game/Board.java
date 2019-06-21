package game;

import enums.Color;
import enums.ErrorCode;
import enums.Position;
import figure.*;

public class Board {

    private static final int BOARD_SIZE = 8;

    private static final String TRY_TO_TAKE_KING =
            Color.Red.getColor() + "You try to take the enemy king! " + Color.ResetColor.getColor();

    private static final Position[] BLACK_PAWNS_STARTING_POSITIONS = {
            Position.A7, Position.B7, Position.C7, Position.D7, Position.E7, Position.F7, Position.G7, Position.H7
    };

    private static final Position[] WHITE_PAWNS_STARTING_POSITIONS = {
            Position.A2, Position.B2, Position.C2, Position.D2, Position.E2, Position.F2, Position.G2, Position.H2
    };

    private Figure board[][];

    public Board() {
        board = new Figure[BOARD_SIZE][BOARD_SIZE];
        fillBoardWithBlackFigures();
        fillBoardWithWhiteFigures();
    }

    private void fillBoardWithBlackFigures() {
        board[Position.A8.getRow()][Position.A8.getColumn()] = new Rook(Position.A8, Color.BlackFigure);
        board[Position.B8.getRow()][Position.B8.getColumn()] = new Knight(Position.B8, Color.BlackFigure);
        board[Position.C8.getRow()][Position.C8.getColumn()] = new Bishop(Position.C8, Color.BlackFigure);
        board[Position.D8.getRow()][Position.D8.getColumn()] = new Queen(Position.D8, Color.BlackFigure);
        board[Position.E8.getRow()][Position.E8.getColumn()] = new King(Position.E8, Color.BlackFigure);
        board[Position.F8.getRow()][Position.F8.getColumn()] = new Bishop(Position.F8, Color.BlackFigure);
        board[Position.G8.getRow()][Position.G8.getColumn()] = new Knight(Position.G8, Color.BlackFigure);
        board[Position.H8.getRow()][Position.H8.getColumn()] = new Rook(Position.H8, Color.BlackFigure);

        for (Position position : BLACK_PAWNS_STARTING_POSITIONS) {
            board[position.getRow()][position.getColumn()] = new Pawn(position, Color.BlackFigure);
        }
    }

    private void fillBoardWithWhiteFigures() {
        for (Position position : WHITE_PAWNS_STARTING_POSITIONS) {
            board[position.getRow()][position.getColumn()] = new Pawn(position, Color.WhiteFigure);
        }

        board[Position.A1.getRow()][Position.A1.getColumn()] = new Rook(Position.A1, Color.WhiteFigure);
        board[Position.B1.getRow()][Position.B1.getColumn()] = new Knight(Position.B1, Color.WhiteFigure);
        board[Position.C1.getRow()][Position.C1.getColumn()] = new Bishop(Position.C1, Color.WhiteFigure);
        board[Position.D1.getRow()][Position.D1.getColumn()] = new Queen(Position.D1, Color.WhiteFigure);
        board[Position.E1.getRow()][Position.E1.getColumn()] = new King(Position.E1, Color.WhiteFigure);
        board[Position.F1.getRow()][Position.F1.getColumn()] = new Bishop(Position.F1, Color.WhiteFigure);
        board[Position.G1.getRow()][Position.G1.getColumn()] = new Knight(Position.G1, Color.WhiteFigure);
        board[Position.H1.getRow()][Position.H1.getColumn()] = new Rook(Position.H1, Color.WhiteFigure);
    }

    public void printBoardFor(Player player) {
        if (player.getColorOfFigures().equals(Color.WhiteFigure)) {
            printBoardForWhitePlayer();
        } else {
            printBoardForBlackPlayer();
        }
    }

    private void printBoardForWhitePlayer() {
        System.out.println();
        for (int i = 0; i < BOARD_SIZE; ++i) {
            System.out.print(BOARD_SIZE - i + " ");
            for (int j = 0; j < BOARD_SIZE; ++j) {
                printGridBackground(i, j);
                printGridForeground(i, j);
            }
            System.out.println(Color.ResetColor.getColor());
        }
        System.out.println("   A  B  C  D  E  F  G  H");
        System.out.println();
    }

    private void printBoardForBlackPlayer() {
        System.out.println();
        for (int i = BOARD_SIZE - 1; i >= 0; --i) {
            System.out.print(BOARD_SIZE - i + " ");
            for (int j = BOARD_SIZE - 1; j >= 0; --j) {
                printGridBackground(i, j);
                printGridForeground(i, j);
            }
            System.out.println(Color.ResetColor.getColor());
        }
        System.out.println("   H  G  F  E  D  C  B  A");
        System.out.println();
    }

    private void printGridBackground(int row, int column) {
        if ((row % 2 == 0 && column % 2 == 0) || (row % 2 != 0 && column % 2 != 0)) {
            System.out.print(Color.YellowBG.getColor());
        } else {
            System.out.print(Color.BlackBG.getColor());
        }
    }

    private void printGridForeground(int row, int column) {
        if (board[row][column] == null) {
            System.out.print("   ");
        } else {
            board[row][column].printFigure();
        }
    }

    public boolean checkUnavailableOfFigure(Position position) {
        return board[position.getRow()][position.getColumn()] == null;
    }

    public boolean checkForForeignPossess(Position position, Player player) {
        return !board[position.getRow()][position.getColumn()].getColor()
                .equals(player.getColorOfFigures());
    }

    public ErrorCode moveFigure(Position fromPosition, Position toPosition, Player player) {
        ErrorCode errorCode = board[fromPosition.getRow()][fromPosition.getColumn()].canMove(toPosition, player, board);

        if (errorCode.equals(ErrorCode.Success)) {
            errorCode = move(fromPosition, toPosition);
        }

        return errorCode;
    }

    private ErrorCode move(Position fromPosition, Position toPosition) {
        if (board[toPosition.getRow()][toPosition.getColumn()] != null
                && board[toPosition.getRow()][toPosition.getColumn()] instanceof King) {
            System.out.print(TRY_TO_TAKE_KING);
            return ErrorCode.TryAgain;
        }

        board[toPosition.getRow()][toPosition.getColumn()] = board[fromPosition.getRow()][fromPosition.getColumn()];
        board[toPosition.getRow()][toPosition.getColumn()].setPosition(toPosition);
        board[fromPosition.getRow()][fromPosition.getColumn()] = null;

        return ErrorCode.Success;
    }
}
