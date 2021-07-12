package unsw.loopmania;

import java.util.Random;

public class Vampire extends BasicEnemy{
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
        int damage = this.getAttack() - target.getDefense();
        target.setHealth(target.getHealth() - damage);

        //Critical
        if (seed == 10){
            this.startFrenzy();
        }

        //If frenzy buff has 0 turns left
        if (frenzyTimer == 0){
            endFrenzy();
        }
        //Deincrement frenzy
        else {
            frenzyTimer--; 
        }
    }

    //For when it scores a critical hit
    public void startFrenzy() {
        Random rand = new Random();
        frenzyTimer = rand.nextInt(4); //Generates a number between 1 and 3
        this.setAttack(this.getAttack() + rand.nextInt(7) + 4); //Increases attack by 5 to 10
    }

    //When critical hit buff expires
    public void endFrenzy() {
        this.setAttack(20); //Reset it to its base attack
    }

    //Returns how many tunrs a vamp has left to frenzy
    //If 0, the vamp is not in frenzy
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
