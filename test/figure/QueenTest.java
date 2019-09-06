package figure;

import enums.Color;
import enums.ErrorCode;
import enums.Position;
import game.Player;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QueenTest {
    private static final int BOARD_SIZE = 8;

    private static final String WHITE_PLAYER_PROMPT = "player with white figures~S ";
    private static final String BLACK_PLAYER_PROMPT = "player with black figures~S ";

    private Figure[][] board;
    private static Player whitePlayer;
    private static Player blackPlayer;
    private static final int D1row = Position.D1.getRow();
    private static final int D1column = Position.D1.getColumn();
    private static final int D8row = Position.D8.getRow();
    private static final int D8column = Position.D8.getColumn();
    private static final int B6row = Position.B6.getRow();
    private static final int B6column = Position.B6.getColumn();

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
    public void testVerticalMove() {
        board[D1row][D1column] = new Queen(Position.D1, Color.WhiteFigure);
        ErrorCode errorCode = board[D1row][D1column].canMove(Position.D5, whitePlayer, board);
        assertEquals(ErrorCode.Success, errorCode);
    }

    @Test
    public void testHorizontalMove() {
        board[D1row][D1column] = new Queen(Position.D1, Color.WhiteFigure);
        ErrorCode errorCode = board[D1row][D1column].canMove(Position.A1, whitePlayer, board);
        assertEquals(ErrorCode.Success, errorCode);
    }

    @Test
    public void testDiagonalMove() {
        board[D1row][D1column] = new Queen(Position.D1, Color.WhiteFigure);
        ErrorCode errorCode = board[D1row][D1column].canMove(Position.A4, whitePlayer, board);
        assertEquals(ErrorCode.Success, errorCode);
    }

    @Test
    public void testGetEnemyFigure() {
        board[D8row][D8column] = new Queen(Position.D8, Color.BlackFigure);
        board[B6row][B6column] = new Queen(Position.B6, Color.WhiteFigure);
        ErrorCode errorCode = board[D8row][D8column].canMove(Position.B6, blackPlayer, board);
        assertEquals(ErrorCode.Success, errorCode);
    }

    @Test
    public void testJumpOveringOfFigure() {
        board[D8row][D8column] = new Queen(Position.D8, Color.BlackFigure);
        board[B6row][B6column] = new Queen(Position.B6, Color.WhiteFigure);
        ErrorCode errorCode = board[D8row][D8column].canMove(Position.A5, blackPlayer, board);
        assertEquals(ErrorCode.TryAgain, errorCode);
    }

    @Test
    public void testInvalidMove() {
        board[D8row][D8column] = new Queen(Position.D8, Color.BlackFigure);
        ErrorCode errorCode = board[D8row][D8column].canMove(Position.C6, blackPlayer, board);
        assertEquals(ErrorCode.TryAgain, errorCode);
    }
}