package seedu.duke;

public class MediumSkill extends Player{
    public String difficulty = "M";
    private int power = 1;
    private final int skill = 2;
    private int exp = 0;

    public MediumSkill(String name) {
        super(name);
    }
    public MediumSkill(String name, int exp) {
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
