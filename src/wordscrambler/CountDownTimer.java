package wordscrambler;

import java.util.Timer;
import java.util.TimerTask;

/**
 * TODO: finish this class
 * @author Chris Reading
 *
 */
public class CountDownTimer {
	
	public enum TimerStatus {
		NOT_STARTED,
		RUNNING,
		FINISHED
	}
	
	private TimerStatus status;
	private final int seconds;
	private int currentTime;
	
	private Timer timer = new Timer();
	
	public CountDownTimer(int seconds) {
		this.seconds = seconds;
		this.currentTime = seconds;
		this.status = TimerStatus.NOT_STARTED;
	}
	
	public int getCountDownTime() {
		return this.seconds;
	}
	
	public int getCurrentTime() {
		return this.currentTime;
	}
	
	public TimerStatus getStatus() {
		return this.status;
	}

	public void start() {
		this.status = TimerStatus.RUNNING;
		
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if(currentTime == 0) {
					status = TimerStatus.FINISHED;
					timer.cancel();
				} else {
					currentTime -= 1;
				}
			}
		}, 1000, this.seconds * 1000);
	}
	
	private class CountDownTimerTask extends TimerTask {
		
		private int totalTime;
		private int currentTime;
		
		public CountDownTimerTask(int totalTime, int currentTime) {
			this.totalTime = totalTime;
			this.currentTime = currentTime;
		}
		
		
		
		public void run() {
			
		}
	}
	
}
