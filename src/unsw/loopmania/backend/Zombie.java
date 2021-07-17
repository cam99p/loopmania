package unsw.loopmania.backend;

import java.util.Random;

public class Zombie extends BasicEnemy{
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
        int damage = this.getAttack() - target.getDefense();

        //If the target enityt (currently only main character) successfully blocks, reduce damage to 0
        if (target.tryBlock(seed)){
            damage = 0;
        }

        target.damageHealth(damage);

        //Critical
        if (seed == 10 && target instanceof Ally){
            Ally ally = (Ally)target;
            ally.setZombified(true);
        }
    }

    /**
     * move a vampire (20% up path, 20% down path, 60% not moving)
     */
    public void move(){
        int directionChoice = (new Random()).nextInt(4);
        if (directionChoice == 1){
            moveUpPath();
        }
        else if (directionChoice == 3){
            moveDownPath();
        }
    }
}
