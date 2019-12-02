package wordscrambler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import wordscrambler.gui.GamePanel;

/**
 * TODO: finish this class, GUI must be implemented before I can look into this class some more
 * @author Chris Reading
 *
 */
public class CountdownTimer {
	
	private GamePanel gamePanel;
	private int startTime;
	private int duration;
	
	private Timer timer;
	
	public CountdownTimer(GamePanel gamePanel, int duration) {
		this.gamePanel = gamePanel;
		this.duration = duration;
		this.startTime = duration;
		
		timer = new Timer(1000, new TimerListener());
	}
	
	public void useHint() {
		int penalty = Math.round(startTime * .05f);
		if(duration >= penalty) {
			duration -= penalty;
			gamePanel.updateTimerLabel(duration);
		}
	}
	
	public int getStartTime() {
		return this.startTime;
	}
	
	public int getDuration() {
		return this.duration;
	}
	
	public void start() {
		timer.start();
	}
	
	public void reset() {
		timer.restart();
	}
	
	public void stop() {
		timer.stop();
	}
	
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
