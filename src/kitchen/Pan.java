package kitchen;

import assets.ImageLoader;

//holds the info for the pan, and egg cooking, position for stove/bowl different from pot.
public class Pan extends KitchenObjects {
	int empty = 0;
	int filled = 1;

	public int state = empty;
	private boolean onStove = false;
	private boolean onBowl = false;

	public Pan(String file, double x, double y) {
		img = ImageLoader.loadImage(file);
		xPos = x;
		yPos = y;
	}

	public void onStove() {
		if ((xPos > 210) && (xPos < 250) && (yPos > 400) && (yPos < 440)) {
			onStove = true;
		}
	}

	public void setImg(int panState) {
		if (panState == 1) {
			img = ImageLoader.loadImage("assets/pan1.png");
		}
		if (panState == 2) {
			img = ImageLoader.loadImage("assets/blank.png");
		}
	}

	public boolean inBowl() {
		return onBowl;
	}

	public void topOfBowl() {
		if ((xPos > 580) && (xPos < 680) && (yPos > 270) && (yPos < 330)) {
			onBowl = true;
			setImg(2);
		}
	}

	public boolean isOnStove() {
		return onStove;
	}

}
