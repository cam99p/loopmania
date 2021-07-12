package test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;
import org.javatuples.Pair;

import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.VampireCastleBuilding;
import unsw.loopmania.ZombiePitBuilding;
import unsw.loopmania.Character;
import unsw.loopmania.HerosCastle;
import unsw.loopmania.VampireCastleCard;

public class BuildingsTest {
    @Test
    public void castleTest() {
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
        assertEquals(world.getBuildings().get(0), castle);
        assertEquals(world.getBuildings().get(0).getX(), 0);
        assertEquals(world.getBuildings().get(0).getY(), 0);
    }

    @Test
    public void cycleCounterTest() {
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
        assertEquals(world.getBuildings().get(0), castle);

        assertEquals(world.getCycle(), 0);

        for(int i = 0; i < 9; i++) {
            System.out.println("( " + character.getX() + " " + character.getY() + " )");
            world.runTickMoves();
        }
        System.out.println(world.getCycle());
        assertEquals(1, world.getCycle());
    }

    @Test
    public void vampireBuildingNonPathTileTest() {
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
        
        VampireCastleCard vampCard = world.loadVampireCard();

        VampireCastleBuilding vampBuilding = world.convertCardToBuildingByCoordinates(vampCard.getX(), vampCard.getY(), 1, 1);
        assertEquals(2, world.getBuildings().size());
        assertEquals(vampBuilding, world.getBuildings().get(1));
    }

    @Test
    public void vampireBuildingPathTileTest() {
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
        
        assertThrows(IllegalArgumentException.class, () -> new VampireCastleBuilding(new SimpleIntegerProperty(0), new SimpleIntegerProperty(1)));
    }

    @Test
    public void vampireBuildingSpawn() {
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

        VampireCastleCard vampCard = world.loadVampireCard();

        world.convertCardToBuildingByCoordinates(vampCard.getX(), vampCard.getY(), 1, 1);

        for(int i = 0; i < 45; i++) {
            world.runTickMoves();
        }

        assertEquals(1, world.getEnemy().size());
        assertEquals(Vampire.class, world.getEnemy().get(0).getClass());
    }

    @Test
    public void vampireBuildingSpawnDown() {
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

        VampireCastleCard vampCard = world.loadVampireCard();

        world.convertCardToBuildingByCoordinates(vampCard.getX(), vampCard.getY(), 1, 1);

        for(int i = 0; i < 45; i++) {
            world.runTickMoves();
        }

        assertEquals(Vampire.class, world.getEnemy().get(0).getClass());
        assertEquals(new SimpleIntegerProperty(1), world.getEnemy().get(0).x());
        assertEquals(new SimpleIntegerProperty(0), world.getEnemy().get(0).y());
    }

    @Test
    public void vampireBuildingSpawnDown1() {
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
        dummyPath.add(new Pair<>(0, 0));
        dummyPath.add(new Pair<>(0, 1));
        dummyPath.add(new Pair<>(0, 2));
        dummyPath.add(new Pair<>(0, 3));
        dummyPath.add(new Pair<>(1, 3));
        dummyPath.add(new Pair<>(2, 3));
        dummyPath.add(new Pair<>(3, 3));
        dummyPath.add(new Pair<>(3, 2));
        dummyPath.add(new Pair<>(3, 1));
        dummyPath.add(new Pair<>(3, 0));
        dummyPath.add(new Pair<>(1, 0));
        dummyPath.add(new Pair<>(2, 0));

        LoopManiaWorld world = new LoopManiaWorld(4, 4, dummyPath);
        PathPosition pos = new PathPosition(0, dummyPath);
        Character character = new Character(pos);
        HerosCastle castle = new HerosCastle(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        world.setCharacter(character);
        world.setCastle(castle);

        VampireCastleCard vampCard = world.loadVampireCard();

        world.convertCardToBuildingByCoordinates(vampCard.getX(), vampCard.getY(), 1, 1);

        for(int i = 0; i < 45; i++) {
            world.runTickMoves();
        }

        assertEquals(5, world.getCycle());
        assertEquals(Vampire.class, world.getEnemy().get(0).getClass());
        assertEquals(new SimpleIntegerProperty(1), world.getEnemy().get(0).x());
        assertEquals(new SimpleIntegerProperty(0), world.getEnemy().get(0).y());
    }

    @Test
    public void vampireBuildingSpawnDown2() {
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
        dummyPath.add(new Pair<>(0, 0));
        dummyPath.add(new Pair<>(0, 1));
        dummyPath.add(new Pair<>(0, 2));
        dummyPath.add(new Pair<>(0, 3));
        dummyPath.add(new Pair<>(1, 3));
        dummyPath.add(new Pair<>(2, 3));
        dummyPath.add(new Pair<>(3, 3));
        dummyPath.add(new Pair<>(3, 2));
        dummyPath.add(new Pair<>(3, 1));
        dummyPath.add(new Pair<>(3, 0));
        dummyPath.add(new Pair<>(1, 0));
        dummyPath.add(new Pair<>(2, 0));

        LoopManiaWorld world = new LoopManiaWorld(4, 4, dummyPath);
        PathPosition pos = new PathPosition(0, dummyPath);
        Character character = new Character(pos);
        HerosCastle castle = new HerosCastle(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        world.setCharacter(character);
        world.setCastle(castle);

        VampireCastleCard vampCard = world.loadVampireCard();

        world.convertCardToBuildingByCoordinates(vampCard.getX(), vampCard.getY(), 2, 1);

        for(int i = 0; i < 45; i++) {
            world.runTickMoves();
        }

        assertEquals(5, world.getCycle());
        assertEquals(Vampire.class, world.getEnemy().get(0).getClass());
        assertEquals(new SimpleIntegerProperty(2), world.getEnemy().get(0).x());
        assertEquals(new SimpleIntegerProperty(0), world.getEnemy().get(0).y());
    }

    @Test
    public void vampireBuildingSpawnLeft() {
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
        dummyPath.add(new Pair<>(0, 0));
        dummyPath.add(new Pair<>(0, 1));
        dummyPath.add(new Pair<>(0, 2));
        dummyPath.add(new Pair<>(0, 3));
        dummyPath.add(new Pair<>(1, 3));
        dummyPath.add(new Pair<>(2, 3));
        dummyPath.add(new Pair<>(3, 3));
        dummyPath.add(new Pair<>(3, 2));
        dummyPath.add(new Pair<>(3, 1));
        dummyPath.add(new Pair<>(3, 0));
        dummyPath.add(new Pair<>(1, 0));
        dummyPath.add(new Pair<>(2, 0));

        LoopManiaWorld world = new LoopManiaWorld(4, 4, dummyPath);
        PathPosition pos = new PathPosition(0, dummyPath);
        Character character = new Character(pos);
        HerosCastle castle = new HerosCastle(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        world.setCharacter(character);
        world.setCastle(castle);

        VampireCastleCard vampCard = world.loadVampireCard();

        world.convertCardToBuildingByCoordinates(vampCard.getX(), vampCard.getY(), 1, 2);

        for(int i = 0; i < 45; i++) {
            world.runTickMoves();
        }

        assertEquals(5, world.getCycle());
        assertEquals(Vampire.class, world.getEnemy().get(0).getClass());
        assertEquals(new SimpleIntegerProperty(0), world.getEnemy().get(0).x());
        assertEquals(new SimpleIntegerProperty(2), world.getEnemy().get(0).y());
    }

    @Test
    public void vampireBuildingSpawnRight() {
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
        dummyPath.add(new Pair<>(0, 0));
        dummyPath.add(new Pair<>(0, 1));
        dummyPath.add(new Pair<>(0, 2));
        dummyPath.add(new Pair<>(0, 3));
        dummyPath.add(new Pair<>(1, 3));
        dummyPath.add(new Pair<>(2, 3));
        dummyPath.add(new Pair<>(3, 3));
        dummyPath.add(new Pair<>(3, 2));
        dummyPath.add(new Pair<>(3, 1));
        dummyPath.add(new Pair<>(3, 0));
        dummyPath.add(new Pair<>(1, 0));
        dummyPath.add(new Pair<>(2, 0));

        LoopManiaWorld world = new LoopManiaWorld(4, 4, dummyPath);
        PathPosition pos = new PathPosition(0, dummyPath);
        Character character = new Character(pos);
        HerosCastle castle = new HerosCastle(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        world.setCharacter(character);
        world.setCastle(castle);

        VampireCastleCard vampCard = world.loadVampireCard();

        world.convertCardToBuildingByCoordinates(vampCard.getX(), vampCard.getY(), 2, 2);

        for(int i = 0; i < 45; i++) {
            world.runTickMoves();
        }

        assertEquals(5, world.getCycle());
        assertEquals(Vampire.class, world.getEnemy().get(0).getClass());
        assertEquals(new SimpleIntegerProperty(3), world.getEnemy().get(0).x());
        assertEquals(new SimpleIntegerProperty(2), world.getEnemy().get(0).y());
    }

    @Test
    public void vampireBuildingSpawnLeft1() {
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
        dummyPath.add(new Pair<>(0, 0));
        dummyPath.add(new Pair<>(0, 1));
        dummyPath.add(new Pair<>(0, 2));
        dummyPath.add(new Pair<>(0, 3));
        dummyPath.add(new Pair<>(0, 4));
        dummyPath.add(new Pair<>(1, 4));
        dummyPath.add(new Pair<>(2, 4));
        dummyPath.add(new Pair<>(2, 3));
        dummyPath.add(new Pair<>(2, 2));
        dummyPath.add(new Pair<>(2, 1));
        dummyPath.add(new Pair<>(2, 0));
        dummyPath.add(new Pair<>(1, 0));

        LoopManiaWorld world = new LoopManiaWorld(3, 5, dummyPath);
        PathPosition pos = new PathPosition(0, dummyPath);
        Character character = new Character(pos);
        HerosCastle castle = new HerosCastle(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        world.setCharacter(character);
        world.setCastle(castle);

        VampireCastleCard vampCard = world.loadVampireCard();

        world.convertCardToBuildingByCoordinates(vampCard.getX(), vampCard.getY(), 1, 2);

        for(int i = 0; i < 45; i++) {
            world.runTickMoves();
        }

        assertEquals(5, world.getCycle());
        assertEquals(Vampire.class, world.getEnemy().get(0).getClass());
        assertEquals(new SimpleIntegerProperty(0), world.getEnemy().get(0).x());
        assertEquals(new SimpleIntegerProperty(2), world.getEnemy().get(0).y());
    }

    @Test
    public void vampireBuildingSpawnDown3() {
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
        dummyPath.add(new Pair<>(0, 0));
        dummyPath.add(new Pair<>(0, 1));
        dummyPath.add(new Pair<>(0, 2));
        dummyPath.add(new Pair<>(1, 2));
        dummyPath.add(new Pair<>(2, 2));
        dummyPath.add(new Pair<>(3, 2));
        dummyPath.add(new Pair<>(4, 2));
        dummyPath.add(new Pair<>(4, 1));
        dummyPath.add(new Pair<>(4, 0));
        dummyPath.add(new Pair<>(3, 0));
        dummyPath.add(new Pair<>(2, 0));
        dummyPath.add(new Pair<>(1, 0));

        LoopManiaWorld world = new LoopManiaWorld(5, 3, dummyPath);
        PathPosition pos = new PathPosition(0, dummyPath);
        Character character = new Character(pos);
        HerosCastle castle = new HerosCastle(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        world.setCharacter(character);
        world.setCastle(castle);

        VampireCastleCard vampCard = world.loadVampireCard();

        world.convertCardToBuildingByCoordinates(vampCard.getX(), vampCard.getY(), 2, 1);

        for(int i = 0; i < 45; i++) {
            world.runTickMoves();
        }

        assertEquals(5, world.getCycle());
        assertEquals(Vampire.class, world.getEnemy().get(0).getClass());
        assertEquals(new SimpleIntegerProperty(2), world.getEnemy().get(0).x());
        assertEquals(new SimpleIntegerProperty(0), world.getEnemy().get(0).y());
    }

    @Test
    public void vampireMultipleSpawn() {
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
        dummyPath.add(new Pair<>(0, 0));
        dummyPath.add(new Pair<>(0, 1));
        dummyPath.add(new Pair<>(0, 2));
        dummyPath.add(new Pair<>(1, 2));
        dummyPath.add(new Pair<>(2, 2));
        dummyPath.add(new Pair<>(3, 2));
        dummyPath.add(new Pair<>(4, 2));
        dummyPath.add(new Pair<>(4, 1));
        dummyPath.add(new Pair<>(4, 0));
        dummyPath.add(new Pair<>(3, 0));
        dummyPath.add(new Pair<>(2, 0));
        dummyPath.add(new Pair<>(1, 0));

        LoopManiaWorld world = new LoopManiaWorld(5, 3, dummyPath);
        PathPosition pos = new PathPosition(0, dummyPath);
        Character character = new Character(pos);
        HerosCastle castle = new HerosCastle(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        world.setCharacter(character);
        world.setCastle(castle);

        VampireCastleCard vampCard = world.loadVampireCard();

        world.convertCardToBuildingByCoordinates(vampCard.getX(), vampCard.getY(), 2, 1);

        for(int i = 0; i < 90; i++) {
            world.runTickMoves();
        }

        assertEquals(10, world.getCycle());
        assertEquals(2, world.getEnemy().size());
        assertEquals(Vampire.class, world.getEnemy().get(0).getClass());
        assertEquals(Vampire.class, world.getEnemy().get(1).getClass());
    }

    @Test
    public void zombieBuildingNonPathTileTest() {
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

        ZombiePitCard zombieCard = world.loadZombieCard();

        ZombiePitBuilding zombieBuilding = world.convertCardToBuildingByCoordinates(zombieCard.getX(), zombieCard.getY(), 1, 1);
        assertEquals(2, world.getBuildings().size());
        assertEquals(zombieBuilding, world.getBuildings().get(1));
    }

    @Test
    public void zombieBuildingPathTileTest() {
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
        
        ZombiePitCard zombieCard = world.loadZombieCard();

        assertThrows(IllegalArgumentException.class, () -> world.convertCardToBuildingByCoordinates(zombieCard.getX(), zombieCard.getY(), 0, 1);
    }

    @Test
    public void zombieBuildingSpawn() {
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

        ZombiePitCard zombieCard = world.loadZombieCard();

        world.convertCardToBuildingByCoordinates(zombieCard.getX(), zombieCard.getY(), 1, 1);

        for(int i = 0; i < 9; i++) {
            world.runTickMoves();
        }

        assertEquals(1, world.getEnemy().size());
        assertEquals(Zombie.class, world.getEnemy().get(0).getClass());
    }

    @Test
    public void zombieBuildingSpawnDown() {
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

        ZombiePitCard zombieCard = world.loadZombieCard();

        world.convertCardToBuildingByCoordinates(zombieCard.getX(), zombieCard.getY(), 1, 1);

        for(int i = 0; i < 9; i++) {
            world.runTickMoves();
        }

        assertEquals(1, world.getCycle());
        assertEquals(new SimpleIntegerProperty(1), world.getEnemy().get(0).x());
        assertEquals(new SimpleIntegerProperty(0), world.getEnemy().get(0).y());
    }

    @Test
    public void zombieBuildingSpawnDown1() {
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
        dummyPath.add(new Pair<>(0, 0));
        dummyPath.add(new Pair<>(0, 1));
        dummyPath.add(new Pair<>(0, 2));
        dummyPath.add(new Pair<>(0, 3));
        dummyPath.add(new Pair<>(1, 3));
        dummyPath.add(new Pair<>(2, 3));
        dummyPath.add(new Pair<>(3, 3));
        dummyPath.add(new Pair<>(3, 2));
        dummyPath.add(new Pair<>(3, 1));
        dummyPath.add(new Pair<>(3, 0));
        dummyPath.add(new Pair<>(1, 0));
        dummyPath.add(new Pair<>(2, 0));

        LoopManiaWorld world = new LoopManiaWorld(4, 4, dummyPath);
        PathPosition pos = new PathPosition(0, dummyPath);
        Character character = new Character(pos);
        HerosCastle castle = new HerosCastle(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        world.setCharacter(character);
        world.setCastle(castle);

        ZombiePitCard zombieCard = world.loadZombieCard();

        world.convertCardToBuildingByCoordinates(zombieCard.getX(), zombieCard.getY(), 1, 1);

        for(int i = 0; i < 9; i++) {
            world.runTickMoves();
        }

        assertEquals(1, world.getCycle());
        assertEquals(new SimpleIntegerProperty(1), world.getEnemy().get(0).x());
        assertEquals(new SimpleIntegerProperty(0), world.getEnemy().get(0).y());
    }

    @Test
    public void zombieBuildingSpawnDown2() {
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
        dummyPath.add(new Pair<>(0, 0));
        dummyPath.add(new Pair<>(0, 1));
        dummyPath.add(new Pair<>(0, 2));
        dummyPath.add(new Pair<>(0, 3));
        dummyPath.add(new Pair<>(1, 3));
        dummyPath.add(new Pair<>(2, 3));
        dummyPath.add(new Pair<>(3, 3));
        dummyPath.add(new Pair<>(3, 2));
        dummyPath.add(new Pair<>(3, 1));
        dummyPath.add(new Pair<>(3, 0));
        dummyPath.add(new Pair<>(1, 0));
        dummyPath.add(new Pair<>(2, 0));

        LoopManiaWorld world = new LoopManiaWorld(4, 4, dummyPath);
        PathPosition pos = new PathPosition(0, dummyPath);
        Character character = new Character(pos);
        HerosCastle castle = new HerosCastle(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        world.setCharacter(character);
        world.setCastle(castle);

        ZombiePitCard zombieCard = world.loadZombieCard();

        world.convertCardToBuildingByCoordinates(zombieCard.getX(), zombieCard.getY(), 2, 1);

        for(int i = 0; i < 9; i++) {
            world.runTickMoves();
        }

        assertEquals(1, world.getCycle());
        assertEquals(new SimpleIntegerProperty(2), world.getEnemy().get(0).x());
        assertEquals(new SimpleIntegerProperty(0), world.getEnemy().get(0).y());
    }

    @Test
    public void zombieBuildingSpawnLeft() {
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
        dummyPath.add(new Pair<>(0, 0));
        dummyPath.add(new Pair<>(0, 1));
        dummyPath.add(new Pair<>(0, 2));
        dummyPath.add(new Pair<>(0, 3));
        dummyPath.add(new Pair<>(1, 3));
        dummyPath.add(new Pair<>(2, 3));
        dummyPath.add(new Pair<>(3, 3));
        dummyPath.add(new Pair<>(3, 2));
        dummyPath.add(new Pair<>(3, 1));
        dummyPath.add(new Pair<>(3, 0));
        dummyPath.add(new Pair<>(1, 0));
        dummyPath.add(new Pair<>(2, 0));

        LoopManiaWorld world = new LoopManiaWorld(4, 4, dummyPath);
        PathPosition pos = new PathPosition(0, dummyPath);
        Character character = new Character(pos);
        HerosCastle castle = new HerosCastle(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        world.setCharacter(character);
        world.setCastle(castle);

        ZombiePitCard zombieCard = world.loadZombieCard();

        world.convertCardToBuildingByCoordinates(zombieCard.getX(), zombieCard.getY(), 1, 2);

        for(int i = 0; i < 9; i++) {
            world.runTickMoves();
        }

        assertEquals(1, world.getCycle());
        assertEquals(new SimpleIntegerProperty(0), world.getEnemy().get(0).x());
        assertEquals(new SimpleIntegerProperty(2), world.getEnemy().get(0).y());
    }

    @Test
    public void zombieBuildingSpawnRight() {
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
        dummyPath.add(new Pair<>(0, 0));
        dummyPath.add(new Pair<>(0, 1));
        dummyPath.add(new Pair<>(0, 2));
        dummyPath.add(new Pair<>(0, 3));
        dummyPath.add(new Pair<>(1, 3));
        dummyPath.add(new Pair<>(2, 3));
        dummyPath.add(new Pair<>(3, 3));
        dummyPath.add(new Pair<>(3, 2));
        dummyPath.add(new Pair<>(3, 1));
        dummyPath.add(new Pair<>(3, 0));
        dummyPath.add(new Pair<>(1, 0));
        dummyPath.add(new Pair<>(2, 0));

        LoopManiaWorld world = new LoopManiaWorld(4, 4, dummyPath);
        PathPosition pos = new PathPosition(0, dummyPath);
        Character character = new Character(pos);
        HerosCastle castle = new HerosCastle(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        world.setCharacter(character);
        world.setCastle(castle);

        ZombiePitCard zombieCard = world.loadZombieCard();

        world.convertCardToBuildingByCoordinates(zombieCard.getX(), zombieCard.getY(), 2, 2);

        for(int i = 0; i < 9; i++) {
            world.runTickMoves();
        }

        assertEquals(1, world.getCycle());
        assertEquals(new SimpleIntegerProperty(3), world.getEnemy().get(0).x());
        assertEquals(new SimpleIntegerProperty(2), world.getEnemy().get(0).y());
    }

    @Test
    public void zombieBuildingSpawnLeft1() {
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
        dummyPath.add(new Pair<>(0, 0));
        dummyPath.add(new Pair<>(0, 1));
        dummyPath.add(new Pair<>(0, 2));
        dummyPath.add(new Pair<>(0, 3));
        dummyPath.add(new Pair<>(0, 4));
        dummyPath.add(new Pair<>(1, 4));
        dummyPath.add(new Pair<>(2, 4));
        dummyPath.add(new Pair<>(2, 3));
        dummyPath.add(new Pair<>(2, 2));
        dummyPath.add(new Pair<>(2, 1));
        dummyPath.add(new Pair<>(2, 0));
        dummyPath.add(new Pair<>(1, 0));

        LoopManiaWorld world = new LoopManiaWorld(3, 5, dummyPath);
        PathPosition pos = new PathPosition(0, dummyPath);
        Character character = new Character(pos);
        HerosCastle castle = new HerosCastle(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        world.setCharacter(character);
        world.setCastle(castle);

        ZombiePitCard zombieCard = world.loadZombieCard();

        world.convertCardToBuildingByCoordinates(zombieCard.getX(), zombieCard.getY(), 1, 2);

        for(int i = 0; i < 9; i++) {
            world.runTickMoves();
        }

        assertEquals(1, world.getCycle());
        assertEquals(new SimpleIntegerProperty(0), world.getEnemy().get(0).x());
        assertEquals(new SimpleIntegerProperty(2), world.getEnemy().get(0).y());
    }

    @Test
    public void zombieBuildingSpawnDown3() {
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
        dummyPath.add(new Pair<>(0, 0));
        dummyPath.add(new Pair<>(0, 1));
        dummyPath.add(new Pair<>(0, 2));
        dummyPath.add(new Pair<>(1, 2));
        dummyPath.add(new Pair<>(2, 2));
        dummyPath.add(new Pair<>(3, 2));
        dummyPath.add(new Pair<>(4, 2));
        dummyPath.add(new Pair<>(4, 1));
        dummyPath.add(new Pair<>(4, 0));
        dummyPath.add(new Pair<>(3, 0));
        dummyPath.add(new Pair<>(2, 0));
        dummyPath.add(new Pair<>(1, 0));

        LoopManiaWorld world = new LoopManiaWorld(5, 3, dummyPath);
        PathPosition pos = new PathPosition(0, dummyPath);
        Character character = new Character(pos);
        HerosCastle castle = new HerosCastle(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        world.setCharacter(character);
        world.setCastle(castle);

        ZombiePitCard zombieCard = world.loadZombieCard();

        world.convertCardToBuildingByCoordinates(zombieCard.getX(), zombieCard.getY(), 2, 1);

        for(int i = 0; i < 9; i++) {
            world.runTickMoves();
        }

        assertEquals(1, world.getCycle());
        assertEquals(new SimpleIntegerProperty(2), world.getEnemy().get(0).x());
        assertEquals(new SimpleIntegerProperty(0), world.getEnemy().get(0).y());
    }

    @Test
    public void zombieMultipleSpawn() {
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
        dummyPath.add(new Pair<>(0, 0));
        dummyPath.add(new Pair<>(0, 1));
        dummyPath.add(new Pair<>(0, 2));
        dummyPath.add(new Pair<>(1, 2));
        dummyPath.add(new Pair<>(2, 2));
        dummyPath.add(new Pair<>(3, 2));
        dummyPath.add(new Pair<>(4, 2));
        dummyPath.add(new Pair<>(4, 1));
        dummyPath.add(new Pair<>(4, 0));
        dummyPath.add(new Pair<>(3, 0));
        dummyPath.add(new Pair<>(2, 0));
        dummyPath.add(new Pair<>(1, 0));

        LoopManiaWorld world = new LoopManiaWorld(5, 3, dummyPath);
        PathPosition pos = new PathPosition(0, dummyPath);
        Character character = new Character(pos);
        HerosCastle castle = new HerosCastle(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        world.setCharacter(character);
        world.setCastle(castle);

        ZombiePitCard zombieCard = world.loadZombieCard();

        world.convertCardToBuildingByCoordinates(zombieCard.getX(), zombieCard.getY(), 2, 1);

        for(int i = 0; i < 45; i++) {
            world.runTickMoves();
        }

        assertEquals(5, world.getCycle());
        assertEquals(5, world.getEnemy().size());
    }
}
