package edu.westga.cs3211.text_adventure_game.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.westga.cs3211.text_adventure_game.utilities.Helper;

/**
 * The location class
 * @author Colby and Jacob
 * @version Fall 2024
 */
public class Location {
    public static final String ACTIONS_CANNOT_BE_NULL = "Actions cannot be null";
    private static final String LOCATION_TYPE_NULL_MSG = "location type cannot be null";
    private static final String ADJACENT_LOCATIONS_CANNOT_BE_NULL_MSG = "adjacent locations cannot be null";
    private String name;
    private String description;
    private List<Action> actions;
    private HashMap<Direction, String> adjacentLocations;
    private LocationType locationType;
    private List<Npc> npcs;

    /**
     * Creates an instance of a location
     * @param name the name of the location
     * @param description the description of the location
     * @param actions the actions at the location
     * @param adjacentLocations the adjacent locations of the current location
     * @param locationType the type of location
     * @preconditions: name != null && name.isEmpty() && description != null && description.isEmpty() && actions != null && adjacentLocations != null &&
     *                  adjacentLocations.isEmpty() && locationType != null
     * @postconditions: this.name == name && this.description == description && this.actions == actions && this.adjacentLocations == adjacentLocations &&
     *                  this.locationType == locationType
     */
    public Location(String name, String description, ArrayList<Action> actions, HashMap<Direction, String> adjacentLocations, LocationType locationType) {
        this(name, description, locationType);
        if (actions == null) {
            throw new IllegalArgumentException(ACTIONS_CANNOT_BE_NULL);
        }
        if (adjacentLocations == null) {
            throw new IllegalArgumentException(ADJACENT_LOCATIONS_CANNOT_BE_NULL_MSG);
        }
        this.actions = actions;
        this.adjacentLocations = adjacentLocations;
    }

    /**
     * Creates an instance of a location
     * @param name the name of the location
     * @param description the description of the location
     * @param locationType the type of location
     * @preconditions: name != null && name.isEmpty() && description != null && description.isEmpty() && locationType != null
     * @postconditions: this.name == name && this.description == description && this.locationType == locationType
     */
    public Location(String name, String description, LocationType locationType) {
        Helper.throwExceptionForIllegalArgument(name);
        Helper.throwExceptionForIllegalArgument(description);
        if (locationType == null) {
            throw new IllegalArgumentException(LOCATION_TYPE_NULL_MSG);
        }
        this.name = name;
        this.description = description;
        this.locationType = locationType;
        this.adjacentLocations = new HashMap<Direction, String>();
        this.actions = new ArrayList<Action>();
        this.npcs = new ArrayList<Npc>();
    }

    /**
     * Adds an NPC to the location.
     * @param npc the NPC to add to the location
     * @precondition npc != null
     */
    public void addNpc(Npc npc) {
        if (npc == null) {
            throw new IllegalArgumentException("NPC cannot be null");
        }
        this.npcs.add(npc);
    }

    /**
     * Gets the list of NPCs in the location.
     * @return the list of NPCs in the location
     */
    public List<Npc> getNpcs() {
        return this.npcs;
    }

    /**
     * Gets the name
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the description
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the actions
     * @return the actions
     */
    public List<Action> getActions() {
        return this.actions;
    }
    
    /**
     * Adds an NPC to the location.
     * @param action the new action to add to the location
     * @precondition npc != null
     */
    public void addAction(Action action) {
        if (action == null) {
            throw new IllegalArgumentException("Action cannot be null");
        }
        this.actions.add(action);
    }

    /**
     * Gets the adjacent locations
     * @return the adjacentLocations
     */
    public HashMap<Direction, String> getAdjacentLocations() {
        return this.adjacentLocations;
    }

    /**
     * Gets the location type
     * @return the locationType
     */
    public LocationType getLocationType() {
        return this.locationType;
    }
   
}
