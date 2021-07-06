package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;

/**
 * Class which contains most of the battle logic
 * It is created whenever a battle occurs
 * It is removed after the battle ends, ending in either the heros death or rewards
 */
public class Battle {
    //Attributes
    private ArrayList<MovingEntity> allies;
    private ArrayList<MovingEntity> enemies;
    private ArrayList<MovingEntity> participants;
    private ArrayList<MovingEntity> defeated;

    public Battle(ArrayList<MovingEntity> Allies, ArrayList<MovingEntity> Enemies) {
        this.allies = Allies;
        this.enemies = Enemies;
        participants = new ArrayList<MovingEntity>();
        participants.addAll(Allies);
        participants.addAll(Enemies);
        //participants.sort(c); //Add sorting by attack speed
    }

    public void Fight(){
        //Do actual combat work here
    }

    public ArrayList<MovingEntity> getAllies() {
        return allies;
    }

    public void setAllies(ArrayList<MovingEntity> allies) {
        this.allies = allies;
    }

    public ArrayList<MovingEntity> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<MovingEntity> enemies) {
        this.enemies = enemies;
    }

    public ArrayList<MovingEntity> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<MovingEntity> participants) {
        this.participants = participants;
    }

    public ArrayList<MovingEntity> getDefeated() {
        return defeated;
    }

    public void setDefeated(ArrayList<MovingEntity> defeated) {
        this.defeated = defeated;
    }

    
    
}
