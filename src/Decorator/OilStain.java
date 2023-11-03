package Decorator;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

//Fractal design for an oil stain on the wall, Lecture 11 Tree example
public class OilStain {

	public void drawOilStain(Graphics2D g2, float len) {

		g2.setStroke(new BasicStroke(2));
		g2.setColor(new Color(150, 75, 0));
		g2.draw(new Line2D.Float(0, 0, 0, -len));

		g2.translate(0, -len);

		len *= 0.66;

		if (len > 2) {
			AffineTransform at = g2.getTransform();
			g2.rotate(Math.PI / 5);
			drawOilStain(g2, len);
			g2.setTransform(at);
			// LEFT
			at = g2.getTransform();
			g2.rotate(-Math.PI / 5);
			drawOilStain(g2, len);
			g2.setTransform(at);

		}
	}

}
