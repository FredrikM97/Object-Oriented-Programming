package framework;

import javax.swing.JComponent;

/**
 * A Template for filters
 * 
 * @author Jesper Holmblad
 * @author Fredrik Mårtensson
 */
public abstract class AbstractFilter implements Filter {
	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.Filter#getGUI()
	 */
	public JComponent getGUI() {
		return null;
	}

	/**
	 * Used to get the name of the filter. This defaults to the name of the class
	 * 
	 * @return the name of this filter
	 */
	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
}
