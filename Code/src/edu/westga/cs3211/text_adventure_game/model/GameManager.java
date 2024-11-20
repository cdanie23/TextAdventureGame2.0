package edu.westga.cs3211.text_adventure_game.model;

import java.io.File;
import java.util.List;

import edu.westga.cs3211.text_adventure_game.datatier.LocationReader;

/**
 * The game manager class
 * @author Colby
 * @version Fall 2024
 */
public class GameManager {
	private static final String YOU_ARE_DEAD_MSG = "You are dead";
	private static final String LOCATIONS_TXT_FILE = "Locations.txt";
	private Player player;
	private List<Location> allLocations;
	private Location currLocation;
	private LocationReader locationReader;
	private Boolean playerHasWon;
	
	/**
	 * Creates an instance of the game manager class
	 * @postcondition player != null && locationReader != null && allLocations != null && currentLocation != null
	 */
	public GameManager() {
		this.player = new Player();
		File file = new File(LOCATIONS_TXT_FILE);
		this.locationReader = new LocationReader(file);
		this.allLocations = this.locationReader.readLocations();
		this.currLocation = this.allLocations.get(0);
		this.playerHasWon = false;
	}
	
	/**
	 * Gets the actions for the current location of the player
	 * @return the collection of actions
	 */
	public List<Action> getActions() {
		return this.currLocation.getActions();
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
		return "Health: " + this.player.getHealth();
	}
	
	/**
	 * updates the location after a move action
	 * @param action the move action taken
	 * @precondition: action != null
	 * @postcondition: this.currLocation == newLocation
	 */
	public void updateLocation(MoveAction action) {
		if (action == null) {
			throw new IllegalArgumentException(Location.ACTIONS_CANNOT_BE_NULL);
		}
		Location newLocation = null;
		String nameOfNewLocation = null;
		
		if (action instanceof Forward) {
			nameOfNewLocation = this.currLocation.getAdjacentLocations().get(Direction.Forward);
		}
		if (action instanceof Left) {
			nameOfNewLocation = this.currLocation.getAdjacentLocations().get(Direction.Left);
		}
		if (action instanceof Right) {
			nameOfNewLocation = this.currLocation.getAdjacentLocations().get(Direction.Right);
		}
		if (action instanceof Backward) {
			nameOfNewLocation = this.currLocation.getAdjacentLocations().get(Direction.Backward);
		}
		
		for (Location location : this.allLocations) {
			if (location.getName().equals(nameOfNewLocation)) {
				newLocation = location;
			}
		}
		this.currLocation = newLocation;
		this.checkForTrapLocation(newLocation);
		this.checkForGoalLocation(newLocation); 
	}

	private void checkForTrapLocation(Location newLocation) {
		if (newLocation instanceof TrapLocation) {
			TrapLocation trapLocation = (TrapLocation) newLocation;
			this.applyDamageInflicted(trapLocation);
		}
	}

	private void applyDamageInflicted(TrapLocation trapLocation) {
		int damageApplied = trapLocation.getDamageInflicted();
		int originalHealth = this.player.getHealth();
		int newHealth = originalHealth - damageApplied;
		this.player.setHealth(newHealth);
	}
	
	private void checkForGoalLocation(Location newLocation) {
		if (newLocation.getLocationType() == LocationType.Goal) {
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
		String availableActionDescription = "";
		for (Action action : this.currLocation.getActions()) {
			availableActionDescription += action.getDescription() + System.lineSeparator();
		}
		return availableActionDescription;
	}
	/**
	 * Gets if the player has won
	 * @return true or false based on if the player has won
	 */
	
	public Boolean getPlayerHasWon() {
		return this.playerHasWon;
	}
}
