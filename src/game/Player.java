package game;

import enums.Color;

public class Player {

    private Color colorOfFigures;
    private String prompt;

    public Player(Color colorOfFigures, String prompt) {
        this.colorOfFigures = colorOfFigures;
        this.prompt = prompt;
    }

    public Color getColorOfFigures() {
        return colorOfFigures;
    }

    public String getPrompt() {
        return prompt;
    }
}
