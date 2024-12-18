package edu.westga.cs3211.text_adventure_game.model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import edu.westga.cs3211.text_adventure_game.datatier.ItemReader;
import edu.westga.cs3211.text_adventure_game.datatier.LocationReader;
import edu.westga.cs3211.text_adventure_game.datatier.NpcReader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * The game manager class
 * @author Colby, Jacob, and Kate
 * @version Fall 2024
 */
public class GameManager {
	public static final int MAX_WEIGHT = 100;
	private static final String YOU_ARE_DEAD_MSG = "DEAD";
	private static final String LOCATIONS_TXT_FILE = "Locations.txt";
	private Player player;
	private List<Location> allLocations;
	private List<Item> allItems;
	private List<Action> allActions;
	private Location currLocation;
	private LocationReader locationReader;
	private ItemReader itemReader;
	private Boolean playerHasWon;

	private NpcReader npcReader;
	private List<Npc> allNpcs;
	private NpcManager npcManager;

	private String itemStatus;

	/** Creates an instance of the game manager class
	 * 
	 * @postcondition player != null && locationReader != null && allLocations != null && currentLocation != null
	 */
	public GameManager() {
		this.setWorldsFeature();
		this.setupPlayer();
		this.initializeStartLocation();
		this.playerHasWon = false;
		
		this.setupActions();
	}
	
	private void initializeStartLocation() {
		this.currLocation = this.allLocations.get(0);
		this.currLocation.addAction(new PickUpItem(this.allItems.get(0)));
		this.npcManager.addNpcByIndex(5, 1, this.currLocation, 1);
		this.npcManager.addNpcByIndex(0, 1, this.currLocation, 4);
	}

	/** reads through all of the files and set up the worlds: location, NPCs, and Items
	 * 
	 */
	private void setWorldsFeature() {
		File locationFile = new File(LOCATIONS_TXT_FILE);
		this.locationReader = new LocationReader(locationFile);
		this.allLocations = this.locationReader.readLocations();
		
		File itemFile = new File("Items.txt");
		this.itemReader = new ItemReader(itemFile);
		this.allItems = this.itemReader.readItems();
		
		File npcFile = new File("Npc.txt");
		this.npcReader = new NpcReader(npcFile);
		this.allNpcs = this.npcReader.readNpcs();
		this.npcManager = new NpcManager(this.allNpcs, this.allItems);
	}
	
	/**
	 * Sets up all the actions from the location
	 * @postcondition: this.allActions == this.currLocation.getActions()
	 */
	public void setupActions() {
		this.allActions = this.currLocation.getActions();
	}
	
	private void setupPlayer() {
		ArrayList<Item> startingItems = new ArrayList<Item>();
		startingItems.add(this.allItems.get(5));
		this.player = new Player(startingItems);
	}
	
	/**
	 * Gets the actions for the current location of the player
	 * @return the collection of actions
	 */
	public List<Action> getActions() {
		return this.allActions;
	}
	
	/**
	 * Gets the description for the current location of the player
	 * @return the description of the location
	 */
	public String getLocationDescription() {
		return this.currLocation.getDescription();
	}
	
	/**
	 * Gets the name of the current location of the player
	 * @return the location name
	 */
	public String getLocationName() {
		return this.currLocation.getName();
	}
	
	/**
	 * Gets the status of the player
	 * @return the status of the player
	 */
	public String getPlayerStatus() {
		if (this.player.getIsDead()) {
			return YOU_ARE_DEAD_MSG;
		}
		return String.valueOf(this.player.getHealth());
	}
	
	/**
	 * Updates the location after a move action and potentially adds NPCs to the new location.
	 * 
	 * @param action the move action taken
	 * @precondition: action != null
	 * @postcondition: this.currLocation == newLocation
	 */
	public void updateLocation(Move action) {
	    if (action == null) {
	        throw new IllegalArgumentException(Location.ACTIONS_CANNOT_BE_NULL);
	    }

	    this.currLocation = action.takeAction(this.currLocation.getAdjacentLocations(), this.allLocations);
	    this.checkForTrapLocation();
	    this.checkForGoalLocation();
	    this.setupActions();
	    this.itemStatus = "You moved to a new location. ";
	    this.npcManager.addNpcsToLocation(this.currLocation);
	}
	
	/**
	 * Use a players item out of their inventory
	 * @param action the action to take on the item
	 * @return true or false based on if the action was completed
	 */
	public Boolean usePlayerActionableItem(ActionableItem action) {
		if (action == null) {
			throw new IllegalArgumentException("action cannot be null");
		}
		boolean tOrF = action.takeAction(this.player, this.currLocation);
		if (action instanceof DropItem) {
			this.itemStatus = "You have dropped " + action.getItem().getName();
		}
		if (action instanceof UseItem) {
		    this.itemStatus = "You have used " + action.getItem().getName();
		    Item item = action.getItem();
		    if (tOrF && (item.getName().toLowerCase().contains("potion"))) {
		        this.player.getInventory().remove(item);
		    }
		}
		if (action instanceof PickUpItem) {
			this.itemStatus = "You have picked up " + action.getItem().getName();
		}
		return tOrF;
	}

	private void checkForTrapLocation() {
		if (this.currLocation instanceof TrapLocation) {
			TrapLocation trapLocation = (TrapLocation) this.currLocation;
			this.applyDamageInflicted(trapLocation);
		}
	}

	private void applyDamageInflicted(TrapLocation trapLocation) {
		int damageApplied = trapLocation.getDamageInflicted();
		int originalHealth = this.player.getHealth();
		int newHealth = originalHealth - damageApplied;
		this.player.setHealth(newHealth);
	}
	
	private void checkForGoalLocation() {
		if (this.currLocation.getLocationType() == LocationType.Goal) {
			this.playerHasWon = true;
		}
	}

	/**
	 * Gets the player
	 * @return the player
	 */
	public Player getPlayer() {
		return this.player;
	}

	/**
	 * Gets all the locations
	 * @return the allLocations
	 */
	public List<Location> getAllLocations() {
		return this.allLocations;
	}
	
	/**
	 * Gets the current location
	 * @return the current location
	 */
	public Location getCurrLocation() {
		return this.currLocation;
	}

	/**
	 * Gets the location reader
	 * @return the locationReader
	 */
	public LocationReader getLocationReader() {
		return this.locationReader;
	}
	
	/**
	 * Gets the description of the available actions
	 * @return the description 
	 */
	public String getAvailableActionsDescription() {
		StringBuilder actionDescriptions = new StringBuilder();
		for (Action action : this.allActions) {
			actionDescriptions.append(action.getDescription() + System.lineSeparator());
		}
		return actionDescriptions.toString();
	}
	
	/**
	 * Gets if the player has won
	 * @return true or false based on if the player has won
	 */
	public Boolean getPlayerHasWon() {
		return this.playerHasWon;
	}
	
	/**
	 * calls the player to interact with npc
	 * 
	 * @param npcAction the npc being interacted with
	 * 
	 */
	public void interactWithNpc(NpcInteract npcAction) {

		if (npcAction.isNpcMerchant()) {
	        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	        alert.setTitle("Merchant");
	        alert.setHeaderText("Choose an Item");
	        alert.setContentText("Click a button to select an item or close to exit:");

	        List<Item> merchantInventory = npcAction.getNpc().getItems();
	        Map<ButtonType, Item> buttonItemMap = new HashMap<>();
	        List<ButtonType> buttonTypes = new ArrayList<>();
	        for (Item item : merchantInventory) {
	            ButtonType button = new ButtonType(item.toString() + "\nPrice: " + item.getPrice() + "\nWeight: " + item.getWeight());
	            buttonTypes.add(button);
	            buttonItemMap.put(button, item); 
	        }

	        ButtonType closeButton = new ButtonType("Exit");
	        buttonTypes.add(closeButton);
	        alert.getButtonTypes().setAll(buttonTypes);
	        Optional<ButtonType> result = alert.showAndWait();

	        if (result.isPresent()) {
	            this.handleButtonPress(npcAction, buttonItemMap, closeButton, result);
	            return;
	        } else {
	        	this.itemStatus = "Dialog was closed without any selection.";
	        	return;
	        }
	    }
	    this.itemStatus = npcAction.takeAction(this.player, this.currLocation);
	}

	private void handleButtonPress(NpcInteract npcAction, Map<ButtonType, Item> buttonItemMap, ButtonType closeButton,
			Optional<ButtonType> result) {
		ButtonType selectedButton = result.get();
		if (selectedButton == closeButton) {
		    this.itemStatus = "Closed the merchant trading";
		    return;
		} else {
		    Item selectedItem = buttonItemMap.get(selectedButton);
		    if (selectedItem != null) {
		        this.handleItemSelected(npcAction, selectedItem);
		    }
		}
	}

	private void handleItemSelected(NpcInteract npcAction, Item selectedItem) {
		if (this.player.getCoins() >= selectedItem.getPrice() && GameManager.MAX_WEIGHT < this.getPlayer().getTotalWeight() + selectedItem.getWeight()) {
		    this.player.setCoins(this.player.getCoins() - selectedItem.getPrice());
		    this.player.getInventory().add(selectedItem);
		    npcAction.getNpc().removeItem(selectedItem);
		} else {
			this.itemStatus = "Not enough coins to buy " + selectedItem.getName();
			return;
		}
	}
	/**
	 * Gets the status of players items
	 * @return the item status
	 */
	
	public String getItemStatus() {
		return this.itemStatus;
	}

	/**
	 * Equipe Tool To User
	 * @param damage the damage the user can do.
	 */
	public void equipToolToUser(int damage) {
		this.player.setDamage(damage);
	}
}
