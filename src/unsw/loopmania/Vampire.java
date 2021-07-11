package unsw.loopmania;

import java.util.Random;

public class Vampire extends BasicEnemy implements Attack{
    //Unique Attributes
    private int frenzyTimer;

    //Construct enemy at certain position, and set all attributes
    public Vampire(PathPosition position) {
        super(position);
        //Set radii
        this.setBattleRadius(2);
        this.setSupportRadius(3);
        //Set stats
        this.setAttack(20);
        this.setDefense(0);
        this.setHealth(200);
        this.setSpeed(10);
    }

    //Attacks the specified target
    public void AttackTarget(MovingEntity target, int seed){

    }

    //For when it scores a critical hit
    public void startFrenzy() {

    }

    //When critical hit buff expires
    public void endFrenzy() {

    }

    //Check how many turns left in vampires frenzy
    public int getFrenzyTimer() {
        return frenzyTimer;
    }

    //Set how many turns a vamp should frenzy for
    public void setFrenzyTimer(int frenzyTimer) {
        this.frenzyTimer = frenzyTimer;
    }

    /**
     * move a vampire (10% up path, 10% down path, 80% not moving)
     */
    public void move(){
        int directionChoice = (new Random()).nextInt(9);
        if (directionChoice == 3){
            moveUpPath();
        }
        else if (directionChoice == 6){
            moveDownPath();
        }
    }
}
