package Decorator;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import assets.ImageLoader;

//Decorator class used to decorate the broccoli for the noodles
public class BroccoliDecorator extends NoodleDecorator {

	public BroccoliDecorator(NoodleInterface baseNoodles, int x, int y) {
		super(baseNoodles, x, y);
		img = ImageLoader.loadImage("assets/broccoli.png");
	}

	public void decorate(Graphics2D g2) {
		decorateWithBroccoli(g2);
	}

	private void decorateWithBroccoli(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.drawImage(img, -img.getWidth() / 2 - 20, -img.getHeight() / 2 - 20, null);
		g2.setTransform(at);
	}
}
