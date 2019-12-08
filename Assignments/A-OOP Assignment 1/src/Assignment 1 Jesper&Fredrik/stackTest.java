import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class stackTest {
	int[] stack = {1,2,3,4,5,6,7};
	@Test
	void testSize() {
		Stack another = new Stack(stack);
		assertEquals(7, another.size());
	}
	@Test
	void testpush() {
		Stack another = new Stack(stack);
		assertEquals("[1, 2, 3, 4, 5, 6, 7]", another.toString());
		assertEquals("[1, 2, 3, 4, 5, 6, 7, 10, 11, 12]", another.push(3, new int[] {10,11,12}).toString());
	}
	@Test
	void testpop() {
		Stack another = new Stack(stack);
		assertEquals("[1, 2, 3, 4, 5, 6, 7]", another.toString());
		assertEquals("[7, 6, 5]", another.pop(3).toString());
		assertEquals("[1, 2, 3, 4]", another.toString());
	}
	@Test
	void testEmpty() {
		Stack another = new Stack(0);
		assertEquals(true, another.isEmpty());
		assertEquals(false, another.push(2, new int[] {10,11}).isEmpty());
	}
}