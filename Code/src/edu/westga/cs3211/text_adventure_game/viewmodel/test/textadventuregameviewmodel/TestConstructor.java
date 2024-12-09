package edu.westga.cs3211.text_adventure_game.viewmodel.test.textadventuregameviewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.viewmodel.TextAdventureViewModel;

class TestConstructor {

	@Test
	void testConstructorInitializesFields() {
	    TextAdventureViewModel viewModel = new TextAdventureViewModel();

	    assertNotNull(viewModel.getLocationDescriptionProperty());
	    assertNotNull(viewModel.getActionsDescriptionProperty());
	    assertNotNull(viewModel.getPlayerStatusDescriptionProperty());
	    assertNotNull(viewModel.getItemsStatusProperty());
	    assertNotNull(viewModel.getCoinsProperty());
	    assertNotNull(viewModel.getCurrentLocationNameProperty());
	    assertNotNull(viewModel.getWeightTextProperty());
	    assertNotNull(viewModel.getActionsListProperty());
	    assertNotNull(viewModel.getSelectedActionProperty());
	    assertNotNull(viewModel.getItemsListProperty());
	    assertNotNull(viewModel.getSelectedItemProperty());
	    assertNotNull(viewModel.getGameManager());
	}
	
}
