package com.github.wildsource.progressmover;

import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.github.wildsource.gui.OptionPickerPanel;

public class ProgressPanel extends JPanel {

	private static final long serialVersionUID = -3735451675720289491L;

	private JLabel instructionsLabel1, instructionsLabel2, instructionsLabel3, instructionsLabel4;

	private JButton startButton;

	private JButton stopButton;

	private JLabel log;

	private JLabel image;

	private ProgressMouseThread mouseThread;

	public ProgressPanel() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		instructionsLabel1 = new JLabel("1. pressing z records the first coordinate on your screen");
		instructionsLabel2 = new JLabel("2. pressing x recors the second coordinate on your screen");
		instructionsLabel3 = new JLabel("3. pressing c calculates an area which the program will take a picture");
		instructionsLabel4 = new JLabel("every 5 seconds to read the progress of the download");

		log = new JLabel("");
		OptionPickerPanel	.getComponentPool()
							.put("progressLog", log);

		image = new JLabel("Picture output");

		JPanel imageJPanel = new JPanel();
		imageJPanel.setSize(500, 10);
		image.setSize(500, 10);
		imageJPanel.add(image);

		Photograph photograph = new Photograph(image);

		ProgressKeyEventDispatch keyboard = new ProgressKeyEventDispatch();
		keyboard.setPhotograph(photograph);

		KeyboardFocusManager.getCurrentKeyboardFocusManager()
							.addKeyEventDispatcher(keyboard);

		startButton = new JButton("start");
		mouseThread = new ProgressMouseThread(photograph);
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mouseThread.start();
			}
		});
		stopButton = new JButton("stop");
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mouseThread.setRunning(false);
			}
		});

		add(instructionsLabel1);
		add(instructionsLabel2);
		add(instructionsLabel3);
		add(instructionsLabel4);
		add(startButton);
		add(stopButton);
		add(log);
		add(imageJPanel);
	}

	public JButton getStartButton() {
		return startButton;
	}

	public void setStartButton(JButton startButton) {
		this.startButton = startButton;
	}

	public JButton getStopButton() {
		return stopButton;
	}

	public void setStopButton(JButton stopButton) {
		this.stopButton = stopButton;
	}

	public JLabel getLog() {
		return log;
	}

	public void setLog(JLabel log) {
		this.log = log;
	}
}
