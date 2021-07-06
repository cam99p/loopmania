package unsw.loopmania;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import org.javatuples.Pair;

public class VampireTest {
    @Test
    void TestFrenzy(){
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<Pair<Integer, Integer>>();
        PathPosition dummyPos = new PathPosition(0, dummyPath);
        Vampire dummyVamp = new Vampire(dummyPos);
        dummyVamp.startFrenzy();

        assertTrue(dummyVamp.getAttack() > 5); //TODO: change 5 to whatever the vampires attack is
        assertTrue(dummyVamp.getAttack() < 10); //TODO: change 10 to whatever the upper limit of the frenzy buff is
    }

    @Test
    void TestEndFrenzy(){
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<Pair<Integer, Integer>>();
        PathPosition dummyPos = new PathPosition(0, dummyPath);
        Vampire dummyVamp = new Vampire(dummyPos);
        dummyVamp.startFrenzy();
        dummyVamp.endFrenzy();

        assertTrue(dummyVamp.getAttack() == 5); //TODO: change 5 to whatever the vampires attack is
    }

    @Test
    void TestAttack(){
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<Pair<Integer, Integer>>();
        PathPosition dummyPos = new PathPosition(0, dummyPath);
        Vampire dummyVamp = new Vampire(dummyPos);
        Character dummyChar = new Character(dummyPos);

        dummyVamp.AttackTarget(dummyChar);

        assertTrue(dummyChar.getHealth() == 195); //TODO: change 195 to whatever the heros health - vampires attack is
    }
}
