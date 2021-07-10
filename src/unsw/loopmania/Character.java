package unsw.loopmania;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity {
    Stats characterStats;

    // TODO = potentially implement relationships between this class and other classes
    public Character(PathPosition position) {
        super(position);
        this.characterStats = new Stats();
       /* this.attack = 5;
        this.defense = 0;
        this.health = 200;
        this.gold = 0;
        this.canBlock = false;
        this.canRevive = false; */
    }

      
}
