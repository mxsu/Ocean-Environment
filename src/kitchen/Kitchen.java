package kitchen;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import assets.ImageLoader;
import main.NoodlePanel;

//holds the background image
public class Kitchen extends KitchenObjects {

	public Kitchen(String file) {
		img = ImageLoader.loadImage(file);

	}

	public void draw(Graphics2D g2) {
		super.draw(g2);
		AffineTransform at = g2.getTransform();
		g2.translate(0, 0);
		g2.scale(1, 1);
		g2.drawImage(img, 0, 0, NoodlePanel.W_WIDTH, NoodlePanel.W_HEIGHT, null);
		g2.setTransform(at);
	}
}
