package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Trap extends Building implements DamageBuildingStrategy {
    public Trap(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public void damage() {
        
    }
}
