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

    public static void executeShoot(String[] readArgumentTokens) {
        String selectedDirection = readArgumentTokens[0];
        int selectedDirectionIndex = Integer.parseInt(selectedDirection);
        boolean isScoreGoal = goalCheck(Ai.getAiDirection(), selectedDirectionIndex);

        Formatter.printGoalAfterShot(isScoreGoal);
        if (isScoreGoal) {
            Ui.currentPlayer.setExpGain(1);
            System.out.println("You gained one experience point. Current exp: " + Ui.currentPlayer.getExp());
            if (Ui.currentPlayer.getExp() == 20) {
                System.out.println("Congrats, you leveled up to Medium");
                Ui.currentPlayer = new MediumSkill(Ui.currentPlayer.name);
                Cache.players.set(0, Ui.currentPlayer);
            } else if (Ui.currentPlayer.getExp() == 50) {
                System.out.println("Congrats, you leveled up to Expert");
                Ui.currentPlayer = new ExpertSkill(Ui.currentPlayer.name);
                Cache.players.set(0, Ui.currentPlayer);
            }
        }

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
                Formatter.printGoalBeforeShot(Ui.roundCount);
                return;
            }
        }
    }

    public static void executeNew(String[] readArgumentTokens) {
        String newUserName = readArgumentTokens[0];
        BeginnerSkill newUser = new BeginnerSkill(newUserName);
        Cache.players.add(newUser);
    }
    //insert new command here
}

