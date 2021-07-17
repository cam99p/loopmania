package test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;
import org.javatuples.Pair;

import unsw.loopmania.backend.Battle;
import unsw.loopmania.backend.Building;
import unsw.loopmania.backend.Card;
import unsw.loopmania.backend.Character;
import unsw.loopmania.backend.HerosCastle;
import unsw.loopmania.backend.LoopManiaWorld;
import unsw.loopmania.backend.MovingEntity;
import unsw.loopmania.backend.PathPosition;
import unsw.loopmania.backend.Slug;
import unsw.loopmania.backend.TowerAlly;
import unsw.loopmania.backend.TowerBuilding;
import unsw.loopmania.backend.TowerCard;
import unsw.loopmania.backend.TrapBuilding;
import unsw.loopmania.backend.TrapCard;

public class DamageBuildingsTest {
    @Test
    public void trapPathTileTest() {
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

        Card trapCard = world.loadCard(TrapCard.class).getValue0();

        assertDoesNotThrow(() -> world.convertCardToBuildingByCoordinates(trapCard.getX(), trapCard.getY(), 0, 1));
        assertEquals(2, world.getBuildings().size());
        assertTrue(world.getBuildings().get(1) instanceof TrapBuilding);
    }

    @Test
    public void trapNonPathTileTest() {
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

        Card trapCard = world.loadCard(TrapCard.class).getValue0();

        world.convertCardToBuildingByCoordinates(trapCard.getX(), trapCard.getY(), 1, 1);
        assertEquals(1, world.getCards().size());
        assertEquals(trapCard, world.getCards().get(0));
    }

    @Test
    public void trapDamageMonsters() {
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

        Card trapCard = world.loadCard(TrapCard.class).getValue0();
        Building trapBuilding = world.convertCardToBuildingByCoordinates(trapCard.getX(), trapCard.getY(), 0, 1);
        assertEquals(2, world.getBuildings().size());
        assertEquals(trapBuilding, world.getBuildings().get(1));

        Slug slug = new Slug(new PathPosition(1, dummyPath));
        world.addEnemy(slug);
        assertEquals(1, world.getEnemy().size());
        assertEquals(slug, world.getEnemy().get(0));

        world.damageEnemy(null);
        assertEquals(0, world.getEnemy().size());
        assertEquals(1, world.getBuildings().size());
        assertEquals(castle, world.getBuildings().get(0));
    }

    @Test
    public void trapNotDamageCharacter() {
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

        Card trapCard = world.loadCard(TrapCard.class).getValue0();
        Building trapBuilding = world.convertCardToBuildingByCoordinates(trapCard.getX(), trapCard.getY(), 0, 1);
        assertEquals(2, world.getBuildings().size());
        assertEquals(trapBuilding, world.getBuildings().get(1));
        assertTrue(character.getHealth() == 200);

        character.moveDownPath();
        world.damageEnemy(null);

        assertEquals(2, world.getBuildings().size());
        assertEquals(trapBuilding, world.getBuildings().get(1));
        assertTrue(character.getHealth() == 200);
    }

    @Test
    public void towerNonPathTileTest() {
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

        Card towerCard = world.loadCard(TowerCard.class).getValue0();
        assertDoesNotThrow(() -> world.convertCardToBuildingByCoordinates(towerCard.getX(), towerCard.getY(), 1, 1));
        assertEquals(2, world.getBuildings().size());
        assertTrue(world.getBuildings().get(1) instanceof TowerBuilding);
    }

    @Test
    public void towerPathTileTest() {
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

        Card towerCard = world.loadCard(TowerCard.class).getValue0();
        world.convertCardToBuildingByCoordinates(towerCard.getX(), towerCard.getY(), 0, 1);
        assertEquals(1, world.getCards().size());
        assertEquals(towerCard, world.getCards().get(0));
    }

    @Test
    public void towerBattleTest() {
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

        TowerAlly towerAlly = new TowerAlly(new PathPosition(0, dummyPath));

        Slug dummySlug = new Slug(pos);

        ArrayList<MovingEntity> allies = new ArrayList<MovingEntity>();
        allies.add(character);
        allies.add(towerAlly);
        ArrayList<MovingEntity> enemies = new ArrayList<MovingEntity>();
        enemies.add(dummySlug);

        Battle dummyBattle = new Battle(character, allies, enemies);

        //Check that the character, slug and tower are in the battle
        assertTrue(dummyBattle.getParticipants().size() == 3);
        dummyBattle.Fight();
    }
}
