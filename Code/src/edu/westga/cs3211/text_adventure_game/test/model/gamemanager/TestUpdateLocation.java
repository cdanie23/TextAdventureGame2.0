package edu.westga.cs3211.text_adventure_game.test.model.gamemanager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Direction;
import edu.westga.cs3211.text_adventure_game.model.GameManager;
import edu.westga.cs3211.text_adventure_game.model.Move;

class TestUpdateLocation {

	@Test
	void testUpdateLocationForward() {
		GameManager gameManager = new GameManager();
		
		gameManager.updateLocation(new Move(Direction.Forward));
		
		String nameOfNewLocation = gameManager.getLocationName();
		
		assertEquals(nameOfNewLocation, "Creaky Castle Halls");
	
	}
	
	@Test
	void testUpdateLocationLeft() {
		GameManager gameManager = new GameManager();
		
		gameManager.updateLocation(new Move(Direction.Forward));
		gameManager.updateLocation(new Move(Direction.Left));
		
		String nameOfNewLocation = gameManager.getLocationName();
		
		assertEquals(nameOfNewLocation, "Creaky Castle Ball Room");
		
	}
	
	@Test
	void testUpdateLocationRight() {
		GameManager gameManager = new GameManager();
		
		gameManager.updateLocation(new Move(Direction.Forward));
		gameManager.updateLocation(new Move(Direction.Right));
		
		String nameOfNewLocation = gameManager.getLocationName();
		
		assertEquals(nameOfNewLocation, "Creaky Castle Dining Room");
		
	}
	@Test
	void testUpdateLocationBackward() {
		GameManager gameManager = new GameManager();
		
		gameManager.updateLocation(new Move(Direction.Forward));
		gameManager.updateLocation(new Move(Direction.Right));
		gameManager.updateLocation(new Move(Direction.Backward));
		
		String nameOfNewLocation = gameManager.getLocationName();
		
		assertEquals(nameOfNewLocation, "Creaky Castle Halls");
	}
	
	@Test
	void testInvalidMoveAction() {
		GameManager gameManager = new GameManager();
		
		assertThrows(IllegalArgumentException.class, () -> gameManager.updateLocation(null));
	}
	@Test
	public void testWhenTrapLocation() {
		GameManager gameManager = new GameManager();
		
		gameManager.updateLocation(new Move(Direction.Forward));
		gameManager.updateLocation(new Move(Direction.Right));
		gameManager.updateLocation(new Move(Direction.Left));
		
		String nameOfNewLocation = gameManager.getLocationName();
		assertEquals(nameOfNewLocation, "Creaky Castle Trap Room");
		
	}
	@Test
	public void testWhenGoalLocation() {
		GameManager gameManager = new GameManager();
		
		gameManager.updateLocation(new Move(Direction.Forward));
		gameManager.updateLocation(new Move(Direction.Left));
		gameManager.updateLocation(new Move(Direction.Right));
		
		String nameOfNewLocation = gameManager.getLocationName();
		assertEquals(nameOfNewLocation, "Creaky Castle Treasury");
	}
}
