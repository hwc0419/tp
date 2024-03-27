package seedu.duke;

import seedu.duke.ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class Cache {
    private static final String CACHE_FILE_PATH = "./data/player_list.txt";
    private static final String CACHE_FILE_DIR = "./data";
    private static final String CACHE_FILE_DELIM = " \\| ";
    //stores the previous game state for each player
    public static ArrayList<Player> players = new ArrayList<>();

    /**
     * Creates a file called "player_list.txt" to cache user data in directory "data"
     * Additionally, creates a new directory called "data" if not created yet
     */
    public static void spawnCacheFile() {
        File f = new File(CACHE_FILE_PATH);
        boolean isSpawnSuccess = false;
        while (!isSpawnSuccess) {
            try {
                isSpawnSuccess = f.createNewFile();
            } catch (IOException e) {
                new File(CACHE_FILE_DIR).mkdirs();
            }
        }
    }

    /**
     * Reads user data from cache file and loads all user accounts based on read data
     *
     * @throws IllegalArgumentException If zone is <= 0.
     */
    public static void loadPlayers() throws FileNotFoundException {
        File f = new File(CACHE_FILE_PATH);
        Scanner CacheReader = new Scanner(f);
        int lineNum = 0;

        while (CacheReader.hasNext()) {
            String curLine = CacheReader.nextLine();
            String[] tokens = curLine.split(CACHE_FILE_DELIM);
            lineNum++;

            if (SyntaxAnalyser.validateCacheTokens(tokens)) {
                String name = tokens[1];
                String difficulty = tokens[2];
                int power = Integer.parseInt(tokens[3]);
                int skill = Integer.parseInt(tokens[4]);
                int exp = Integer.parseInt(tokens[5]);

                players.add(new Player(name, difficulty, power, skill, exp));
            } else {
                Formatter.printFileCorruptionError(lineNum);
                return;
            }
        }
    }

    /**
     * Overwrites the entire cache file whenever user runs the "shoot" or "new" command
     */
    public static void updateCache() {
        try {
            FileWriter fw = new FileWriter(CACHE_FILE_PATH);
            String cacheFormattedString;
            for (Player shooter : players) {

                cacheFormattedString = shooter.toString("cache");
                fw.write(Formatter.appendNewLine(cacheFormattedString));
            }
            fw.close();
        } catch (IOException e) {
            Formatter.printErrorUnknown();
        }
    }
    public static boolean isCacheEmpty() {
        File f = new File(CACHE_FILE_PATH);
        Scanner CacheReader = null;
        try {
            CacheReader = new Scanner(f);
            return CacheReader.hasNext();
        } catch (FileNotFoundException e) {
            Formatter.printErrorUnknown();
            return true;
        }
    }
}
