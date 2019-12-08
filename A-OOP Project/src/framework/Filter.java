package framework;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

/**
 * This interface denotes that the implementing class can filter images
 * 
 * @author Jesper Holmblad 
 * @author Fredrik Mårtensson
 */
public interface Filter {
	/**
	 * Performs a filtering action on an image
	 * 
	 * @param image
	 *            the image to be filtered
	 * @return the filtered image
	 */
	public ImageIcon filter(ImageIcon image);

	/**
	 * gets the GUI belonging to this filter. This can return null to create a blank GUI
	 * 
	 * @return the GUI JComponent of this filter
	 */
	public JComponent getGUI();
}
