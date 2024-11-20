package edu.westga.cs3211.text_adventure_game.model.test.gamemanager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.GameManager;
import edu.westga.cs3211.text_adventure_game.model.Location;

class TestConstructor {

	@Test
	void testValidConstructor() {
		GameManager gameManager = new GameManager();
		
		assertNotNull(gameManager.getPlayer());
		assertNotNull(gameManager.getAllLocations());
		assertNotNull(gameManager.getLocationReader());
		
		File file = gameManager.getLocationReader().getFile();
		assertTrue(file.exists());
		
		Location startingLocation = gameManager.getAllLocations().get(0);
		assertEquals(gameManager.getCurrLocation(), startingLocation);
		
	}

}
