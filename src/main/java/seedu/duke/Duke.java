package seedu.duke;

import seedu.duke.exception.ProcessInputException;
import seedu.duke.player.BeginnerSkillPlayer;
import seedu.duke.player.Player;
import seedu.duke.saver.BeginnerSkillSaver;
import seedu.duke.saver.Saver;
import seedu.duke.stats.MatchStat;
import seedu.duke.stats.PlayerList;
import seedu.duke.ui.Ui;

import static seedu.duke.ui.Ui.curSaver;

public class Duke {


    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        Formatter.printWelcomeMsg();

        Player playerThisRound = createNewPlayer();
        Saver saverThisRound = createNewSaver();
        //Assume there is single player, can only have one player in the game
        //After account login function done,
        //the PlayerThisRound will either return a new player, or a player existed in the PlayerList

        while (Ui.getIsRunning()) {
            //@@author runxinghuan
            if (MatchStat.getIsNewMatch()) {
                Formatter.printBeforeCoinToss();
                try {
                    Ui.beginListening();
                    Ui.processInput();
                    Ui.executeCommand();
                    continue;
                } catch (ProcessInputException e) {
                    Formatter.printErrorExecutionFail();
                    continue;
                }
            }
            //@@author

            if (MatchStat.getIsMatchEnd()) {
                Formatter.printMatchResult();
                PlayerList.skillUpgrade(Ui.curPlayer);
                playerThisRound = PlayerList.playerList.get(Ui.curPlayer);
                SaverList.saverSkillUpgrade(curSaver);
                saverThisRound = SaverList.saverList.get(curSaver);
            } else if (MatchStat.getIsPlayerTurn()) {
                playerThisRound.printGoalBeforeShoot();
            } else {
                saverThisRound.printGoalBeforeSave();
            }
            try {
                Ui.beginListening();
                Ui.processInput();
                Ui.executeCommand();
            } catch (ProcessInputException e) {
                Formatter.printErrorExecutionFail();
            }
        }
    }

    //Bruno is a sample player for demonstration, you can try any level player
    private static Player createNewPlayer() {
        PlayerList.playerList.add(new BeginnerSkillPlayer("Bruno",0));
        Player playerThisRound = PlayerList.playerList.get(Ui.curPlayer);
        playerThisRound.printSelfInfo();
        MatchStat.setMatchCount(playerThisRound.matchCount);
        return playerThisRound;
    }

    //@@author ymirmeddeb
    /**
     * Creates a new saver with beginner skills, adds it to the saver list and initializes the match count based on the saver's match count.
     * This method assumes a new game round is starting, and a new saver is entering the game for the first time.
     *
     * @return The newly created {@code Saver} object that represents the current saver for the round.
     */
    private static Saver createNewSaver() {
        SaverList.saverList.add(new BeginnerSkillSaver("Mars",0));
        Saver saverThisRound = SaverList.saverList.get(curSaver);
        saverThisRound.printSelfInfo();
        MatchStat.setMatchCount(saverThisRound.matchCount);
        return saverThisRound;
    }
}
