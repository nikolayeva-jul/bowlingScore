package bowlingGame.controller;

import java.util.Random;

import bowlingGame.model.Game;

public class BowlingGame {
	public static void main(String[] args) {
		Game game1 = new Game();
		Random rnd = new Random();
		
		for(int i = 0; i < 10; i++) {
			int pins = rnd.nextInt(11);
			game1.roll(pins);
			if(pins == 10) {
				System.out.println(pins);
				System.out.println(game1.score());
				continue;
			}
			int pins2 = rnd.nextInt(11-pins);
			game1.roll(pins2);
			System.out.println(pins + ": " + pins2);
			System.out.println(game1.score());
		}
	}
}
