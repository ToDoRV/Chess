public class Player {

    private String colorOfFigures;
    private String prompt;

    public Player(String colorOfFigures, String prompt) {
        this.colorOfFigures = colorOfFigures;
        this.prompt = prompt;
    }

    public String getColorOfFigures() {
        return colorOfFigures;
    }

    public String getPrompt() {
        return prompt;
    }
}
