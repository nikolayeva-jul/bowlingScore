package bowlingGame.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import bowlingGame.exceptions.BowlingGameException;
import bowlingGame.model.AbstractGame;
import bowlingGame.model.BowlingGameFactory;

public class BowlingGameTest {

	BowlingGameFactory newMatch = new BowlingGameFactory();
	AbstractGame game;
	
	@Before
	public void setUp() throws Exception {
		game = newMatch.start();
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
		assertEquals(0, game.score());

		game.roll(4);
		game.roll(3);
		assertEquals(21, game.score());
	}

	@Test
	public void testStrikeInFirstFrame() {
		game.roll(10);
		assertEquals(0, game.score());

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
		assertEquals(4, game.score());

		game.roll(6);
		game.roll(2);
		assertEquals(30, game.score());
	}

	@Test
	public void test2ConsequtiveStrikes() {
		game.roll(10);
		assertEquals(0, game.score());

		game.roll(10);
		assertEquals(0, game.score());

		game.roll(3);
		game.roll(6);
		assertEquals(51, game.score());
	}

	@Test
	public void test3ConsecutiveStrikes() {
		game.roll(10);
		assertEquals(0, game.score());

		game.roll(10);
		assertEquals(0, game.score());

		game.roll(10);
		assertEquals(30, game.score());

		game.roll(3);
		game.roll(6);
		assertEquals(81, game.score());
	}

	@Test
	public void testStrikeInEveryFrame() {
		int score = 0;
		for (int i = 0; i < 10; i++) {
			game.roll(10);
			score = game.score();
		}
		assertEquals(240,score);
		game.roll(10);
		game.roll(10);
		assertEquals(300, game.score());
	}
	
	@Test(expected=BowlingGameException.class)
	public void testThrownException() {
		int score = 0;
		for (int i = 0; i < 10; i++) {
			game.roll(10);
			score = game.score();
		}
		assertEquals(240,score);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		assertEquals(300, game.score());
		
	}
	
	@Test
	public void testZeroPinsInEveryFrame() {
		int score = 0;
		for (int i = 0; i < 10; i++) {
			game.roll(0);
			game.roll(0);
			score = game.score();
		}
		assertEquals(0, score);
	}
	
	@Test
	public void testSpareInEveryFrame() {
		int score = 0;
		for (int i = 0; i < 10; i++) {
			game.roll(5);
			game.roll(5);
			score = game.score();
		}
		assertEquals(135, score);
		game.roll(5);
		assertEquals(150, game.score());
	}
	
	@Test
	public void testBonusScoreAfterStrikeInLastFrame() {
		int score = 0;
		for (int i = 0; i < 9; i++) {
			game.roll(4);
			game.roll(4);
			score = game.score();
		}
		assertEquals(72, score);
		
		game.roll(10);
		assertEquals(72, game.score());
		
		game.roll(6);
		game.roll(3);
		assertEquals(91, game.score());

	}

	@Test
	public void testBonusScoreAfterSpareInLastFrame() {
		for (int i = 0; i < 10; i++) {
			game.roll(2);
			game.roll(8);
			game.score();
		}
		game.roll(5);
		assertEquals(123, game.score());
	}
	
	@Test
	public void testWholeGameTest() {
		game.roll(1);
		game.roll(2);
		assertEquals(3, game.score());
		
		game.roll(10);
		assertEquals(3, game.score());
		
		game.roll(8);
		game.roll(1);
		assertEquals(31, game.score());
		
		game.roll(6);
		game.roll(4);
		assertEquals(31, game.score());
		
		game.roll(10);
		assertEquals(51, game.score());
		
		game.roll(10);
		assertEquals(51, game.score());
		
		game.roll(8);
		game.roll(1);
		assertEquals(107, game.score());
		
		game.roll(5);
		game.roll(5);
		assertEquals(107, game.score());
		
		game.roll(10);
		assertEquals(127, game.score());
		
		game.roll(10);
		assertEquals(127, game.score());
		
		game.roll(10);
		game.roll(7);
		assertEquals(184, game.score());
	}
	
	
	
}
