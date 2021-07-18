package unsw.loopmania;


/**
 * An interface for methods unique to item type 
 */
public interface ItemInterface {
    public void useItem(Character target);
    public void onEquip(Stats stats);
    public void onDeequip(Stats stats);
}