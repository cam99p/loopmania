package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.List;

public class TrapBuilding extends Building {

    private final static int TRAP_DAMAGE = 100;

    public TrapBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public Building damage(List<BasicEnemy> enemies, List<Building> buildings) {
        BasicEnemy potentialDeadEnemy = null;
        for(BasicEnemy e : enemies) {
            if(e.getX() == this.getX() && e.getY() == this.getY()) {
                e.damageHealth(TRAP_DAMAGE);
                if(e.getHealth() <= 0) {
                    potentialDeadEnemy = e;
                }

            }
        }
        // Remove all enemies killed off by traps
        if(potentialDeadEnemy != null) {
            enemies.remove(potentialDeadEnemy);
            potentialDeadEnemy.destroy();
            return this;
        } else {
            return null;
        }
    }
}
