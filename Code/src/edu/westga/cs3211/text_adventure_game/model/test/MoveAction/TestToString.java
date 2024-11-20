package edu.westga.cs3211.text_adventure_game.model.test.MoveAction;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Forward;
import edu.westga.cs3211.text_adventure_game.model.MoveAction;

class TestToString {

	@Test
	void testToString() {
		MoveAction moveAction = new Forward();
		
		assertEquals(moveAction.toString(), "Forward");
	}

}
