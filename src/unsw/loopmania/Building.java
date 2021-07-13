package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;
import java.util.List;
import org.javatuples.Pair;

public class Building extends StaticEntity {

    public Building(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public PathPosition possiblyGetSpawnPosition(List<Pair<Integer, Integer>> path) {
        // 8 path tiles possibly arround the vampire castle
        Pair<Integer, Integer> castlePosLeft = new Pair<Integer, Integer>(this.getX() - 1, this.getY());
        Pair<Integer, Integer> castlePosRight = new Pair<Integer, Integer>(this.getX() + 1, this.getY());
        Pair<Integer, Integer> castlePosUp  = new Pair<Integer, Integer>(this.getX(), this.getY() + 1);
        Pair<Integer, Integer> castlePosDown = new Pair<Integer, Integer>(this.getX(), this.getY() - 1);
        Pair<Integer, Integer> castlePosLeftUp = new Pair<Integer, Integer>(this.getX() - 1, this.getY() + 1);
        Pair<Integer, Integer> castlePosRightUp = new Pair<Integer, Integer>(this.getX() + 1, this.getY() + 1);
        Pair<Integer, Integer> castlePosLeftDown = new Pair<Integer, Integer>(this.getX() - 1, this.getY() - 1);
        Pair<Integer, Integer> castlePosRightDown = new Pair<Integer, Integer>(this.getX() + 1, this.getY() - 1);

        boolean castlePosLeftBool = false;
        boolean castlePosRightBool = false;
        boolean castlePosUpBool = false;
        boolean castlePosDownBool = false;
        boolean castlePosLeftUpBool = false;
        boolean castlePosRightUpBool = false;
        boolean castlePosLeftDownBool = false;
        boolean castlePosRightDownBool = false;

        if(path.contains(castlePosLeft)) {
            castlePosLeftBool = true;
        }
        if(path.contains(castlePosRight)) {
            castlePosRightBool = true;
        }
        if(path.contains(castlePosUp)) {
            castlePosUpBool = true;
        }
        if(path.contains(castlePosDown)) {
            castlePosDownBool = true;
        }
        if(path.contains(castlePosLeftUp)) {
            castlePosLeftUpBool = true;
        }
        if(path.contains(castlePosRightUp)) {
            castlePosRightUpBool = true;
        }
        if(path.contains(castlePosLeftDown)) {
            castlePosLeftDownBool = true;
        }
        if(path.contains(castlePosRightDown)) {
            castlePosRightDownBool = true;
        }
        PathPosition spawnPos = null;

        // Spawns from order left path tile and then preceeds clockwise 
        // If there are no immediate squares left, up, right or down, it will 
        // try spawn a zombie in the diagonals starting from left down and preceeds clockwise
        if(castlePosLeftBool) {
            spawnPos = new PathPosition(path.indexOf(castlePosLeft), path);
        } else if(castlePosUpBool) {
            spawnPos = new PathPosition(path.indexOf(castlePosUp), path);
        } else if(castlePosRightBool) {
            spawnPos = new PathPosition(path.indexOf(castlePosRight), path);
        } else if(castlePosDownBool) {
            spawnPos = new PathPosition(path.indexOf(castlePosDown), path);
        } else if(castlePosLeftDownBool) {
            spawnPos = new PathPosition(path.indexOf(castlePosLeftDown), path);
        } else if(castlePosLeftUpBool) {
            spawnPos = new PathPosition(path.indexOf(castlePosLeftUp), path);
        } else if(castlePosRightUpBool) {
            spawnPos = new PathPosition(path.indexOf(castlePosRightUp), path);
        } else if(castlePosRightDownBool) {
            spawnPos = new PathPosition(path.indexOf(castlePosRightDown), path);
        } else {
            throw new IllegalArgumentException("Vampire castle is not adjacent to a Path tile\n");
        }
        
        return spawnPos;
    }
}
