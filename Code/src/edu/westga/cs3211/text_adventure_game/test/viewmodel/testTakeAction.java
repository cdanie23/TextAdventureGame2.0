package edu.westga.cs3211.text_adventure_game.test.viewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Action;
import edu.westga.cs3211.text_adventure_game.model.Direction;
import edu.westga.cs3211.text_adventure_game.model.DropItem;
import edu.westga.cs3211.text_adventure_game.model.Item;
import edu.westga.cs3211.text_adventure_game.model.Move;
import edu.westga.cs3211.text_adventure_game.model.Npc;
import edu.westga.cs3211.text_adventure_game.model.NpcInteract;
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

		viewModel.getActionsListProperty().clear();
		viewModel.getItemsListProperty().clear();

		Item potion = new Item("Healing Potion", 10, 50, 10);
		viewModel.getItemsListProperty().add(potion);
		viewModel.addItemUseActions(potion);
		viewModel.getSelectedItemProperty().setValue(potion);
		Action useItem = viewModel.getActionsListProperty().get(0);
		Action dropItem = viewModel.getActionsListProperty().get(1);

		viewModel.getSelectedActionProperty().setValue(useItem);
		viewModel.getGameManager().getPlayer().setHealth(30);

		assertTrue(useItem instanceof UseItem);
		assertTrue(dropItem instanceof DropItem);
		assertEquals(viewModel.getActionsDescriptionProperty().getValue(),
				"Use Healing Potion" + System.lineSeparator() + "Drop Healing Potion" + System.lineSeparator());
		
		viewModel.takeAction();
		
		assertEquals(viewModel.getGameManager().getPlayerStatus(), "80");
		assertEquals(viewModel.getPlayerStatusDescriptionProperty().getValue(), "80");
		
		assertEquals(viewModel.getActionsDescriptionProperty().getValue(), "");

	}

	@Test
	void testValidDropPlayerItem() {
		TextAdventureViewModel viewModel = new TextAdventureViewModel();

		viewModel.getActionsListProperty().clear();
		viewModel.getItemsListProperty().clear();

		Item potion = new Item("Potion", 10, 50, 10);

		viewModel.getItemsListProperty().add(potion);
		viewModel.addItemUseActions(potion);

		Action useItem = viewModel.getActionsListProperty().get(0);
		Action dropItem = viewModel.getActionsListProperty().get(1);

		viewModel.getSelectedActionProperty().setValue(dropItem);

		assertTrue(useItem instanceof UseItem);
		assertTrue(dropItem instanceof DropItem);

		viewModel.takeAction();

		assertEquals(viewModel.getItemsListProperty().size(), 0);

	}

	@Test
	void testPlayerUseWeapon() {
		TextAdventureViewModel viewModel = new TextAdventureViewModel();
		
		viewModel.getActionsListProperty().clear();
		viewModel.getItemsListProperty().clear();
		
		Item dagger = new Item("Dagger", 10, -25, 10);
		
		UseItem useDagger = new UseItem(dagger);
		
		viewModel.getSelectedActionProperty().setValue(useDagger);
		
		viewModel.takeAction();
		
		assertEquals(viewModel.getGameManager().getPlayer().getDamage(), 25);
		

	}
	@Test
	void testInteractWithChest() {
		TextAdventureViewModel viewModel = new TextAdventureViewModel();
		
		viewModel.getActionsListProperty().clear();
		viewModel.getItemsListProperty().clear();
		
		Item dagger = new Item("Dagger", 10, -25, 10);
		Npc chest = new Npc("Chest", 0, 50, 999);
		chest.addItem(dagger);
		NpcInteract interactChest = new NpcInteract(chest, "Interact with chest");
		
		viewModel.getSelectedActionProperty().setValue(interactChest);
		viewModel.takeAction();
		
		assertEquals(viewModel.getActionsListProperty().size(), 1);
		
	}
}
