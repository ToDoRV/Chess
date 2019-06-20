package figure;

import enums.Position;

public class Bishop extends Figure{

    public static final String BISHOP_SIGN = " B ";

    public Bishop(Position position, String color) {
        super(position, color);
    }

    @Override
    public void printFigure() {
        System.out.print(color + BISHOP_SIGN);
    }
}
