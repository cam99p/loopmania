package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;
import java.util.List;
import java.util.ArrayList;
import org.javatuples.Pair;

/**
 * a basic form of building in the world
 */
public class VampireCastleBuilding extends Building implements SpawnBuilding {

    public VampireCastleBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    @Override
    public List<BasicEnemy> spawn(List<BasicEnemy> enemies, List<Pair<Integer, Integer>> path, int cycle) {
        List<BasicEnemy> spawningEnemies = null;
        if(cycle%5 == 0) {
            PathPosition pos = possiblyGetSpawnPosition(path);
            if(pos != null) {
                spawningEnemies = new ArrayList<>();
                Vampire newVamp = new Vampire(pos);
                enemies.add(newVamp);
                spawningEnemies.add(newVamp);
            }
        }
        return spawningEnemies;
    }
}
