package com.github.wildsource.progressmover;

import java.awt.KeyEventDispatcher;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class ProgressKeyEventDispatch implements KeyEventDispatcher {

	private Point location1, location2;

	private Photograph photograph;

	public ProgressKeyEventDispatch() {
	}

	public ProgressKeyEventDispatch(Photograph photograph) {
		this.photograph = photograph;
	}

	public boolean dispatchKeyEvent(KeyEvent e) {
		if (e.getID() == KeyEvent.KEY_TYPED) {
			char keyChar = e.getKeyChar();
			if (keyChar == 'z') {
				Point location = MouseInfo	.getPointerInfo()
											.getLocation();
				this.location1 = location;
				System.out.println("Location 1: " + location);
				if (this.location2 != null) {
					photograph.notifiedAndCalculatePhotoArea(this.location1, this.location2);
				}
			}
			if (keyChar == 'x') {
				Point location = MouseInfo	.getPointerInfo()
											.getLocation();
				this.location2 = location;
				System.out.println("Location 2: " + location);
				if (this.location1 != null) {
					photograph.notifiedAndCalculatePhotoArea(this.location1, this.location2);
				}
			}
		}
		return false;
	}

	public Point getLocation1() {
		return location1;
	}

	public void setLocation1(Point location1) {
		this.location1 = location1;
	}

	public Point getLocation2() {
		return location2;
	}

	public void setLocation2(Point location2) {
		this.location2 = location2;
	}

	public List<Point> getLocations() {
		List<Point> locations = new ArrayList<Point>();
		locations.add(location1);
		locations.add(location2);
		return locations;
	}

	public Photograph getPhotograph() {
		return photograph;
	}

	public void setPhotograph(Photograph photograph) {
		this.photograph = photograph;
	}
}
