package kitchen;

import assets.ImageLoader;

//holds the info for pot empty and with water, and position for the stove and bowl.
public class Pot extends KitchenObjects {
	int empty = 0;
	int filled = 1;

	public int state = empty;
	private boolean onStove = false;
	private boolean onBowl = false;

	public Pot(String file, double x, double y) {
		img = ImageLoader.loadImage(file);
		xPos = x;
		yPos = y;
	}

	public void underSink(Sink sink) {
		if ((xPos > 930) && (xPos < 1050) && (yPos > 330) && (yPos < 420)) {
			sink.setImg(0);
			state = filled;
			setImg(1);
		}
	}

	public void onStove() {
		if ((xPos > 350) && (xPos < 444) && (yPos > 380) && (yPos < 430))
			onStove = true;
	}

	public boolean isOnStove() {
		return onStove;
	}

	public void takeOffStove() {
		onStove = false;
	}

	public boolean inBowl() {
		return onBowl;
	}

	public void topOfBowl() {
		if ((xPos > 580) && (xPos < 700) && (yPos > 240) && (yPos < 330)) {
			onBowl = true;
			setImg(2);
		}
	}

	private void setImg(int potState) {
		if (potState == 1) {
			img = ImageLoader.loadImage("assets/pot2.png");
		}
		if (potState == 2) {
			img = ImageLoader.loadImage("assets/blank.png");
		}
	}

}
