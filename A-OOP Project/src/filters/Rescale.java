package filters;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JSlider;

import framework.AbstractFilter;

public class Rescale extends AbstractFilter {
	private int scale = 100;

	@Override
	public ImageIcon filter(ImageIcon image) {
		if (scale != 0) {
			BufferedImage b = (BufferedImage) image.getImage();
			final int width = (b.getWidth() * 100) / scale;
			final int height = (b.getHeight() * 100) / scale;
			BufferedImage img = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
			Graphics2D g2 = img.createGraphics();
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2.drawImage(b, 0, 0, width, height, null);
			g2.dispose();
			image.setImage(img);
		}
		return image;
	}

	@Override
	public JComponent getGUI() {
		JSlider slider = new JSlider(0, 150, 200-scale);
		slider.addChangeListener(event -> scale = 200 - slider.getValue());
		return slider;
	}
}
