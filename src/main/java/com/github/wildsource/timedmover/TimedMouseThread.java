package com.github.wildsource.timedmover;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.time.Duration;
import java.time.LocalDateTime;

import javax.swing.JLabel;

import com.github.wildsource.gui.OptionPickerPanel;

public class TimedMouseThread extends Thread {

	private boolean isRunning;

	private int timeMinutes;

	public TimedMouseThread(int timeMinutes) {
		this.isRunning = true;
		this.timeMinutes = timeMinutes;
	}

	@Override
	public void run() {
		try {
			Robot robot = new Robot();
			LocalDateTime startTime = LocalDateTime.now();
			LocalDateTime endTime = startTime.plusMinutes(timeMinutes);
			while (isRunning && LocalDateTime	.now()
												.isBefore(endTime)) {
				robot.mouseMove(MouseInfo	.getPointerInfo()
											.getLocation().x
						+ 100,
						MouseInfo	.getPointerInfo()
									.getLocation().y);

				sleepThread();

				robot.mouseMove(MouseInfo	.getPointerInfo()
											.getLocation().x
						- 100,
						MouseInfo	.getPointerInfo()
									.getLocation().y);

				Duration remainingTime = Duration.between(LocalDateTime.now(), endTime);
				long minutes = remainingTime.toMinutes();
				long seconds = remainingTime.minusMinutes(minutes)
											.getSeconds();

				sleepThread();

				updateClockLabel(minutes, seconds);
			}
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public void updateClockLabel(long minutes, long seconds) {
		((JLabel) OptionPickerPanel	.getComponentPool()
									.get("clock")).setText(minutes + ":" + seconds);
	}

	public void sleepThread() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getTime() {
		LocalDateTime.now();
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public int getTimeMinutes() {
		return timeMinutes;
	}

	public void setTimeMinutes(int timeMinutes) {
		this.timeMinutes = timeMinutes;
	}
}
