package edu.westga.cs3211.text_adventure_game.viewmodel.test.textadventuregameviewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Item;
import edu.westga.cs3211.text_adventure_game.viewmodel.TextAdventureViewModel;

class TestAddItemUseActions {

	@Test
	void testAddItemUseActionsWhenNoItemWasSelectedBefore() {
		TextAdventureViewModel viewModel = new TextAdventureViewModel();
		
		Item dagger = new Item("Dagger", 10, 15, 10);
		
		viewModel.addItemUseActions(dagger);
		
		assertEquals(viewModel.getActionsListProperty().size(), 3);
	}
	
	@Test
	void testAddItemUseActionsWhenItemsWereSelectedBefore() {
		TextAdventureViewModel viewModel = new TextAdventureViewModel();
		
		Item dagger = new Item("Dagger", 10, 15, 10);
		Item potion = new Item("Healing Potion", 10, 50, 10);
		
		viewModel.addItemUseActions(dagger);
		viewModel.addItemUseActions(potion);
		
		assertEquals(viewModel.getActionsListProperty().size(), 3);
		
	}

}
