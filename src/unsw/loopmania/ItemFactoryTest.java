package unsw.loopmania;

import org.junit.Test;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Item.Slot;
import unsw.loopmania.ItemFactory.ItemType;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemFactoryTest {
    private ItemFactory itemFactory = new ItemFactory();
      //Tests that the characters unarmed attack does the right amount of damage
    @Test
    public void TestCreateSword(){
         Item test = itemFactory.createItem(ItemType.SWORD, new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
         assertTrue(test.getClass() == Sword.class);
         assertTrue(test.getName().equals("Sword"));
         assertTrue(test.getSlot().equals(Slot.RIGHT_ARM));
         assertTrue(test.getValue() == 15);
    }
    @Test
    public void TestCreateStake(){
         Item test = itemFactory.createItem(ItemType.STAKE, new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
         assertTrue(test.getClass() == Stake.class);
         assertTrue(test.getName().equals("Stake"));
         assertTrue(test.getSlot().equals(Slot.RIGHT_ARM));
         assertTrue(test.getValue() == 7);
    }

    @Test
    public void TestCreateStaff(){
         Item test = itemFactory.createItem(ItemType.STAFF, new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
         assertTrue(test.getClass() == Staff.class);
         assertTrue(test.getName().equals("Staff"));
         assertTrue(test.getSlot().equals(Slot.RIGHT_ARM));
         assertTrue(test.getValue() == 5);
    }

    @Test
    public void TestCreateArmour(){
         Item test = itemFactory.createItem(ItemType.ARMOUR, new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
         assertTrue(test.getClass() == Armour.class);
         assertTrue(test.getName().equals("Armour"));
         assertTrue(test.getSlot().equals(Slot.CHEST));
         assertTrue(test.getValue() == 10);
    }

    @Test
    public void TestCreateShield(){
          Item test = itemFactory.createItem(ItemType.SHIELD, new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
          assertTrue(test.getClass() == Shield.class);
          assertTrue(test.getName().equals("Shield"));
          assertTrue(test.getSlot().equals(Slot.LEFT_ARM));
          assertTrue(test.getValue() == 5);
    }
    
    @Test
    public void TestCreateHelmet(){
          Item test = itemFactory.createItem(ItemType.HELMET, new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
          assertTrue(test.getClass() == Helmet.class);
          assertTrue(test.getName().equals("Helmet"));
          assertTrue(test.getSlot().equals(Slot.HEAD));
          assertTrue(test.getValue() == 5);
    }
    /*
    @Test
    public void TestCreateGold(){
          Item test = itemFactory.createItem(ItemType.GOLD, new SimpleIntegerProperty(0), new SimpleIntegerProperty(0), 100);
          assertTrue(test.getClass() == Gold.class);
          assertTrue(test.getName().equals("Gold"));
          assertTrue(test.getValue() == 100);
    }
    */
    @Test
    public void TestCreateHealthPotion(){
          Item test = itemFactory.createItem(ItemType.HEALTH_POTION, new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
          assertTrue(test.getClass() == HealthPotion.class);
          assertTrue(test.getName().equals("Health Potion"));
          assertTrue(test.getSlot().equals(Slot.POTION));
          assertTrue(test.getValue() == 50);
    }

    @Test
    public void TestCreateTheOneRing(){
          Item test = itemFactory.createItem(ItemType.THE_ONE_RING, new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
          assertTrue(test.getClass() == TheOneRing.class);
          assertTrue(test.getName().equals("The One Ring"));
          assertTrue(test.getSlot().equals(Slot.SPECIAL));
          assertTrue(test.getValue() == 1);
    }
}
