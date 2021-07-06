package unsw.loopmania;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import org.javatuples.Pair;


public class CharacterTest {
    @Test
    void TestAttack(){
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<Pair<Integer, Integer>>();
        PathPosition dummyPos = new PathPosition(0, dummyPath);
        Slug dummySlug = new Slug(dummyPos);
        Character dummyChar = new Character(dummyPos);

        dummyChar.AttackTarget(dummySlug);

        assertTrue(dummySlug.getHealth() == 45); //TODO: change 195 to whatever the slugs health - heros unarmed attack
    }
}
