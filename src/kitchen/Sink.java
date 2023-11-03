package kitchen;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import assets.ImageLoader;

//images for running water and no water.
public class Sink extends KitchenObjects {

	public Sink(String file, double x, double y) {
		img = ImageLoader.loadImage(file);
		xPos = x;
		yPos = y;
	}

	public void draw(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(scale, scale);
		g2.drawImage(img, -img.getWidth() / 2, -img.getHeight() / 2, null);
		g2.setTransform(at);
	}

	public void setImg(int sinkState) {
		if (sinkState == 0) {
			img = ImageLoader.loadImage("assets/sink.png");
		}
		if (sinkState == 1) {
			img = ImageLoader.loadImage("assets/sink2.png");
		}
	}
}
