package figure;

import enums.Color;
import enums.ErrorCode;
import enums.Position;
import game.Player;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class RookTest {

    private static final int BOARD_SIZE = 8;

    private static final String WHITE_PLAYER_PROMPT = "player with white figures~S ";
    private static final String BLACK_PLAYER_PROMPT = "player with black figures~S ";

    private Figure[][] board;
    private static Player whitePlayer;
    private static Player blackPlayer;

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
    public void testCanMoveByColumn() {
        board[Position.A1.getRow()][Position.A1.getColumn()] = new Rook(Position.A1, Color.WhiteFigure);
        ErrorCode errorCodes =
                board[Position.A1.getRow()][Position.A1.getColumn()].canMove(Position.A5, whitePlayer, board);
        assertEquals(ErrorCode.Success, errorCodes);
    }

    @Test
    public void testCanMoveByRow() {
        board[Position.A8.getRow()][Position.A8.getColumn()] = new Rook(Position.A8, Color.BlackFigure);
        ErrorCode errorCodes =
                board[Position.A8.getRow()][Position.A8.getColumn()].canMove(Position.E8, blackPlayer, board);
        assertEquals(ErrorCode.Success, errorCodes);
    }

    @Test
    public void testTakeEnemyFigure() {
        board[Position.A1.getRow()][Position.A1.getColumn()] = new Rook(Position.A1, Color.WhiteFigure);
        board[Position.A8.getRow()][Position.A8.getColumn()] = new Rook(Position.A8, Color.BlackFigure);
        ErrorCode errorCodes =
                board[Position.A1.getRow()][Position.A1.getColumn()].canMove(Position.A8, whitePlayer, board);
        assertEquals(ErrorCode.Success, errorCodes);
    }

    @Test
    public void testInvalidMove() {
        board[Position.A8.getRow()][Position.A8.getColumn()] = new Rook(Position.A8, Color.BlackFigure);
        ErrorCode errorCodes =
                board[Position.A8.getRow()][Position.A8.getColumn()].canMove(Position.B7, blackPlayer, board);
        assertEquals(ErrorCode.TryAgain, errorCodes);
    }

    @Test
    public void testJumpOveringOfFigure() {
        board[Position.A1.getRow()][Position.A1.getColumn()] = new Rook(Position.A1, Color.WhiteFigure);
        board[Position.A3.getRow()][Position.A3.getColumn()] = new Rook(Position.A3, Color.BlackFigure);
        ErrorCode errorCodes =
                board[Position.A1.getRow()][Position.A1.getColumn()].canMove(Position.A5, whitePlayer, board);
        assertEquals(ErrorCode.TryAgain, errorCodes);
    }

    @Test
    public void testToTakeOwnFigure() {
        board[Position.A1.getRow()][Position.A1.getColumn()] = new Rook(Position.A1, Color.WhiteFigure);
        board[Position.H1.getRow()][Position.H1.getColumn()] = new Rook(Position.H1, Color.WhiteFigure);
        ErrorCode errorCodes =
                board[Position.A1.getRow()][Position.A1.getColumn()].canMove(Position.H1, whitePlayer, board);
        assertEquals(ErrorCode.TryAgain, errorCodes);
    }
}