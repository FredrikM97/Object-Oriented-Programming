package filters;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import framework.AbstractFilter;

public class FlipX extends AbstractFilter {

	@Override
	public ImageIcon filter(ImageIcon image) {
		BufferedImage bi = ((BufferedImage) image.getImage());
		final int width = bi.getWidth();
		BufferedImage newBI = new BufferedImage(width, bi.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < width; x++)
			for (int y = 0; y < bi.getHeight(); y++)
				newBI.setRGB(x, y, bi.getRGB(width - x - 1, y));
		image.setImage(newBI);
		return image;
	}
}
