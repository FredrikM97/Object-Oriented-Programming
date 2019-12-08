package filters;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

import framework.AbstractFilter;

public class Chess extends AbstractFilter {

	@Override
	public ImageIcon filter(ImageIcon image) {
		Graphics g = image.getImage().getGraphics();
		g.setColor(Color.BLACK);
		final int tileWidth = image.getIconWidth() / 8;
		final int tileHeight = image.getIconHeight() / 8;
		for (int x = 0; x < 8; x++)
			for (int y = 0; y < 8; y++)
				if ((x + y) % 2 == 1)
					g.fillRect(x*tileWidth, y*tileHeight, tileWidth, tileHeight);

		return image;
	}

}
