package unsw.loopmania;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import org.javatuples.Pair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.List;

public class LoopManiaWorldLoaderTest {
    @Test
    public void TestSetComplexGoals() throws FileNotFoundException{
        //setup world
        LoopManiaWorldControllerLoader loopManiaLoader = new LoopManiaWorldControllerLoader("basic_world_with_advanced_goals.json");
        //For some reason, it can't find the file?
        LoopManiaWorld world = loopManiaLoader.load();

        //Generate matching goals structure
        //Structure is:
        //           AND
        //         /     \
        //    cycles       OR
        //               /    \
        //            exp      gold

        assertTrue(world.getGoal() instanceof GoalAND);

        GoalAND and = (GoalAND)world.getGoal();
        assertTrue(and.getG1() instanceof GoalSINGLE);
        assertTrue(and.getG2() instanceof GoalOR);

        GoalOR or = (GoalOR)and.getG2();
        assertTrue(or.getG1() instanceof GoalSINGLE);
        assertTrue(or.getG2() instanceof GoalSINGLE);

        GoalSINGLE cycle = (GoalSINGLE)and.getG1();
        assertTrue(cycle.getType().equals("cycles"));
        assertTrue(cycle.getAmount() == 100);

        GoalSINGLE exp = (GoalSINGLE)or.getG1();
        assertTrue(exp.getType().equals("experience"));
        assertTrue(exp.getAmount() == 123456);

        GoalSINGLE gold = (GoalSINGLE)or.getG2();
        assertTrue(gold.getType().equals("gold"));
        assertTrue(gold.getAmount() == 900000);



        
    }
}
