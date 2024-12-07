package edu.westga.cs3211.text_adventure_game.viewmodel.test.textadventuregameviewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Direction;

import edu.westga.cs3211.text_adventure_game.model.GameManager;

import edu.westga.cs3211.text_adventure_game.model.Move;

import edu.westga.cs3211.text_adventure_game.viewmodel.TextAdventureViewModel;

class TestCheckIfLostState {

	@Test
	void testWhenPlayerHasntLost() {
		TextAdventureViewModel viewModel = new TextAdventureViewModel();
		
		assertFalse(viewModel.checkIfLostState());
		
	}
	
	@Test
	void testWhenPlayerHasLost() {
		TextAdventureViewModel viewModel = new TextAdventureViewModel();
		GameManager gameManager = viewModel.getGameManager();
		
		gameManager.updateLocation(new Move(Direction.Forward));
		gameManager.updateLocation(new Move(Direction.Right));
		gameManager.updateLocation(new Move(Direction.Left));
		
		assertTrue(viewModel.checkIfLostState());
	}

}
