package unsw.loopmania;

import java.util.Random;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.ItemFactory.ItemType;

/**
 * Defines the behaviour of Anduril, Flame of the west
 */
public class Anduril extends Sword implements RareItem {

    private ItemType extendedItem;

    public Anduril(SimpleIntegerProperty x, SimpleIntegerProperty y)
    {
        super(x,y);
        this.extendedItem = null;
        setName("Anduril, Flame of the West");
        setValue(30);
    }

    public void extendProperty(Stats stats) {
        Random rand = new Random();
        int int_random = rand.nextInt(2);
        System.out.println(int_random);
        if (int_random == 0) {       
            extendedItem = ItemType.THE_ONE_RING;
            stats.setRevive(); 
        } else {
            extendedItem = ItemType.TREE_STUMP;
            stats.modifyDefense(30); 
        }
    }

    public void removeExtendedProperty(Stats stats) {
        if (extendedItem.equals(ItemType.THE_ONE_RING)) {
            stats.unsetRevive();
        } else if (extendedItem.equals(ItemType.TREE_STUMP)) {
            stats.modifyDefense(-30);
        }
        
    }
}