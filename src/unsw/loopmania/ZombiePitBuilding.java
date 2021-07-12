package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class ZombiePitBuilding extends Building implements SpawnBuildingStrategy {
    public ZombiePitBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public void spawn() {
        
    }
}
