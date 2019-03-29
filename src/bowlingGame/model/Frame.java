package bowlingGame.model;

public class Frame {
	private static final int MAX_TRIES = 2;
	private int[] scores;
	private int pins;
	private int tries;
	private int frameScore;
	private boolean bonus;

	public Frame() {
		this.scores = new int[MAX_TRIES];
		this.pins = 10;
	}
	
	public boolean isBonus() {
		return bonus;
	}

	public boolean isStrike() {
		return pins == 0 && tries == MAX_TRIES - 1;
	}

	public boolean isSpare() {
		return pins == 0 && tries == MAX_TRIES;
	}

	public boolean isFinished() {
		if(bonus) {
			return tries == MAX_TRIES;
		}
		return isStrike() || tries == MAX_TRIES;
	}

	public int getFirstTryScore() {
		return scores[0];
	}

	public int getSecondTryScore() {
		return scores[1];
	}

	public void setScore(int score) {
		scores[tries++] = score;
		pins -= score;
	}
	
	public int getScoreSum() {
		return scores[0] + scores[1];
	}
	public int getFrameScore() {
		return frameScore;
	}
	
	public void updateFrameScore(int score) {
		frameScore+=score;
	}

	public void setBonus() {
		bonus=true;
		
	}
}
