package unsw.loopmania.backend;

import java.util.List;

public interface DamageBuilding {
    public Building damage(List<BasicEnemy> enemies, List<Building> building);
}
