package unsw.loopmania;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity{
    public Character(PathPosition position) {
        super(position);
        //Set stats
        this.attack = 5;
        this.defense = 0;
        this.health = 200;
        this.speed = 8;
        this.canBlock = false;
        this.canRevive = false;
    }

    //Attacks the specified target
    public void AttackTarget(MovingEntity target, int seed){
        int damage = this.getAttack() - target.getDefense();
        target.setHealth(target.getHealth() - damage);

}
