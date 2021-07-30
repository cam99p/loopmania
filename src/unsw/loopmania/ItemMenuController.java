package unsw.loopmania;

import java.io.IOException;

import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.List;
import org.javatuples.Pair;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import unsw.loopmania.ItemFactory.ItemType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class ItemMenuController {
    @FXML 
    private GridPane itemsGridPane;

    @FXML 
    private Button helmetButton;

    @FXML 
    private Button potionButton;

    @FXML 
    private Button swordButton;

    @FXML 
    private Button shieldButton;

    @FXML 
    private Button stakeButton;

    @FXML 
    private Button staffButton;

    @FXML 
    private Button armorButton;

    @FXML 
    private Button continueGameButton;

    @FXML 
    private Label goldValue;

    @FXML 
    private Label xpValue;

    @FXML
    private Label cycleValue;

    @FXML
    private GridPane inventoryGridPane;

    @FXML
    private Pane potionPane;
    @FXML
    private Pane shieldPane;
    @FXML
    private Pane armourPane;
    @FXML
    private Pane helmetPane;
    @FXML
    private Pane stakePane;
    @FXML
    private Pane swordPane;
    @FXML
    private Pane staffPane;
    @FXML
    private Pane sellPane;


    private MenuSwitcher gameSwitcher;

    private LoopManiaWorldController mainController;

    ImageView healthPotionImage = new ImageView(new Image((new File("src/images/brilliant_blue_new.png")).toURI().toString()));
    ImageView swordImage =  new ImageView(new Image((new File("src/images/basic_sword.png")).toURI().toString()));
    ImageView stakeImage = new ImageView(new Image((new File("src/images/stake.png")).toURI().toString()));
    ImageView staffImage = new ImageView(new Image((new File("src/images/staff.png")).toURI().toString()));
    ImageView armourImage = new ImageView(new Image((new File("src/images/armour.png")).toURI().toString()));
    ImageView shieldImage = new ImageView(new Image((new File("src/images/shield.png")).toURI().toString()));
    ImageView theOneRingImage = new ImageView(new Image((new File("src/images/the_one_ring.png")).toURI().toString()));
    ImageView helmetImage = new ImageView(new Image((new File("src/images/helmet.png")).toURI().toString()));

    public ItemMenuController (LoopManiaWorldController mainController) {
        this.mainController = mainController;
    }

    public void setGameSwitcher(MenuSwitcher gameSwitcher) {
        this.gameSwitcher = gameSwitcher;
    }

    @FXML
    public void initialize() {
        potionPane.setVisible(false);
        shieldPane.setVisible(false);
        armourPane.setVisible(false);
        helmetPane.setVisible(false);
        stakePane.setVisible(false);
        swordPane.setVisible(false);
        staffPane.setVisible(false);
        sellPane.setVisible(false);
    }

    @FXML
    public void switchToGame() throws IOException {
        gameSwitcher.switchMenu();
    }

    @FXML 
    public void handlePotion() throws IOException {
        // Check if the player has enough money
        int currentGold = mainController.getGold();
        if(currentGold >= 50 && mainController.getWorldUnequippedInventory().size() < 16) {
            mainController.loadItem(ItemType.HEALTH_POTION);
            setShopInventory();
            mainController.minusGold(50);
            goldValue.setText(mainController.getGoldString());
        }
    }

    @FXML
    public void handleHelmet() throws IOException {
        int currentGold = mainController.getGold();
        if(currentGold >= 300 && mainController.getWorldUnequippedInventory().size() < 16) {
            mainController.loadItem(ItemType.HELMET);
            mainController.minusGold(300);
            setShopInventory();
            goldValue.setText(mainController.getGoldString());
        }
    }

    @FXML
    public void handleSword() throws IOException {
        int currentGold = mainController.getGold();
        if(currentGold >= 300 && mainController.getWorldUnequippedInventory().size() < 16) {
            mainController.loadItem(ItemType.SWORD);
            mainController.minusGold(300);
            setShopInventory();
            goldValue.setText(mainController.getGoldString());
        }
    }

    @FXML
    public void handleShield() throws IOException {
        int currentGold = mainController.getGold();
        if(currentGold >= 300 && mainController.getWorldUnequippedInventory().size() < 16) {
            mainController.loadItem(ItemType.SHIELD);
            mainController.minusGold(300);
            setShopInventory();
            goldValue.setText(mainController.getGoldString());
        }
    }

    @FXML
    public void handleStake() throws IOException {
        int currentGold = mainController.getGold();
        if(currentGold >= 300 && mainController.getWorldUnequippedInventory().size() < 16) {
            mainController.loadItem(ItemType.STAKE);
            mainController.minusGold(300);
            setShopInventory();
            goldValue.setText(mainController.getGoldString());
        }
    }

    @FXML
    public void handleStaff() throws IOException {
        int currentGold = mainController.getGold();
        if(currentGold >= 300 && mainController.getWorldUnequippedInventory().size() < 16) {
            mainController.loadItem(ItemType.STAFF);
            mainController.minusGold(300);
            setShopInventory();
            goldValue.setText(mainController.getGoldString());
        }
    }

    @FXML 
    public void handleArmor() throws IOException {
        int currentGold = mainController.getGold();
        if(currentGold >= 300 && mainController.getWorldUnequippedInventory().size() < 16) {
            mainController.loadItem(ItemType.ARMOUR);
            mainController.minusGold(300);
            setShopInventory();
            goldValue.setText(mainController.getGoldString());
        }
    }

    @FXML
    public void sellAllItems() throws IOException {
        setUneqippedInventory();
        List<Item> removedItems = new ArrayList<>();
        for(Item i : mainController.getWorldUnequippedInventory()) {
            removedItems.add(i);
        }
        for(Item i : removedItems) {
            mainController.removeItemByCoordinates(i.getX(), i.getY());
            mainController.addGold(50);
        }
        setShopInventory();
    }

    @FXML
    public void enterPotion() {
        potionPane.setVisible(true);
    }

    @FXML
    public void exitPotion() {
        potionPane.setVisible(false);
    }

    @FXML
    public void enterShield() {
        shieldPane.setVisible(true);
    }

    @FXML
    public void exitShield() {
        shieldPane.setVisible(false);
    }

    @FXML
    public void enterArmour() {
        armourPane.setVisible(true);
    }

    @FXML
    public void exitArmour() {
        armourPane.setVisible(false);
    }

    @FXML
    public void enterHelmet() {
        helmetPane.setVisible(true);
    }

    @FXML
    public void exitHelmet() {
        helmetPane.setVisible(false);
    }

    @FXML
    public void enterStake() {
        stakePane.setVisible(true);
    }

    @FXML
    public void exitStake() {
        stakePane.setVisible(false);
    }

    @FXML
    public void enterSword() {
        swordPane.setVisible(true);
    }

    @FXML
    public void exitSword() {
        swordPane.setVisible(false);
    }

    @FXML
    public void enterStaff() {
        staffPane.setVisible(true);
    }

    @FXML
    public void exitStaff() {
        staffPane.setVisible(false);
    }

    @FXML
    public void enterSell() {
        sellPane.setVisible(true);
    }

    @FXML
    public void exitSell() {
        sellPane.setVisible(false);
    }


    public void setGoldValue() {
        this.goldValue.setText(mainController.getGoldString());
    }

    public void setExpValue() {
        this.xpValue.setText(mainController.getXpString());
    }

    public void setCycleValue() {
        this.cycleValue.setText(mainController.getCycleString());
    }

    public void setShopInventory() {
        GridPane uneqippedItems = mainController.getUneqippedInventory();
        ObservableList<Node> items = uneqippedItems.getChildren();
        inventoryGridPane.getChildren().addAll(items);
    }

    public void setUneqippedInventory() {
        GridPane unequippedItems = mainController.getUneqippedInventory();
        ObservableList<Node> items = inventoryGridPane.getChildren();
        unequippedItems.getChildren().addAll(items);
    }

    public GridPane getInventory() {
        return inventoryGridPane;
    }
} 
