package unsw.loopmania;

import java.util.Random;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.ItemFactory.ItemType;

/**
 * Defines the behaviour of a Tree stump
 */
public class TreeStump extends Shield implements RareItem  {

    private ItemType extendedItem;

    public TreeStump(SimpleIntegerProperty x, SimpleIntegerProperty y)
    {
        super(x,y);
        this.extendedItem = null;
        setName("Tree Stump");
        setValue(30);
    }

    public void extendProperty(Stats stats) {
        Random rand = new Random();
        int int_random = rand.nextInt(2);
        if (int_random == 0) {
            extendedItem = ItemType.ANDURIL;
            stats.modifyAttack(30); 
        } else {
            extendedItem = ItemType.THE_ONE_RING;
            stats.setRevive(); 
        }
    }

    public void removeExtendedProperty(Stats stats) {
        if (extendedItem.equals(ItemType.THE_ONE_RING)) {
            stats.unsetRevive();
        } else if (extendedItem.equals(ItemType.ANDURIL)) {
            stats.modifyAttack(-30);
        }
    
    }

}