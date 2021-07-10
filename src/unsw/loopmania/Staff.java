package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Defines the behaviour of a Staff when used
 */
public class Staff extends Item {
    public Staff(SimpleIntegerProperty x, SimpleIntegerProperty y)
    {
        super(x,y, "Staff", Slot.RIGHT_ARM);
        setValue(2);
    }

    public void useItem(Character target)
    {
        
    }

    public void onEquip(StatsInterface stats)
    {
        stats.modifyAttack(getValue());
    }

    public void onDeequip(StatsInterface stats)
    {
        stats.modifyAttack(-getValue());
    }
}