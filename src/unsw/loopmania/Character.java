package unsw.loopmania;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity implements Attack{
    // TODO = potentially implement relationships between this class and other classes
    public Character(PathPosition position) {
        super(position);
        //Set stats
        this.setAttack(5);
        this.setDefense(0);
        this.setHealth(200);
        this.setSpeed(8);
    }

    //Attacks the specified target
    public void AttackTarget(MovingEntity target, int seed){
        int damage = this.getAttack() - target.getDefense();
        target.setHealth(target.getHealth() - damage);
    }
    
}
