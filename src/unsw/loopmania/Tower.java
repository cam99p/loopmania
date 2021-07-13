package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Tower extends Building implements DamageBuildingStrategy {
    public Tower(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public void damage() {
        
    }
}
