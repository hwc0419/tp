package seedu.duke;

import seedu.duke.exception.ProcessInputException;
import seedu.duke.ui.Ui;

import java.io.FileNotFoundException;

public class Duke {

    public Duke() {
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        Formatter.printWelcomeMsg();

        try {
            Cache.loadPlayers();
        } catch (FileNotFoundException e) {
            Cache.spawnCacheFile();
        }

        if (Ui.currentPlayer == null) {
            Formatter.printUserNotFound("Guest user name");
        }

        while (Ui.getIsRunning()) {
            try {
                Ui.beginListening();
                Ui.processInput();
                Ui.executeCommand();
            } catch (ProcessInputException e) {
                Formatter.printErrorExecutionFail();
            }
        }
    }
}

