package com.github.wildsource.gui;

import java.awt.Component;
import java.awt.FlowLayout;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.github.wildsource.infinitemover.UntimedButtonActionListener;
import com.github.wildsource.infinitemover.UntimedPanel;
import com.github.wildsource.progressmover.ProgressButtonActionListener;
import com.github.wildsource.progressmover.ProgressPanel;
import com.github.wildsource.timedmover.TimedButtonActionListener;
import com.github.wildsource.timedmover.TimedPanel;

public class OptionPickerPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private static HashMap<String, Component> componentPool;

	private JButton infiniteOptionButton;

	private JButton timedOptionButton;

	private JButton progressOptionButton;

	private JPanel infiniteJPanel;

	private JPanel timedJPanel;

	private JPanel progressJPanel;

	private static boolean mouseThreadRunning;

	public OptionPickerPanel() {
		mouseThreadRunning = false;
		componentPool = new HashMap<String, Component>();

		setLayout(new FlowLayout());

		infiniteOptionButton = new JButton("untimed mouse movement");
		infiniteOptionButton.addActionListener(new UntimedButtonActionListener());
		timedOptionButton = new JButton("timed mouse movement");
		timedOptionButton.addActionListener(new TimedButtonActionListener());
		progressOptionButton = new JButton("loading based mouse movement");
		progressOptionButton.addActionListener(new ProgressButtonActionListener());
		add(infiniteOptionButton);
		add(timedOptionButton);
		add(progressOptionButton);

		infiniteJPanel = new UntimedPanel();
		infiniteJPanel.setVisible(false);
		componentPool.put("infinite", infiniteJPanel);
		add(infiniteJPanel);

		timedJPanel = new TimedPanel();
		timedJPanel.setVisible(false);
		componentPool.put("timed", timedJPanel);
		add(timedJPanel);

		progressJPanel = new ProgressPanel();
		progressJPanel.setVisible(false);
		componentPool.put("progress", progressJPanel);
		add(progressJPanel);
	}

	public static HashMap<String, Component> getComponentPool() {
		return componentPool;
	}

	public static void setComponentPool(HashMap<String, Component> componentPool) {
		OptionPickerPanel.componentPool = componentPool;
	}

	public JButton getInfiniteOptionButton() {
		return infiniteOptionButton;
	}

	public void setInfiniteOptionButton(JButton infiniteOptionButton) {
		this.infiniteOptionButton = infiniteOptionButton;
	}

	public JButton getTimedOptionButton() {
		return timedOptionButton;
	}

	public void setTimedOptionButton(JButton timedOptionButton) {
		this.timedOptionButton = timedOptionButton;
	}

	public JButton getProgressOptionButton() {
		return progressOptionButton;
	}

	public void setProgressOptionButton(JButton progressOptionButton) {
		this.progressOptionButton = progressOptionButton;
	}

	public static boolean isMouseThreadRunning() {
		return mouseThreadRunning;
	}

	public static void setMouseThreadRunning(boolean state) {
		mouseThreadRunning = state;
	}

}
