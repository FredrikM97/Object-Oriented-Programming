import java.util.Arrays;

/**
 *
 *Fix post and preconditions and CONTRACTS
 */



public class MessageStack {
	private Message[] stack;
	private int topOfStack;
	/**
	 * 
	 * @param x
	 * @precondition x must be bigger than 0
	 * @postcondition topOfStack is -1 and stack has a size that isn't null
	 *  
	 */
	public MessageStack(int x) {
		topOfStack = 0;
		stack = new Message[x];
	}
	/**
	 * 
	 * @param x
	 * @precondition array can't be empty
	 * @postcondition stack cant be null and topOfStack must be -1 or bigger than 0
	 */
	public MessageStack(Message[] array) {
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
	/**
	 * 
	 * @param n
	 * @preconditions n >= 0 and n = data.size()
	 * @postconditions 
	 */
	public MessageStack push(int n, Message[] data) {
		
		
		if (topOfStack <= (this.size() + n)) {
			this.stack = Arrays.copyOf(stack, topOfStack + n);
		}
		for (int i = 0; i < n; i++) {
			stack[topOfStack] = data[i];
			topOfStack++;
		}
		;
		return new MessageStack(this.stack);
	}
	/**
	 * 
	 * @param n
	 * @precondition n > 0
	 * @postcondition topOfStack - n
	 */
	public MessageStack pop(int n) {
		assert(n > 0);
		if (this.isEmpty()) return null;

		Message[] items = new Message[n];
		for (int i = 0; i < n && topOfStack > 0; i++) {
			items[i] = this.stack[--topOfStack];
		}
		return new MessageStack(items);
	}

	@Override
	public String toString() {		
		return Arrays.toString(Arrays.copyOf(this.stack, topOfStack));
	}
}
