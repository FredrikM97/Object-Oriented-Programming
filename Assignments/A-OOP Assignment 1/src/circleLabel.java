import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.Icon;

public class circleLabel implements Icon{
	private Color color;
	int height;
	int width;
	public circleLabel(int i) {
		height = width = i;
		color = Color.RED;
	}

	@Override
	public int getIconHeight() {
		return height;
	}
	@Override
	public int getIconWidth() {
		// TODO Auto-generated method stub
		return width;
	}
	
	public void setColor(Color color) {
        this.color=color;
    }
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
	    Ellipse2D.Double circle = new Ellipse2D.Double(x, y, width, height);
	    g2.setColor(color);
	    g2.fill(circle);
	}    
}
