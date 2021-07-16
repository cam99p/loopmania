package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * The moving entity
 */
public abstract class MovingEntity extends Entity implements Attack, Stats{
    //Attributes
    private int attack;
    private int defense;
    private int health;
    private int speed;
    private PathPosition position;
    protected Boolean canBlock;
    protected Boolean canRevive;

    /**
     * object holding position in the path
     */
    
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

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void modifyHealth(int value) {
        health += value;
    }

    // Modifies value to character attack stat
    public void modifyAttack(int value) {
        attack += value; 
    }

    // Modifies value to character defense stat
    public void modifyDefense(int value) {
        defense += value;
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

    //Used when the MovingEntity takes damage
    public void damageHealth(int value) {
        health -= value;
    }

    //Used when the entity is attacked, and will try to block
    public boolean tryBlock(int seed) {
        if (this.canBlock && seed <= 2){
            return true;
        } else {
            return false;
        }
    }

}
