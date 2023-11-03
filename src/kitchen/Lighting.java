package kitchen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import assets.ImageLoader;
import main.NoodlePanel;

//Lightswitch object, room lighting, and 
public class Lighting extends KitchenObjects {

	public Lighting(String file, double x, double y) {
		img = ImageLoader.loadImage(file);
		xPos = x;
		yPos = y;
		state = 0;
	}

	public void draw(Graphics2D g2) {
		// super.draw(g2);
		if (state == 0) { // draw a white bulb using geoms on top to hide the light
			g2.setColor(new Color(0, 0, 0, 169));
			Rectangle2D.Double dark = new Rectangle2D.Double(0, 0, NoodlePanel.W_WIDTH, NoodlePanel.W_HEIGHT);
			g2.fill(dark);
		}

		AffineTransform at = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(1, 1);
		g2.drawImage(img, -img.getWidth() / 2, -img.getHeight() / 2, null);
		g2.setTransform(at);
	}

	public void setLightOn(int on) {
		state = on;
		if (on == 1)
			img = ImageLoader.loadImage("assets/lightSwitch2.png");
		if (on == 0)
			img = ImageLoader.loadImage("assets/lightSwitch.png");
	}

}
