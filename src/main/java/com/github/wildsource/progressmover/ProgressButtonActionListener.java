package com.github.wildsource.progressmover;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import com.github.wildsource.gui.OptionPickerPanel;

public class ProgressButtonActionListener implements ActionListener {

	HashMap<String, Component> pool = OptionPickerPanel.getComponentPool();

	public void actionPerformed(ActionEvent e) {
		pool.get("progress")
			.setVisible(true);
		pool.get("timed")
			.setVisible(false);
		pool.get("infinite")
			.setVisible(false);
	}

}
