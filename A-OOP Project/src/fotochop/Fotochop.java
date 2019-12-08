package fotochop;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import framework.AbstractWindow;
import framework.Filter;

/**
 * This class is an image editor made for OOP-VT18. It can find and apply any
 * filter placed in the filters package
 * 
 * @author Jesper Holmblad
 * @author Fredrik Mårtensson
 */
public class Fotochop extends AbstractWindow {

	private static final long serialVersionUID = 1L;
	private DefaultListModel<Filter> activeFilters;
	private Stack<DefaultListModel<Filter>> state = new Stack<>();
	private JPanel filterGUI;
	private ImageIcon original = null;
	private JList<Filter> filterList;

	/**
	 * Creates a Fotochop window
	 */
	public Fotochop() {
		this.setTitle("Fotochop");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.AbstractWindow#getCenterComponent()
	 */
	@Override
	public JComponent getCenterComponent() {
		image = new ImageIcon();

		// Make filter/GUI panel
		JButton remove = new JButton("Remove");
		BorderLayout filterLayout = new BorderLayout();
		filterGUI = new JPanel(filterLayout);
		filterGUI.add(remove, BorderLayout.NORTH);
		filterGUI.add(new JPanel()); // dummy panel
		activeFilters = new DefaultListModel<>();
		activeFilters.addListDataListener(filterListListener());
		filterList = new JList<>(activeFilters);
		filterList.setDragEnabled(true);
		filterList.setLayoutOrientation(JList.VERTICAL);
		filterList.addListSelectionListener(event -> showFilterGUI(filterList.getSelectedIndex()));
		remove.addActionListener(event -> {
			if (filterList.getSelectedIndex() >= 0) {
				activeFilters.remove(filterList.getSelectedIndex());
				showFilterGUI(filterList.getSelectedIndex());
			}
		});
		JSplitPane filterPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, false, new JScrollPane(filterList),
				new JScrollPane(filterGUI));
		filterPanel.setResizeWeight(0.3);
		filterPanel.setDividerSize(5);

		// Make image/filter panel
		JSplitPane centerPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, false, new JScrollPane(new JLabel(image)),
				filterPanel);
		centerPanel.setResizeWeight(1.0);
		centerPanel.setDividerSize(5);

		return centerPanel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.AbstractWindow#getFilterItems()
	 */
	@Override
	protected JMenuItem[] getFilterItems() {
		final Filter[] filters = getFilters();
		JMenuItem[] items = new JMenuItem[filters.length];
		for (int i = 0; i < filters.length; i++) {
			final int fi = i;
			items[fi] = new JMenuItem(filters[fi].toString());
			items[fi].addActionListener(event -> {
				try {
					activeFilters.addElement(filters[fi].getClass().newInstance());
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			});
		}
		return items;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.AbstractWindow#undoAction()
	 */
	@Override
	protected void undoAction() {
		ListDataListener[] listeners = activeFilters.getListDataListeners();
		Arrays.stream(listeners).forEach(listener -> activeFilters.removeListDataListener(listener));
		if (state.isEmpty()) {
			activeFilters.removeAllElements();
			Arrays.stream(listeners).forEach(listener -> activeFilters.addListDataListener(listener));
			runFilters();
		} else if (activeFilters.getSize() > 0) {
			Object[] array = state.pop().toArray();
			if (array.length == activeFilters.getSize()) {
				Arrays.stream(listeners).forEach(listener -> activeFilters.addListDataListener(listener));
				undoAction();
			} else {
				activeFilters.removeAllElements();
				Arrays.stream(array).forEach(item -> activeFilters.addElement((Filter) item));
				Arrays.stream(listeners).forEach(listener -> activeFilters.addListDataListener(listener));
				ListChangeAction();
			}
		}
		filterList.updateUI();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.AbstractWindow#help()
	 */
	@Override
	protected void help() {
		Help.help();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.AbstractWindow#about()
	 */
	@Override
	protected void about() {
		Help.about();
	}

	/**
	 * This method utilize URLClassLoader to load all classes in the package
	 * "filters". All classes are assumed to implement the filter interface
	 * 
	 * @return An array containing filter prototypes
	 * 
	 * @pre Only filters inside of filter package
	 * @post list != null
	 */
	private Filter[] getFilters() {
		try {
			String absPath = new File("").getAbsolutePath();
			String[] filters = new File(absPath + "/bin/filters").list((dir, name) -> {
				return name.contains(".class");
			});
			ArrayList<Filter> list = new ArrayList<>(filters.length);
			URLClassLoader loader = new URLClassLoader(new URL[] { new URL("file://" + absPath + "/bin/") });
			for (String filter : filters) {
				String name = filter.substring(0, filter.length() - ".class".length());
				Class<?> clazz = loader.loadClass("filters." + name);
				if (Filter.class.isAssignableFrom(clazz))
					list.add((Filter) clazz.newInstance());
			}
			loader.close();
			return list.toArray(new Filter[list.size()]);
		} catch (ClassNotFoundException | IOException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This methods performs all filtering actions in the DefaultListModel on a copy
	 * of the original ImageIcon
	 * 
	 * @pre image.getImage() != null
	 * @post image != null
	 * 
	 */
	private void runFilters() {
		if (image.getImage() != null)
			if (original != null) {
				image.setImage(deepCopy(original).getImage());
				for (int i = 0; i < activeFilters.getSize(); i++)
					image = activeFilters.get(i).filter(image);
				this.repaint();
			} else {
				original = deepCopy(image);
				runFilters();
			}
	}

	/**
	 * Provides a deep copy of the IconImage
	 * 
	 * @param image
	 *            the ImageIcon to be copied
	 * 
	 * @return copy of the ImageIcon
	 * 
	 * @pre image != null
	 * @post new ImageIcon != null
	 */
	private ImageIcon deepCopy(ImageIcon image) {
		BufferedImage bi = ((BufferedImage) image.getImage());
		ColorModel cm = bi.getColorModel();
		return new ImageIcon(new BufferedImage(cm, bi.copyData(null), cm.isAlphaPremultiplied(), null));
	}

	/**
	 * Shows the GUI of the filter at the index in the DefaltListModel
	 * 
	 * @param index
	 *            in the DefaultListModel of the filter GUI to be displayed
	 * 
	 * @pre index >= 0
	 */
	private void showFilterGUI(int index) {
		filterGUI.remove(1);
		if (index >= 0 && index < activeFilters.size()) {
			JComponent panelGUI = activeFilters.getElementAt(index).getGUI();
			if (panelGUI != null) {
				filterGUI.add(panelGUI, BorderLayout.CENTER);
				panelGUI.addMouseListener(new MouseListener() {
					public void mouseReleased(MouseEvent e) {
						runFilters();
					}

					public void mousePressed(MouseEvent e) {
					}

					public void mouseExited(MouseEvent e) {
					}

					public void mouseEntered(MouseEvent e) {
					}

					public void mouseClicked(MouseEvent e) {
					}
				});
			} else
				filterGUI.add(new JPanel());
		} else
			filterGUI.add(new JPanel());
		filterGUI.revalidate();
	}

	/*
	 * @return Listener intended for fotochops DefaultListModel
	 */
	private ListDataListener filterListListener() {
		return new ListDataListener() {
			public void intervalRemoved(ListDataEvent e) {
				ListChangeAction();
			}

			public void intervalAdded(ListDataEvent e) {
				ListChangeAction();
			}

			public void contentsChanged(ListDataEvent e) {
				ListChangeAction();
			}
		};
	}

	/*
	 * The action to be performed whenever DefaultListModel is altered
	 */
	private void ListChangeAction() {
		DefaultListModel<Filter> backup = new DefaultListModel<>();
		backup.ensureCapacity(activeFilters.size());
		Arrays.stream(activeFilters.toArray()).forEach(item -> backup.addElement((Filter) item));
		backup.trimToSize();
		state.push(backup);
		runFilters();
	}
}
