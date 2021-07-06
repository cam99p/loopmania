package unsw.loopmania;

public class Vampire extends BasicEnemy implements Attack{
    //Unique Attributes
    private int frenzyTimer;

    //Construct enemy at certain position, and set all attributes
    public Vampire(PathPosition position) {
        super(position);
        //Set radii and stats
    }

    //Attacks the specified target
    public void AttackTarget(MovingEntity target){

    }

    //For when it scores a critical hit
    public void startFrenzy() {

    }

    //When critical hit buff expires
    public void endFrenzy() {

    }
}
