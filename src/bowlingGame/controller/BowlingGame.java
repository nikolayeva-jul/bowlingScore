package bowlingGame.controller;

import bowlingGame.model.AbstractGame;
import bowlingGame.model.BowlingGameFactory;

public class BowlingGame {
	public static void main(String[] args) {
		BowlingGameFactory newMatch = new BowlingGameFactory();
		AbstractGame game = newMatch.start();
	}
}
