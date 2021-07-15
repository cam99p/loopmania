package unsw.loopmania;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity{

    private boolean doubleDamage;
    private List<Ally> allies;

    public Character(PathPosition position) {
        super(position);
        //Set stats
        setAttack(5);
        setDefense(0);
        setHealth(200);
        setSpeed(8);
        this.canBlock = false;
        this.canRevive = false;
        this.doubleDamage = false;
        this.allies = new ArrayList<>();
    }

    //Attacks the specified target
    public void AttackTarget(MovingEntity target, int seed){
        int damage = this.getAttack() - target.getDefense();

        //TODO: Account for stake vs vampire case

        if (doubleDamage){
            damage = damage*2;
        }
        target.setHealth(target.getHealth() - damage);
    }

    public void setDoubleDamage(boolean bool) {
        doubleDamage = bool;
    }

    public boolean getDoubleDamage() {
        return doubleDamage;
    }

    //TODO: Passing through barracks should make a new ally and call this
    //Adds an ally to the list of the charcters allies, if there is room
    public void AddAlly(Ally ally){
        //Should the ally be made here, or passed in?
        //Assuming passed in for now
        if (allies.size() < 3){
            allies.add(ally);
        }
    }

    //Returns list of allies. Should be contain 0 - 3 allies
    public List<Ally> getAllies() {
        return allies;
    }
    
}
