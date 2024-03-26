package seedu.duke;

public class ExpertSkill extends Player{
    public String difficulty = "E";
    private int power = 1;
    private final int skill = 3;
    private int exp = 0;

    public ExpertSkill(String name) {
        super(name);
    }
    public ExpertSkill(String name, int exp) {
        super(name);
        this.exp = exp;
    }
    @Override
    public String toString(String formatType) {
        switch (formatType) {
        case "cache":
            return String.format("%s | %s | %d | %d | %d", name, difficulty, power, skill, exp);
        default :
            return String.format("%s | %s | %d | %d | %d", name, difficulty, power, skill, exp);
        }
    }
}
