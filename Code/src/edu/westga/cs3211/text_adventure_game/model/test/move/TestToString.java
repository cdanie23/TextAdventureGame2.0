package edu.westga.cs3211.text_adventure_game.model.test.move;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Direction;
import edu.westga.cs3211.text_adventure_game.model.Move;

class TestToString {

	@Test
	void testToString() {
		Move moveAction = new Move(Direction.Forward);
		
		assertEquals(moveAction.toString(), "Move Forward");
	}

}
