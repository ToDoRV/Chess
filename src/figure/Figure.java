package figure;

import game.Game;
import game.Player;
import enums.Position;

public abstract class Figure {

    static final int ERROR_CODE_SUCCESS = Game.ERROR_CODE_SUCCESS;
    static final int ERROR_CODE_TRY_AGAIN = Game.ERROR_CODE_TRY_AGAIN;

    private static final String RESET_COLOR = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    static final String INVALID_MOVE_MESSAGE = RED + "Invalid move! " + RESET_COLOR;

    Position position;
    String color;

    public Figure(Position position, String color) {
        this.position = position;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract void printFigure();

    public abstract int canMove(Position toPosition, Player player, Figure[][] board);
}
