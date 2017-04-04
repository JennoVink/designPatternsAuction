package virtualProxy;

import java.awt.Component;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImageIcon implements Icon {

	protected int imageWidth = 0;
	protected int imageHeigth = 0;
	URL imageURL;
	BufferedImage image;
	
	public ImageIcon(URL imageURL) {
		this.imageURL = imageURL;
		
		try {
			image = ImageIO.read(imageURL);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getIconWidth() {
		return imageWidth;
	}

	@Override
	public int getIconHeight() {
		return imageHeigth;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		c.repaint();

	}

	
}
