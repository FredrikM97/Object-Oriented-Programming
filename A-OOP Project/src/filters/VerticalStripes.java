package filters;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

import framework.AbstractFilter;

public class VerticalStripes extends AbstractFilter {

	@Override
	public ImageIcon filter(ImageIcon image) {
		Graphics g = image.getImage().getGraphics();
		g.setColor(Color.BLACK);
		final int Width = (int) (image.getIconWidth() / 21);
		final int Height = image.getIconHeight();
		for (int x = 1; x < 21; x = x + 2)
			g.fillRect(x * Width, 0, Width, Height);
		return image;
	}
}
