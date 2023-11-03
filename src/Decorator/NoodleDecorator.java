package Decorator;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


//abstract class holding the decorate and decorator
public abstract class NoodleDecorator implements NoodleInterface {
	NoodleInterface noods;

	protected BufferedImage img;
	protected int xPos, yPos;

	public NoodleDecorator(NoodleInterface baseNoodles, int x, int y) {
		noods = baseNoodles;
		xPos = x;
		yPos = y;
	}

	public void decorate(Graphics2D g2) {

	}
}
