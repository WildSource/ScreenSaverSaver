package com.github.wildsource.timedmover;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.github.wildsource.gui.OptionPickerPanel;

public class TimedPanel extends JPanel {

	private static final long serialVersionUID = -3326450743139583870L;

	private JLabel instructions;

	private JLabel minutesJLabel;

	private JSlider minuteSlider;

	private JButton startButton;

	private JButton stopButton;

	private TimedMouseThread timedMouseThread;

	private JLabel timerLabel;

	public TimedPanel() {
		setLayout(new GridLayout(10, 10));

		timerLabel = new JLabel("00:00");
		OptionPickerPanel	.getComponentPool()
							.put("clock", timerLabel);

		instructions = new JLabel("Select a time in minutes  and press start or stop manually");
		minutesJLabel = new JLabel("time of execution in minutes:");
		minuteSlider = new JSlider(0, 120);
		minuteSlider.setValue(0);
		minuteSlider.setMajorTickSpacing(20);
		minuteSlider.setPaintLabels(true);
		minuteSlider.setPaintTicks(true);
		minuteSlider.setPaintTrack(true);
		minuteSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				minutesJLabel.setText("time of execution in minutes: " + minuteSlider.getValue());
				timerLabel.setText(minuteSlider.getValue() + ":00");
				timedMouseThread = new TimedMouseThread(minuteSlider.getValue());
			}
		});

		startButton = new JButton("start");
		startButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				timedMouseThread.start();
			}
		});

		stopButton = new JButton("stop");
		stopButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				timedMouseThread.setRunning(false);
			}
		});

		add(instructions);
		add(minutesJLabel);
		add(minuteSlider);
		add(startButton);
		add(stopButton);
		add(timerLabel);
	}

	public JSlider getMinuteSlider() {
		return minuteSlider;
	}

	public void setMinuteSlider(JSlider minuteSlider) {
		this.minuteSlider = minuteSlider;
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

}
