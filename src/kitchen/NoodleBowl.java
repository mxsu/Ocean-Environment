package kitchen;

import assets.ImageLoader;

//holds the images for the noodle bowl, empty, with noodles, egg and noodles
public class NoodleBowl extends KitchenObjects {

	public NoodleBowl(String file, double x, double y) {
		img = ImageLoader.loadImage(file);
		xPos = x;
		yPos = y;
	}

	public void setImg(int bowlState) {
		if (bowlState == 0) {
			img = ImageLoader.loadImage("assets/bowl0.png");
		}
		if (bowlState == 1) {
			img = ImageLoader.loadImage("assets/bowl1.png");
		}
		if (bowlState == 2) {
			img = ImageLoader.loadImage("assets/bowl2.png");
		}
	}

}
