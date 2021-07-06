package unsw.loopmania;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import org.javatuples.Pair;

public class BattleTest {
    //A hero should always beat a slug, so the initial test will use the slug enemy
    @Test
    void TestCombat(){
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<Pair<Integer, Integer>>();
        PathPosition dummyPos = new PathPosition(0, dummyPath);
        Slug dummySlug = new Slug(dummyPos);
        Character dummyChar = new Character(dummyPos);

        ArrayList<MovingEntity> allies = new ArrayList<MovingEntity>();
        allies.add(dummyChar);
        ArrayList<MovingEntity> enemies = new ArrayList<MovingEntity>();
        enemies.add(dummySlug);

        Battle dummyBattle = new Battle(allies, enemies);

        assertTrue(dummyBattle.getParticipants().size() == 2); //Check that both hero and slug are in the battle order

        dummyBattle.Fight();

        assertTrue(dummyBattle.getAllies().size() == 1); //Check that the hero is still alive
        assertTrue(dummyBattle.getDefeated().size() == 1); //Check that the slug has been defeated
    }
}
