package bowlingGame.model;

public class BowlingGameFactory {

	public AbstractGame start() {
		return new Game();
	}
}
