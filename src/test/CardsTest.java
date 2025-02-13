package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;
import org.javatuples.Pair;

import unsw.loopmania.BarracksCard;
import unsw.loopmania.BasicEnemy;
import unsw.loopmania.Card;
import unsw.loopmania.Character;
import unsw.loopmania.HerosCastle;
import unsw.loopmania.Item;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Slug;
import unsw.loopmania.TowerCard;
import unsw.loopmania.TrapCard;
import unsw.loopmania.VampireCastleCard;
import unsw.loopmania.VillageCard;
import unsw.loopmania.ZombiePitCard;

public class CardsTest {
    @Test
    public void testRandomCard() {
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

        List<Pair<Card,Item>> cards = new ArrayList<>();
        for(int i = 0; i < 200; i++) {
            Pair<Card, Item> cardPair = world.randomCard();
            cards.add(cardPair);
        }
        
        boolean vamp = false;
        boolean tower = false;
        boolean zombie = false;
        boolean village = false;
        boolean barracks = false;
        boolean trap = false;
        boolean campfire = false;
        for(Pair<Card,Item> pair : cards) {
            if(pair.getValue0().getClass() == VampireCastleCard.class) {
                vamp = true;
            } else if(pair.getValue0().getClass() == TowerCard.class) {
                tower = true;
            } else if(pair.getValue0().getClass() == ZombiePitCard.class) {
                zombie = true;
            } else if(pair.getValue0().getClass() == BarracksCard.class) {
                barracks = true;
            } else if(pair.getValue0().getClass() == TrapCard.class) {
                trap = true;
            } else if (pair.getValue0().getClass() == VillageCard.class) {
                village = true;
            } else {
                campfire = true;
            }
        }
        if(vamp && tower && zombie && village && barracks && trap && campfire) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }

    @Test
    public void testRandomItemFromCard() {
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

        List<Pair<Card,Item>> cards = new ArrayList<>();
        for(int i = 0; i < 300; i++) {
            Pair<Card, Item> cardPair = world.loadCard(VampireCastleCard.class);
            cards.add(cardPair);
        }
        
        assertTrue(world.getCards().size() > 0);
    }

    @Test 
    public void testCardFromBattle() {
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

        //Slug
        Slug dummySlug = new Slug(pos);
        world.addEnemy(dummySlug);

        //Run battles
        List<BasicEnemy> defeated = world.runBattles();

        //Rewards
        world.GainBattleRewards(defeated);
        world.randomCard();

        assertEquals(1, world.getCards().size());
    }
}
