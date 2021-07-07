package unsw.loopmania;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import org.javatuples.Pair;

public class LoopManiaWorldTest {
    @Test
    public void TestGatherAllies(){
        LoopManiaWorld d = new LoopManiaWorld(3, 3, new ArrayList<>());
        PathPosition dummyPos = new PathPosition(0, new ArrayList<>());
        Character dummyChar = new Character(dummyPos);
        d.setCharacter(dummyChar);
        //Add ally
        //Add tower
        //Add tower too far away from battle
        //Cannot be completed till ally and tower are implemented
        assertTrue(d.gatherAllies().size() == 3);

    }

    @Test
    public void TestGatherEnemies(){
        LoopManiaWorld d = new LoopManiaWorld(3, 3, new ArrayList<>());
        PathPosition dummyPos = new PathPosition(0, new ArrayList<>());
        Character dummyChar = new Character(dummyPos);
        d.setCharacter(dummyChar);

        //Vampire within support range
        PathPosition dummyPos2 = new PathPosition(3, new ArrayList<>());
        Vampire dummyVamp = new Vampire(dummyPos2);
        d.addEnemy(dummyVamp);
        //Zombie within battle range
        PathPosition dummyPos3 = new PathPosition(1, new ArrayList<>());
        Zombie dummyZombie = new Zombie(dummyPos3);
        d.addEnemy(dummyZombie);
        //Slug too far away
        PathPosition dummyPos4 = new PathPosition(5, new ArrayList<>());
        Slug dummySlug = new Slug(dummyPos4);
        d.addEnemy(dummySlug);

        assertTrue(d.gatherEnemies().size() == 2);
    }

    @Test
    public void TestRunBattles(){
        //World
        LoopManiaWorld d = new LoopManiaWorld(3, 3, new ArrayList<>());

        //Character
        PathPosition dummyPos = new PathPosition(0, new ArrayList<>());
        Character dummyChar = new Character(dummyPos);
        d.setCharacter(dummyChar);

        //Slug
        PathPosition dummyPos2 = new PathPosition(5, new ArrayList<>());
        Slug dummySlug = new Slug(dummyPos2);
        d.addEnemy(dummySlug);

        //Run battles
        List<BasicEnemy> defeated = d.runBattles();

        //Hero should always beat a slug, so check that there is 1 defeated enemy
        assertTrue(defeated.size() == 1);
    }
}
