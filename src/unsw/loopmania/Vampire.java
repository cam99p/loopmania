package unsw.loopmania;

/**
 * represents a zombie
 */
public class Vampire extends BasicEnemy {
    public Vampire(PathPosition position, String name, int health, int damage) {
        super(position, name, 100, 10);
    }
}
