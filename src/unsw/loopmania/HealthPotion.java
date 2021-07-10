package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

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
        int increasedHealth = (int)(target.characterStats.getHealth()*(130.0f/100.0f));   
        target.characterStats.modifyHealth(increasedHealth);
    }

    public void onEquip(StatsInterface stats)
    {
        return;
    }

    public void onDeequip(StatsInterface stats)
    {
        return;
    }
}