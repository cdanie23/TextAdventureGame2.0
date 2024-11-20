package edu.westga.cs3211.text_adventure_game.model.test.moveactions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Backward;
import edu.westga.cs3211.text_adventure_game.model.Direction;
import edu.westga.cs3211.text_adventure_game.model.Forward;
import edu.westga.cs3211.text_adventure_game.model.Left;
import edu.westga.cs3211.text_adventure_game.model.Right;

class TestConstructors {

	@Test
	void testBackwardValidConstructor() {
		Backward backward = new Backward();
		
		assertEquals(backward.getDescription(), "Move Backward");
		assertEquals(backward.getDirection(), Direction.Backward);
	}
	
	@Test
	void testForwardValidConstructor() {
		Forward forward = new Forward();
		
		assertEquals(forward.getDescription(), "Move Forward");
		assertEquals(forward.getDirection(), Direction.Forward);
	}
	@Test
	void testLeftValidConstructor() {
		Left left = new Left();
		
		assertEquals(left.getDescription(), "Move Left");
		assertEquals(left.getDirection(), Direction.Left);
	}
	@Test
	void testRightValidConstructor() {
		Right right = new Right();
		
		assertEquals(right.getDescription(), "Move Right");
		assertEquals(right.getDirection(), Direction.Right);
	}

}
