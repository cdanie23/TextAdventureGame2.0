package edu.westga.cs3211.text_adventure_game.model.test.moveactions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import edu.westga.cs3211.text_adventure_game.model.Direction;
import edu.westga.cs3211.text_adventure_game.model.Move;


class TestConstructors {

	@Test
	void testBackwardValidConstructor() {
		Move backward = new Move(Direction.Backward);
		
		assertEquals(backward.getDescription(), "Move Backward");
		assertEquals(backward.getDirection(), Direction.Backward);
	}
	
	@Test
	void testForwardValidConstructor() {
		Move forward = new Move(Direction.Forward);
		
		assertEquals(forward.getDescription(), "Move Forward");
		assertEquals(forward.getDirection(), Direction.Forward);
	}
	@Test
	void testLeftValidConstructor() {
		Move left = new Move(Direction.Left);
		
		assertEquals(left.getDescription(), "Move Left");
		assertEquals(left.getDirection(), Direction.Left);
	}
	@Test
	void testRightValidConstructor() {
		Move right = new Move(Direction.Right);
		
		assertEquals(right.getDescription(), "Move Right");
		assertEquals(right.getDirection(), Direction.Right);
	}

}
