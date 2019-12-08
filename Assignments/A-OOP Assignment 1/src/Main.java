import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {
	public static void main(String[] args) {
		//D2
		
	}

	private static void D2() { // Exercise 2
		double[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 9, 1, 3 } };
		double[][] matrix2 = { { 1, 2, 3 }, { 4, 5, 6 }, { 9, 1, 3 } };
		Matrix another = new Matrix(matrix);
		System.out.print(another.add(new Matrix(matrix2)).toString());
	}

	private static void D4() { // Exercise 4
		int[] stack = { 1, 2, 3, 4, 5, 6, 7 };
		int[] stack2 = { 10, 11, 12, 13, 14, 15, 16 };
		Stack another = new Stack(stack);
		System.out.println(another.push(7, stack2));
		System.out.println(another.pop(2));
	}

	private static void D5() {// Exercise 5
		MessageQueue m = new MessageQueue(4);
		for (int i = 0; i < 4; i++) {
			m.add(new Message(""+i));
		}
		System.out.print(m.toString());
	}

	private static void D8() { // Exercise 8
		Message[] matrix = { new Message("6"), new Message("7"), new Message("8"), new Message("9"),
				new Message("10") };
		MessageQueue m = new MessageQueue(4);

		m.multiAdd(matrix);
		m.print();
		m.multiRemove(2);
		m.print();
	}

	private static void D12() {// Exercise 12
		String[] string = new String[] { "a", "b", "asd", "data", "gurka", "m", "s" };
		filterTest a = new filterTest();
		System.out.print(filterTest.filter(string, a)[1]);
	}

	public static void D6() { // Exercise 6
		Message[] matrix = { new Message("6"), new Message("7"), new Message("8"), new Message("9"),
				new Message("10") };
		Message[] matrix2 = { new Message("hejsan") };
		MessageStack stack = new MessageStack(matrix);
		stack.push(1, matrix2);
		System.out.println(stack.toString());
		System.out.println(stack.size());

	}

	public static void D13() {// Exercise 13

		String[] colors = new String[] { "RED", "GREEN", "BLUE" };
		JFrame frame = new JFrame();
		circleLabel circle = new circleLabel(50);
		JLabel label = new JLabel(circle);

		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < colors.length; i++) {
					if (e.getActionCommand().equals(colors[i])) {
						switch (colors[i]) {
						case "RED":
							circle.setColor(Color.RED);
							break;
						case "GREEN":
							circle.setColor(Color.GREEN);
							break;
						case "BLUE":
							circle.setColor(Color.BLUE);
							break;

						default:
							break;
						}
					}
				}
				label.repaint();
			}
		};

		frame.setLayout(new FlowLayout());
		frame.add(label);

		JButton btn[] = new JButton[3];
		for (int i = 0; i < 3; i++) {
			btn[i] = new JButton(colors[i]);
			btn[i].addActionListener(listener);
			frame.add(btn[i]);
		}

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}
}
