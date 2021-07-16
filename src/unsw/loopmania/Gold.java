package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Defines the behaviour of gold when used
 */
public class Gold extends Item {
    public Gold(SimpleIntegerProperty x, SimpleIntegerProperty y, int value)
    {
        super(x,y, "Gold", null);
        setValue(value);
    }

    public void useItem(Character target)
    {
        
    }

    public void onEquip(Stats stats)
    {
        
    }

    public void onDeequip(Stats stats)
    {
        
    }
}