public class MessageQueue {
	private Message[] elements;
	private int count;
	private int head;
	private int tail;

	/**
	 * Constructs an empty message queue.
	 * 
	 * @param capacity
	 *            the maximum capacity of the queue
	 * @precondition capacity > 0
	 */
	public MessageQueue(int capacity) {
		assert capacity > 0;
		if (capacity <= 0)
			throw new IllegalArgumentException("Capacity is not greater than zero");
		elements = new Message[capacity];
		count = 0;
		head = 0;
		tail = 0;
	}

	/**
	 * Remove message at head.
	 * 
	 * @return the message that has been removed from the queue
	 * @precondition size() > 0
	 */
	public Message remove() {
		assert size() > 0;
		Message r = elements[head];
		head = (head + 1) % elements.length;
		count--;
		return r;
	}

	/**
	 * Append a message at tail.
	 * 
	 * @param aMessage
	 *            the message to be appended
	 * @precondition !isFull();
	 */
	public void add(Message aMessage) {
		assert !isFull();
		if (isFull())
			throw new RuntimeException("Queue is full");
		elements[tail] = aMessage;
		tail = (tail + 1) % elements.length;
		count++;
	}

	/**
	 * Get the total number of messages in the queue.
	 * 
	 * @return the total number of messages in the queue
	 */
	public int size() {
		return count;
	}

	/**
	 * Check if the queue is full
	 * 
	 * @return if full: True, else: false
	 */
	public boolean isFull() {
		return count >= elements.length;
	}
}