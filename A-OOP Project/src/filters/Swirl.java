package filters;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JSlider;

import framework.AbstractFilter;

public class Swirl extends AbstractFilter {
	private double k = 0;

	@Override
	public ImageIcon filter(ImageIcon image) {
		if (k == 0)
			return image;
		BufferedImage bi = (BufferedImage) image.getImage();
		BufferedImage newBI = new BufferedImage(bi.getWidth(), bi.getHeight(), bi.getType());
		final double x0 = 0.5 * (bi.getWidth() - 1);
		final double y0 = 0.5 * (bi.getHeight() - 1);
		for (int x = 0; x < bi.getWidth(); x++) {
			final double dx = x - x0;
			for (int y = 0; y < bi.getHeight(); y++) {
				final double dy = y - y0;
				final double r = Math.hypot(dx, dy);
				final double angle = k * r;
				final int tx = (int) (dx * Math.cos(angle) - dy * Math.sin(angle) + x0);
				final int ty = (int) (dx * Math.sin(angle) + dy * Math.cos(angle) + y0);

				if (tx >= 0 && tx < bi.getWidth() && ty >= 0 && ty < bi.getHeight())
					newBI.setRGB(x, y, bi.getRGB(tx, ty));
				else
					newBI.setRGB(x, y, 0);
			}
		}
		image.setImage(newBI);
		return image;
	}

	@Override
	public JComponent getGUI() {
		JSlider slider = new JSlider(0, 100, (int) (k * 5000));
		slider.addChangeListener(event -> k = slider.getValue() / 5000.0);
		return slider;
	}
}
