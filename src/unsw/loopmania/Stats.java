package unsw.loopmania;

public class Stats implements StatsInterface {
    private int attack;
    private int defense;
    private int speed;
    private int health;
    private int gold;
    private Boolean canBlock;
    private Boolean canRevive;

    public Stats() {
        this.attack = 5;
        this.defense = 0;
        this.health = 200;
        this.gold = 0;
        this.speed = 8;
        this.canBlock = false;
        this.canRevive = false;
    }

    public void modifyHealth(int value) {
        this.health = value;
    }

    // Modifies value to character attack stat
    public void modifyAttack(int value) {
        this.attack = value; 
    }

    // Modifies value to character defense stat
    public void modifyDefense(int value) {
        this.defense = value;
    }

    // Modifies value of character gold stat
    public void modifyGold(int value) {
        this.gold = value; 
    }

    // Makes it possible for the character to block
    public void setBlocking() {
        this.canBlock = true;
    }

    // Makes it impossible for the character to block
    public void unsetBlocking() {
        this.canBlock = false; 
    }

    // Makes it possible for the character to be revived
    public void setRevive() {
        this.canRevive = true;
    }
    
    // Makes it impossible for the character to be revived
    public void unsetRevive() {
        this.canRevive = false; 
    }

    public int getHealth() {
        return health;
    }

    public int getGold() {
        return gold;
    }
}
