package figure;

import enums.Position;

public abstract class Figure {

    Position position;
    String color;

    public Figure(Position position, String color) {
        this.position = position;
        this.color = color;
    }

    public abstract void printFigure();
}
