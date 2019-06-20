package figure;

import enums.Position;

public class Pawn extends Figure {

    public static final String PAWN_SIGN = " P ";

    public Pawn(Position position, String color) {
        super(position, color);
    }

    @Override
    public void printFigure() {
        System.out.print(color + PAWN_SIGN);
    }
}
