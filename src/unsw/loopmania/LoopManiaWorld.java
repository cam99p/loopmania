package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * A backend world.
 *
 * A world can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 */
public class LoopManiaWorld {
    // TODO = add additional backend functionality

    public static final int unequippedInventoryWidth = 4;
    public static final int unequippedInventoryHeight = 4;

    /**
     * width of the world in GridPane cells
     */
    private int width;

    /**
     * height of the world in GridPane cells
     */
    private int height;

    /**
     * generic entitites - i.e. those which don't have dedicated fields
     */
    private List<Entity> nonSpecifiedEntities;

    private Character character;

    private HerosCastle castle;

    private int cycle;

    // TODO = add more lists for other entities, for equipped inventory items, etc...

    // TODO = expand the range of enemies
    private List<BasicEnemy> enemies;

    // TODO = expand the range of cards
    private List<Card> cardEntities;

    // TODO = expand the range of items
    private List<Entity> unequippedInventoryItems;

    // TODO = expand the range of buildings
    private List<Building> buildingEntities;

    private List<HealthPotion> healthPotions;


    /**
     * list of x,y coordinate pairs in the order by which moving entities traverse them
     */
    private List<Pair<Integer, Integer>> orderedPath;

    /**
     * create the world (constructor)
     * 
     * @param width width of world in number of cells
     * @param height height of world in number of cells
     * @param orderedPath ordered list of x, y coordinate pairs representing position of path cells in world
     */
    public LoopManiaWorld(int width, int height, List<Pair<Integer, Integer>> orderedPath) {
        this.width = width;
        this.height = height;
        nonSpecifiedEntities = new ArrayList<>();
        character = null;
        castle = null;
        cycle = 0;
        enemies = new ArrayList<>();
        cardEntities = new ArrayList<>();
        unequippedInventoryItems = new ArrayList<>();
        this.orderedPath = orderedPath;
        buildingEntities = new ArrayList<>();
        healthPotions = new ArrayList<>();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * set the character. This is necessary because it is loaded as a special entity out of the file
     * @param character the character
     */
    public void setCharacter(Character character) {
        this.character = character;
    }

    public void setCastle(HerosCastle castle) {
        this.castle = castle;
        buildingEntities.add(castle);
    }

    public Character getCharacter() {
        return character;
    }

    /**
     * add a generic entity (without it's own dedicated method for adding to the world)
     * @param entity
     */
    public void addEntity(Entity entity) {
        // for adding non-specific entities (ones without another dedicated list)
        // TODO = if more specialised types being added from main menu, add more methods like this with specific input types...
        nonSpecifiedEntities.add(entity);
    }

    /**
     * spawns slugs if the conditions warrant it, adds to world
     * @return list of the slugs to be displayed on screen
     */
    public List<BasicEnemy> possiblySpawnEnemies(){
        // TODO = expand this very basic version
        Pair<Integer, Integer> pos = possiblyGetBasicEnemySpawnPosition();
        List<BasicEnemy> spawningEnemies = new ArrayList<>();
        if (pos != null){
            int indexInPath = orderedPath.indexOf(pos);
            BasicEnemy enemy = new Slug(new PathPosition(indexInPath, orderedPath));
            enemies.add(enemy);
            spawningEnemies.add(enemy);
        }
        return spawningEnemies;
    }

    /**
     * Adds the specified enemy to the enemies list. For ease of testing
     */
    public void addEnemy(BasicEnemy enemy) {
        enemies.add(enemy);
    }

    /**
     * kill an enemy
     * @param enemy enemy to be killed
     */
    private void killEnemy(BasicEnemy enemy){
        enemy.destroy();
        enemies.remove(enemy);
    }

    /**
     * run the expected battles in the world, based on current world state
     * @return list of enemies which have been killed
     */
    public List<BasicEnemy> runBattles() {
        //old stuff
        //// TODO = modify this - currently the character automatically wins all battles without any damage!
        //List<BasicEnemy> defeatedEnemies = new ArrayList<BasicEnemy>();
        //for (BasicEnemy e: enemies){
        //    // Pythagoras: a^2+b^2 < radius^2 to see if within radius
        //    // TODO = you should implement different RHS on this inequality, based on influence radii and battle radii
        //    if (Math.pow((character.getX()-e.getX()), 2) +  Math.pow((character.getY()-e.getY()), 2) < 4){
        //        // fight...
        //        defeatedEnemies.add(e);
        //    }
        //}

        List<MovingEntity> battleEnemies = gatherEnemies();
        //If there is at least one enemy to fight, start a battle
        if (battleEnemies.size() > 0){
            List<MovingEntity> battleAllies = gatherAllies();
            Battle battle = new Battle(battleAllies,  battleEnemies);
            battle.Fight();

            List<BasicEnemy> defeatedEnemies = battle.getDefeatedEnemies();
            for (BasicEnemy e: defeatedEnemies){
                // IMPORTANT = we kill enemies here, because killEnemy removes the enemy from the enemies list
                // if we killEnemy in prior loop, we get java.util.ConcurrentModificationException
                // due to mutating list we're iterating over
                killEnemy(e);
            }
            return defeatedEnemies;
        } 
        //If theres nothing to fight, return an empty list
        else {
            return new ArrayList<BasicEnemy>();
        }
    }

    /**
     * determines which allies take part in a specific fight, and adds them to a list alongside hero to pass to Battle()
     * @return list of MovingEnititys on the side of good
     */
    public List<MovingEntity> gatherAllies() {
        ArrayList<MovingEntity> allies = new ArrayList<MovingEntity>();
        allies.add(character); //Add character
        //Add ally. Probalby just a bool flag in the character
        //Add towers. Checkk if their xy coords are close enough to chars xy coords
        return allies;
    }

    /**
     * determines which enemies take part in a specific fight, and adds them to a list to pass to Battle()
     * if its count is 0, no battle occurs this iteration
     * @return list of MovingEnititys on the side of good
     */
    public List<MovingEntity> gatherEnemies() {
        ArrayList<MovingEntity> battleEnemies = new ArrayList<MovingEntity>();

        //Check for those in battle range
        for (BasicEnemy e: enemies){
            if (Math.pow((character.getX()-e.getX()), 2) +  Math.pow((character.getY()-e.getY()), 2) < (e.getBattleRadius() * e.getBattleRadius())){
                battleEnemies.add(e);
            }
        }

        //if there is at least one in battle range, look for additional enemies in support range
        if (battleEnemies.size() > 0){
            //Check for those in support range
            for (BasicEnemy e: enemies){
                if (Math.pow((character.getX()-e.getX()), 2) +  Math.pow((character.getY()-e.getY()), 2) < (e.getSupportRadius() * e.getSupportRadius())){
                    //Check it wasnt already added due to being in battle range
                    if (!battleEnemies.contains(e)){
                        battleEnemies.add(e);
                    }

                }
            }   
        }

        return battleEnemies;
    }

    /**
     * spawn a vampire castle card in the world and return the card entity
     * @return a card to be spawned in the controller as a JavaFX node
     */
    public VampireCastleCard loadVampireCard(){
        // if adding more cards than have, remove the first card...
        if (cardEntities.size() >= getWidth()){
            // TODO = give some cash/experience/item rewards for the discarding of the oldest card
            removeCard(0);
        }
        VampireCastleCard vampireCastleCard = new VampireCastleCard(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        cardEntities.add(vampireCastleCard);
        return vampireCastleCard;
    }

    /**
     * spawn a zombie pit card in the world and return the card entity
     * @return a card to be spawned in the controller as a node
     */
    public ZombiePitCard loadZombieCard() {
        if (cardEntities.size() >= getWidth()){
            cardRemovalLoot();
            removeCard(0);
        }
        ZombiePitCard zombiePitCard = new ZombiePitCard(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        cardEntities.add(zombiePitCard);
        return zombiePitCard;
    }

    /**
     * spawn a village card in the world and return the card entity
     * @return a card to be spawned in the controller as a node
     */
    public VillageCard loadVillageCard() {
        if (cardEntities.size() >= getWidth()){
            cardRemovalLoot();
            removeCard(0);
        }
        VillageCard villageCard = new VillageCard(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        cardEntities.add(villageCard);
        return villageCard;
    }

    /**
     * spawn a campfire card in the world and return the card entity
     * @return a card to be spawned in the controller as a node
     */
    public CampfireCard loadCampfireCard() {
        if (cardEntities.size() >= getWidth()){
            cardRemovalLoot();
            removeCard(0);
        }
        CampfireCard campfireCard = new CampfireCard(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        cardEntities.add(campfireCard);
        return campfireCard;
    }

    /**
     * spawn a trap card in the world and return the card entity
     * @return a card to be spawned in the controller as a node
     */
    public TrapCard loadTrapCard() {
        if (cardEntities.size() >= getWidth()){
            cardRemovalLoot();
            removeCard(0);
        }
        TrapCard trapCard = new TrapCard(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        cardEntities.add(trapCard);
        return trapCard;
    }

    /**
     * spawn a tower card in the world and return the card entity
     * @return a card to be spawned in the controller as a node
     */
    public TowerCard loadTowerCard() {
        if (cardEntities.size() >= getWidth()){
            cardRemovalLoot();
            removeCard(0);
        }
        TowerCard towerCard = new TowerCard(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        cardEntities.add(towerCard);
        return towerCard;
    }

    /**
     * Give gold/experience/item rewards for the discarding of the oldest card
     */
    private void cardRemovalLoot() {

    }

    /**
     * remove card at a particular index of cards (position in gridpane of unplayed cards)
     * @param index the index of the card, from 0 to length-1
     */
    private void removeCard(int index){
        Card c = cardEntities.get(index);
        int x = c.getX();
        c.destroy();
        cardEntities.remove(index);
        shiftCardsDownFromXCoordinate(x);
    }

    public Boolean usingPotion() {
        if (!healthPotions.isEmpty() && character.getHealth() < 200) {
            HealthPotion hp = healthPotions.get(0);
            hp.useItem(character);
            removeUnequippedInventoryItemByCoordinates(hp.getX(), hp.getY());
            healthPotions.remove(hp);
            return true;
        }
        return false;
    }

    /**
     * spawn a sword in the world and return the sword entity
     * @return a sword to be spawned in the controller as a JavaFX node
     */
    public Sword addUnequippedSword(){
        // TODO = expand this - we would like to be able to add multiple types of items, apart from swords
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null){
            // eject the oldest unequipped item and replace it... oldest item is that at beginning of items
            // TODO = give some cash/experience rewards for the discarding of the oldest sword
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }
        
        // now we insert the new sword, as we know we have at least made a slot available...
        Sword sword = new Sword(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
        unequippedInventoryItems.add(sword);
        return sword;
    }

    public HealthPotion addUnequippedHealthPotion() {
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null){
            // eject the oldest unequipped item and replace it... oldest item is that at beginning of items
            // TODO = give some cash/experience rewards for the discarding of the oldest sword
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }
        
        // now we insert the new sword, as we know we have at least made a slot available...
        HealthPotion healthPotion = new HealthPotion(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
        unequippedInventoryItems.add(healthPotion);
        healthPotions.add(healthPotion);

        return healthPotion;
    }

    /**
     * remove an item by x,y coordinates
     * @param x x coordinate from 0 to width-1
     * @param y y coordinate from 0 to height-1
     */
    public void removeUnequippedInventoryItemByCoordinates(int x, int y){
        Entity item = getUnequippedInventoryItemEntityByCoordinates(x, y);
        removeUnequippedInventoryItem(item);
    }

    /**
     * run moves which occur with every tick without needing to spawn anything immediately
     */
    public void runTickMoves(){
        character.moveDownPath();
        moveBasicEnemies();
        if(character.getX() == castle.getX() && character.getY() == castle.getY()) {
            cycle++;
        }
    }

    /**
     * remove an item from the unequipped inventory
     * @param item item to be removed
     */
    private void removeUnequippedInventoryItem(Entity item){
        item.destroy();
        unequippedInventoryItems.remove(item);
    }

    /**
     * return an unequipped inventory item by x and y coordinates
     * assumes that no 2 unequipped inventory items share x and y coordinates
     * @param x x index from 0 to width-1
     * @param y y index from 0 to height-1
     * @return unequipped inventory item at the input position
     */
    private Entity getUnequippedInventoryItemEntityByCoordinates(int x, int y){
        for (Entity e: unequippedInventoryItems){
            if ((e.getX() == x) && (e.getY() == y)){
                return e;
            }
        }
        return null;
    }

    /**
     * remove item at a particular index in the unequipped inventory items list (this is ordered based on age in the starter code)
     * @param index index from 0 to length-1
     */
    private void removeItemByPositionInUnequippedInventoryItems(int index){
        Entity item = unequippedInventoryItems.get(index);
        item.destroy();
        unequippedInventoryItems.remove(index);
    }

    /**
     * get the first pair of x,y coordinates which don't have any items in it in the unequipped inventory
     * @return x,y coordinate pair
     */
    private Pair<Integer, Integer> getFirstAvailableSlotForItem(){
        // first available slot for an item...
        // IMPORTANT - have to check by y then x, since trying to find first available slot defined by looking row by row
        for (int y=0; y<unequippedInventoryHeight; y++){
            for (int x=0; x<unequippedInventoryWidth; x++){
                if (getUnequippedInventoryItemEntityByCoordinates(x, y) == null){
                    return new Pair<Integer, Integer>(x, y);
                }
            }
        }
        return null;
    }

    /**
     * shift card coordinates down starting from x coordinate
     * @param x x coordinate which can range from 0 to width-1
     */
    private void shiftCardsDownFromXCoordinate(int x){
        for (Card c: cardEntities){
            if (c.getX() >= x){
                c.x().set(c.getX()-1);
            }
        }
    }

    /**
     * move all enemies
     */
    private void moveBasicEnemies() {
        for (BasicEnemy e: enemies){
            e.move();
        }
    }

    /**
     * get a randomly generated position which could be used to spawn an enemy
     * @return null if random choice is that wont be spawning an enemy or it isn't possible, or random coordinate pair if should go ahead
     */
    // I'm guessing this is to randomly spawn a slug
    private Pair<Integer, Integer> possiblyGetBasicEnemySpawnPosition(){
        // TODO = modify this
        
        // has a chance spawning a basic enemy on a tile the character isn't on or immediately before or after (currently space required = 2)...
        Random rand = new Random();
        int choice = rand.nextInt(2); // TODO = change based on spec... currently low value for dev purposes...
        // TODO = change based on spec
        if ((choice == 0) && (enemies.size() < 2)){
            List<Pair<Integer, Integer>> orderedPathSpawnCandidates = new ArrayList<>();
            int indexPosition = orderedPath.indexOf(new Pair<Integer, Integer>(character.getX(), character.getY()));
            // inclusive start and exclusive end of range of positions not allowed
            int startNotAllowed = (indexPosition - 2 + orderedPath.size())%orderedPath.size();
            int endNotAllowed = (indexPosition + 3)%orderedPath.size();
            // note terminating condition has to be != rather than < since wrap around...
            for (int i=endNotAllowed; i!=startNotAllowed; i=(i+1)%orderedPath.size()){
                orderedPathSpawnCandidates.add(orderedPath.get(i));
            }

            // choose random choice
            Pair<Integer, Integer> spawnPosition = orderedPathSpawnCandidates.get(rand.nextInt(orderedPathSpawnCandidates.size()));

            return spawnPosition;
        }
        return null;
    }

    /**
     * remove a card by its x, y coordinates
     * @param cardNodeX x index from 0 to width-1 of card to be removed
     * @param cardNodeY y index from 0 to height-1 of card to be removed
     * @param buildingNodeX x index from 0 to width-1 of building to be added
     * @param buildingNodeY y index from 0 to height-1 of building to be added
     */
    public Building convertCardToBuildingByCoordinates(int cardNodeX, int cardNodeY, int buildingNodeX, int buildingNodeY) {
        // start by getting card
        Card card = null;
        for (Card c: cardEntities){
            if ((c.getX() == cardNodeX) && (c.getY() == cardNodeY)){
                card = c;
                break;
            }
        }
        
        // now spawn building
        Pair<Integer, Integer> pos = new Pair<Integer, Integer>(buildingNodeX, buildingNodeY);
        Building newBuilding = null;
        boolean checkAdjacent = checkIfAdjacentPathTile(buildingNodeX, buildingNodeY);
        if(card instanceof VampireCastleCard) {
            if(!orderedPath.contains(pos) && checkAdjacent) {
                newBuilding = new VampireCastleBuilding(new SimpleIntegerProperty(buildingNodeX), new SimpleIntegerProperty(buildingNodeY));
            }
        } else if(card instanceof ZombiePitCard) {
            if(!orderedPath.contains(pos) && checkAdjacent) {
                newBuilding = new ZombiePitBuilding(new SimpleIntegerProperty(buildingNodeX), new SimpleIntegerProperty(buildingNodeY));
            }
        } else if(card instanceof BarracksCard) {
            if(orderedPath.contains(pos)) {
                newBuilding = new BarracksBuilding(new SimpleIntegerProperty(buildingNodeX), new SimpleIntegerProperty(buildingNodeY));
            }
        } else if(card instanceof TowerCard) {
            if(!orderedPath.contains(pos) && checkAdjacent) {
                newBuilding = new TowerBuilding(new SimpleIntegerProperty(buildingNodeX), new SimpleIntegerProperty(buildingNodeY));
            }
        } else if(card instanceof VillageCard) {
            if(orderedPath.contains(pos)) {
                newBuilding = new VillageBuilding(new SimpleIntegerProperty(buildingNodeX), new SimpleIntegerProperty(buildingNodeY));
            }
        } else if(card instanceof CampfireCard) {
            if(!orderedPath.contains(pos)) {
                newBuilding = new CampfireBuilding(new SimpleIntegerProperty(buildingNodeX), new SimpleIntegerProperty(buildingNodeY));
            }
        } else if(card instanceof TrapCard) {
            if(orderedPath.contains(pos)) {
                newBuilding = new TrapBuilding(new SimpleIntegerProperty(buildingNodeX), new SimpleIntegerProperty(buildingNodeY));
            }
        }

        if(newBuilding != null) {
            buildingEntities.add(newBuilding);
        } else {
            throw new IllegalArgumentException();
        }

        // destroy the card
        card.destroy();
        cardEntities.remove(card);
        shiftCardsDownFromXCoordinate(cardNodeX);

        return newBuilding;
    }

    public int getCycle() {
        return cycle;
    }

    public List<Building> getBuildings() {
        return buildingEntities;
    }

    public List<BasicEnemy> getEnemy() {
        return enemies;
    }

    public List<BasicEnemy> spawnEnemies() {
        List<BasicEnemy> spawnedEnemies = new ArrayList<>();
        for(Building b : buildingEntities) {
            if(b instanceof VampireCastleBuilding) {
                spawnedEnemies.add(((VampireCastleBuilding) b).spawn(enemies, orderedPath, cycle));
            } else if(b instanceof ZombiePitBuilding) {
                spawnedEnemies.add(((ZombiePitBuilding) b).spawn(enemies, orderedPath, cycle));
            }
        }
        return spawnedEnemies;
    }

    public void buffCharacter() {
        for(Building b : buildingEntities) {
            if(b instanceof VillageBuilding) {
                ((VillageBuilding) b).buff(character);
            } else if(b instanceof CampfireBuilding) {
                ((CampfireBuilding) b).buff(character);
            }
        }
    }

    public void damageEnemy(Battle battle) {
        List<Building> buildingsToRemove = new ArrayList<>();
        for(Building b : buildingEntities) {
            if(b instanceof TrapBuilding) {
                if(((TrapBuilding) b).damage(enemies, buildingEntities, null) != null) {
                    buildingsToRemove.add(b);
                };
            } else if(b instanceof TowerBuilding) {
                ((TowerBuilding) b).damage(enemies, buildingEntities, battle);
            }
        }
        for(Building b : buildingsToRemove) {
            buildingEntities.remove(b);
        }
    }

    public boolean checkIfAdjacentPathTile(int x, int y) {
        Pair<Integer, Integer> posLeft = new Pair<Integer, Integer>(x - 1, y);
        Pair<Integer, Integer> posRight = new Pair<Integer, Integer>(x + 1, y);
        Pair<Integer, Integer> posUp  = new Pair<Integer, Integer>(x, y + 1);
        Pair<Integer, Integer> posDown = new Pair<Integer, Integer>(x, y - 1);
        Pair<Integer, Integer> posLeftUp = new Pair<Integer, Integer>(x - 1, y + 1);
        Pair<Integer, Integer> posRightUp = new Pair<Integer, Integer>(x + 1, y + 1);
        Pair<Integer, Integer> posLeftDown = new Pair<Integer, Integer>(x - 1, y - 1);
        Pair<Integer, Integer> posRightDown = new Pair<Integer, Integer>(x + 1, y - 1);

        if(orderedPath.contains(posLeft) || orderedPath.contains(posRight) || orderedPath.contains(posUp) || orderedPath.contains(posDown) ||
            orderedPath.contains(posLeftUp) || orderedPath.contains(posRightUp) || orderedPath.contains(posLeftDown) || orderedPath.contains(posRightDown)) {
                return true;
            } else {
                return false;
            }

    }
}
