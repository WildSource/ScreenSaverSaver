package com.github.wildsource.infinitemover;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.github.wildsource.gui.OptionPickerPanel;

public class UntimedPanel extends JPanel {
	private static final long serialVersionUID = -9030512952655163023L;

	private JButton startButton;

	private JButton stopButton;

	private JLabel instructionsJLabel1;

	private JLabel instructionsJLabel2;

	private JLabel log;

	private InfiniteMouseThread infiniteMouseThread;

	public UntimedPanel() {
		setLayout(new GridLayout(10, 10));

		infiniteMouseThread = new InfiniteMouseThread();

		instructionsJLabel1 = new JLabel(
				"Press start and it will move your mouse 50px from left to right back and forth indefinitely");
		instructionsJLabel2 = new JLabel("each 2 seconds until you click on stop");

		startButton = new JButton("start");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				infiniteMouseThread.start();
			}
		});

		stopButton = new JButton("stop");
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				infiniteMouseThread.setRunning(false);
			}
		});

		log = new JLabel("this is where it will output the state");
		OptionPickerPanel	.getComponentPool()
							.put("untimedLog", this.log);

		add(instructionsJLabel1);
		add(instructionsJLabel2);
		add(startButton);
		add(stopButton);
		add(log);
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