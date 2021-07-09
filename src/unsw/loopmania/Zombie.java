package unsw.loopmania;

public class Zombie extends BasicEnemy implements Attack{
    //Construct enemy at certain position, and set all attributes
    public Zombie(PathPosition position) {
        super(position);
        //Set radii
        this.setBattleRadius(2);
        this.setSupportRadius(2);
        //Set stats
        this.setAttack(10);
        this.setDefense(0);
        this.setHealth(100);
        this.setSpeed(5);
    }

    //Attacks the specified target
    public void AttackTarget(MovingEntity target, int seed){

    }

}
