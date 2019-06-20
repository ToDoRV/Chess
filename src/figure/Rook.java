package figure;

import enums.Position;

public class Rook extends Figure {

    public static final String ROOK_SIGN = " R ";

    public Rook(Position position, String color) {
        super(position, color);
    }

    @Override
    public void printFigure() {
        System.out.print(color + ROOK_SIGN);
    }
}
