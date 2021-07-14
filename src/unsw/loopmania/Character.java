package unsw.loopmania;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity{

    private boolean doubleDamage;

    public Character(PathPosition position) {
        super(position);
        //Set stats
        setAttack(5);
        setDefense(0);
        setHealth(200);
        setSpeed(8);
        canBlock = false;
        this.canRevive = false;
        doubleDamage = false;
    }

    //Attacks the specified target
    public void AttackTarget(MovingEntity target, int seed){
        int damage = this.getAttack() - target.getDefense();
        target.setHealth(target.getHealth() - damage);
    }

    public void setDoubleDamage(boolean bool) {
        doubleDamage = bool;
    }

    public boolean getDoubleDamage() {
        return doubleDamage;
    }
}
