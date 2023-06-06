package com.github.wildsource.progressmover;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Robot;

import javax.swing.JLabel;

import com.github.wildsource.gui.OptionPickerPanel;

public class ProgressMouseThread extends Thread {

	private boolean isRunning;

	private JLabel log;

	private Photograph photograph;

	public ProgressMouseThread() {
		this.isRunning = true;
		this.log = (JLabel) OptionPickerPanel	.getComponentPool()
												.get("progressLog");
	}

	public ProgressMouseThread(Photograph photograph) {
		this.isRunning = true;
		this.photograph = photograph;
		this.log = (JLabel) OptionPickerPanel	.getComponentPool()
												.get("progressLog");

	}

	@Override
	public void run() {
		try {
			Robot robot = new Robot();
			while (isRunning && photograph.readProgress() < 100) {

				this.log.setText("Thread is running");

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
		} catch (AWTException e) {
			e.printStackTrace();
		}
		this.log.setText("Thread Killed");
	}

	public void sleepThread() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public Photograph getPhotograph() {
		return photograph;
	}

	public void setPhotograph(Photograph photograph) {
		this.photograph = photograph;
	}
}
