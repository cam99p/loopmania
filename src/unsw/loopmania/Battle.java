package unsw.loopmania;

import java.util.ArrayList;
import java.util.Collections;
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
        defeated = new ArrayList<MovingEntity>();
        participants.addAll(Allies);
        participants.addAll(Enemies);
        participants.sort(Comparator.comparingInt(MovingEntity::getSpeed)); //Sorts by attack speed, lowest to highest
        Collections.reverse(participants); //reverses list to get highest to lowest
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

    //Removes an enemy from participants and enemies and adds it to defeated
    //Ally gets handled differently, and hero leads to a game over, no need to resolve anything else
    public void defeatEntity(MovingEntity defeatedEnemy){
        participants.remove(defeatedEnemy);
        enemies.remove(defeatedEnemy);
        defeated.add(defeatedEnemy);
    }

    //Based on priority, returns an ally for an enemy to attack
    public MovingEntity getTargetAlly(){
        //requires ally implementation, but essentially its ally first, hero second
        //towers cannot be targeted
        return null;
    }

    //Based on priority, returns an enemy for an ally to attack
    //In this case, priotity is simply the last element of the list
    //Since that means it was either supporting or is the main enemy if its last
    public MovingEntity getTargetEnemy(){
        if (enemies.size() == 0){
            return null;
        }
        return enemies.get(enemies.size() - 1);
    }
    
}
