package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Stats;
import unsw.loopmania.Character;

/**
 * Defines the behaviour of Health Potion when used
 */
public class HealthPotion extends Item {
    public HealthPotion(SimpleIntegerProperty x, SimpleIntegerProperty y)
    {
        super(x,y, "Health Potion", Slot.POTION);
        setValue(50);
    }

    public void useItem(Character target)
    { 
        target.setHealth(200);
    }

    public void onEquip(Stats stats)
    {
        return;
    }

    public void onDeequip(Stats stats)
    {
        return;
    }
}