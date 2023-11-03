package kitchen;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import main.NoodlePanel;

//abstract class pull up
public abstract class KitchenObjects {

	protected BufferedImage img;
	protected int state;
	protected double xPos;
	protected double yPos;
	protected double scale = 1;

	public void draw(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(1, 1);
		g2.drawImage(img, -img.getWidth() / 2, -img.getHeight() / 2, null);
		g2.setTransform(at);
	}

	public void setPos(double x, double y) {
		xPos = x;
		yPos = y;
	}

	public boolean clicked(double x, double y) {
		boolean clicked = false;

		if (x > (xPos - ((double) img.getWidth()) / 2 * scale) && x < (xPos + ((double) img.getWidth()) / 2 * scale)
				&& y > (yPos - ((double) img.getHeight()) / 2 * scale)
				&& y < (yPos + ((double) img.getHeight()) / 2 * scale))
			clicked = true;

		return clicked;
	}
	

}
