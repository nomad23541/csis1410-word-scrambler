package wordscrambler.level;

import wordscrambler.CountdownTimer;
import wordscrambler.WordGenerator;
import wordscrambler.gui.GamePanel;

/**
 * Class representing a level, holds a timer and WordGenerator for each level.
 * 
 * @author Chris Reading
 *
 */
public class Level {
	
	/**
	 * Word to be used for this Level
	 */
	private WordGenerator gen;
	/**
	 * CountdownTimer to be used for this level
	 */
	private CountdownTimer timer;
	/**
	 * The level number (ex: 1, 2, 3, 4, etc)
	 */
	private int levelNum;
	
	private int wordLength;

	/**
	 * Constructs the Level class, takes in the level number and countdown amount in seconds as parameters
	 * @param levelNum int representing this level
	 * @param countdown int in seconds to be used for the timer
	 * @param gamePanel GamePanel is passed through to update the timer JLabel
	 */
	public Level(int levelNum, int wordLength, int countdown, GamePanel gamePanel) {
		this.levelNum = levelNum;
		this.wordLength = wordLength;
		this.gen = new WordGenerator(wordLength);
		this.timer = new CountdownTimer(gamePanel, countdown);
	}
	
	/**
	 * Returns this Level's number
	 * @return int of this level's number
	 */
	public int getLevelNumber() {
		return this.levelNum;
	}
	
	/**
	 * Sets the level number
	 * @return
	 */
	public void setLevelNumber(int num) {
		levelNum = num;
	}
	
	public int getWordLength() {
		return this.wordLength;
	}
	
	/**
	 * Returns the WordGenerator used for this Level
	 * @return WordGenerator for this level
	 */
	public WordGenerator getWordGenerator() {
		return this.gen;
	}
	
	/**
	 * Returns the CountdownTimer instance for this level
	 * @return CountdownTimer instance
	 */
	public CountdownTimer getTimer() {
		return this.timer;
	}

}
