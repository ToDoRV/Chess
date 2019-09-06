package figure;

import enums.Color;
import enums.ErrorCode;
import enums.Position;
import game.Player;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class KnightTest {

    private static final int BOARD_SIZE = 8;

    private static final String WHITE_PLAYER_PROMPT = "player with white figures~S ";

    private Figure[][] board;
    private static Player whitePlayer;

    @BeforeClass
    public static void setUpClass() {
        whitePlayer = new Player(Color.WhiteFigure, WHITE_PLAYER_PROMPT);
    }

    @Before
    public void setUp() {
        board = new Figure[BOARD_SIZE][BOARD_SIZE];
    }

    @Test
    public void testCanMoveFromB1ToC3WithWhiteKnight() {
        board[Position.B1.getRow()][Position.B1.getColumn()] = new Knight(Position.B1, Color.WhiteFigure);
        ErrorCode errorCode =
                board[Position.B1.getRow()][Position.B1.getColumn()].canMove(Position.C3, whitePlayer, board);
        assertEquals(ErrorCode.Success, errorCode);
    }

    @Test
    public void testTakeEnemyFigure() {
        board[Position.A4.getRow()][Position.A4.getColumn()] = new Knight(Position.A4, Color.WhiteFigure);
        board[Position.C5.getRow()][Position.C5.getColumn()] = new Knight(Position.C5, Color.BlackFigure);
        ErrorCode errorCode =
                board[Position.A4.getRow()][Position.A4.getColumn()].canMove(Position.C5, whitePlayer, board);
        assertEquals(ErrorCode.Success, errorCode);
    }

    @Test
    public void testCanMoveToInvalidMove() {
        board[Position.C6.getRow()][Position.C6.getColumn()] = new Knight(Position.C6, Color.BlackFigure);
        ErrorCode errorCode =
                board[Position.C6.getRow()][Position.C6.getColumn()].canMove(Position.C5, whitePlayer, board);
        assertEquals(ErrorCode.TryAgain, errorCode);
    }
}