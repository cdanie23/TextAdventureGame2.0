package edu.westga.cs3211.text_adventure_game.viewmodel.test.textadventuregameviewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Forward;
import edu.westga.cs3211.text_adventure_game.model.GameManager;
import edu.westga.cs3211.text_adventure_game.model.Left;
import edu.westga.cs3211.text_adventure_game.model.Right;
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
		
		gameManager.updateLocation(new Forward());
		gameManager.updateLocation(new Right());
		gameManager.updateLocation(new Left());
		
		assertTrue(viewModel.checkIfLostState());
	}

}
