package filters;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;

import javax.swing.ImageIcon;

import framework.AbstractFilter;

public class Greyscale extends AbstractFilter {

	@Override
	public ImageIcon filter(ImageIcon image) {
		BufferedImage bi = (BufferedImage) image.getImage();
		ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);  
		ColorConvertOp op = new ColorConvertOp(cs, null);  
		image.setImage(op.filter(bi, null));
		return image;
	}
}