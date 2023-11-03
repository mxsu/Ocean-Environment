package kitchen;

import assets.ImageLoader;

//information for the start/retry buttons, start screen and end screen
public class StartButton extends KitchenObjects {

	public StartButton(String file, double x, double y) {
		img = ImageLoader.loadImage(file);
		xPos = x;
		yPos = y;
		state = 0;
	}

	public void setImg(int ButtonState) {
		if (ButtonState == 1) {
			img = ImageLoader.loadImage("assets/start.png");
		}
		if (ButtonState == 2) {
			img = ImageLoader.loadImage("assets/startBG.png");
		}
		if (ButtonState == 3) {
			img = ImageLoader.loadImage("assets/retry.png");
		}
		if (ButtonState == 4) {
			img = ImageLoader.loadImage("assets/endBG.png");
		}
	}
}
