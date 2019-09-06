package figure;

import enums.Color;
import enums.ErrorCode;
import game.Player;
import enums.Position;

public abstract class Figure {

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

    public abstract ErrorCode canMove(Position toPosition, Player player, Figure[][] board);

    protected boolean isThereFigureOn(Position position, Figure[][] board) {
        return board[position.getRow()][position.getColumn()] != null;
    }

    protected boolean isThereFigureOn(int row, int column, Figure[][] board) {
        return board[row][column] != null;
    }

    protected boolean isThereOwnFigureOn(Position position, Player player, Figure[][] board) {
        return isThereFigureOn(position, board)
                && board[position.getRow()][position.getColumn()].getColor().equals(player.getColorOfFigures());
    }

    protected boolean isThereEnemyFigureOn(Position position, Player player, Figure[][] board) {
        return isThereFigureOn(position, board)
                && !board[position.getRow()][position.getColumn()].getColor().equals(player.getColorOfFigures());
    }
}
