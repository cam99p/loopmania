package unsw.loopmania;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import org.javatuples.Pair;
import java.util.Arrays;

public class ZombieTest {
    @Test
    void TestAttack(){
        ArrayList<Pair<Integer, Integer>> dummyPath = new ArrayList<>(Arrays.asList(new Pair<>(0,0), new Pair<>(0,1), new Pair<>(0,2), new Pair<>(1,2),
                                                                new Pair<>(2,2), new Pair<>(2,1), new Pair<>(2,0), new Pair<>(1,0)));
        PathPosition dummyPos = new PathPosition(0, dummyPath);
        Zombie dummyZombie = new Zombie(dummyPos);
        Character dummyChar = new Character(dummyPos);

        dummyZombie.AttackTarget(dummyChar, 0);

        assertTrue(dummyChar.getHealth() == 190); 
    }

    @Test
    void TestZombification(){
        //Cannot be implemented until ally is implemented
    }
}
