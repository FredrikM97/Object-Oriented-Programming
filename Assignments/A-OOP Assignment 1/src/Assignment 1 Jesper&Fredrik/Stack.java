import java.util.Arrays;

public class Stack {
	private int[] stack;
	private int topOfStack;

	public Stack(int x) {
		topOfStack = 0;
		stack = new int[x];
	}

	public Stack(int[] array) {
		stack = array.clone();
		topOfStack = stack.length;
	}

	public boolean isEmpty() {
		return topOfStack == 0;
	}
	public int empty() {
		return topOfStack = 0;
	}
	public int size() {
		return topOfStack;

	}
	public Stack push(int n, int[] data) {
		if (topOfStack <= (this.size() + n)) {
			this.stack = Arrays.copyOf(stack, topOfStack + n);
		}
		for (int i = 0; i < n; i++) {
			stack[topOfStack++] = data[i];
		}
		return new Stack(this.stack);
	}

	public Stack pop(int n) {
		if (this.isEmpty())
			return null;

		int[] items = new int[n];
		for (int i = 0; i < n && topOfStack > 0; i++) {
			items[i] = this.stack[--topOfStack];
		}
		return new Stack(items);
	}

	@Override
	public String toString() {		
		return Arrays.toString(Arrays.copyOf(this.stack, topOfStack)); 
	}
}
