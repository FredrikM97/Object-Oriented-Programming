import java.util.Arrays;

@SuppressWarnings("hiding")
public class MessageQueue {

	/**
	 * Constructs an empty message queue. 8 @param capacity the maximum capacity
	 * of the queue @precondition capacity > 0
	 * 
	 */
	//private Message[] elements;
	private Message[] elements;
	private int head;
	private int tail;
	private int count;
	/**
	 * @invariant 0<= head & head < elements.length
	 * @param capacity capacity > 0
	 * 
	 * @precondition 
	 * **/
	public MessageQueue(int capacity) {
		assert(capacity > 0): "Capacity is too small";
		//elements = new Message[capacity];
		elements = new Message[capacity];
		count = 0;
		head = 0;
		tail = 0;
		assert(0 <= head & head < elements.length): "Error in MessageQueue";
	}

	/**
	 * Remove message at head. 21 @return the message that has been removed from the
	 * queue @precondition size() > 0
	 */

	public Message remove() {
		assert(this.size() > 0);
		Message r = elements[head];
		head = (head + 1) % elements.length;
		count--;
		return r;
	}

	/**
	 * Append a message at tail.
	 * 
	 * @param aMessage
	 * @precondition !isFull();
	 * 
	 */

	public void add(Message aMessage) {
		if(isFull()) expandList(); 
		assert(!isFull()) : "List is full and something happened in expandList()";
		elements[tail] = aMessage;
		tail = (tail + 1) % elements.length;
		count++;
	}
	
	/**
	 * Get the total number of messages in the queue. @return the total number of
	 * messages in the queue
	 */

	public int size() {
		return count;
	}

	/** Checks whether this queue is full @return true if the queue is full */

	public boolean isFull() {
		return count == elements.length;
	}
	
	/**
	 * @precondition isFill()
	 */
	public void expandList() {
		assert(isFull()): "List is not full!";
		Message[] copy = new Message[elements.length + 1];
	    System.arraycopy(elements, 0, copy, 0, elements.length);
	  
	    elements = copy;
		tail = elements.length-1;
	}

	/**
	 * Get message at head. @return the message that is at the head of the queue
	 * 
	 * @precondition size() > 0
	 */

	public Message peek() {
		assert(size() > 0):"Size is too small";
		return elements[head];
	}
	public void print(){
		for(int x = 0; x < elements.length; x++) {
			System.out.println(elements[x] + " " + head + " " + tail);
		}
		System.out.println("");
		
	}
	/*
	 * @precondition element.length > 0;
	 * 
	 */
	public void multiAdd(Message[] element) { 
		assert(element.length > 0);
		for(int a = 0; a < element.length; a++) {
			this.add(element[a]);
		}	
	}
	/*
	 * @postcondition elements.length > 0;
	 * 
	 * @precondition n > 0;
	 * 
	 */
	public void multiRemove(int n) {
		assert(n > 0): "Error";
		for(int a = 0; a < n; a++) {
			this.remove();
		}
	}
	//Funkar inte <- NOT wraparound
	public String toString() {	
		return Arrays.toString(Arrays.copyOfRange(elements, head, tail));
	}
}
