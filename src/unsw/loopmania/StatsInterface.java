package unsw.loopmania;

/**
 * An interface for methods to modify stats of a character
 */
public interface StatsInterface {
    // Modifies health to 'current health'
    public void modifyHealth(int value);
    // Modifies value to character attack stat
    public void modifyAttack(int value);
    // Modifies value to character defense stat
    public void modifyDefense(int value);
    // Modifies value of character gold stat
    public void modifyGold(int value);
    // Modifies value of character experience stat
    public void modifyExperience(int value);
    // Makes it possible for the character to block
    public void setBlocking();
    // Makes it impossible for the character to block
    public void unsetBlocking();
    // Makes it possible for the character to be revived
    public void setRevive();
    // Makes it impossible for the character to be revived
    public void unsetRevive();
}