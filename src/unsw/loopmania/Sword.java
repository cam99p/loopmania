package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Defines the behaviour of a sword when used
 */
public class Sword extends Item{
    public Sword(SimpleIntegerProperty x, SimpleIntegerProperty y)
    {
        super(x,y, "Sword", Slot.RIGHT_ARM);
        setValue(10);
    }

    public void useItem(Character target)
    {

    }

    public void onEquip(Stats stats)
    {
        stats.modifyAttack(getValue());
    }

    public void onDeequip(Stats stats)
    {
        stats.modifyAttack(-getValue());
    }
}