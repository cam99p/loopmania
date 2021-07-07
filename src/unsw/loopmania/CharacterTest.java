package unsw.loopmania;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import org.javatuples.Pair;

public class CharacterTest {
    @Test
    void TestUnarmedAttack(){
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<Pair<Integer, Integer>>();
        PathPosition dummyPos = new PathPosition(0, dummyPath);
        Slug dummySlug = new Slug(dummyPos);
        Character dummyChar = new Character(dummyPos);

        dummyChar.AttackTarget(dummySlug, 0);

        assertTrue(dummySlug.getHealth() == 45); 
    }

    void TestSwordAttack(){
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<Pair<Integer, Integer>>();
        PathPosition dummyPos = new PathPosition(0, dummyPath);
        Slug dummySlug = new Slug(dummyPos);
        Character dummyChar = new Character(dummyPos);

        dummyChar.AttackTarget(dummySlug, 0);

        assertTrue(dummySlug.getHealth() == 30); 
    }

    void TestStakeAttack(){
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<Pair<Integer, Integer>>();
        PathPosition dummyPos = new PathPosition(0, dummyPath);
        Slug dummySlug = new Slug(dummyPos);
        Character dummyChar = new Character(dummyPos);

        dummyChar.AttackTarget(dummySlug, 0);

        assertTrue(dummySlug.getHealth() == 38); 
    }

    void TestStakeAttackVampire(){
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<Pair<Integer, Integer>>();
        PathPosition dummyPos = new PathPosition(0, dummyPath);
        Vampire dummyVamp = new Vampire(dummyPos);
        Character dummyChar = new Character(dummyPos);

        dummyChar.AttackTarget(dummyVamp, 0);

        assertTrue(dummyVamp.getHealth() == 165); 
    }

    void TestStaffAttack(){
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<Pair<Integer, Integer>>();
        PathPosition dummyPos = new PathPosition(0, dummyPath);
        Slug dummySlug = new Slug(dummyPos);
        Character dummyChar = new Character(dummyPos);

        dummyChar.AttackTarget(dummySlug, 0);

        assertTrue(dummySlug.getHealth() == 40); 
    }
}
