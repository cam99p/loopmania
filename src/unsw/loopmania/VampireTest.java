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

        assertTrue(dummyVamp.getAttack() >= 25); 
        assertTrue(dummyVamp.getAttack() <= 30); 
    }

    @Test
    void TestEndFrenzy(){
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<Pair<Integer, Integer>>();
        PathPosition dummyPos = new PathPosition(0, dummyPath);
        Vampire dummyVamp = new Vampire(dummyPos);
        dummyVamp.startFrenzy();
        dummyVamp.endFrenzy();

        assertTrue(dummyVamp.getAttack() == 20); 
    }

    @Test
    void TestAttack(){
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<Pair<Integer, Integer>>();
        PathPosition dummyPos = new PathPosition(0, dummyPath);
        Vampire dummyVamp = new Vampire(dummyPos);
        Character dummyChar = new Character(dummyPos);

        dummyVamp.AttackTarget(dummyChar, 0);

        assertTrue(dummyChar.getHealth() == 180); 
    }

    @Test
    void TestCriticalAttack(){
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<Pair<Integer, Integer>>();
        PathPosition dummyPos = new PathPosition(0, dummyPath);
        Vampire dummyVamp = new Vampire(dummyPos);
        Character dummyChar = new Character(dummyPos);

        dummyVamp.AttackTarget(dummyChar, 10);

        assertTrue(dummyVamp.getFrenzyTimer() != 0); 
    }
}
