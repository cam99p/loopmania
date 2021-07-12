package unsw.loopmania;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import org.javatuples.Pair;
import java.util.Arrays;

public class CharacterTest {
    //Tests that the characters unarmed attack does the right amount of damage
    @Test
    public void TestUnarmedAttack(){
        ArrayList<Pair<Integer, Integer>> dummyPath = new ArrayList<>(Arrays.asList(new Pair<>(0,0), new Pair<>(0,1), new Pair<>(0,2), new Pair<>(1,2),
                                                                new Pair<>(2,2), new Pair<>(2,1), new Pair<>(2,0), new Pair<>(1,0)));
        PathPosition dummyPos = new PathPosition(0, dummyPath);
        Slug dummySlug = new Slug(dummyPos);
        Character dummyChar = new Character(dummyPos);

        dummyChar.AttackTarget(dummySlug, 0);

        assertTrue(dummySlug.getHealth() == 45); 
    }

    //Tests that the characters sword attack does the right amount of damage
    @Test
    public void TestSwordAttack(){
        ArrayList<Pair<Integer, Integer>> dummyPath = new ArrayList<>(Arrays.asList(new Pair<>(0,0), new Pair<>(0,1), new Pair<>(0,2), new Pair<>(1,2),
                                                                new Pair<>(2,2), new Pair<>(2,1), new Pair<>(2,0), new Pair<>(1,0)));
        PathPosition dummyPos = new PathPosition(0, dummyPath);
        Slug dummySlug = new Slug(dummyPos);
        Character dummyChar = new Character(dummyPos);

        //Equip Sword

        dummyChar.AttackTarget(dummySlug, 0);

        assertTrue(dummySlug.getHealth() == 30); 
    }

    //Tests that the characters stake attack does the right amount of damage to non vampires
    @Test
    public void TestStakeAttack(){
        ArrayList<Pair<Integer, Integer>> dummyPath = new ArrayList<>(Arrays.asList(new Pair<>(0,0), new Pair<>(0,1), new Pair<>(0,2), new Pair<>(1,2),
                                                                new Pair<>(2,2), new Pair<>(2,1), new Pair<>(2,0), new Pair<>(1,0)));
        PathPosition dummyPos = new PathPosition(0, dummyPath);
        Slug dummySlug = new Slug(dummyPos);
        Character dummyChar = new Character(dummyPos);

        //Equip Stake

        dummyChar.AttackTarget(dummySlug, 0);

        assertTrue(dummySlug.getHealth() == 38); 
    }

    //Tests that the characters stake attack does the right amount of damage to vampires 
    @Test
    public void TestStakeAttackVampire(){
        ArrayList<Pair<Integer, Integer>> dummyPath = new ArrayList<>(Arrays.asList(new Pair<>(0,0), new Pair<>(0,1), new Pair<>(0,2), new Pair<>(1,2),
                                                                new Pair<>(2,2), new Pair<>(2,1), new Pair<>(2,0), new Pair<>(1,0)));
        PathPosition dummyPos = new PathPosition(0, dummyPath);
        Vampire dummyVamp = new Vampire(dummyPos);
        Character dummyChar = new Character(dummyPos);

        //Equip Stake

        dummyChar.AttackTarget(dummyVamp, 0);

        assertTrue(dummyVamp.getHealth() == 165); 
    }

    //Tests that the characters staff attack does the right amount of damage
    @Test
    public void TestStaffAttack(){
        ArrayList<Pair<Integer, Integer>> dummyPath = new ArrayList<>(Arrays.asList(new Pair<>(0,0), new Pair<>(0,1), new Pair<>(0,2), new Pair<>(1,2),
                                                                new Pair<>(2,2), new Pair<>(2,1), new Pair<>(2,0), new Pair<>(1,0)));
        PathPosition dummyPos = new PathPosition(0, dummyPath);
        Slug dummySlug = new Slug(dummyPos);
        Character dummyChar = new Character(dummyPos);

        //Equip Staff

        dummyChar.AttackTarget(dummySlug, 0);

        assertTrue(dummySlug.getHealth() == 40); 
    }
}
