package figure;

import enums.Position;

public class Knight extends Figure {

    public static final String KNIGHT_SIGN = " N ";

    public Knight(Position position, String color) {
        super(position, color);
    }

    @Override
    public void printFigure() {
        System.out.print(color + KNIGHT_SIGN);
    }
}
