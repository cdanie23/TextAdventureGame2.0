package edu.westga.cs3211.text_adventure_game.test.viewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Direction;

import edu.westga.cs3211.text_adventure_game.model.GameManager;

import edu.westga.cs3211.text_adventure_game.model.Move;

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
		
		gameManager.updateLocation(new Move(Direction.Forward));
		gameManager.updateLocation(new Move(Direction.Left));
		gameManager.updateLocation(new Move(Direction.Right));
		
		Boolean hasPlayerWon = viewModel.checkIfWinState();
		
		assertTrue(hasPlayerWon);
	}

}
