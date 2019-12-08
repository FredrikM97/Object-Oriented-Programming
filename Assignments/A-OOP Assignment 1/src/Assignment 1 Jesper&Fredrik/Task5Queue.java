import java.util.Arrays;
import java.util.stream.IntStream;

public class Task5Queue {
	MessageQueue thisQueue;

	/**
	 * Constructs an empty message queue.
	 * 
	 * @param capacity
	 *            the maximum capacity of the queue
	 * @precondition capacity > 0
	 */

	public Task5Queue(int capacity) {
		assert capacity > 0;
		thisQueue = new MessageQueue(capacity);
	}

	/**
	 * Remove message at head.
	 * 
	 * @return the message that has been removed from the queue
	 * @precondition size() > 0
	 */
	public Message remove() {
		return thisQueue.remove();
	}

	/**
	 * Get the total number of messages in the queue.
	 * 
	 * @return the total number of messages in the queue
	 */
	public int size() {
		return thisQueue.size();
	}

	/**
	 * Check if the queue is full
	 * 
	 * @return if full: True, else: false
	 */
	public boolean isFull() {
		return thisQueue.isFull();
	}

	/**
	 * Append a message at tail. Extends queue if full
	 * 
	 * @param aMessage
	 *            the message to be appended
	 */
	public void add(Message aMessage) {
		if (thisQueue.isFull())
			doubleQueue();
		thisQueue.add(aMessage);
	}

	/**
	 * Doubles the size of the MessageQueue
	 */

	private void doubleQueue() {
		Message[] data = new Message[thisQueue.size()];
		IntStream.range(0, data.length).forEach(i -> {data[i] = thisQueue.remove();});
		thisQueue = new MessageQueue(data.length * 2);
		Arrays.stream(data).forEach(item -> thisQueue.add(item));
	}
}