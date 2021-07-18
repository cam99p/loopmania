package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * A factory class to create items
 */
public class ItemFactory {
    public enum ItemType{
        SWORD,
        STAKE,
        STAFF,
        ARMOUR,
        SHIELD,
        HELMET,
        HEALTH_POTION,
        THE_ONE_RING
    }

    public static final int size;
       static {
          size = ItemType.values().length;
       }
    
    public Item createItem(ItemType type, SimpleIntegerProperty x, SimpleIntegerProperty y)
    {
        switch(type){
            case SWORD:
               return new Sword(x, y);
            case STAKE:
               return new Stake(x, y);
            case STAFF:
                return new Staff(x, y);
            case ARMOUR:
                return new Armour(x, y);
            case SHIELD:
                return new Shield(x, y);
            case HELMET:
                return new Helmet(x, y);
            case HEALTH_POTION:
                return new HealthPotion(x, y);
            case THE_ONE_RING:
                return new TheOneRing(x, y);
        }
        return null;
    }
    
    public int getSpawnableItems() {
        return size;
    }
}