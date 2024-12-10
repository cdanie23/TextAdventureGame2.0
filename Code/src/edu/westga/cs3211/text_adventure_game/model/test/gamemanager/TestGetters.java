package edu.westga.cs3211.text_adventure_game.model.test.gamemanager;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;


import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Action;
import edu.westga.cs3211.text_adventure_game.model.GameManager;
import edu.westga.cs3211.text_adventure_game.model.Move;

class TestGetters {

	@Test
	void testGetActions() {
		GameManager gameManager = new GameManager();
		
		List<Action> allActions = gameManager.getActions();
		
		assertTrue(allActions.get(0) instanceof Move);
	}
	@Test
	void testGetLocationDescription() {
		GameManager gameManager = new GameManager();
		
		assertEquals(gameManager.getLocationDescription(), "After your long journey to explore the Creaky Castle in hopes of finding the riches you have arrived at the front gate");
	}
	@Test
	void testGetPlayerStatusWhenAlive() {
		GameManager gameManager = new GameManager();
		
		assertEquals(gameManager.getPlayerStatus(), "100");
	}
	@Test
	void testGetPlayerStatsWhenDead() {
		GameManager gameManager = new GameManager(); 
		gameManager.getPlayer().setIsDead(true);
		
		String status = gameManager.getPlayerStatus();
		
		assertEquals(status, "You are dead");
		
	}
	@Test
	void testGetAvailableActionsDescription() {
		GameManager gameManager = new GameManager();
		String availableActionsDescription = gameManager.getAvailableActionsDescription();
		
		assertEquals(true, availableActionsDescription.contains("Move Forward" + System.lineSeparator()));
	}
	@Test
	void testGetPlayerHasWon() {
		GameManager gameManager = new GameManager();
		
		Boolean playerHasWon = gameManager.getPlayerHasWon();
		
		assertFalse(playerHasWon);
	}
	
}
