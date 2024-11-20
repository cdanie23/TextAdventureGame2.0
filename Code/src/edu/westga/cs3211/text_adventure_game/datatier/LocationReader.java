package edu.westga.cs3211.text_adventure_game.datatier;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import edu.westga.cs3211.text_adventure_game.model.Action;
import edu.westga.cs3211.text_adventure_game.model.Backward;
import edu.westga.cs3211.text_adventure_game.model.Direction;
import edu.westga.cs3211.text_adventure_game.model.Forward;
import edu.westga.cs3211.text_adventure_game.model.Left;
import edu.westga.cs3211.text_adventure_game.model.Location;
import edu.westga.cs3211.text_adventure_game.model.LocationType;
import edu.westga.cs3211.text_adventure_game.model.Right;
import edu.westga.cs3211.text_adventure_game.model.TrapLocation;

/**
 * The location reader class
 * 
 * @author Colby
 * @version Fall 2024
 */
public class LocationReader {
	private static final String BACKWARD = "Backward";
	private static final String RIGHT = "Right";
	private static final String LEFT = "Left";
	private static final String FORWARD = "Forward";
	private static final String FILE_COULD_NOT_BE_FOUND = "Your file could not be found";
	private static final String GOAL = "Goal";
	private static final String TRAP = "Trap";
	private static final String SAFE = "Safe";
	private static final char END_OF_DIRECTION = ')';
	private static final char START_OF_DIRECTION = '(';
	private static final String MOVE_RIGHT = "Move Right";
	private static final String MOVE_LEFT = "Move Left";
	private static final String MOVE_FORWARD = "Move Forward";
	private static final String REGEX_SPLITTER = "&";
	private static final String FILE_CANNOT_BE_NULL = "file cannot be null";
	
	private File file;

	/**
	 * Creates an instance of the location reader
	 * 
	 * @param file the file of locations to read Precondition: file != null
	 *             Postcondition: this.file == file;
	 */

	public LocationReader(File file) {
		if (file == null) {
			throw new IllegalArgumentException(FILE_CANNOT_BE_NULL);
		}
		this.file = file;
	}

	/**
	 * Reads the locations in from a file
	 * 
	 * @return the list of locations read by the file
	 */
	public List<Location> readLocations() {
		List<Location> locations = new ArrayList<Location>();
		try (Scanner fileScanner = new Scanner(this.file)) {
			while (fileScanner.hasNext()) {
				String nextLine = fileScanner.nextLine();
				String[] tokens = nextLine.split(",");

				String name = tokens[0];
				String description = tokens[1];

				ArrayList<Action> actions = new ArrayList<Action>();
				String[] actionsTokens = tokens[2].split(REGEX_SPLITTER);
				for (String action : actionsTokens) {
					if (action.equals(MOVE_FORWARD)) {
						actions.add(new Forward());
					}
					if (action.equals(MOVE_LEFT)) {
						actions.add(new Left());
					}
					if (action.equals(MOVE_RIGHT)) {
						actions.add(new Right());
					}
					if (action.equals("Move Backward")) {
						actions.add(new Backward());
					}
				}
				HashMap<Direction, String> adjacentLocations = new HashMap<Direction, String>();
				String[] adjacentLocationsTokens = tokens[3].split(REGEX_SPLITTER);
				this.createAdjacentLocations(adjacentLocations, adjacentLocationsTokens);
				String locationTypeToken = tokens[4];
				LocationType locationType = null;
				Location location = null;
				switch (locationTypeToken) {
				case SAFE:
					locationType = LocationType.Safe;
					location = new Location(name, description, actions, adjacentLocations, locationType);
					break;
				case TRAP:
					locationType = LocationType.Trap;
					int damageInflicted = Integer.parseInt(tokens[5]);
					location = new TrapLocation(name, description, locationType, damageInflicted);
					break;
				case GOAL:
					locationType = LocationType.Goal;
					location = new Location(name, description, locationType);
					break;
				default:
					break;
				}
				locations.add(location);
			}
		} catch (FileNotFoundException error) {
			System.err.println(FILE_COULD_NOT_BE_FOUND);
		}
		return locations;
	}

	private void createAdjacentLocations(HashMap<Direction, String> adjacentLocations,
			String[] adjacentLocationsTokens) {
		for (String location : adjacentLocationsTokens) {
			int indexOfStartingDirectionType = location.indexOf(START_OF_DIRECTION) + 1;
			int indexOfEndingDirectionType = location.indexOf(END_OF_DIRECTION);
			String direction = location.substring(indexOfStartingDirectionType, indexOfEndingDirectionType).trim();
			Direction directionType = this.findDirectionType(direction);
			String nameOfAdjacentLocation = location.substring(0, indexOfStartingDirectionType - 1).trim();
			adjacentLocations.put(directionType, nameOfAdjacentLocation);
		}
	}

	private Direction findDirectionType(String direction) {
		Direction directionType = null;
		switch (direction) {
		case FORWARD:
			directionType = Direction.Forward;
			break;
		case LEFT:
			directionType = Direction.Left;
			break;
		case RIGHT:
			directionType = Direction.Right;
			break;
		case BACKWARD:
			directionType = Direction.Backward;
		default:
			break;
		}
		return directionType;
	}

	/**
	 * Gets the file
	 * 
	 * @return the file
	 */
	public File getFile() {
		return this.file;
	}
}
