package com.github.wildsource;

import javax.swing.SwingUtilities;

import com.github.wildsource.gui.Window;

public class Application {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Window();
			}
		});
	}

}
