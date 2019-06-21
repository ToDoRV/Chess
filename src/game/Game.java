package game;

import enums.Color;
import enums.Position;

import java.util.Scanner;

public class Game {

    private static final String END_GAME_COMMAND = "checkmate";
    private static final String MOVE_FIGURE_COMMAND = "move";
    private static final String MOVE_FIGURE_AUXILIARY_COMMAND = "to";

    //messages
    private static final String WELCOME_MESSAGE =
            Color.Underline.getColor() + "   Welcome to CHESS GAME!   " + Color.ResetColor.getColor();
    private static final String MOVE_HELP_MESSAGE =
            "To move the figure write command: " + Color.Green.getColor() + MOVE_FIGURE_COMMAND
                    + " <position> to <position>" + Color.ResetColor.getColor();
    private static final String POSITIONS_HELP_MESSAGE =
            "<position> := {A, B, C, D, E, F, G, H} x {1, 2, 3, 4, 5, 6, 7, 8}";
    private static final String END_GAME_HELP_MESSAGE =
            "To end the game announce: " + Color.Green.getColor() + END_GAME_COMMAND + Color.ResetColor.getColor();
    private static final String GOOD_LUCK_MESSAGE = "GOOD LUCK!";

    private static final String INVALID_POSITION_MESSAGE =
            Color.Red.getColor() + "Invalid position! " + Color.ResetColor.getColor();
    private static final String EMPTY_POSITION_MESSAGE =
            Color.Red.getColor() + "The selected position is empty! " + Color.ResetColor.getColor();
    private static final String UNALLOWED_ACCESS_MESSAGE =
            Color.Red.getColor() + "You are trying to move an enemy figure! " + Color.ResetColor.getColor();
    private static final String INVALID_COMMAND_MESSAGE =
            Color.Red.getColor() + "Invalid command! " + Color.ResetColor.getColor();
    private static final String TRY_AGAIN_MESSAGE =
            Color.Red.getColor() + "Please try again!" + Color.ResetColor.getColor();

    private static final String WHITE_PLAYER_WIN_MESSAGE
            = "Congratulations, the player with white figures won the game!";
    private static final String BLACK_PLAYER_WIN_MESSAGE
            = "Congratulations, the player with black figures won the game!";

    private static final String WHITE_PLAYER_PROMPT = "player with white figures~S ";
    private static final String BLACK_PLAYER_PROMPT = "player with black figures~S ";

    private static final String WHITE_SIGNS_REGEX = "\\s+";

    private static final int COUNT_ARGUMENTS_IN_END_GAME_COMMAND_LINE = 1;
    private static final int COUNT_ARGUMENTS_IN_MOVE_COMMAND_LINE = 4;

    private static final int ACTION_COMMAND_INDEX = 0;
    private static final int MOVE_FIGURE_AUXILIARY_COMMAND_INDEX = 2;
    private static final int FROM_POSITION_INDEX = 1;
    private static final int TO_POSITION_INDEX = 3;

    public static final int ERROR_CODE_SUCCESS = 1;
    public static final int ERROR_CODE_END_GAME = 0;
    public static final int ERROR_CODE_TRY_AGAIN = -1;

    private Board board;
    private Player whitePlayer;
    private Player blackPlayer;

    public Game() {
        this.board = new Board();
        this.whitePlayer = new Player(Color.WhiteFigure, WHITE_PLAYER_PROMPT);
        this.blackPlayer = new Player(Color.BlackFigure, BLACK_PLAYER_PROMPT);
    }

    public void play() {
        System.out.println();
        System.out.println(WELCOME_MESSAGE);
        System.out.println(MOVE_HELP_MESSAGE);
        System.out.println(POSITIONS_HELP_MESSAGE);
        System.out.println(END_GAME_HELP_MESSAGE);
        System.out.println(GOOD_LUCK_MESSAGE);

        int errorCode;

        while (true) {
            errorCode = playWith(whitePlayer);

            if (errorCode == ERROR_CODE_END_GAME) {
                System.out.println(BLACK_PLAYER_WIN_MESSAGE);
                return;
            }

            errorCode = playWith(blackPlayer);

            if (errorCode == ERROR_CODE_END_GAME) {
                System.out.println(WHITE_PLAYER_WIN_MESSAGE);
                return;
            }
        }
    }

    private int playWith(Player player) {
        board.printBoardFor(player);

        int errorCode;

        do {
            errorCode = executeCommandFrom(player);

            if (errorCode == ERROR_CODE_END_GAME) {
                return errorCode;
            }

            if (errorCode == ERROR_CODE_TRY_AGAIN) {
                System.out.println(TRY_AGAIN_MESSAGE);
            }

        } while (errorCode != ERROR_CODE_SUCCESS);

        return errorCode;
    }

    private int executeCommandFrom(Player player) {
        String prompt = player.getPrompt();
        System.out.print(prompt);

        Scanner input = new Scanner(System.in);
        String inputLine = input.nextLine();
        String[] arguments = inputLine.split(WHITE_SIGNS_REGEX);

        if (arguments.length == COUNT_ARGUMENTS_IN_MOVE_COMMAND_LINE
                && arguments[ACTION_COMMAND_INDEX].equals(MOVE_FIGURE_COMMAND)
                && arguments[MOVE_FIGURE_AUXILIARY_COMMAND_INDEX].equals(MOVE_FIGURE_AUXILIARY_COMMAND)) {
            return move(arguments, player);
        }

        if (arguments.length == COUNT_ARGUMENTS_IN_END_GAME_COMMAND_LINE
                && arguments[ACTION_COMMAND_INDEX].equals(END_GAME_COMMAND)) {
            return ERROR_CODE_END_GAME;
        }

        System.out.print(INVALID_COMMAND_MESSAGE);
        return ERROR_CODE_TRY_AGAIN;
    }

    private int move(String[] arguments, Player player) {
        Position fromPosition;
        Position toPosition;

        try {
            fromPosition = Position.valueOf(arguments[FROM_POSITION_INDEX]);
            toPosition = Position.valueOf(arguments[TO_POSITION_INDEX]);
        } catch (IllegalArgumentException e) {
            System.out.print(INVALID_POSITION_MESSAGE);
            return ERROR_CODE_TRY_AGAIN;
        }

        if (fromPosition.equals(toPosition)) {
            System.out.print(INVALID_POSITION_MESSAGE);
            return ERROR_CODE_TRY_AGAIN;
        }

        if (board.checkUnavailableOfFigure(fromPosition)) {
            System.out.print(EMPTY_POSITION_MESSAGE);
            return ERROR_CODE_TRY_AGAIN;
        }

        if (board.checkForForeignPossess(fromPosition, player)) {
            System.out.print(UNALLOWED_ACCESS_MESSAGE);
            return ERROR_CODE_TRY_AGAIN;
        }

        return board.moveFigure(fromPosition, toPosition, player);
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
