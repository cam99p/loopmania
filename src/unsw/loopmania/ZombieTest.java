package unsw.loopmania;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import org.javatuples.Pair;

public class ZombieTest {
    @Test
    void TestAttack(){
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<Pair<Integer, Integer>>();
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
