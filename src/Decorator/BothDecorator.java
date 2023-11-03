package Decorator;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import assets.ImageLoader;

//Decorator class used to decorate both the broccoli and hotdog
public class BothDecorator extends NoodleDecorator {

	public BothDecorator(NoodleInterface baseNoodles, int x, int y) {
		super(baseNoodles, x, y);
		img = ImageLoader.loadImage("assets/hotdog.png");
	}

	public void decorate(Graphics2D g2) {
		decorateWithHotdog(g2);
		decorateWithBroccoli(g2);
	}

	private void decorateWithHotdog(Graphics2D g2) {
		img = ImageLoader.loadImage("assets/hotdog.png");
		AffineTransform at = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.drawImage(img, -img.getWidth() / 2 - 30, -img.getHeight() / 2 - 20, null);
		g2.setTransform(at);
	}

	private void decorateWithBroccoli(Graphics2D g2) {
		img = ImageLoader.loadImage("assets/broccoli.png");
		AffineTransform at = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.drawImage(img, -img.getWidth() / 2 - 20, -img.getHeight() / 2 - 20, null);
		g2.setTransform(at);
	}
}
