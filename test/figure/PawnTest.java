package figure;

import enums.Color;
import enums.ErrorCode;
import enums.Position;
import game.Player;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class PawnTest {

    private static final int BOARD_SIZE = 8;

    private static final String WHITE_PLAYER_PROMPT = "player with white figures~S ";
    private static final String BLACK_PLAYER_PROMPT = "player with black figures~S ";

    private Figure[][] board;
    private static Player whitePlayer;
    private static Player blackPlayer;
    private static final int A2row = Position.A2.getRow();
    private static final int A2column = Position.A2.getColumn();
    private static final int B7row = Position.B7.getRow();
    private static final int B7column = Position.B7.getColumn();


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
    public void testOneWhiteMoveForward() {
        board[A2row][A2column] = new Pawn(Position.A2, Color.WhiteFigure);
        ErrorCode errorCode = board[A2row][A2column].canMove(Position.A3, whitePlayer, board);
        assertEquals(ErrorCode.Success, errorCode);
    }

    @Test
    public void tesTwoWhiteMoveForward() {
        board[A2row][A2column] = new Pawn(Position.A2, Color.WhiteFigure);
        ErrorCode errorCode = board[A2row][A2column].canMove(Position.A4, whitePlayer, board);
        assertEquals(ErrorCode.Success, errorCode);
    }

    @Test
    public void testWhiteDiagonalMoveAndGettingEnemyFigure() {
        board[A2row][A2column] = new Pawn(Position.A2, Color.WhiteFigure);
        board[Position.B3.getRow()][Position.B3.getColumn()] = new Pawn(Position.B3, Color.BlackFigure);
        ErrorCode errorCode = board[A2row][A2column].canMove(Position.B3, whitePlayer, board);
        assertEquals(ErrorCode.Success, errorCode);
    }

    @Test
    public void testOneBlackMoveForward() {
        board[B7row][B7column] = new Pawn(Position.B7, Color.BlackFigure);
        ErrorCode errorCode = board[B7row][B7column].canMove(Position.B6, blackPlayer, board);
        assertEquals(ErrorCode.Success, errorCode);
    }

    @Test
    public void tesTwoBlackMoveForward() {
        board[B7row][B7column] = new Pawn(Position.B7, Color.BlackFigure);
        ErrorCode errorCode = board[B7row][B7column].canMove(Position.B5, blackPlayer, board);
        assertEquals(ErrorCode.Success, errorCode);
    }

    @Test
    public void testBlackDiagonalMoveAndGettingEnemyFigure() {
        board[B7row][B7column] = new Pawn(Position.B7, Color.BlackFigure);
        board[Position.A6.getRow()][Position.A6.getColumn()] = new Pawn(Position.A6, Color.WhiteFigure);
        ErrorCode errorCode = board[B7row][B7column].canMove(Position.A6, blackPlayer, board);
        assertEquals(ErrorCode.Success, errorCode);
    }

    @Test
    public void testTwoMoveForwardWithJumpOveringOfFigure() {
        board[A2row][A2column] = new Pawn(Position.A2, Color.WhiteFigure);
        board[Position.A3.getRow()][Position.A3.getColumn()] = new Pawn(Position.A3, Color.BlackFigure);
        ErrorCode errorCode = board[A2row][A2column].canMove(Position.A4, whitePlayer, board);
        assertEquals(ErrorCode.TryAgain, errorCode);
    }

    @Test
    public void testTwoMoveForwardAfterFirstMove() {
        board[A2row][A2column] = new Pawn(Position.A2, Color.WhiteFigure);
        ErrorCode errorCode = board[A2row][A2column].canMove(Position.A4, whitePlayer, board);
        assertEquals(ErrorCode.Success, errorCode);
        board[Position.A4.getRow()][Position.A4.getColumn()] = board[A2row][A2column];
        errorCode = board[Position.A4.getRow()][Position.A4.getColumn()].canMove(Position.A6, whitePlayer, board);
        assertEquals(ErrorCode.TryAgain, errorCode);
    }

    /*
    public void printBoard() {
        for (int i = 0; i < BOARD_SIZE; ++i) {
            for (int j = 0; j < BOARD_SIZE; ++j) {
                System.out.print(board[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
    */
}
