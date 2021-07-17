package unsw.loopmania;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Menu;

public class DeathMenuController {

    MenuSwitcher gameSwitcher;

    MenuSwitcher menuSwitcher;

    private LoopManiaWorldController mainController;

    public DeathMenuController(LoopManiaWorldController mainController) {
        this.mainController = mainController;
    }

    public void setGameSwitcher(MenuSwitcher gameSwitcher){
        this.gameSwitcher = gameSwitcher;
    }

    public void setMenuSwitcher(MenuSwitcher menuSwitcher) {
        this.menuSwitcher = menuSwitcher;
    }

    @FXML
    public void restartGame() throws IOException {
        gameSwitcher.switchMenu();
    }

    @FXML
    public void exitGame() {
        menuSwitcher.switchMenu();
    }
}
