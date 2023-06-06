package com.github.wildsource.timedmover;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import com.github.wildsource.gui.OptionPickerPanel;

public class TimedButtonActionListener implements ActionListener {

	HashMap<String, Component> pool = OptionPickerPanel.getComponentPool();

	public void actionPerformed(ActionEvent e) {
		pool.get("timed")
			.setVisible(true);
		pool.get("infinite")
			.setVisible(false);
		pool.get("progress")
			.setVisible(false);
	}
}
