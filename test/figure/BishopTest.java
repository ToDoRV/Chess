package figure;

import enums.Color;
import enums.ErrorCode;
import enums.Position;
import game.Player;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BishopTest {
    private static final int BOARD_SIZE = 8;

    private static final String WHITE_PLAYER_PROMPT = "player with white figures~S ";
    private static final String BLACK_PLAYER_PROMPT = "player with black figures~S ";

    private Figure[][] board;
    private static Player whitePlayer;
    private static Player blackPlayer;
    private static final int A3row = Position.A3.getRow();
    private static final int A3column = Position.A3.getColumn();
    private static final int C1row = Position.C1.getRow();
    private static final int C1column = Position.C1.getColumn();
    private static final int D3row = Position.D3.getRow();
    private static final int D3column = Position.D3.getColumn();
    private static final int F1row = Position.F1.getRow();
    private static final int F1column = Position.F1.getColumn();
    private static final int F8row = Position.F8.getRow();
    private static final int F8column = Position.F8.getColumn();


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
    public void testMoveFromC1ToE3() {
        board[C1row][C1column] = new Bishop(Position.C1, Color.WhiteFigure);
        ErrorCode errorCode = board[C1row][C1column].canMove(Position.E3, whitePlayer, board);
        assertEquals(ErrorCode.Success, errorCode);
    }

    @Test
    public void testMoveFromF8ToH6() {
        board[F8row][F8column] = new Bishop(Position.F8, Color.BlackFigure);
        ErrorCode errorCode = board[F8row][F8column].canMove(Position.H6, blackPlayer, board);
        assertEquals(ErrorCode.Success, errorCode);
    }

    @Test
    public void testGetEnemyFigure() {
        board[C1row][C1column] = new Bishop(Position.C1, Color.WhiteFigure);
        board[A3row][A3column] = new Bishop(Position.A3, Color.BlackFigure);
        ErrorCode errorCode = board[C1row][C1column].canMove(Position.A3, whitePlayer, board);
        assertEquals(ErrorCode.Success, errorCode);
    }

    @Test
    public void testJumpOveringOfFigure() {
        board[F1row][F1column] = new Bishop(Position.F1, Color.WhiteFigure);
        board[D3row][D3column] = new Bishop(Position.D3, Color.BlackFigure);
        ErrorCode errorCode = board[F1row][F1column].canMove(Position.B5, whitePlayer, board);
        assertEquals(ErrorCode.TryAgain, errorCode);
    }

    @Test
    public void testWithInvalidPosition() {
        board[F1row][F1column] = new Bishop(Position.F1, Color.WhiteFigure);
        ErrorCode errorCode = board[F1row][F1column].canMove(Position.F3, whitePlayer, board);
        assertEquals(ErrorCode.TryAgain, errorCode);
    }

    @Test
    public void testToTakeOwnFigure() {
        board[F8row][F8column] = new Bishop(Position.F8, Color.BlackFigure);
        board[A3row][A3column] = new Bishop(Position.A3, Color.BlackFigure);
        ErrorCode errorCode = board[F8row][F8column].canMove(Position.A3, blackPlayer, board);
        assertEquals(ErrorCode.TryAgain, errorCode);
    }
}