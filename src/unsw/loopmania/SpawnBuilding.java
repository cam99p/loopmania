package unsw.loopmania;

import java.util.List;
import org.javatuples.Pair;

public interface SpawnBuilding {
    public List<BasicEnemy> spawn(List<BasicEnemy> enemies, List<Pair<Integer, Integer>> path, int cycle);
}
