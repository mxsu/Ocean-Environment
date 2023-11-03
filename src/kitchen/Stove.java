package kitchen;

import assets.ImageLoader;

//images for the stove top elements hot and not hot, small/big size
public class Stove extends KitchenObjects {

	private boolean hot = false;

	public Stove(String file, double x, double y) {
		img = ImageLoader.loadImage(file);
		xPos = x;
		yPos = y;
		state = 0;
	}

	public boolean isHot() {
		return hot;
	}

	public void setImg(int potState) {
		if (potState == 0) {
			img = ImageLoader.loadImage("assets/stoveBig0.png");
		}
		if (potState == 1) {
			hot = true;
			img = ImageLoader.loadImage("assets/stoveBig1.png");
		}
		if (potState == 2) {
			img = ImageLoader.loadImage("assets/stoveSmall0.png");
		}
		if (potState == 3) {
			hot = true;
			img = ImageLoader.loadImage("assets/stoveSmall1.png");
		}
	}

	public double getXPos() {
		return xPos;
	}

	public double getYPos() {
		return yPos;
	}

}
