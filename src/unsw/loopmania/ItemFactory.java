package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * A factory class to create items
 */
public class ItemFactory {
    public static Sword createSword(SimpleIntegerProperty x, SimpleIntegerProperty y)
    {
        return new Sword(x, y);
    }

    public static Stake createStake(SimpleIntegerProperty x, SimpleIntegerProperty y)
    {
        return new Stake(x, y);
    }

    public Staff createStaff(SimpleIntegerProperty x, SimpleIntegerProperty y)
    {
        return new Staff(x, y);
    }
    
    public Armour createArmour(SimpleIntegerProperty x, SimpleIntegerProperty y)
    {
        return new Armour(x, y);
    }

    public Shield createShield(SimpleIntegerProperty x, SimpleIntegerProperty y)
    {
        return new Shield(x, y);
    }

    public Helmet createHelmet(SimpleIntegerProperty x, SimpleIntegerProperty y)
    {
        return new Helmet(x, y);
    }
    
    public Gold createGold(SimpleIntegerProperty x, SimpleIntegerProperty y)
    {
        return new Gold(x, y);
    }

    public HealthPotion createHealthPotion(SimpleIntegerProperty x, SimpleIntegerProperty y)
    {
        return new HealthPotion(x, y);
    }

    public TheOneRing createTheOneRing(SimpleIntegerProperty x, SimpleIntegerProperty y)
    {
        return new TheOneRing(x, y);
    }
}