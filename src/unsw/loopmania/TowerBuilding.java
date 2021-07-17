package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;
import java.util.List;

public class TowerBuilding extends Building {

    private int supportRadius;  
    private boolean inBattle;
    private int attack;

    public TowerBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        attack = 10;
        supportRadius = 3;
        inBattle = false;
    }

    public Building damage(List<BasicEnemy> enemies, List<Building> building, Battle battle) {
        if(isInBattle() && battle != null) {
            MovingEntity target = battle.getTargetEnemy();
            int damage = this.getAttack() - target.getDefense();
            target.setHealth(target.getHealth() - damage);
        }

        return null;
    }

    public boolean isInBattle() {
        return inBattle;
    }

    public void setInBattle(boolean inBattle) {
        this.inBattle = inBattle;
    }

    public int getSupportRadius() {
        return supportRadius;
    }

    public int getAttack() {
        return attack;
    }
}
