package bowlingGame.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import bowlingGame.model.Game;


public class BowlingGameTest {
	
	Game game; 

	@Before
	public void setUp() throws Exception {
		game= new Game(); 
	}

	@Test
	public void testOpenFrameScore() {
		game.roll(1);
		game.roll(4);
		assertEquals(5, game.score());
		
		game.roll(4);
		game.roll(5);
		assertEquals(14, game.score());
	}
	
	@Test
	public void testSpareScore() {
		game.roll(2);
		game.roll(8);
		assertEquals(10, game.score());
		
		game.roll(4);
		game.roll(3);
		assertEquals(21, game.score());
	}
	
	@Test
	public void testStrikeInFirstFrame() {
		game.roll(10);
		assertEquals(10, game.score());
		
		game.roll(3);
		game.roll(6);
		assertEquals(28, game.score());
	}
	
	@Test
	public void testStrikeInSecondFrame() {
		game.roll(1);
		game.roll(3);
		assertEquals(4, game.score());
		
		game.roll(10);
		assertEquals(14, game.score());
		
		game.roll(6);
		game.roll(2);
		assertEquals(30, game.score());
	}
	
	@Test
	public void testConsequtiveStrikes() {
		game.roll(10);
		assertEquals(10, game.score());
		
		game.roll(10);
		assertEquals(30, game.score());
		
		game.roll(3);
		game.roll(7);
		assertEquals(53, game.score());
	}

}
