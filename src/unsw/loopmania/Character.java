package unsw.loopmania;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity {

    // TODO = potentially implement relationships between this class and other classes
    public Character(PathPosition position) {
        super(position);
        this.attack = 5;
        this.defense = 0;
        this.health = 100;
        this.gold = 0;
        this.experience = 0;
        this.speed = 8;
        this.canBlock = false;
        this.canRevive = false;
    }

    



      
}
