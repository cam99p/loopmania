package test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;
import org.javatuples.Pair;

import unsw.loopmania.Building;
import unsw.loopmania.CampfireBuilding;
import unsw.loopmania.CampfireCard;
import unsw.loopmania.Card;
import unsw.loopmania.Character;
import unsw.loopmania.HerosCastle;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Slug;
import unsw.loopmania.VillageCard;

public class BuffBuildingsTest {
    @Test
    public void villagePathTileTest() {
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
        dummyPath.add(new Pair<>(0, 0));
        dummyPath.add(new Pair<>(0, 1));
        dummyPath.add(new Pair<>(0, 2));
        dummyPath.add(new Pair<>(1, 2));
        dummyPath.add(new Pair<>(2, 2));
        dummyPath.add(new Pair<>(2, 1));
        dummyPath.add(new Pair<>(2, 0));
        dummyPath.add(new Pair<>(1, 0));

        LoopManiaWorld world = new LoopManiaWorld(3, 3, dummyPath);
        PathPosition pos = new PathPosition(0, dummyPath);
        Character character = new Character(pos);
        HerosCastle castle = new HerosCastle(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        world.setCharacter(character);
        world.setCastle(castle);

        Card villageCard = world.loadCard(VillageCard.class).getValue0();

        Building village = world.convertCardToBuildingByCoordinates(villageCard.getX(), villageCard.getY(), 0, 1);
        assertEquals(2, world.getBuildings().size());
        assertEquals(village, world.getBuildings().get(1));
    }

    @Test
    public void villageNonPathTileTest() {
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
        dummyPath.add(new Pair<>(0, 0));
        dummyPath.add(new Pair<>(0, 1));
        dummyPath.add(new Pair<>(0, 2));
        dummyPath.add(new Pair<>(1, 2));
        dummyPath.add(new Pair<>(2, 2));
        dummyPath.add(new Pair<>(2, 1));
        dummyPath.add(new Pair<>(2, 0));
        dummyPath.add(new Pair<>(1, 0));

        LoopManiaWorld world = new LoopManiaWorld(3, 3, dummyPath);
        PathPosition pos = new PathPosition(0, dummyPath);
        Character character = new Character(pos);
        HerosCastle castle = new HerosCastle(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        world.setCharacter(character);
        world.setCastle(castle);

        Card villageCard = world.loadCard(VillageCard.class).getValue0();

        world.convertCardToBuildingByCoordinates(villageCard.getX(), villageCard.getY(), 1, 1);

        assertEquals(1, world.getBuildings().size());
        assertEquals(1, world.getCards().size());
        assertEquals(villageCard, world.getCards().get(0));
    }

    @Test
    public void villageCharacterTest() {
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
        dummyPath.add(new Pair<>(0, 0));
        dummyPath.add(new Pair<>(0, 1));
        dummyPath.add(new Pair<>(0, 2));
        dummyPath.add(new Pair<>(1, 2));
        dummyPath.add(new Pair<>(2, 2));
        dummyPath.add(new Pair<>(2, 1));
        dummyPath.add(new Pair<>(2, 0));
        dummyPath.add(new Pair<>(1, 0));

        LoopManiaWorld world = new LoopManiaWorld(3, 3, dummyPath);
        PathPosition pos = new PathPosition(0, dummyPath);
        Character character = new Character(pos);
        HerosCastle castle = new HerosCastle(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        world.setCharacter(character);
        world.setCastle(castle);

        character.setHealth(140);

        Card villageCard = world.loadCard(VillageCard.class).getValue0();
        world.convertCardToBuildingByCoordinates(villageCard.getX(), villageCard.getY(), 0, 1);

        character.moveDownPath();

        world.buffCharacter();

        assertTrue(character.getHealth() == 200);
    }

    @Test
    public void villageMonsterTest() {
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
        dummyPath.add(new Pair<>(0, 0));
        dummyPath.add(new Pair<>(0, 1));
        dummyPath.add(new Pair<>(0, 2));
        dummyPath.add(new Pair<>(1, 2));
        dummyPath.add(new Pair<>(2, 2));
        dummyPath.add(new Pair<>(2, 1));
        dummyPath.add(new Pair<>(2, 0));
        dummyPath.add(new Pair<>(1, 0));

        LoopManiaWorld world = new LoopManiaWorld(3, 3, dummyPath);
        PathPosition pos = new PathPosition(0, dummyPath);
        Character character = new Character(pos);
        HerosCastle castle = new HerosCastle(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        world.setCharacter(character);
        world.setCastle(castle);

        Slug slug = new Slug(new PathPosition(1, dummyPath));
        slug.setHealth(30);

        assertTrue(slug.getHealth() == 30);

        Card villageCard = world.loadCard(VillageCard.class).getValue0();
        world.convertCardToBuildingByCoordinates(villageCard.getX(), villageCard.getY(), 0, 2);

        slug.moveDownPath();

        world.buffCharacter();

        assertTrue(slug.getHealth() == 30);
    
    }
    @Test
    public void campfirePathTileTest() {
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
        dummyPath.add(new Pair<>(0, 0));
        dummyPath.add(new Pair<>(0, 1));
        dummyPath.add(new Pair<>(0, 2));
        dummyPath.add(new Pair<>(1, 2));
        dummyPath.add(new Pair<>(2, 2));
        dummyPath.add(new Pair<>(2, 1));
        dummyPath.add(new Pair<>(2, 0));
        dummyPath.add(new Pair<>(1, 0));

        LoopManiaWorld world = new LoopManiaWorld(3, 3, dummyPath);
        PathPosition pos = new PathPosition(0, dummyPath);
        Character character = new Character(pos);
        HerosCastle castle = new HerosCastle(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        world.setCharacter(character);
        world.setCastle(castle);

        Card campfireCard = world.loadCard(CampfireCard.class).getValue0();

        world.convertCardToBuildingByCoordinates(campfireCard.getX(), campfireCard.getY(), 0, 1);
        assertEquals(1, world.getBuildings().size());
        assertEquals(1, world.getCards().size());
        assertEquals(campfireCard, world.getCards().get(0));
    }

    @Test
    public void campfireNonPathTileTest() {
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
        dummyPath.add(new Pair<>(0, 0));
        dummyPath.add(new Pair<>(0, 1));
        dummyPath.add(new Pair<>(0, 2));
        dummyPath.add(new Pair<>(1, 2));
        dummyPath.add(new Pair<>(2, 2));
        dummyPath.add(new Pair<>(2, 1));
        dummyPath.add(new Pair<>(2, 0));
        dummyPath.add(new Pair<>(1, 0));

        LoopManiaWorld world = new LoopManiaWorld(3, 3, dummyPath);
        PathPosition pos = new PathPosition(0, dummyPath);
        Character character = new Character(pos);
        HerosCastle castle = new HerosCastle(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        world.setCharacter(character);
        world.setCastle(castle);

        Card campfireCard = world.loadCard(CampfireCard.class).getValue0();
        assertDoesNotThrow(() -> world.convertCardToBuildingByCoordinates(campfireCard.getX(), campfireCard.getY(), 1, 1));
        assertEquals(2, world.getBuildings().size());
        assertTrue(world.getBuildings().get(1) instanceof CampfireBuilding);
    }

    @Test
    public void campfireInsideRadiusTest() {
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
        dummyPath.add(new Pair<>(0, 0));
        dummyPath.add(new Pair<>(0, 1));
        dummyPath.add(new Pair<>(0, 2));
        dummyPath.add(new Pair<>(1, 2));
        dummyPath.add(new Pair<>(2, 2));
        dummyPath.add(new Pair<>(2, 1));
        dummyPath.add(new Pair<>(2, 0));
        dummyPath.add(new Pair<>(1, 0));

        LoopManiaWorld world = new LoopManiaWorld(3, 3, dummyPath);
        PathPosition pos = new PathPosition(0, dummyPath);
        Character character = new Character(pos);
        HerosCastle castle = new HerosCastle(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        world.setCharacter(character);
        world.setCastle(castle);

        assertTrue(character.getAttack() == 5);

        Card campfireCard = world.loadCard(CampfireCard.class).getValue0();
        world.convertCardToBuildingByCoordinates(campfireCard.getX(), campfireCard.getY(), 1, 1);

        world.buffCharacter();

        assertTrue(character.getAttack() == 10);
    }

    @Test 
    public void campfireOutsideRadiusTest() {
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

        assertTrue(character.getAttack() == 5);

        Card campfireCard = world.loadCard(CampfireCard.class).getValue0();
        world.convertCardToBuildingByCoordinates(campfireCard.getX(), campfireCard.getY(), 3, 1);

        world.buffCharacter();

        assertTrue(character.getAttack() == 5);
    }
}
