package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;
import org.javatuples.Pair;

import unsw.loopmania.Card;
import unsw.loopmania.Character;
import unsw.loopmania.HerosCastle;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.VampireCastleCard;
import unsw.loopmania.Vampire;
import unsw.loopmania.BasicEnemy;
import unsw.loopmania.CampfireCard;

public class VampireMovementTest {
    @Test
    public void testVampireMovement() {
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
        dummyPath.add(new Pair<>(0, 0));
        dummyPath.add(new Pair<>(0, 1));
        dummyPath.add(new Pair<>(0, 2));
        dummyPath.add(new Pair<>(0, 3));
        dummyPath.add(new Pair<>(0, 4));
        dummyPath.add(new Pair<>(1, 4));
        dummyPath.add(new Pair<>(2, 4));
        dummyPath.add(new Pair<>(3, 4));
        dummyPath.add(new Pair<>(4, 4));
        dummyPath.add(new Pair<>(4, 3));
        dummyPath.add(new Pair<>(4, 2));
        dummyPath.add(new Pair<>(4, 1));
        dummyPath.add(new Pair<>(4, 0));
        dummyPath.add(new Pair<>(3, 0));
        dummyPath.add(new Pair<>(2, 0));
        dummyPath.add(new Pair<>(1, 0));

        LoopManiaWorld world = new LoopManiaWorld(5, 5, dummyPath);
        PathPosition pos = new PathPosition(0, dummyPath);
        Character character = new Character(pos);
        HerosCastle castle = new HerosCastle(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        world.setCharacter(character);
        world.setCastle(castle);
        
        Card vampCard = world.loadCard(VampireCastleCard.class).getValue0();;
        world.convertCardToBuildingByCoordinates(vampCard.getX(), vampCard.getY(), 1, 1);

        for(int i = 0; i < 80; i++) {
            world.runTickMoves();
        }

        world.spawnEnemies();

        assertEquals(1, world.getEnemy().size());
        assertEquals(5, world.getCycle());

        Card campfireCard = world.loadCard(CampfireCard.class).getValue0();
        world.convertCardToBuildingByCoordinates(campfireCard.getX(), campfireCard.getY(), 3, 3);

        assertEquals(3, world.getBuildings().size());

        BasicEnemy vamp = world.getEnemy().get(0);

        if(vamp instanceof Vampire) {
            assertTrue(((Vampire) vamp).getDirection() == false);
        }

        world.runTickMoves();

        assertTrue(((Vampire) vamp).getDirection() == true);

        world.runTickMoves();

        assertTrue(((Vampire) vamp).getDirection() == false);
    }
}
