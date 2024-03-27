package seedu.duke;

public class Player {
    public static int totalPlayerCount = 0;
    private int id;
    public String name;
    public String difficulty;
    private int power;
    private int skill;
    private int exp;
    public Player(String name, String difficulty, int power, int skill, int exp) {
        this.id = totalPlayerCount;
        this.name = name;
        this.difficulty = difficulty;
        this.power = power;
        this.skill = skill;
        this.exp = exp;
        totalPlayerCount++;
    }
    public String toString(String formatType) {
        switch (formatType) {
        case "cache":
            return String.format("%d | %s | %s | %d | %d | %d", id, name, difficulty, power, skill, exp);
        default :
            return String.format("%d | %s | %s | %d | %d | %d", id, name, difficulty, power, skill, exp);
        }
    }
    public String getName() {
        return name;
    }
    public String getDifficulty() {
        return difficulty;
    }
    public int getPower() {
        return power;
    }
    public int getSkill() {
        return skill;
    }
    public int getExp() {
        return exp;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    public void setPower(int power) {
        this.power = power;
    }
    public void setSkill(int skill) {
        this.skill = skill;
    }
    public void setExp(int exp) {
        this.exp = exp;
    }
    public void setExpGain(int exp) {
        this.exp += exp;
    }
}
