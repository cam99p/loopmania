package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class VillageBuilding extends Building implements BuffBuilding {

    private final static int VILLAGE_HEAL = 60;

    public VillageBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    @Override
    public void buff(Character character) {
        if(character.getX() == this.getX() && character.getY() == this.getY()) {
            if(character.getHealth() < 140) {
                character.modifyHealth(VILLAGE_HEAL);
            } else {
                character.setHealth(200);
            }
        }
    }
}
