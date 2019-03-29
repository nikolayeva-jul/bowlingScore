package bowlingGame.model;

import java.util.ArrayList;
import java.util.List;

import bowlingGame.exceptions.BowlingGameException;

public class Game {
	private static final int FRMAES = 10;
	private static final int PINS = 10;
	private List<Frame> frames;
	private int frameCount;
	private int totalScore;

	public Game() {
		this.frames = new ArrayList<>(FRMAES);
		for (int i = 0; i < FRMAES; i++) {
			frames.add(new Frame());
		}
	}

	public void roll(int pins) {
		if (pins > PINS) {
			throw new BowlingGameException("Wrong number of pins: " + pins + ". Max pins " + PINS);
		}
		Frame frame = getFrame();

		if (frame == null) {
			throw new BowlingGameException("The game is over. Start new game.");
		}
		frame.setScore(pins);
		// FIXME if it is bonus frame and previous was spare should we limit to one try
		// only?
	}

	public int score() {
		int score = 0;
		Frame currFrame;
		Frame prevFrame;
		Frame prePrevFrame;
		if (frameCount == 0) {
			currFrame = getCurrentFrame();
			score = currFrame.getScore();
			totalScore += score;
			return totalScore;
		}
		currFrame = getCurrentFrame();
		prevFrame = getPreviousFrame();
		score += currFrame.getScore();
		if (prevFrame.isStrike()) {
			//if consecutive strikes
			if(frameCount >= 2) {
				prePrevFrame = getPreReviousFrame();
				if (prePrevFrame.isStrike()) {
					score += prevFrame.getScore() + currFrame.getFirstTryScore();
					totalScore += score;
					return totalScore;
				}
			}
			
			// score += (prevFrame.getScore() + currFrame.getFirstTryScore() +
			// currFrame.getSecondTryScore());
			score = currFrame.getScore() * 2;
		}
		if (prevFrame.isSpare() && !isBonusFrame()) {
			// score += (prevFrame.getScore() + currFrame.getFirstTryScore());
			score = currFrame.getFirstTryScore() * 2 + currFrame.getSecondTryScore();
		}
		if (isBonusFrame()) {
			score = currFrame.getScore();
		}
		totalScore += score;
		return totalScore;
	}

	private boolean isBonusFrame() {
		return frames.size() == FRMAES + 1;
	}

	private Frame getFrame() {
		Frame frame = getCurrentFrame();

		if (frame.isFinished()) {
			// add a bonus frame
			if (frameCount == FRMAES - 1 && (frame.isStrike() || frame.isSpare())) {
				Frame bonusFrame = new Frame();
				frames.add(bonusFrame);
				frameCount++;
				return bonusFrame;
			}
			frameCount++;
			// check if it is a bonus frame
			if (isBonusFrame()) {
				return null;
			}
			frame = getCurrentFrame();
		}
		return frame;
	}

	private Frame getCurrentFrame() {
		return frames.get(frameCount);
	}
	
	private Frame getPreviousFrame() {
		return frames.get(frameCount-1);
	}
	
	private Frame getPreReviousFrame() {
		return frames.get(frameCount-2);
	}
}
