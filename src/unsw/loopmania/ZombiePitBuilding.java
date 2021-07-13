package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;
import java.util.List;
import java.util.ArrayList;
import org.javatuples.Pair;

public class ZombiePitBuilding extends Building implements SpawnBuilding {
    public ZombiePitBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    @Override
    public List<BasicEnemy> spawn(List<BasicEnemy> enemies, List<Pair<Integer, Integer>> path, int cycle) {
        List<BasicEnemy> spawningEnemies = null;
        PathPosition pos = possiblyGetSpawnPosition(path);
        if(pos != null) {
            spawningEnemies = new ArrayList<>();
            Zombie newZomb = new Zombie(pos);
            enemies.add(newZomb);
            spawningEnemies.add(newZomb);
        }
        return spawningEnemies;
    }
}
