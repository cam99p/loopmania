package unsw.loopmania;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;
import java.util.Random;

/**
 * Class which contains most of the battle logic
 * It is created whenever a battle occurs
 * It is removed after the battle ends, ending in either the heros death or rewards
 */
public class Battle {
    //Attributes
    private List<MovingEntity> allies;
    private List<MovingEntity> enemies;
    private List<MovingEntity> participants;
    private List<MovingEntity> defeated;

    public Battle(List<MovingEntity> Allies, List<MovingEntity> Enemies) {
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

        int i = 0;
        //While there are enemies remaining, fight
        while (enemies.size() != 0){
            var attacker = participants.get((i%participants.size()));

            //Check that its still an active combatenant
            if (attacker.getHealth() <= 0){
                //If its dead, skip it
                i++;
                continue;
            }

            Random rand = new Random();
            int seed = rand.nextInt(11); //Random number between 1 and 10 inclusive

            //Its either an allied force
            if (allies.contains(attacker)){
                MovingEntity target = getTargetEnemy();
                attacker.AttackTarget(target, seed);

                //If the attack kills the enemy, defeat it
                if (target.getHealth() < 0){
                    defeatEntity(target);
                }
            } 
            //Or an Enemy
            else if (enemies.contains(attacker)){
                MovingEntity target = getTargetAlly();
                attacker.AttackTarget(target, seed);
                //Disposal of hero is handled seperately
                //Ally must be implemented before we consider itss disposal here
            }
            else {
                //This shouldn't happen
                //Log error? Or remove final else statement?
            }

            //Increment to move onto next entity in turn order
            i++;
        }
    }

    public List<MovingEntity> getAllies() {
        return allies;
    }

    public List<MovingEntity> getEnemies() {
        return enemies;
    }

    public List<MovingEntity> getParticipants() {
        return participants;
    }

    public List<MovingEntity> getDefeated() {
        return defeated;
    }

    //Returns the defeated enemies as BasicEnemy list, as is required by the world funcs
    public List<BasicEnemy> getDefeatedEnemies(){
        List<BasicEnemy> result = new ArrayList<BasicEnemy>();
        for (MovingEntity e: getDefeated()) {
            result.add((BasicEnemy)e);
        }
        return result;
    }

    //Removes an enemy from participants and enemies and adds it to defeated
    //Ally gets handled differently, and hero leads to a game over, no need to resolve anything else
    public void defeatEntity(MovingEntity defeatedEnemy){
        //participants.remove(defeatedEnemy); 
        //does not remove from particpants to keep loop through turn order stable
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
