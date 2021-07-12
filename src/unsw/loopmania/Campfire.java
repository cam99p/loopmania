package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Campfire extends Building implements BuffBuildingStrategy {
    public Campfire(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public void buff() {
        
    }
}
