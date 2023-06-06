package com.github.wildsource.infinitemover;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Robot;

import javax.swing.JLabel;

import com.github.wildsource.gui.OptionPickerPanel;

public class InfiniteMouseThread extends Thread {

	private boolean isRunning;

	public InfiniteMouseThread() {
		this.isRunning = true;
	}

	@Override
	public void run() {
		try {
			Robot robot = new Robot();
			while (isRunning) {
				log("Thread is running");

				robot.mouseMove(MouseInfo	.getPointerInfo()
											.getLocation().x
						+ 50,
						MouseInfo	.getPointerInfo()
									.getLocation().y);

				sleepThread();

				robot.mouseMove(MouseInfo	.getPointerInfo()
											.getLocation().x
						- 50,
						MouseInfo	.getPointerInfo()
									.getLocation().y);

				sleepThread();
			}
			log("Thread Killed");
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public void sleepThread() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void log(String logMessage) {
		((JLabel) OptionPickerPanel	.getComponentPool()
									.get("untimedLog")).setText(logMessage);
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

}
