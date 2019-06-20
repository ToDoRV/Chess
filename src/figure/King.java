package figure;

import enums.Position;

public class King extends Figure {

    public static final String KING_SIGN = " K ";

    public King(Position position, String color) {
        super(position, color);
    }

    @Override
    public void printFigure() {
        System.out.print(color + KING_SIGN);
    }
}
