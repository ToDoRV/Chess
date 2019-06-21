package figure;

import enums.Color;
import enums.ErrorCode;
import enums.Position;
import game.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PawnTest {

    @Test
    public void testOneMoveForward() {
        Figure[][] board = new Figure[8][8];
        board[Position.A2.getRow()][Position.A2.getColumn()] = new Pawn(Position.A2, Color.WhiteFigure);
        Player player = new Player(Color.WhiteFigure, "player");
        ErrorCode errorCode = board[Position.A2.getRow()][Position.A2.getColumn()].canMove(Position.A4, player, board);
        assertEquals(ErrorCode.Success, errorCode);
    }
}
