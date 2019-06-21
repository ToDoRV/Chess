package figure;

import enums.Color;
import game.Game;
import game.Player;
import enums.Position;

public abstract class Figure {

    static final int ERROR_CODE_SUCCESS = Game.ERROR_CODE_SUCCESS;
    static final int ERROR_CODE_TRY_AGAIN = Game.ERROR_CODE_TRY_AGAIN;

    static final String INVALID_MOVE_MESSAGE = Color.Red.getColor() + "Invalid move! " + Color.ResetColor.getColor();

    Position position;
    Color color;

    public Figure(Position position, Color color) {
        this.position = position;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract void printFigure();

    public abstract int canMove(Position toPosition, Player player, Figure[][] board);
}
