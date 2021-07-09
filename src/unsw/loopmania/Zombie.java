package unsw.loopmania;

/**
 * represents a zombie
 */
public class Zombie extends BasicEnemy {
    public Zombie(PathPosition position, String name, int health, int damage) {
        super(position, name, 50, 5);
    }
}
