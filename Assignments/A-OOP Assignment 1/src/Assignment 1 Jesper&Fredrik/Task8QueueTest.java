import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class Task8QueueTest {
	Task8Queue testClass;
	Message aMessage;
	Message bMessage;
	Message cMessage;
	Message dMessage;

	@Before
	public void setUp() throws Exception {
		testClass = new Task8Queue(3);
	}

	@Test(expected = AssertionError.class)
	public void testMultAdd() {
		Message aMessage = new Message("A");
		Message bMessage = new Message("B");
		Message cMessage = new Message("C");
		Message dMessage = new Message("D");
		testClass.multAdd(aMessage, bMessage, cMessage, dMessage);
	}

	@Test
	public void testMultAdd2() {
		testClass.multAdd(aMessage, bMessage, cMessage);
		assertTrue(testClass.isFull());
	}

	@Test(expected = RuntimeException.class)
	public void testmultRemove() {
		testClass.add(aMessage);
		testClass.multRemove(2);
	}

	@Test
	public void testmultRemove2() {
		testClass.add(aMessage);
		testClass.add(bMessage);
		Object[] result = testClass.multRemove(2);
		assertEquals(aMessage, result[0]);
		assertEquals(bMessage, result[1]);
	}

}
