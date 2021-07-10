package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Defines the behaviour of gold when used
 */
public class Gold extends Item {
    public Gold(SimpleIntegerProperty x, SimpleIntegerProperty y)
    {
        super(x,y, "Gold", null);
        setValue(50);
    }

    public void useItem(Character target)
    {
        
    }

    public void onEquip(StatsInterface stats)
    {
        stats.modifyGold(getValue());
    }

    public void onDeequip(StatsInterface stats)
    {
        stats.modifyGold(-getValue());
    }
}