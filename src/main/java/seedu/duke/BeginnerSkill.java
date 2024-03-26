package seedu.duke;

public class BeginnerSkill extends Player{
    public String difficulty = "B";
    private int power = 1;
    private final int skill = 1;
    private int exp = 0;

    public BeginnerSkill(String name) {
        super(name);
    }
    public BeginnerSkill(String name, int exp) {
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
