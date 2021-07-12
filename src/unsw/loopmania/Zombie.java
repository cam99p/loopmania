package unsw.loopmania;

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
        target.setHealth(target.getHealth() - damage);

        //Critical
        if (seed == 10){
            //Cannot be implemented until ally is
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
