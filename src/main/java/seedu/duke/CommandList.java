package seedu.duke;

import seedu.duke.ui.Ui;
import seedu.duke.ai.Ai;
import java.util.logging.Logger;

import java.io.FileNotFoundException;

public enum CommandList {
    BYE, SHOOT, PENALTY, LOAD, NEW,
    //insert new user command name here
    ;

    private static final Logger logger = Logger.getLogger(Formatter.class.getName());
    /**
     * Exits the program
     */
    public static void executeBye() {
        Formatter.printGoodbyeMsg();
        Ui.setIsRunning(false);
    }

    public static boolean goalCheck(int userInput, int aiInput) {
        assert userInput >= 0 && userInput <= 2 :
                "Illegal userInput generated!";
        assert aiInput >= 0 && aiInput <= 2 :
                "Illegal aiInput generated!";
        return userInput != aiInput;
    }

    public static boolean executeShoot(String[] readArgumentTokens) {
        String selectedDirection = readArgumentTokens[0];
        int selectedDirectionIndex = Integer.parseInt(selectedDirection);
        return goalCheck(Ai.getAiDirection(), selectedDirectionIndex);
    }

    public static void executePenalty() {
        Penalty.executePenalty();
    }

    public static void executeLoad(String[] readArgumentTokens) {
        String selectedUser = readArgumentTokens[0];
        for (int i = 0; i < Cache.players.size(); i++) {
            if (Cache.players.get(i).name.equals(selectedUser)) {
                Ui.currentPlayer = Cache.players.get(i);
                Formatter.printWelcomeUser(selectedUser);
                System.out.println("Current Difficulty: " + Ui.currentPlayer.difficulty);
                Ui.roundCount = Ui.currentPlayer.getExp() + 1;
                Formatter.printGoalBeforeShot(Ui.roundCount);
                return;
            }
        }
        System.out.println("Load error, user " + "\"" + selectedUser + "\"" + " not found");
    }

    public static void executeNew(String[] readArgumentTokens) {
        String newUserName = readArgumentTokens[0];
        Player newUser = new Player(newUserName, "Beginner", 1, 1, 0);
        Cache.players.add(newUser);
    }
    //insert new command here
}

