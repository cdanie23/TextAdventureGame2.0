package edu.westga.cs3211.text_adventure_game.viewmodel.test.textadventuregameviewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.viewmodel.TextAdventureViewModel;

class TestConstructor {

	@Test
	void testValidConstructor() {
		TextAdventureViewModel viewModel = new TextAdventureViewModel();
		
		assertTrue(viewModel.getGameManager() != null);
		assertTrue(viewModel.getActionsDescriptionProperty() != null);
		assertTrue(viewModel.getActionsListProperty() != null);
		assertTrue(viewModel.getLocationDescriptionProperty() != null);
		assertTrue(viewModel.getPlayerStatusDescriptionProperty() != null);
		assertTrue(viewModel.getSelectedActionProperty() != null);
	}

}
