import java.util.Stack;
import java.util.stream.IntStream;

public class MyStack extends Stack<Message> {
	/**
	 *  @invariant size >= 0
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param n
	 *            Number of items to pop
	 * @return Array with popped items
	 * @precondition stack contains n or more elements
	 * @postcondition output is not null
	 **/
	public Message[] pop(int n) {
		assert this.size() > 0;
		Message[] output = new Message[n];
		IntStream.range(0, n).forEach(i -> output[n-i-1] = super.pop());
		assert (output != null);
		return output;
	}

	/**
	 * @param n
	 *            Length of item array
	 * @param item
	 *            Array containing items to be pushed on to the stack
	 * @return item
	 * @precondition n is the length of the item array
	 */
	public Message[] push(int n, Message[] item) {
		assert (n == item.length);
		IntStream.range(0, n).forEach(i -> super.push(item[i]));
		return item;
	}
}
