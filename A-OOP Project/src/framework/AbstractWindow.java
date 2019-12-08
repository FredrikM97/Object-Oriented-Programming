package framework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSpinner;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * An abstract class that functions as a framework for applications with the
 * minimum requirements of: <br>
 * <br>
 * 1. A menu in the top with the following items: <br>
 * <span style="margin-left:2em">File: shall present the following options:
 * </span><br>
 * <span style="margin-left:4em">Open: to load an image from a file. </span><br>
 * <span style="margin-left:4em">Save: to save into a file the current image.
 * </span><br>
 * <span style="margin-left:4em">Exit: to quit the application. </span><br>
 * <span style="margin-left:2em">Filters: shall present the following options:
 * </span><br>
 * <span style="margin-left:4em">A list of possible filters. Please refer to
 * section Filter for more information. </span><br>
 * <span style="margin-left:4em">Undo/Restore: to restore the image to its
 * previous state. </span><br>
 * <span style="margin-left:2em">Help: shall present the following options:
 * </span><br>
 * <span style="margin-left:4em">Help: a simple help about how to use the
 * system. </span><br>
 * <span style="margin-left:4em">About: a simple information box about the
 * authors and associated responsibilities. </span><br>
 * 2. An area in which the image will be displayed.
 * 
 * @author Jesper Holmblad
 * @author Fredrik Mårtensson
 */
abstract public class AbstractWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	protected ImageIcon image;
	static private JDialog dialog;

	/**
	 * Creates a window fulfilling the requirements of this framework
	 */
	public AbstractWindow() {
		JMenuBar menuBar;
		JMenuItem newImage = new JMenuItem("New");
		newImage.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		newImage.addActionListener(event -> newPhoto());
		JMenuItem open = new JMenuItem("Open");
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		open.addActionListener(event -> loadPhoto());
		JMenuItem save = new JMenuItem("Save");
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		save.addActionListener(event -> savePhoto());
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(event -> System.exit(0));
		JMenu filters = new JMenu("Filters");
		Arrays.stream(getFilterItems()).forEach(filter -> filters.add(filter));
		JMenuItem refresh = new JMenuItem("Refresh Filter");
		refresh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
		refresh.addActionListener(event -> {
			filters.removeAll();
			Arrays.stream(getFilterItems()).forEach(filter -> filters.add(filter));
		});

		JMenuItem undo = new JMenuItem("Undo");
		undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		undo.addActionListener(event -> undoAction());
		JMenuItem help = new JMenuItem("Help");
		help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
		help.addActionListener(event -> help());
		JMenuItem about = new JMenuItem("About");
		about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		about.addActionListener(event -> about());

		// Assemble menu
		ArrayList<JMenuItem[]> menuItems = new ArrayList<>();
		menuItems.add(new JMenuItem[] { newImage, open, save, exit });
		menuItems.add(new JMenuItem[] { filters, refresh, undo });
		menuItems.add(new JMenuItem[] { help, about });
		JMenu[] menus = { new JMenu("File"), new JMenu("Filters"), new JMenu("Help") };
		menuBar = new JMenuBar();
		IntStream.range(0, menus.length).forEach(i -> {
			Arrays.stream(menuItems.get(i)).forEach(item -> {
				menus[i].add(item);
			});
			menuBar.add(menus[i]);
		});
		JComponent centerObject = getCenterComponent();
		this.add(centerObject, BorderLayout.CENTER);
		this.setJMenuBar(menuBar);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		this.setPreferredSize(new Dimension(gd.getDisplayMode().getWidth() / 2, gd.getDisplayMode().getHeight() / 2));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	/**
	 * Undoes previous action based on programmers implementation
	 */
	abstract protected void undoAction();

	/**
	 * Retrieves a list of items to be appended to the filter sub-menu
	 * 
	 * @return array containing the menu items for all filters
	 */
	abstract protected JMenuItem[] getFilterItems();

	/**
	 * Gets the main component of the GUI to be added to the center of the frame
	 * 
	 * @return the swing component to be displayed in the frame
	 */
	abstract protected JComponent getCenterComponent();

	/**
	 * Opens the help documentation
	 */
	abstract protected void help();

	/**
	 * Opens the about feature
	 */
	abstract protected void about();

	private void savePhoto() {
		FileDialog chooser = new FileDialog(this, "Save image", FileDialog.SAVE);
		chooser.setVisible(true);
		String filename = chooser.getFile();
		if (filename != null)
			try {
				BufferedImage bImage = (BufferedImage) image.getImage();
				File selectedFile = new File(chooser.getDirectory() + filename + ".png");
				ImageIO.write(bImage, "png", selectedFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	/**
	 * Opens a FileDialog and attempts to open the filename chosen
	 * 
	 * @post image != null
	 */
	private void loadPhoto() {
		FileDialog chooser = new FileDialog(this, "Load image", FileDialog.LOAD);
		chooser.setVisible(true);
		String filename = chooser.getFile();
		if (filename != null)
			try {
				image.setImage(ImageIO.read(new File(chooser.getDirectory() + filename)));
				this.repaint();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	/**
	 * Opens a dialog to get height and width. The dialog has an OK button that
	 * passes the values to the blankImage method and a cancel button that hides the
	 * dialog.
	 * 
	 */
	private void newPhoto() {
		if (dialog == null) {
			dialog = new JDialog(this, "New Image");
			GridLayout layout = new GridLayout(3, 2);
			dialog.setLayout(layout);
			SpinnerNumberModel heightModel = new SpinnerNumberModel(500, 0, 9001, 1);
			SpinnerNumberModel widthModel = new SpinnerNumberModel(500, 0, 9001, 1);
			JButton ok = new JButton("Ok");
			ok.addActionListener(event -> {
				blankImage(widthModel.getNumber().intValue(), heightModel.getNumber().intValue());
				dialog.setVisible(false);
			});
			JButton cancel = new JButton("Cancel");
			cancel.addActionListener(event -> dialog.setVisible(false));
			dialog.add(new JLabel("Height"), layout);
			dialog.add(new JSpinner(heightModel), layout);
			dialog.add(new JLabel("Width"), layout);
			dialog.add(new JSpinner(widthModel), layout);
			dialog.add(ok, layout);
			dialog.add(cancel, layout);
			dialog.pack();
			dialog.setBounds(0, 0, dialog.getWidth() + 20, dialog.getHeight());
			dialog.setVisible(true);
		} else
			dialog.setVisible(true);
	}

	/**
	 * Creates a blank image with the parameters width and height as dimensions
	 * 
	 * @param width
	 *            the width of the image
	 * @param height
	 *            the height of the image
	 * 
	 * @pre width > 0 && height > 0
	 * @post image != null
	 */
	private void blankImage(int width, int height) {
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		image.setImage(bi);
		this.repaint();
	}
}
