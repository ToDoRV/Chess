package enums;

public enum Color {

    //ANSI colors
    ResetColor("\u001B[0m"),
    Underline("\u001B[4m"),
    WhiteFigure("\u001B[1;30m"),
    BlackFigure("\u001B[1;97m"),
    Green("\u001B[32m"),
    Red("\u001B[31m"),
    YellowBG("\u001B[43m"),
    BlackBG("\u001B[100m");


    private final String color;

    Color(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
