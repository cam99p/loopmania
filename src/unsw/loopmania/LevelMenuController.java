package unsw.loopmania;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LevelMenuController {

    @FXML 
    private Label goldValue1;

    @FXML 
    private Label xpValue1;

    @FXML
    private Label cycleValue1;

    private MenuSwitcher itemSwitcher;

    private MenuSwitcher gameSwitcher;

    private LoopManiaWorldController mainController;

    public LevelMenuController(LoopManiaWorldController mainController) {
        this.mainController = mainController;
    }

    public void setGameSwitcher(MenuSwitcher gameSwitcher) {
        this.gameSwitcher = gameSwitcher;
    }

    public void setItemSwitcher(MenuSwitcher itemSwitcher) {
        this.itemSwitcher = itemSwitcher;
    } 

    @FXML
    public void switchToGame() throws IOException {
        gameSwitcher.switchMenu();
    }

    @FXML
    public void switchToLevelShop() throws IOException {
        itemSwitcher.switchMenu();
    }

    @FXML
    public void handleAttack() {
        if(mainController.getXp() >= 1000) {
            mainController.getCharacter().modifyAttack(1);
            mainController.minusXp(1000);
            xpValue1.setText(mainController.getXpString());
        }
    }

    @FXML
    public void handleSpeed() {
        if(mainController.getXp() >= 1000) {
            mainController.getCharacter().modifySpeed(1);
            mainController.minusXp(1000);
            xpValue1.setText(mainController.getXpString());
        }
    }

    @FXML
    public void handleDefense() {
        if(mainController.getXp() >= 1000) {
            mainController.getCharacter().modifyDefense(1);
            mainController.minusXp(1000);
            xpValue1.setText(mainController.getXpString());
        }
    }

    @FXML
    public void handleHealth() {
        if(mainController.getXp() >= 1000) {
            mainController.getCharacter().modifyMaxHealth(25);
            mainController.getCharacter().modifyHealth(25);
            mainController.minusXp(1000);
            xpValue1.setText(mainController.getXpString());
        }
    }

    public void setGoldValue() {
        this.goldValue1.setText(mainController.getGoldString());
    }

    public void setExpValue() {
        this.xpValue1.setText(mainController.getXpString());
    }

    public void setCycleValue() {
        this.cycleValue1.setText(mainController.getCycleString());
    }
}
