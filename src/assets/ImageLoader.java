package assets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//From Lab example, used to load images
public class ImageLoader {

	public static BufferedImage loadImage(String imgFile) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(imgFile));
		} catch (IOException e) {
			System.out.println("Oops, something wrong with your image file");
		}
		return img;
	}

	public static boolean saveImage(BufferedImage img, String fileName, String fileFormat) {
		try {

			File saveFile = new File(fileName + "." + fileFormat);
			ImageIO.write(img, fileFormat, saveFile);

		} catch (IOException e) {
			return false;
		}
		return true;
	}

}
