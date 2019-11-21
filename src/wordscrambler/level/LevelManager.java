package wordscrambler.level;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import wordscrambler.gui.GamePanel;

/**
 * Manages advancing to the next level, or resetting the current level
 * 
 * @author Chris Reading
 *
 */
public class LevelManager {
		
	/**
	 * List that contains all the levels in the game
	 * 
	 * TODO: this will become a queue type in later revisions
	 */
	private List<Level> levels;
	
	/**
	 * Level type that holds the current level
	 */
	private Level currentLevel;
	
	/**
	 * GamePanel for updating the timer JLabel
	 */
	private GamePanel gamePanel;
	
	/**
	 * Constructs the LevelManager class, initiates the levels List and adds 10 test levels
	 */
	public LevelManager(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		levels = new ArrayList<Level>();
		
		// add levels for testing
		for(int i = 1; i <= 10; i++) {
			levels.add(new Level(i, 30, gamePanel));
		}
		
		// initially set the current level to first level
		currentLevel = levels.get(0);
	}
	
	/**
	 * Advance to the next level by get the current level's number and incrementing 1
	 * 
	 * TODO: implement a queue instead of using this wonky for loop to find the next level
	 */
	public void nextLevel() {
		for(Level level : levels) {
			if(level.getLevelNumber() == currentLevel.getLevelNumber() + 1) {
				currentLevel = level;
				break;
			}
		}
	}
	
	/**
	 * Resets the current level with a new Level type (creates a new word and such)
	 */
	public void resetLevel() {
		currentLevel = new Level(currentLevel.getLevelNumber(), currentLevel.getTimer().getStartTime(), gamePanel);
	}
	
	/**
	 * Returns the current level 
	 * @return Level representing the current level
	 */
	public Level getCurrentLevel() {
		return this.currentLevel;
	}
	
}
