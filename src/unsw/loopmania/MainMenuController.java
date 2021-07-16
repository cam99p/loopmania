package unsw.loopmania;

import java.io.IOException;
import javafx.fxml.FXML;

/**
 * controller for the main menu.
 * you could extend this, for example with a settings menu, or a menu to load particular maps.
 */
public class MainMenuController {
    /**
     * facilitates switching to level selection
     */
    private MenuSwitcher LevelSwitcher;

    public void setLevelSwitcher(MenuSwitcher LevelSwitcher){
        this.LevelSwitcher = LevelSwitcher;
    }

    /**
     * facilitates switching to level selection upon button click
     * @throws IOException
     */
    @FXML
    private void switchToLevel() throws IOException {
        LevelSwitcher.switchMenu();
    }
}
