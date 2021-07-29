package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Defines the behaviour of a Tree stump
 */
public class TreeStump extends Shield {
    public TreeStump(SimpleIntegerProperty x, SimpleIntegerProperty y)
    {
        super(x,y);
        setName("Tree Stump");
        setValue(30);
    }

}