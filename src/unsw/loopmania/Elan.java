package unsw.loopmania;

public class Elan extends BasicEnemy{
    //Construct enemy at certain position, and set all attributes
    public Elan(PathPosition position) {
        super(position);
        //Set radii
        this.setBattleRadius(1);
        this.setSupportRadius(1);
        //set stats
        this.setAttack(0);
        this.setDefense(0);
        this.setHealth(750);
        this.setSpeed(9);
        //Set other
        this.tranced = true; //This is to switch Elan to target the enemies for his healing
                            //Alternatviely, could check if its an instance of Elan in Battle
        this.canBlock = false;
        this.stunned = false;
        this.isBoss = true;
    }

    //Attacks the specified target
    public void AttackTarget(MovingEntity target, int seed){
        
        //We do not deincrement Elans trance timer
        target.modifyHealth(seed);
    }
}
