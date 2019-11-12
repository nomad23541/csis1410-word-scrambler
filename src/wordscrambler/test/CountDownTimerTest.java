package wordscrambler.test;

import wordscrambler.CountDownTimer;

public class CountDownTimerTest {
	
	public static void main(String[] args) {
		CountDownTimer timer = new CountDownTimer(10);
		timer.start();
		
		while(timer.getCurrentTime() > 0) {
			System.out.println(timer.getStatus() + " " + timer.getCurrentTime());
		}
	}

}
