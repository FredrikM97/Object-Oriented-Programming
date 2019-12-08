package filters;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;

import javax.swing.ImageIcon;

import framework.AbstractFilter;

public class RedFilter extends AbstractFilter {

	@Override
	public ImageIcon filter(ImageIcon image) {
		BufferedImage bi = (BufferedImage) image.getImage();
		BufferedImage newBI = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_ARGB);
		ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);  
		ColorConvertOp op = new ColorConvertOp(cs, null);  
		image.setImage(op.filter(bi, null));
		for (int x = 0; x < bi.getWidth(); x++)
			for (int y = 0; y < bi.getHeight(); y++) {
				final int rgb = bi.getRGB(x, y);
				newBI.setRGB(x, y, rgb&0xFFFF0000);
			}
		image.setImage(newBI);
		return image;
	}

}
