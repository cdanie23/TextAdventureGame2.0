package edu.westga.cs3211.text_adventure_game.viewmodel.test.textadventuregameviewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Forward;
import edu.westga.cs3211.text_adventure_game.model.GameManager;
import edu.westga.cs3211.text_adventure_game.model.Left;
import edu.westga.cs3211.text_adventure_game.model.Right;
import edu.westga.cs3211.text_adventure_game.viewmodel.TextAdventureViewModel;

class TestCheckIfWinState {

	@Test
	void testCheckIfWinStateWhenPlayerHasntWon() {
		TextAdventureViewModel viewModel = new TextAdventureViewModel();
		
		Boolean hasPlayerWon = viewModel.checkIfWinState();
		
		assertFalse(hasPlayerWon);
	}
	
	@Test
	void testCheckIfWinStateWhenPlayerHasWon() {
		TextAdventureViewModel viewModel = new TextAdventureViewModel();
		GameManager gameManager = viewModel.getGameManager();
		
		gameManager.updateLocation(new Forward());
		gameManager.updateLocation(new Left());
		gameManager.updateLocation(new Right());
		
		Boolean hasPlayerWon = viewModel.checkIfWinState();
		
		assertTrue(hasPlayerWon);
	}

}
