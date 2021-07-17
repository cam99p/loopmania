package unsw.loopmania.backend;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.backend.Item.Slot;

/**
 * Defines the behaviour of an Armour when used
 */
public class Armour extends Item {
    public Armour(SimpleIntegerProperty x, SimpleIntegerProperty y)
    {
        super(x,y, "Armour", Slot.CHEST);
        setValue(10);
    }

    public void useItem(Character target)
    {
        
    }

    public void onEquip(Stats stats)
    {
        stats.modifyDefense(getValue());
    }

    public void onDeequip(Stats stats)
    {
        stats.modifyDefense(-getValue());
    }
}