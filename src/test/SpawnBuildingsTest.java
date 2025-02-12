package test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;
import org.javatuples.Pair;

import unsw.loopmania.BarracksBuilding;
import unsw.loopmania.BarracksCard;
import unsw.loopmania.Building;
import unsw.loopmania.Card;
import unsw.loopmania.Character;
import unsw.loopmania.HerosCastle;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Vampire;
import unsw.loopmania.VampireCastleBuilding;
import unsw.loopmania.VampireCastleCard;
import unsw.loopmania.Zombie;
import unsw.loopmania.ZombiePitCard;

public class SpawnBuildingsTest {
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
            world.runTickMoves();
        }
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
        
        Card vampCard = world.loadCard(VampireCastleCard.class).getValue0();

        assertDoesNotThrow(() -> world.convertCardToBuildingByCoordinates(vampCard.getX(), vampCard.getY(), 1, 1));
        assertEquals(2, world.getBuildings().size());
        assertTrue(world.getBuildings().get(1) instanceof VampireCastleBuilding);
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
        
        Card vampCard = world.loadCard(VampireCastleCard.class).getValue0();

        world.convertCardToBuildingByCoordinates(vampCard.getX(), vampCard.getY(), 0, 1);
        assertEquals(1, world.getCards().size());
        assertEquals(vampCard, world.getCards().get(0));
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

        Card vampCard = world.loadCard(VampireCastleCard.class).getValue0();

        world.convertCardToBuildingByCoordinates(vampCard.getX(), vampCard.getY(), 1, 1);

        for(int i = 0; i < 45; i++) {
            world.runTickMoves();
        }

        world.spawnEnemies();

        Vampire vamp = new Vampire(pos);

        assertEquals(1, world.getEnemy().size());
        assertEquals(vamp.getClass(), world.getEnemy().get(0).getClass());
    }

    @Test
    public void vampireBuildingSpawnLeft() {
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

        Card vampCard = world.loadCard(VampireCastleCard.class).getValue0();

        world.convertCardToBuildingByCoordinates(vampCard.getX(), vampCard.getY(), 1, 1);

        for(int i = 0; i < 45; i++) {
            world.runTickMoves();
        }

        world.spawnEnemies();

        Vampire vamp = new Vampire(pos);
        assertEquals(vamp.getClass(), world.getEnemy().get(0).getClass());
        assertEquals(0, world.getEnemy().get(0).getX());
        assertEquals(1, world.getEnemy().get(0).getY());
    }

    @Test
    public void vampireBuildingSpawnUp() {
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

        Card vampCard = world.loadCard(VampireCastleCard.class).getValue0();

        world.convertCardToBuildingByCoordinates(vampCard.getX(), vampCard.getY(), 2, 2);

        for(int i = 0; i < 60; i++) {
            world.runTickMoves();
        }

        world.spawnEnemies();

        Vampire vamp = new Vampire(pos);
        assertEquals(5, world.getCycle());
        assertEquals(vamp.getClass(), world.getEnemy().get(0).getClass());
        assertEquals(2, world.getEnemy().get(0).getX());
        assertEquals(3, world.getEnemy().get(0).getY());
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

        Card vampCard = world.loadCard(VampireCastleCard.class).getValue0();

        world.convertCardToBuildingByCoordinates(vampCard.getX(), vampCard.getY(), 2, 1);

        for(int i = 0; i < 60; i++) {
            world.runTickMoves();
        }

        world.spawnEnemies();

        Vampire vamp = new Vampire(pos);
        assertEquals(5, world.getCycle());
        assertEquals(vamp.getClass(), world.getEnemy().get(0).getClass());
        assertEquals(3, world.getEnemy().get(0).getX());
        assertEquals(1, world.getEnemy().get(0).getY());
    }
    
    @Test
    public void vampireBuildingSpawnDown() {
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

        Card vampCard = world.loadCard(VampireCastleCard.class).getValue0();

        world.convertCardToBuildingByCoordinates(vampCard.getX(), vampCard.getY(), 2, 1);

        for(int i = 0; i < 80; i++) {
            world.runTickMoves();
        }

        world.spawnEnemies();

        Vampire vamp = new Vampire(pos);
        assertEquals(5, world.getCycle());
        assertEquals(vamp.getClass(), world.getEnemy().get(0).getClass());
        assertEquals(2, world.getEnemy().get(0).getX());
        assertEquals(0, world.getEnemy().get(0).getY());
    }

    @Test
    public void vampireMultipleSpawn() {
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

        Card vampCard = world.loadCard(VampireCastleCard.class).getValue0();;

        world.convertCardToBuildingByCoordinates(vampCard.getX(), vampCard.getY(), 1, 1);

        for(int i = 0; i < 45; i++) {
            world.runTickMoves();
        }

        world.spawnEnemies();

        for(int i = 0; i < 36; i++) {
            world.runTickMoves();
        }

        world.spawnEnemies();

        Vampire vamp = new Vampire(pos);
        assertEquals(10, world.getCycle());
        assertEquals(2, world.getEnemy().size());
        assertEquals(vamp.getClass(), world.getEnemy().get(0).getClass());
        assertEquals(vamp.getClass(), world.getEnemy().get(1).getClass());
    }

    @Test
    public void vampireSpawnDiagonal() {

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

        Card zombieCard = world.loadCard(ZombiePitCard.class).getValue0();

        Building zombieBuilding = world.convertCardToBuildingByCoordinates(zombieCard.getX(), zombieCard.getY(), 1, 1);
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
        
        Card zombieCard = world.loadCard(ZombiePitCard.class).getValue0();

        world.convertCardToBuildingByCoordinates(zombieCard.getX(), zombieCard.getY(), 0, 1);
        assertEquals(1, world.getCards().size());
        assertEquals(zombieCard, world.getCards().get(0));
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

        Card zombieCard = world.loadCard(ZombiePitCard.class).getValue0();

        world.convertCardToBuildingByCoordinates(zombieCard.getX(), zombieCard.getY(), 1, 1);

        for(int i = 0; i < 9; i++) {
            world.runTickMoves();
        }

        world.spawnEnemies();
        Zombie zombie = new Zombie(pos);

        assertEquals(1, world.getEnemy().size());
        assertEquals(zombie.getClass(), world.getEnemy().get(0).getClass());
    }

    @Test
    public void zombieBuildingSpawnLeft() {
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

        Card zombieCard = world.loadCard(ZombiePitCard.class).getValue0();

        world.convertCardToBuildingByCoordinates(zombieCard.getX(), zombieCard.getY(), 1, 1);

        for(int i = 0; i < 9; i++) {
            world.runTickMoves();
        }

        world.spawnEnemies();

        Zombie zombie = new Zombie(pos);

        assertEquals(1, world.getCycle());
        assertEquals(zombie.getClass(), world.getEnemy().get(0).getClass());
        assertEquals(0, world.getEnemy().get(0).getX());
        assertEquals(1, world.getEnemy().get(0).getY());
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

        Card zombieCard = world.loadCard(ZombiePitCard.class).getValue0();

        world.convertCardToBuildingByCoordinates(zombieCard.getX(), zombieCard.getY(), 2, 1);

        for(int i = 0; i < 12; i++) {
            world.runTickMoves();
        }

        world.spawnEnemies();

        Zombie zombie = new Zombie(pos);

        assertEquals(1, world.getCycle());
        assertEquals(zombie.getClass(), world.getEnemy().get(0).getClass());
        assertEquals(3, world.getEnemy().get(0).getX());
        assertEquals(1, world.getEnemy().get(0).getY());
    }

    @Test
    public void zombieBuildingSpawnUp() {
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

        Card zombieCard = world.loadCard(ZombiePitCard.class).getValue0();

        world.convertCardToBuildingByCoordinates(zombieCard.getX(), zombieCard.getY(), 2, 2);

        for(int i = 0; i < 12; i++) {
            world.runTickMoves();
        }

        world.spawnEnemies();
        
        Zombie zombie = new Zombie(pos);

        assertEquals(1, world.getCycle());
        assertEquals(zombie.getClass(), world.getEnemy().get(0).getClass());
        assertEquals(2, world.getEnemy().get(0).getX());
        assertEquals(3, world.getEnemy().get(0).getY());
    }

    @Test
    public void zombieBuildingSpawnDown() {
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

        Card zombieCard = world.loadCard(ZombiePitCard.class).getValue0();

        world.convertCardToBuildingByCoordinates(zombieCard.getX(), zombieCard.getY(), 2, 1);

        for(int i = 0; i < 16; i++) {
            world.runTickMoves();
        }

        world.spawnEnemies();

        Zombie zombie = new Zombie(pos);
        assertEquals(1, world.getCycle());
        assertEquals(zombie.getClass(), world.getEnemy().get(0).getClass());
        assertEquals(2, world.getEnemy().get(0).getX());
        assertEquals(0, world.getEnemy().get(0).getY());
    }

    @Test
    public void zombieMultipleSpawn() {
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

        Card zombieCard = world.loadCard(ZombiePitCard.class).getValue0();;

        world.convertCardToBuildingByCoordinates(zombieCard.getX(), zombieCard.getY(), 1, 1);

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 9; j++) {
                world.runTickMoves();
            }
            world.spawnEnemies();
        }

        Zombie zombie = new Zombie(pos);

        assertEquals(5, world.getCycle());
        assertEquals(5, world.getEnemy().size());
        for(int i = 0; i < 5; i++) {
            assertEquals(zombie.getClass(), world.getEnemy().get(i).getClass());
        }
    }

    @Test
    public void zombieSpawnDiagonal() {
        
    }

    @Test
    public void barracksPathTileTest() {
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

        Card barracksCard = world.loadCard(BarracksCard.class).getValue0();
        assertDoesNotThrow(() -> world.convertCardToBuildingByCoordinates(barracksCard.getX(), barracksCard.getY(), 0, 1));
        assertEquals(2, world.getBuildings().size());
        assertTrue(world.getBuildings().get(1) instanceof BarracksBuilding);
    }

    @Test
    public void barracksNonPathTileTest() {
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

        Card barracksCard = world.loadCard(BarracksCard.class).getValue0();
        world.convertCardToBuildingByCoordinates(barracksCard.getX(), barracksCard.getY(), 1, 1);
        assertEquals(1, world.getCards().size());
        assertEquals(barracksCard, world.getCards().get(0));
    }

    
    @Test
    public void barracksSpawnAllyTest() {
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

        Card barracksCard = world.loadCard(BarracksCard.class).getValue0();
        world.convertCardToBuildingByCoordinates(barracksCard.getX(), barracksCard.getY(), 0, 1);

        character.moveDownPath();
        
        world.spawnAllies();

        assertEquals(1, character.getAllies().size());
        assertEquals(5, character.getAllies().get(0).getAttack());
        assertEquals(50, character.getAllies().get(0).getHealth());
        assertEquals(0, character.getAllies().get(0).getDefense());
        assertEquals(7, character.getAllies().get(0).getSpeed());
    }

    @Test
    public void barracksMaxAlliesTest() {
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

        Card barracksCard = world.loadCard(BarracksCard.class).getValue0();
        world.convertCardToBuildingByCoordinates(barracksCard.getX(), barracksCard.getY(), 0, 1);

        character.moveDownPath();
        for(int i = 1; i <= 3; i++) {
            world.spawnAllies();
            assertEquals(i, character.getAllies().size());
            for(int j = 0; j < 8; j++) {
                world.runTickMoves();
            }
        }
        // Does not increase past 3
        world.spawnAllies();
        assertEquals(3, character.getAllies().size());
    }
}
