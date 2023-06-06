package com.github.wildsource.progressmover;

import java.awt.AWTException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import net.sourceforge.tess4j.ITessAPI.TessPageSegMode;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Photograph {
	private Rectangle rectangle;

	private JLabel imageLabel;

	public Photograph() {
		this.rectangle = null;
	}

	public Photograph(JLabel imageLabel) {
		this.rectangle = null;
		this.imageLabel = imageLabel;

	}

	public void notifiedAndCalculatePhotoArea(Point point1, Point point2) {
		int x1 = point1.x;
		int y1 = point1.y;
		int x2 = point2.x;
		int y2 = point2.y;
		// Calculate the distance between the two endpoints
		double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

		// Calculate the angle of the diagonal line
		double angle = Math.atan2(y2 - y1, x2 - x1);

		// Calculate the width and height
		double width = Math.abs(distance * Math.cos(angle));
		double height = Math.abs(distance * Math.sin(angle));

		System.out.println("Width of the rectangle: " + width);
		System.out.println("Height of the rectangle: " + height);

		this.rectangle = new Rectangle(x1, y1, (int) width, (int) height);
		takePhoto();
	}

	public BufferedImage takePhoto() {
		BufferedImage image = null;
		if (this.rectangle != null) {
			try {
				Robot robot = new Robot();
				image = robot.createScreenCapture(this.rectangle);
				resize(image, imageLabel.getWidth(), imageLabel.getHeight());
				updateImageUI(image);
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return image;
	}

	public void updateImageUI(BufferedImage image) {
		this.imageLabel.setIcon(new ImageIcon(image));
	}

	public int readProgress() {
		int progressValue = -1;
		try {
			Tesseract tesseract = new Tesseract();

			tesseractSetup(tesseract);

			String result = tesseract.doOCR(takePhoto());

			progressValue = Integer.parseInt(result.replaceAll("[^0-9]", ""));
			System.out.println(progressValue);
		} catch (TesseractException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return progressValue;
	}

	public static BufferedImage resize(BufferedImage img, int newW, int newH) {
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}

	public void tesseractSetup(Tesseract tesseract) {
		tesseract.setDatapath("tessdata");
		tesseract.setLanguage("eng");
		tesseract.setPageSegMode(1);
		tesseract.setOcrEngineMode(1);
		tesseract.setPageSegMode(TessPageSegMode.PSM_SINGLE_BLOCK);
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	public JLabel getImageLabel() {
		return imageLabel;
	}

	public void setImageLabel(JLabel imageLabel) {
		this.imageLabel = imageLabel;
	}

}
