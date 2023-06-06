package com.github.wildsource.gui;

import javax.swing.JFrame;

public class Window {
	private JFrame frame;

	public Window() {
		this.frame = new JFrame();
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setTitle("Screen Saver Saver");
		this.frame.setSize(650, 400);
		this.frame.setResizable(false);
		this.frame.setLocationRelativeTo(null);
		this.frame.add(new OptionPickerPanel());
		this.frame.setVisible(true);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

}
