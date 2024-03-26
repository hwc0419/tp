package seedu.duke;

public class Player {
    public String name;
    public String difficulty;
    private int power;
    private int skill;
    private int exp;
    public Player(String name) {
        this.name = name;
    }
    public String toString(String formatType) {
        switch (formatType) {
        case "cache":
            return String.format("%s | %s | %d | %d | %d", name, difficulty, power, skill, exp);
        default :
            return String.format("%s | %s | %d | %d | %d", name, difficulty, power, skill, exp);
        }
    }
    public void setExpGain(int exp) {
        this.exp += exp;
    }

    public int getExp() {
        return exp;
    }
}
