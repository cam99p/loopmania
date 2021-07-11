package unsw.loopmania;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;

import org.javatuples.Pair;

import unsw.loopmania.LoopManiaWorld;



/**
 * this class is a dummy class demonstrating how to setup tests for the project
 * you should setup additional test classes in a similar fashion, aiming to achieve high coverage.
 * A clickable "Run Test" link should appear if you have installed the Java Extension Pack properly.
 */
public class HealthTest {
    @Test
    public void consumePotion() {

        ArrayList<Pair<Integer, Integer>> path = new ArrayList<>(Arrays.asList(new Pair<>(0,0), new Pair<>(0,1), new Pair<>(0,2)));
        PathPosition pathPosition = new PathPosition(0, path);
        Character c = new Character(pathPosition); 
        c.characterStats.modifyHealth(100);
        HealthPotion potion = new HealthPotion(new SimpleIntegerProperty(1), new SimpleIntegerProperty(0));
        potion.useItem(c);
        assertTrue(c.characterStats.getHealth() == 200); 

        // When the health bar is less than 0, the player loses the game

        //


        
    }

}
