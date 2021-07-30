package unsw.loopmania;

import java.io.IOException;
import javafx.fxml.FXML;
import unsw.loopmania.GameMode.Mode;

/**
 * controller for the level selection.
 */
public class LevelController {
    /**
     * facilitates switching to main game
     */
    private MenuSwitcher gameSwitcher;

    private Mode gameMode;

    public void setGameSwitcher(MenuSwitcher gameSwitcher){
        this.gameSwitcher = gameSwitcher;
    }

    /**
     * facilitates switching to main game upon button click
     * @throws IOException
     */
    @FXML
    private void switchToStandard() throws IOException {
        this.gameMode = Mode.STANDARD;
        gameSwitcher.switchMenu();
    }

    @FXML
    private void switchToSurvival() throws IOException {  
        this.gameMode = Mode.SURVIVAL;
        gameSwitcher.switchMenu();
    }

    @FXML
    private void switchToBerserker() throws IOException {
        this.gameMode = Mode.BERSERKER;
        gameSwitcher.switchMenu();     
    }

    @FXML
    private void switchToConfusing() throws IOException {
        this.gameMode = Mode.CONFUSING;
        gameSwitcher.switchMenu();     
    }

    public Mode getGameMode() {
        return gameMode;
    }
}
