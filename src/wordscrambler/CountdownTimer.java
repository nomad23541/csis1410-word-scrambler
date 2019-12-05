package wordscrambler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import wordscrambler.gui.GamePanel;

/**
 * Creates a timer that will be implemented in the GamePanel class.
 * @author Chris Reading
 *
 */
public class CountdownTimer {
	
	/**
	 * Creates an object of type GamePanel
	 */
	private GamePanel gamePanel;
	/**
	 * Creates a startTime int
	 */
	private int startTime;
	/**
	 * Creates a duration int
	 */
	private int duration;
	
	/**
	 * Initializes the timer
	 */
	private Timer timer;
	
	/**
	 * Constructor - Sets the gamePanel, duration, and startTime with the arguments. Sets the timer with a new Timer.
	 * @param gamePanel
	 * @param duration
	 */
	public CountdownTimer(GamePanel gamePanel, int duration) {
		this.gamePanel = gamePanel;
		this.duration = duration;
		this.startTime = duration;
		
		timer = new Timer(1000, new TimerListener());
	}
	
	/**
	 * Docks half of the timer if a hint is used.
	 */
	public void useHint() {
		int penalty = Math.round(startTime * .50f);
		duration -= penalty;
		gamePanel.updateTimerLabel(duration);
		
		if(duration <= 0) {
			gamePanel.updateTimerLabel(0);
			System.out.println("Game Over");
		}
	}
	
	/**
	 * Returns the startTime.
	 * @return
	 */
	public int getStartTime() {
		return this.startTime;
	}
	
	/**
	 * Returns the duration.
	 * @return
	 */
	public int getDuration() {
		return this.duration;
	}
	
	/**
	 * Starts the timer.
	 */
	public void start() {
		timer.start();
	}
	
	/**
	 * Resets the timer to the start value.
	 */
	public void reset() {
		timer.restart();
	}
	
	/**
	 * Stops the timer.
	 */
	public void stop() {
		timer.stop();
	}
	
	/**
	 * TimerListener class updates the timer as time passes.
	 * @author 
	 *
	 */
	private class TimerListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			--duration;
			
			if(duration >= 0) {
				gamePanel.updateTimerLabel(duration);
			} else {
				((Timer) (e.getSource())).stop();
				gamePanel.displayTimeOut();
			}
		}
		
	}
	
}
