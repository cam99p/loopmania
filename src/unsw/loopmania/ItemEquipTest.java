package unsw.loopmania;

import org.junit.Test;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Item.Slot;
import unsw.loopmania.ItemFactory.ItemType;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemEquipTest {
    private ItemFactory itemFactory = new ItemFactory();
      //Tests that the characters unarmed attack does the right amount of damage
    @Test
    public void TestCreateSword(){
         Item test = itemFactory.createItem(ItemType.SWORD, new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
         
    }
    @Test
    public void TestCreateStake(){
         Item test = itemFactory.createItem(ItemType.STAKE, new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
         
    }

    @Test
    public void TestCreateStaff(){
         Item test = itemFactory.createItem(ItemType.STAFF, new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
         
    }

    @Test
    public void TestCreateArmour(){
         Item test = itemFactory.createItem(ItemType.ARMOUR, new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
         
    }

    @Test
    public void TestCreateShield(){
          Item test = itemFactory.createItem(ItemType.SHIELD, new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
          
    }
    
    @Test
    public void TestCreateHelmet(){
          
    }

    @Test
    public void TestCreateHealthPotion(){
          Item test = itemFactory.createItem(ItemType.HEALTH_POTION, new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
          
    }

    @Test
    public void TestCreateTheOneRing(){
          Item test = itemFactory.createItem(ItemType.THE_ONE_RING, new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
          
    }
}