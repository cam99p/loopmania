package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Defines the behaviour of Anduril, Flame of the west
 */
public class Anduril extends Sword {
    public Anduril(SimpleIntegerProperty x, SimpleIntegerProperty y)
    {
        super(x,y);
        setName("Anduril, Flame of the West");
        setValue(30);
    }
}