package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Village extends Building implements BuffBuildingStrategy {
    public Village(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public void buff() {
        
    }
}
