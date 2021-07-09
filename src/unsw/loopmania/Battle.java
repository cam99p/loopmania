package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

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
        participants.sort(Comparator.comparingInt(MovingEntity::getSpeed)); //Sorts by attack speed
    }

    //Runs through the turn order, calling each entitys attack func, until battle is resolved
    public void Fight(){
        //Do actual combat work here
    }

    public ArrayList<MovingEntity> getAllies() {
        return allies;
    }

    public ArrayList<MovingEntity> getEnemies() {
        return enemies;
    }

    public ArrayList<MovingEntity> getParticipants() {
        return participants;
    }

    public ArrayList<MovingEntity> getDefeated() {
        return defeated;
    }

    //Removes an enemy or ally (not hero) from participants and adds it to defeated
    public void defeatEntity(MovingEntity defeated){
        //Called when an enemy or ally (not hero) is defeated
    }

    //Based on priority, returns an ally for an enemy to attack
    public MovingEntity getTargetAlly(){
        return null;
    }

    //Based on priority, returns an enemy for an ally to attack
    public MovingEntity getTargetEnemy(){
        return null;
    }
    
}
