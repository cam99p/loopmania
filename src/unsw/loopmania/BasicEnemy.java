package unsw.loopmania;

import java.util.Random;

/**
 * a basic form of enemy in the world
 */
public class BasicEnemy extends MovingEntity {

    private String name;
    private int health;
    private int damage;

    // modify this, and add additional forms of enemy
    public BasicEnemy(PathPosition position, String name, int health, int damage) {
        super(position);
        this.name = name;
        this.health = health;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    /**
     * Setter for health
     * @param health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    /**
     * Setter for damage
     * @param damage
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * move the enemy
     */
    public void move(){
        // modify this, since this implementation doesn't provide the expected enemy behaviour
        // this basic enemy moves in a random direction... 25% chance up or down, 50% chance not at all...
        int directionChoice = (new Random()).nextInt(2);
        if (directionChoice == 0){
            moveUpPath();
        }
        else if (directionChoice == 1){
            moveDownPath();
        }
    }
}
