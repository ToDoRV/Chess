package figure;

import enums.Position;

public class Queen extends Figure {

    public static final String QUEEN_SIGN = " Q ";

    public Queen(Position position, String color) {
        super(position, color);
    }

    @Override
    public void printFigure() {
        System.out.print(color + QUEEN_SIGN);
    }
}
