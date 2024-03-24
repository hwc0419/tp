package seedu.duke;

import seedu.duke.ui.Ui;
import seedu.duke.ai.Ai;

public enum CommandList {
    BYE, PENALTY
    //insert new user command name here
    ;

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

    public static void executePenalty() {
        Penalty.executePenalty();
    }
    
    //insert new command here
}

