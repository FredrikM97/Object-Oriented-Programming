package filters;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

import framework.AbstractFilter;

public class BlackCircle extends AbstractFilter{

	@Override
	public ImageIcon filter(ImageIcon image) {
		Graphics g = image.getImage().getGraphics();
		g.setColor(Color.BLACK);
		g.fillOval(0, (image.getIconHeight()-image.getIconWidth())/2, image.getIconWidth(), image.getIconWidth());
		return image;
	}

}
