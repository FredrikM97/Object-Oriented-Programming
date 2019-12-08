package filters;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JList;

import framework.AbstractFilter;

public class Convolve extends AbstractFilter {
	private JList<String> list;
	private ArrayList<Kernel> matrix;

	public Convolve() {
		matrix = new ArrayList<>();
		DefaultListModel<String> model = new DefaultListModel<>();
		list = new JList<>(model);
		final float f = (float) (1.0 / 9);
		model.addElement("Blur");
		matrix.add(new Kernel(3, 3, new float[] { f, f, f, f, f, f, f, f, f }));
		model.addElement("Y-Sobel");
		matrix.add(new Kernel(3, 3, new float[] { 1, 2, 1, 0, 0, 0, -1, -2, -1 }));
		model.addElement("Emboss");
		matrix.add(new Kernel(4, 3, new float[] { 1, 2, 1, 0, 0, 0, -1, -2, -1, 3 * f, 3 * f, 3 * f }));
	}

	@Override
	public ImageIcon filter(ImageIcon image) {
		BufferedImage bi = (BufferedImage) image.getImage();
		final int index = list.getSelectedIndex();
		if (index >= 0) {
			BufferedImage newBI = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_ARGB);
			newBI = (new ConvolveOp(matrix.get(index)).filter(bi, newBI));
			for (int x = 0; x < newBI.getWidth(); x++)
				for (int y = 0; y < newBI.getHeight(); y++)
					newBI.setRGB(x, y, (bi.getRGB(x, y) & 0xFF000000) | newBI.getRGB(x, y));
			image.setImage(newBI);
		}
		return image;
	}

	@Override
	public JComponent getGUI() {
		return list;
	}
}
