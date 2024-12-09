package edu.westga.cs3211.text_adventure_game.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import edu.westga.cs3211.text_adventure_game.datatier.ItemReader;
import edu.westga.cs3211.text_adventure_game.datatier.LocationReader;
import edu.westga.cs3211.text_adventure_game.datatier.NpcReader;

/**
 * The game manager class
 * @author Colby and Jacob
 * @version Fall 2024
 */
public class GameManager {
	public static final int MAX_WEIGHT = 100;
	private static final String YOU_ARE_DEAD_MSG = "You are dead";
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
		
		this.setupPlayer();
		this.currLocation = this.allLocations.get(0);
		this.npcManager.addRandomNpcs(2, this.currLocation);
		this.playerHasWon = false;
		
		this.setupActions();
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
	 * updates the location after a move action
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
		this.itemStatus = "";
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
		if (action instanceof DropItem) {
			this.itemStatus = "You have dropped " + action.getItem().getName();
		}
		if (action instanceof UseItem) {
			this.itemStatus = "You have used " + action.getItem().getName();
		}
		this.player.getInventory().remove(action.getItem());
		return action.takeAction(this.player);
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

		this.itemStatus = npcAction.takeAction(this.player);

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
	public void equipeToolToUser(int damage) {
		this.player.getDamage();
	}
}
