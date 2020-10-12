package boundedpipe;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Gasser Ahmed
 *
 */
public class ListPipeTest {

	private Pipe<String> list_empty_3;
	private Pipe<String> list_ABC_6;

	@Before
	public void setUp() throws Exception {
		list_empty_3 = new ListPipe<>(3);
		list_ABC_6 = new ListPipe<>(6);
		list_ABC_6.append("A");
		list_ABC_6.append("B");
		list_ABC_6.append("C");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorInvalidParam() {
		ListPipe<String> list = new ListPipe<>(-1);
		fail();
	}

	@Test
	public void testEmptyToString() {
		assertEquals("[]:3", list_empty_3.toString());
	}

	@Test
	public void testABCToString() {
		assertEquals("[A, B, C]:6", list_ABC_6.toString());
	}

	@Test
	public void testABCEqualsNull() {
		Pipe<String> p = null;
		assertFalse(list_ABC_6.equals(p));
	}

	@Test
	public void testABCEqualsSelf() {
		assertTrue(list_ABC_6.equals(list_ABC_6));
	}

	@Test
	public void testABCEqualsNonPipe() {
		assertFalse(list_ABC_6.equals("[A, B, C]:6"));
	}

	@Test
	public void testABC6EqualsDifferentABC6() {
		Pipe<String> list_ABC_6_dup = new ListPipe<>(6);
		list_ABC_6_dup.append("A");
		list_ABC_6_dup.append("B");
		list_ABC_6_dup.append("C");

		assertTrue(list_ABC_6.equals(list_ABC_6_dup));
	}

	@Test
	public void testABC6EqualsABC10() {
		Pipe<String> list_ABC_10 = new ListPipe<>(10);
		list_ABC_10.append("A");
		list_ABC_10.append("B");
		list_ABC_10.append("C");

		assertFalse(list_ABC_6.equals(list_ABC_10));
	}

	@Test
	public void testABC6EqualsAB6() {
		Pipe<String> list_AB_6 = new ListPipe<>(6);
		list_AB_6.append("A");
		list_AB_6.append("B");

		assertFalse(list_ABC_6.equals(list_AB_6));
	}

	@Test
	public void testEmpty3EqualsDifferentEmpty3() {
		Pipe<String> list_empty_6_dup = new ListPipe<>(3);

		assertTrue(list_empty_3.equals(list_empty_6_dup));
	}

	@Test
	public void testEmpty3EqualsEmpty5() {
		Pipe<String> list_empty_5 = new ListPipe<>(5);

		assertFalse(list_empty_3.equals(list_empty_5));
	}

	@Test
	public void testABC6EqualsDEF6() {
		Pipe<String> list_DEF_6 = new ListPipe<>(6);
		list_DEF_6.append("D");
		list_DEF_6.append("E");
		list_DEF_6.append("F");

		assertFalse(list_ABC_6.equals(list_DEF_6));
	}

	@Test
	public void testABC6ArrayBasedEqualsABC6ListBased() {
		Pipe<String> pipe_ABC_6_array = new CircArrayPipe<>(6);
		pipe_ABC_6_array.append("A");
		pipe_ABC_6_array.append("B");
		pipe_ABC_6_array.append("C");

        assertTrue(list_ABC_6.equals(pipe_ABC_6_array));
	}

	@Test
	public void testABC6LinkedBasedEqualsABC6ListBased() {
		Pipe<String> pipe_ABC_6_linked = new LinkedPipe<>(6);
		pipe_ABC_6_linked.append("A");
		pipe_ABC_6_linked.append("B");
		pipe_ABC_6_linked.append("C");

		assertTrue(list_ABC_6.equals(pipe_ABC_6_linked));
	}

	@Test
	public void testHashCodeABC6AndAB6() {
		Pipe<String> list_AB_6 = new ListPipe<>(6);
		list_AB_6.append("A");
		list_AB_6.append("B");

		assertFalse(list_ABC_6.hashCode() == list_AB_6.hashCode());
	}

	@Test
	public void testHashCodeABC6AndDifferentABC6() {
		Pipe<String> list_ABC_6_dup = new ListPipe<>(6);
		list_ABC_6_dup.append("A");
		list_ABC_6_dup.append("B");
		list_ABC_6_dup.append("C");

		assertTrue(list_ABC_6.hashCode() == list_ABC_6_dup.hashCode());
	}

	@Test
	public void testHashCodeABC6AndABC10() {
		Pipe<String> list_ABC_10 = new ListPipe<>(10);
		list_ABC_10.append("A");
		list_ABC_10.append("B");
		list_ABC_10.append("C");

		assertFalse(list_ABC_6.hashCode() == list_ABC_10.hashCode());
	}

	@Test
	public void testHashCodeABC6ArrayBasedAndABC6ListBased() {
		Pipe<String> pipe_ABC_6_array = new CircArrayPipe<>(6);
		pipe_ABC_6_array.append("A");
		pipe_ABC_6_array.append("B");
		pipe_ABC_6_array.append("C");

		assertTrue(list_ABC_6.hashCode() == pipe_ABC_6_array.hashCode());
	}

	@Test
	public void testHashCodeABC6LinkedBasedAndABC6ListBased() {
		Pipe<String> pipe_ABC_6_linked = new LinkedPipe<>(6);
		pipe_ABC_6_linked.append("A");
		pipe_ABC_6_linked.append("B");
		pipe_ABC_6_linked.append("C");

		assertTrue(list_ABC_6.hashCode() == pipe_ABC_6_linked.hashCode());
	}

	@Test
	public void testEmptyLength() {
		assertEquals(0, list_empty_3.length());
		assertTrue(list_empty_3.isEmpty());
	}

	@Test
	public void testEmptyCapacity() {
		assertEquals(3, list_empty_3.capacity());
	}

	@Test
	public void testEmptyAppendA() {
		list_empty_3.append("A");
		assertEquals(1, list_empty_3.length());
		assertFalse(list_empty_3.isEmpty());
	}

	@Test
	public void testEmptyAppendARemoveFirst() {
		list_empty_3.append("A");
		assertEquals(1, list_empty_3.length());
		String str = list_empty_3.removeFirst();
		assertEquals(0, list_empty_3.length());
		assertEquals("A", str);
	}

	@Test
	public void testEmptyAppendAAppendBRemoveFirst() {
		list_empty_3.append("A");
		list_empty_3.append("B");
		assertEquals(2, list_empty_3.length());
		String str = list_empty_3.removeFirst();
		assertEquals(1, list_empty_3.length());
		assertEquals("A", str);
	}

	@Test(expected = IllegalStateException.class)
	public void testRemoveFirstEmptyPipe() {
		list_empty_3.removeFirst();
		fail();
	}

	@Test(expected = IllegalStateException.class)
	public void testRemoveLastEmptyPipe() {
		list_empty_3.removeLast();
		fail();
	}

	@Test
	public void testABCAppendD() {
		list_ABC_6.append("D");
		assertEquals(4, list_ABC_6.length());
	}

	@Test
	public void testABCRemoveFirst() {
		String str = list_ABC_6.removeFirst();
		assertEquals(2, list_ABC_6.length());
		assertEquals("A", str);
	}

	@Test(expected = IllegalStateException.class)
	public void testAppendIntoFullPipe() {
		list_ABC_6.append("D");
		list_ABC_6.append("E");
		list_ABC_6.append("F");
		list_ABC_6.append("G");
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAppendNullIntoPipe() {
		String str = null;
		list_ABC_6.append(str);
		fail();
	}

	@Test
	public void testABCLength() {
		assertEquals(3, list_ABC_6.length());
	}

	@Test
	public void testABCCapacity() {
		assertEquals(6, list_ABC_6.capacity());
	}

	@Test
	public void testEmptyPrependA() {
		list_empty_3.prepend("A");
		assertEquals(1, list_empty_3.length());
	}

	@Test
	public void testEmptyPrependARemoveFirst() {
		list_empty_3.prepend("A");
		assertEquals(1, list_empty_3.length());
		String str = list_empty_3.removeFirst();
		assertEquals(0, list_empty_3.length());
		assertEquals("A", str);
	}

	@Test
	public void testEmptyAppendBPrependARemoveFirst() {
		list_empty_3.append("B");
		list_empty_3.prepend("A");
		String str = list_empty_3.removeFirst();
		assertEquals(1, list_empty_3.length());
		assertEquals("A", str);
	}

	@Test
	public void testEmptyPrependARemoveLast() {
		list_empty_3.prepend("A");
		assertEquals(1, list_empty_3.length());
		String str = list_empty_3.removeLast();
		assertEquals(0, list_empty_3.length());
		assertEquals("A", str);
	}

	@Test
	public void testEmptyAppendAAppendBRemoveLast() {
		list_empty_3.append("A");
		list_empty_3.append("B");
		String str = list_empty_3.removeLast();
		assertEquals(1, list_empty_3.length());
		assertEquals("B", str);
	}

	@Test
	public void testEmptyPrependAPrependBRemoveLast() {
		list_empty_3.prepend("A");
		list_empty_3.prepend("B");
		assertEquals(2, list_empty_3.length());
		String str = list_empty_3.removeLast();
		assertEquals(1, list_empty_3.length());
		assertEquals("A", str);
	}

	@Test
	public void testABCPrependZ() {
		list_ABC_6.prepend("F");
		assertEquals(4, list_ABC_6.length());
	}

	@Test(expected = IllegalStateException.class)
	public void testPrependIntoFullPipe() {
		list_ABC_6.prepend("F");
		list_ABC_6.prepend("E");
		list_ABC_6.prepend("D");
		list_ABC_6.prepend("G");
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPrependNullIntoPipe() {
		String str = null;
		list_ABC_6.prepend(str);
		fail();
	}

	@Test
	public void testEmptyNewInstance() {
		Pipe<String> newPipe = list_empty_3.newInstance();
		assertEquals(0, newPipe.length());
		assertEquals(3, newPipe.capacity());
	}

	@Test
	public void testABCNewInstance() {
		Pipe<String> newPipe = list_ABC_6.newInstance();
		assertEquals(0, newPipe.length());
		assertEquals(6, newPipe.capacity());
	}

	@Test
	public void testEmptyCopy() {
		Pipe<String> copy = list_empty_3.copy();
		assertEquals(0, copy.length());
		assertEquals(3, copy.capacity());
		assertEquals(list_empty_3, copy);
	}

	@Test
	public void testABCCopy() {
		Pipe<String> copy = list_ABC_6.copy();
		assertEquals(3, copy.length());
		assertEquals(6, copy.capacity());
		assertEquals(list_ABC_6, copy);
		String s1 = list_ABC_6.removeFirst();
		String s1_copy = copy.removeFirst();
		assertTrue(s1 == s1_copy);
	}

	@Test
	public void testEmptyClear() {
		list_empty_3.clear();
		String result = "";
		for (String s : list_empty_3) {
			if (s != null) {
				result += s;
			}
		}

		assertEquals(0, list_empty_3.length());
		assertEquals(3, list_empty_3.capacity());
		assertEquals("", result);
	}

	@Test
	public void testABCClear() {
		list_ABC_6.clear();
		String result = "";
		for (String s : list_ABC_6) {
			if (s != null) {
				result += s;
			}
		}

		assertEquals(0, list_ABC_6.length());
		assertEquals(6, list_ABC_6.capacity());
		assertEquals("", result);
	}

	@Test
	public void testEmptyFirst() {
		assertEquals(null, list_empty_3.first());
	}

	@Test
	public void testABCFirst() {
		assertEquals("A", list_ABC_6.first());
	}

	@Test
	public void testEmptyLast() {
		assertEquals(null, list_empty_3.last());
	}

	@Test
	public void testABCLast() {
		assertEquals("C", list_ABC_6.last());
	}

	@Test
	public void testEmptyAppendAllList() {
		list_empty_3.appendAll(list_ABC_6);
		String emptyResult = "";
		for (String s : list_empty_3) {
			if (s != null) {
				emptyResult += s;
			}
		}

		String abcResult = "";
		for (String s : list_ABC_6) {
			if (s != null) {
				abcResult += s;
			}
		}

		assertEquals(3, list_empty_3.length());
		assertEquals(3, list_empty_3.capacity());
		assertEquals("ABC", emptyResult);
		assertEquals(0, list_ABC_6.length());
		assertEquals(6, list_ABC_6.capacity());
		assertEquals("", abcResult);
	}

	@Test(expected = IllegalStateException.class)
	public void testEmptyAppendAllListWhenFull() {
		testEmptyAppendAllList();
		ListPipe<String> testList = new ListPipe<String>(2);
		testList.append("X");
		testList.append("Y");
		list_empty_3.appendAll(testList);
		fail();
	}

}
