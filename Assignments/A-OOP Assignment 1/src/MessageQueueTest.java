import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class MessageQueueTest {
	Message[] matrix = new Message[]{new Message("6"), new Message("7"), new Message("8"), new Message("9"), new Message("10")};
	
	@Test
	void testadd() {
		MessageQueue m = new MessageQueue(4);
		assertEquals("[]", m.toString());
		m.add(new Message("1"));
		m.add(new Message("2"));
		m.add(new Message("3"));
		m.add(new Message("4"));
		assertEquals("[1, 2, 3, 4]", m.toString());
	}
	@Test
	void testmultiAdd() {
		MessageQueue m = new MessageQueue(4);
		assertEquals("[]", m.toString());
		m.multiAdd(matrix);
		assertEquals("[6, 7, 8, 9, 10]", m.toString());
	}
	@Test
	void testremove() {
		MessageQueue m = new MessageQueue(4);
		assertEquals("[]", m.toString());
		m.multiAdd(matrix);
		m.remove();
		m.remove();
		assertEquals("[6, 7, 8]", m.toString());
	}
	@Test 
	void testmultiRemove() {
		MessageQueue m = new MessageQueue(4);
		assertEquals("[]", m.toString());
		m.multiAdd(matrix);
		assertEquals("[6, 7, 8, 9, 10]", m.toString());
		m.multiRemove(4);
		assertEquals("[6]", m.toString());	
	}
}
