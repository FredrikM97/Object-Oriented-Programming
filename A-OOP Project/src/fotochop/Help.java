package fotochop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * A static class with methods that 
 * @author Jesper Holmblad
 * @author Fredrik Mårtensson
 */
public class Help {
	static private JFrame helpFrame = null;
	static private JFrame aboutFrame = null;

	/**
	 * Opens a help window
	 */
	public static void help() {
		if (helpFrame == null) {
			helpFrame = new JFrame("Help");
			String[] s = new String[] { "New", "Save", "Load", "Filter", "Undo" };
			String[] is = new String[] { "Welcome! Select a category if you need help!",
					"The new button enables the user to create a new image for the project. To access it, press File->new. The program will ask for dimensions on how big the image should be. There after press OK and the program will create a new image.",
					"The save button enables the user to save the image that has been created. To access it, press File->save. The program will ask for a directory and a file name.",
					"The load button enables the user to load an image that the user has downloaded or created. To access it, press File->load. The program will ask for an image file that the user can choose.",
					"The filter button enables the user to add a filter for the chosen image. From Filter->Filter a filter can be accessed and added to the image. The chosen filter will also appear in the right list with all other chosen images. If the filter contain a setting it will be availible from the setting menu by pressing the filter name.",
					"The undo button enables the user to undo a recent change from the image From Filter->Undo this feature will be available" };
			DefaultMutableTreeNode top = new DefaultMutableTreeNode("Fotochop");
			JPanel panel = new JPanel(new BorderLayout());
			JTextArea label = new JTextArea();
			panel.add(label, BorderLayout.NORTH);
			panel.setBackground(Color.WHITE);

			label.setLineWrap(true);
			label.setWrapStyleWord(true);
			label.setMargin(new Insets(20, 20, 20, 20));
			label.setFont(label.getFont().deriveFont(18f));
			label.setPreferredSize(new Dimension(500, 350));
			label.setEditable(false);

			for (int i = 0; i < s.length; i++) {
				DefaultMutableTreeNode t = new DefaultMutableTreeNode(s[i]);
				top.add(t);
			}

			JTree Tree = new JTree(top);
			Tree.setPreferredSize(new Dimension(150, 200));
			label.setText(is[0]);
			Tree.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					if (Tree.getLeadSelectionRow() != -1)
						label.setText(is[Tree.getLeadSelectionRow()]);
				}
			});

			JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, false, new JScrollPane(Tree),
					new JScrollPane(panel));
			split.setDividerSize(5);
			helpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			helpFrame.add(split);
			helpFrame.pack();
			helpFrame.setVisible(true);
		} else
			helpFrame.setVisible(true);
	}

	/**
	 * Opens an about window
	 */
	public static void about() {
		if (aboutFrame == null) {
			aboutFrame = new JFrame("About");
			JTextArea label = new JTextArea();
			label.setText("PhotoChop!\n\nThis program is created by Jesper and Fredrik\nLast Edited: 2018");
			label.setFont(label.getFont().deriveFont(16f));
			label.setWrapStyleWord(true);
			label.setEditable(false);
			label.setMargin(new Insets(20, 20, 20, 20));
			aboutFrame.add(label);
			aboutFrame.pack();
			aboutFrame.setVisible(true);
		} else
			aboutFrame.setVisible(true);
	}
}
