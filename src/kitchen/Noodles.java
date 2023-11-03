package kitchen;

import assets.ImageLoader;

//info for the noodle package, open noodles, and cooking in pot
public class Noodles extends KitchenObjects {
	private boolean open = false;
	private boolean onPot = false;

	public Noodles(String file, double x, double y) {
		img = ImageLoader.loadImage(file);
		xPos = x;
		yPos = y;
	}

	public boolean isOpen() {
		return open;
	}

	public void onPot() {
		if ((xPos > 370) && (xPos < 425) && (yPos > 296) && (yPos < 370))
			onPot = true;
	}

	public boolean isOnPot() {
		return onPot;
	}

	public void setImg(int noodleState) {
		if (noodleState == 0) {
			img = ImageLoader.loadImage("assets/noodles0.png");
		}
		if (noodleState == 1) {
			open = true;
			img = ImageLoader.loadImage("assets/noodles1.png");
		}
		if (noodleState == 2) {
			open = true;
			img = ImageLoader.loadImage("assets/noodles2.png");
		}
		if (noodleState == 3) {
			open = true;
			img = ImageLoader.loadImage("assets/blank.png");
		}

	}

}
