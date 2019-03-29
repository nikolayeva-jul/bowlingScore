package bowlingGame.controller;

import bowlingGame.model.Game;

public class BowlingGame {
	public static void main(String[] args) {
		Game game1 = new Game();
		game1.roll(1);
		game1.roll(4);
		System.out.println(game1.score());
		
		game1.roll(4);
		game1.roll(5);
		System.out.println(game1.score());
		
		game1.roll(6);
		game1.roll(4);
		System.out.println(game1.score());
	}
}
