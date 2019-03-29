package bowlingGame.model;

import java.util.ArrayList;
import java.util.List;

import bowlingGame.exceptions.BowlingGameException;

public class Game {
	private static final int FRMAES = 10;
	private static final int PINS = 10;
	private List<Frame> frames;
	private int frameIndex;

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
	}

	public int score() {
		Frame currFrame = getCurrentFrame();
		Frame prevFrame;
		Frame prePrevFrame;
		if (frameIndex == 0) {
			return getFirstFrameScore();
		}
		prevFrame = getPreviousFrame();
		if(currFrame.isBonus() && prevFrame.isStrike()) {
			prevFrame.updateFrameScore(10 + currFrame.getScoreSum());
		}
		if (prevFrame.isStrike()) {
			if (frameIndex >= 2) {
				prePrevFrame = getPreReviousFrame();
				if (prePrevFrame.isStrike()) {
					prePrevFrame.updateFrameScore(10 + prevFrame.getScoreSum() + currFrame.getFirstTryScore());
				}
			}
			if (currFrame.isOpenFrame()) {
				prevFrame.updateFrameScore(10 + currFrame.getScoreSum());
			}
		}
		
		if (prevFrame.isSpare()) {
			prevFrame.updateFrameScore(10 + currFrame.getFirstTryScore());
		}
		if (currFrame.isOpenFrame()) {
			currFrame.updateFrameScore(currFrame.getScoreSum());
		}
		return frames.stream().map(e -> e.getFrameScore()).reduce(0, (e1, e2) -> e1 + e2);
	}
	
	private int getFirstFrameScore() {
		Frame currFrame = frames.get(0);
		if (!currFrame.isStrike() && !currFrame.isSpare()) {
			currFrame.updateFrameScore(currFrame.getScoreSum());
			return currFrame.getFrameScore();
		}
		return 0;
	}

	private Frame getFrame() {
		Frame frame = getCurrentFrame();

		if (frame.isFinished()) {
			// add a bonus frame
			if (frameIndex >= FRMAES - 1 && (frame.isStrike() || frame.isSpare())) {
				Frame bonusFrame = new Frame();
				bonusFrame.setBonus();
				frames.add(bonusFrame);
				frameIndex++;
				return bonusFrame;
			}
			frameIndex++;
			// check if it is a bonus frame
			if (frame.isBonus()) {
				return null;
			}
			frame = getCurrentFrame();
		}
		return frame;
	}

	private Frame getCurrentFrame() {
		return frames.get(frameIndex);
	}

	private Frame getPreviousFrame() {
		return frames.get(frameIndex - 1);
	}

	private Frame getPreReviousFrame() {
		return frames.get(frameIndex - 2);
	}
}
