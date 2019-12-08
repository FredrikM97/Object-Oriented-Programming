import java.util.stream.IntStream;

public class Task8Queue extends MessageQueue {

	/**
	 * Constructs an empty message queue.
	 * 
	 * @param capacity
	 *            the maximum capacity of the queue
	 * @precondition capacity > 0
	 */
	public Task8Queue(int capacity) {
		super(capacity);
	}

	/**
	 * Append a message at tail.
	 * 
	 * @param aMessage
	 *            the message to be appended
	 * @precondition !isFull(); for each element
	 */
	public void multAdd(Message... aMessage) {
		for (int i = 0; i < aMessage.length; i++)
			try {
				super.add(aMessage[i]);
			} catch (Exception e) {
				throw e;
			}
	}

	/**
	 * 
	 * Remove n messages at head.
	 * 
	 * @return the messages that has been removed from the queue
	 * @precondition size() >= n
	 */
	public Message[] multRemove(int n) {
		if (super.size() < n)
			throw new RuntimeException("Not enough elements in queue");
		Message[] result =  new Message[n];
		IntStream.range(0, n).forEach(i -> result[i] = super.remove());
		return result;
	}

}