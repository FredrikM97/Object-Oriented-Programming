import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 * An icon that has the shape of the planet Mars.
 */
public class MarsIcon implements Icon {

	/**
	 * Constructs a Mars icon of a given size.
	 * 
	 * @param aSize
	 *            the size of the icon
	 */
	private Color color;
	private int size;

	public MarsIcon(int aSize) {
		size = aSize;
		color = Color.RED;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getIconWidth() {
		return size;
	}

	public int getIconHeight() {
		return size;
	}

	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		Ellipse2D.Double planet = new Ellipse2D.Double(x, y, size, size);
		g2.setColor(color);
		g2.fill(planet);
	}
}

// import java.awt.Button;
// import java.awt.Canvas;
// import java.awt.Color;
// import java.awt.Font;
// import java.awt.FontMetrics;
// import java.awt.Graphics;
// import java.awt.Image;
// import java.awt.Rectangle;
// import java.awt.Shape;
// import java.awt.image.ImageObserver;
// import java.text.AttributedCharacterIterator;
//
// import javax.swing.JFrame;
//
// public class RGBButton {
// JFrame window;
// public RGBButton() {
// window = new JFrame("RGB Button");
// window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// Graphics g = new Graphics;
// g.drawOval(50, 50, 50, 50);
// g.setColor(new Color(255, 0, 0));
// Canvas cancan = new Canvas();
// window.add(cancan);
// cancan.paint(g);
// Button red = new Button("Red");
// red.addActionListener(e -> {});
// }
//
// }
