import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {
	public static void main(String[] args) {
		task13();
	}
	public static void task2() {
		double[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 9, 1, 3 } };
		double[][] matrix2 = { { 1, 2, 3 }, { 4, 5, 6 }, { 9, 1, 3 } };
		Matrix another = new Matrix(matrix);
		System.out.print(another.add(new Matrix(matrix2)).toString());
	}
	
	public static void task4() { 
		int[] stack = { 1, 2, 3, 4, 5, 6, 7 };
		int[] stack2 = { 10, 11, 12, 13, 14, 15, 16 };
		Stack another = new Stack(stack);
		System.out.println(another.push(7, stack2));
		System.out.println(another.pop(2));
	}
	
	public static void task5() {
		Task5Queue que = new Task5Queue(1);
		que.add(new Message("A"));
		que.add(new Message("B"));
		System.out.println(que.remove());
	}

	public static void task11() {
		String[] namn = { "Fredrik", "Jesper", "Bob", "Von" };
		ArrayList<String> list = new ArrayList<String>(Arrays.asList(namn));
		System.out.println(maximum(list, (o1, o2) -> {
			return o1.length() - o2.length();
		}));
	}
	
	public static void task12() {// Exercise 12
		String[] string = new String[] { "a", "b", "asd", "data", "gurka", "m", "s" };
		filterTest a = new filterTest();
		System.out.print(filterTest.filter(string, a)[1]);
	}

	public static void task13() {
		JFrame window = new JFrame();
		MarsIcon rund = new MarsIcon(40);
		JLabel boll = new JLabel(rund);
		JButton[] buttons = { new JButton("RED"), new JButton("GREEN"), new JButton("BLUE") };
		Color[] colors = { Color.RED, Color.GREEN, Color.BLUE };
		IntStream.range(0, buttons.length).forEach(i -> {
			buttons[i].addActionListener(event -> {
				rund.setColor(colors[i]);
				boll.repaint();
			});
		});
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(new FlowLayout());
		window.add(boll);
		Arrays.stream(buttons).forEach(butt -> window.add(butt));
		window.pack();
		window.setVisible(true);
	}

	public static String maximum(ArrayList<String> a, Comparator<String> c) {
		return a.stream().max(c).get();
	}
}
