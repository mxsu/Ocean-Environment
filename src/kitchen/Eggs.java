package kitchen;

import assets.ImageLoader;

//Class holding both the egg carton, and egg information

public class Eggs extends KitchenObjects {
	private boolean open = false;
	private boolean onPan = false;

	public Eggs(String file, double x, double y) {
		img = ImageLoader.loadImage(file);
		xPos = x;
		yPos = y;
	}

	public boolean isOpen() {
		return open;
	}

	public void onPan() {
		if ((xPos > 240) && (xPos < 280) && (yPos > 350) && (yPos < 400))
			onPan = true;
	}

	public boolean isOnPan() {
		return onPan;
	}

	public void setImg(int eggState) {
		if (eggState == 0) {
			img = ImageLoader.loadImage("assets/eggs0.png");
		}
		if (eggState == 1) {
			open = true;
			img = ImageLoader.loadImage("assets/eggs1.png");
		}
		if (eggState == 2) {
			img = ImageLoader.loadImage("assets/egg.png");
		}
		if (eggState == 3) {
			img = ImageLoader.loadImage("assets/egg1.png");
		}
		if (eggState == 4) {
			img = ImageLoader.loadImage("assets/blank.png");
		}
	}

}
