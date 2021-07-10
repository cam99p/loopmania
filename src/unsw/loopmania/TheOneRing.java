package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Defines the behaviour of The One Ring when used
 */
public class TheOneRing extends Item {
    public TheOneRing(SimpleIntegerProperty x, SimpleIntegerProperty y)
    {
        super(x,y, "The One Ring", Slot.SPECIAL);
        setValue(1);
    }

    public void useItem(Character target)
    {
        
    }

    public void onEquip(StatsInterface stats)
    {
        stats.setRevive();
    }

    public void onDeequip(StatsInterface stats)
    {
        stats.unsetRevive();
    }
}