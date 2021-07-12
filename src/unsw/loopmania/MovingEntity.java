package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * The moving entity
 */
public abstract class MovingEntity extends Entity implements Stats {

    /**
     * object holding position in the path
     */
    private PathPosition position;
    protected int attack;
    protected int defense;
    protected int speed;
    protected int health;
    protected int gold;
    protected int experience;
    protected Boolean canBlock;
    protected Boolean canRevive;

    /**
     * Create a moving entity which moves up and down the path in position
     * @param position represents the current position in the path
     */
    public MovingEntity(PathPosition position) {
        super();
        this.position = position;
        
    }

    /**
     * move clockwise through the path
     */
    public void moveDownPath() {
        position.moveDownPath();
    }

    /**
     * move anticlockwise through the path
     */
    public void moveUpPath() {
        position.moveUpPath();
    }

    public SimpleIntegerProperty x() {
        return position.getX();
    }

    public SimpleIntegerProperty y() {
        return position.getY();
    }

    public int getX() {
        return x().get();
    }

    public int getY() {
        return y().get();
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

    // Modifies value of character gold stat
    public void modifyExperience(int value) {
        this.experience = value; 
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
