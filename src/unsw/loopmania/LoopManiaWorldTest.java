package unsw.loopmania;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.javatuples.Pair;

public class LoopManiaWorldTest {
    @Test
    public void TestGatherAllies(){
        ArrayList<Pair<Integer, Integer>> dummyPath = new ArrayList<>(Arrays.asList(new Pair<>(0,0), new Pair<>(0,1), new Pair<>(0,2), new Pair<>(1,2),
                                                                new Pair<>(2,2), new Pair<>(2,1), new Pair<>(2,0), new Pair<>(1,0)));
        LoopManiaWorld d = new LoopManiaWorld(3, 3, dummyPath);
        PathPosition dummyPos = new PathPosition(0, dummyPath);
        Character dummyChar = new Character(dummyPos);
        d.setCharacter(dummyChar);
        Ally dummyAlly = new Ally(dummyPos);
        dummyChar.AddAlly(dummyAlly);
        TowerCard towerCard = d.loadTowerCard();
        Building towerBuilding = d.convertCardToBuildingByCoordinates(towerCard.getX(), towerCard.getY(), 1, 1);

        assertTrue(d.gatherAllies().size() == 3);
    }

    @Test
    public void TestGatherEnemies(){
        ArrayList<Pair<Integer, Integer>> dummyPath = new ArrayList<>(Arrays.asList(new Pair<>(0,0), new Pair<>(0,1), new Pair<>(0,2), new Pair<>(1,2),
                                                                new Pair<>(2,2), new Pair<>(2,1), new Pair<>(2,0), new Pair<>(1,0)));
        LoopManiaWorld d = new LoopManiaWorld(3, 3, dummyPath);
        PathPosition dummyPos = new PathPosition(0, dummyPath);
        Character dummyChar = new Character(dummyPos);
        d.setCharacter(dummyChar);

        //Vampire within support range
        PathPosition dummyPos2 = new PathPosition(3, dummyPath);
        Vampire dummyVamp = new Vampire(dummyPos2);
        d.addEnemy(dummyVamp);
        //Zombie within battle range
        PathPosition dummyPos3 = new PathPosition(1, dummyPath);
        Zombie dummyZombie = new Zombie(dummyPos3);
        d.addEnemy(dummyZombie);
        //Slug too far away
        PathPosition dummyPos4 = new PathPosition(5, dummyPath);
        Slug dummySlug = new Slug(dummyPos4);
        d.addEnemy(dummySlug);

        assertTrue(d.gatherEnemies().size() == 2);
    }

    @Test
    public void TestRunBattles(){
        //Path
        ArrayList<Pair<Integer, Integer>> dummyPath = new ArrayList<>(Arrays.asList(new Pair<>(0,0), new Pair<>(0,1), new Pair<>(0,2), new Pair<>(1,2),
                                                                new Pair<>(2,2), new Pair<>(2,1), new Pair<>(2,0), new Pair<>(1,0)));

        //World
        LoopManiaWorld d = new LoopManiaWorld(3, 3, dummyPath);

        //Character
        PathPosition dummyPos = new PathPosition(0, dummyPath);
        Character dummyChar = new Character(dummyPos);
        d.setCharacter(dummyChar);

        //Slug
        Slug dummySlug = new Slug(dummyPos);
        d.addEnemy(dummySlug);

        //Run battles
        List<BasicEnemy> defeated = d.runBattles();

        //Hero should always beat a slug, so check that there is 1 defeated enemy
        assertTrue(defeated.size() == 1);
        //The only memeber of the list should be the dummy slug
        assertTrue(defeated.get(0) == dummySlug);
    }
}
