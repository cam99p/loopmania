package unsw.loopmania;

import unsw.loopmania.Item.Slot;

public class Doggie extends BasicEnemy{
    //Construct enemy at certain position, and set all attributes
    public Doggie(PathPosition position) {
        super(position);
        //Set radii
        this.setBattleRadius(1);
        this.setSupportRadius(1);
        //set stats
        this.setAttack(50);
        this.setDefense(0);
        this.setHealth(500);
        this.setSpeed(12);
        //Set other
        this.tranced = false;
        this.isBoss = true;
    }

    //Attacks the specified target
    public void AttackTarget(MovingEntity target, int seed){
        
          
    }
}
