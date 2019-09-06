package figure;

import enums.Color;
import enums.ErrorCode;
import enums.Position;
import game.Board;
import game.Player;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KingTest {
    private static final int BOARD_SIZE = 8;

    private static final String WHITE_PLAYER_PROMPT = "player with white figures~S ";
    private static final String BLACK_PLAYER_PROMPT = "player with black figures~S ";

    private Figure[][] board;
    private static Player whitePlayer;
    private static Player blackPlayer;
    private static final int E1row = Position.E1.getRow();
    private static final int E1column = Position.E1.getColumn();
    private static final int E8row = Position.E8.getRow();
    private static final int E8column = Position.E8.getColumn();
    private static final int H1row = Position.H1.getRow();
    private static final int H1column = Position.H1.getColumn();
    private static final int A8row = Position.A8.getRow();
    private static final int A8column = Position.A8.getColumn();

    @BeforeClass
    public static void setUpClass() {
        whitePlayer = new Player(Color.WhiteFigure, WHITE_PLAYER_PROMPT);
        blackPlayer = new Player(Color.BlackFigure, BLACK_PLAYER_PROMPT);
    }

    @Before
    public void setUp() {
        board = new Figure[BOARD_SIZE][BOARD_SIZE];
    }

    @Test
    public void testOneMoveForward() {
        board[E1row][E1column] = new King(Position.E1, Color.WhiteFigure);
        ErrorCode errorCode = board[E1row][E1column].canMove(Position.E2, whitePlayer, board);
        assertEquals(ErrorCode.Success, errorCode);
    }

    @Test
    public void testOneDiagonalMove() {
        board[E1row][E1column] = new King(Position.E1, Color.WhiteFigure);
        ErrorCode errorCode = board[E1row][E1column].canMove(Position.D2, whitePlayer, board);
        assertEquals(ErrorCode.Success, errorCode);
    }

    @Test
    public void testWhiteCastling() {
        Board gameBoard = new Board();
        gameBoard.moveFigure(Position.E2, Position.E3, whitePlayer);
        gameBoard.moveFigure(Position.F1, Position.D3, whitePlayer);
        gameBoard.moveFigure(Position.G1, Position.F3, whitePlayer);

        board[E1row][E1column] = new King(Position.E1, Color.WhiteFigure);
        board[H1row][H1column] = new Rook(Position.H1, Color.WhiteFigure);
        ErrorCode errorCode = board[E1row][E1column].canMove(Position.G1, whitePlayer, board);
        assertEquals(ErrorCode.Success, errorCode);
    }

    @Test
    public void testBlackCastling() {
        Board gameBoard = new Board();
        gameBoard.moveFigure(Position.D7, Position.D6, whitePlayer);
        gameBoard.moveFigure(Position.C8, Position.E6, whitePlayer);
        gameBoard.moveFigure(Position.D8, Position.D7, whitePlayer);
        gameBoard.moveFigure(Position.B8, Position.C6, whitePlayer);

        board[E8row][E8column] = new King(Position.E8, Color.BlackFigure);
        board[A8row][A8column] = new Rook(Position.A8, Color.BlackFigure);
        ErrorCode errorCode = board[E8row][E8column].canMove(Position.C8, blackPlayer, board);
        assertEquals(ErrorCode.Success, errorCode);
    }

    @Test
    public void testInvalidMove() {
        board[E8row][E8column] = new King(Position.E8, Color.BlackFigure);
        ErrorCode errorCode = board[E8row][E8column].canMove(Position.E6, blackPlayer, board);
        assertEquals(ErrorCode.TryAgain, errorCode);
    }
}