package unsw.loopmania;

import java.util.List;

public interface DamageBuilding {
    public Building damage(List<BasicEnemy> enemies, List<Building> building, Battle battle);
}
