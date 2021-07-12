package unsw.loopmania;

import java.util.Random;

public class Slug extends BasicEnemy implements Attack {
    //Construct enemy at certain position, and set all attributes
    public Slug(PathPosition position) {
        super(position);
        //Set radii
        this.setBattleRadius(1);
        this.setSupportRadius(1);
        //set stats
        this.setAttack(5);
        this.setDefense(0);
        this.setHealth(50);
        this.setSpeed(7);
    }

    //Attacks the specified target
    public void AttackTarget(MovingEntity target, int seed){
        int damage = this.getAttack() - target.getDefense();
        target.setHealth(target.getHealth() - damage);
    }

    /**
     * move a slug (50% up path 50% down path)
     * slug will always move
     */
    public void move(){
        int directionChoice = (new Random()).nextInt(1);
        if (directionChoice == 0){
            moveUpPath();
        }
        else if (directionChoice == 1){
            moveDownPath();
        }
    }
}
