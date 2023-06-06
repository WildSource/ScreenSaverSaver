package com.github.wildsource.infinitemover;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import com.github.wildsource.gui.OptionPickerPanel;

public class UntimedButtonActionListener implements ActionListener {

	HashMap<String, Component> pool = OptionPickerPanel.getComponentPool();

	public void actionPerformed(ActionEvent e) {
		pool.get("infinite")
			.setVisible(true);
		pool.get("timed")
			.setVisible(false);
		pool.get("progress")
			.setVisible(false);
	}

}
