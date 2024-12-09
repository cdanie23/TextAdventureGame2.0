package edu.westga.cs3211.text_adventure_game.viewmodel.test.textadventuregameviewmodel;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Action;
import edu.westga.cs3211.text_adventure_game.model.Direction;
import edu.westga.cs3211.text_adventure_game.model.DropItem;
import edu.westga.cs3211.text_adventure_game.model.Item;
import edu.westga.cs3211.text_adventure_game.model.Move;
import edu.westga.cs3211.text_adventure_game.model.UseItem;
import edu.westga.cs3211.text_adventure_game.viewmodel.TextAdventureViewModel;

class testTakeAction {
	@BeforeEach
	void setupViewModel() {
		
	}
	@Test
	void testValidMoveAction() {
		TextAdventureViewModel viewModel = new TextAdventureViewModel();

		viewModel.getSelectedActionProperty().setValue(new Move(Direction.Forward));
		viewModel.takeAction();
		Action actionOne = viewModel.getActionsListProperty().get(0);
		Action actionTwo = viewModel.getActionsListProperty().get(1);
		
		
		
		assertEquals(viewModel.getLocationDescriptionProperty().getValue(),
				"You lower the gates from the lever and step into the long hallway of the castle in front of you is a fork in the hallway");
		assertTrue(actionOne instanceof Move);
		assertTrue(actionTwo instanceof Move);
		assertEquals(viewModel.getActionsDescriptionProperty().getValue(), "Move Left" + System.lineSeparator() + "Move Right" + System.lineSeparator());
		assertEquals(viewModel.getPlayerStatusDescriptionProperty().getValue(), "100");
	}
	@Test
	void testInvalidAction() {
		TextAdventureViewModel viewModel = new TextAdventureViewModel();
		viewModel.takeAction();
	}
	
	@Test
	void testValidUsePlayerItem() {
		TextAdventureViewModel viewModel = new TextAdventureViewModel();
		
		Item potion = new Item("Healing Potion", 10, 50, 10);
		viewModel.addItemUseActions(potion);
		viewModel.getSelectedItemProperty().setValue(potion);
		Action useItem = viewModel.getActionsListProperty().get(1);
		Action dropItem = viewModel.getActionsListProperty().get(2);
		
		viewModel.getSelectedActionProperty().setValue(useItem);
		viewModel.getGameManager().getPlayer().setHealth(30);
		
		assertTrue(useItem instanceof UseItem);
		assertTrue(dropItem instanceof DropItem);
		assertEquals(viewModel.getActionsDescriptionProperty().getValue(), "Move Forward" + System.lineSeparator() + 
																		   "Use Healing Potion" + System.lineSeparator() + "Drop Healing Potion" +
																			System.lineSeparator());
		viewModel.takeAction();
		assertEquals(viewModel.getGameManager().getPlayerStatus(), "80");
		assertEquals(viewModel.getPlayerStatusDescriptionProperty().getValue(), "80");
		
	}
	
	@Test
	void testValidDropPlayerItem() {
		TextAdventureViewModel viewModel = new TextAdventureViewModel();
		
		List<Item> items = viewModel.getItemsListProperty();
		assertEquals(items.size(), 1);
		
		Item potion = items.get(0);
		viewModel.addItemUseActions(potion);
		viewModel.getSelectedItemProperty().setValue(potion);
		Action useItem = viewModel.getActionsListProperty().get(1);
		Action dropItem = viewModel.getActionsListProperty().get(2);
		
		viewModel.getSelectedActionProperty().setValue(dropItem);
		
		assertTrue(useItem instanceof UseItem);
		assertTrue(dropItem instanceof DropItem);
		
		viewModel.takeAction();
		
		assertEquals(items.size(), 0);
		
	}
	@Test
	void testInteractWithChest() {
		TextAdventureViewModel viewModel = new TextAdventureViewModel();
		

	}
}
