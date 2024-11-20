package edu.westga.cs3211.text_adventure_game.viewmodel.test.textadventuregameviewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Action;
import edu.westga.cs3211.text_adventure_game.model.Forward;
import edu.westga.cs3211.text_adventure_game.model.Left;
import edu.westga.cs3211.text_adventure_game.model.Right;
import edu.westga.cs3211.text_adventure_game.viewmodel.TextAdventureViewModel;

class testTakeAction {

	@Test
	void testValidMoveAction() {
		TextAdventureViewModel viewModel = new TextAdventureViewModel();

		viewModel.getSelectedActionProperty().setValue(new Forward());
		viewModel.takeAction();
		Action actionOne = viewModel.getActionsListProperty().get(0);
		Action actionTwo = viewModel.getActionsListProperty().get(1);
		
		
		
		assertEquals(viewModel.getLocationDescriptionProperty().getValue(),
				"You lower the gates from the lever and step into the long hallway of the castle in front of you is a fork in the hallway");
		assertTrue(actionOne instanceof Left);
		assertTrue(actionTwo instanceof Right);
		assertEquals(viewModel.getActionsDescriptionProperty().getValue(), "Move Left" + System.lineSeparator() + "Move Right" + System.lineSeparator());
		assertEquals(viewModel.getPlayerStatusDescriptionProperty().getValue(), "Health: 100");
	}
	@Test
	void testInvalidAction() {
		TextAdventureViewModel viewModel = new TextAdventureViewModel();
		viewModel.takeAction();
	}
}
