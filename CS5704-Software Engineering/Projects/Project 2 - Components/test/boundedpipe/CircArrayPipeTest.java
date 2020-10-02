package boundedpipe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import boundedpipe.CircArrayPipe;

/**
 * 
 * @author Gasser Ahmed
 *
 */
public class CircArrayPipeTest {

	private CircArrayPipe<String> arr_empty_3;
	private CircArrayPipe<String> arr_ABC_6;

	@Before
	public void setUp() throws Exception {
		arr_empty_3 = new CircArrayPipe<>(3);
		arr_ABC_6 = new CircArrayPipe<>(6);
		arr_ABC_6.append("A");
		arr_ABC_6.append("B");
		arr_ABC_6.append("C");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorInvalidParam() {
		CircArrayPipe<String> arr = new CircArrayPipe<>(-1);
		fail();
	}

	@Test
	public void testEmptyLength() {
		assertEquals(0, arr_empty_3.length());
	}

	@Test
	public void testEmptyCapacity() {
		assertEquals(3, arr_empty_3.capacity());
	}

	@Test
	public void testEmptyAppendA() {
		arr_empty_3.append("A");
		assertEquals(1, arr_empty_3.length());
	}

	@Test
	public void testEmptyAppendARemoveFirst() {
		arr_empty_3.append("A");
		assertEquals(1, arr_empty_3.length());
		String str = arr_empty_3.removeFirst();
		assertEquals(0, arr_empty_3.length());
		assertEquals("A", str);
	}

	@Test
	public void testEmptyAppendAAppendBRemoveFirst() {
		arr_empty_3.append("A");
		arr_empty_3.append("B");
		assertEquals(2, arr_empty_3.length());
		String str = arr_empty_3.removeFirst();
		assertEquals(1, arr_empty_3.length());
		assertEquals("A", str);
	}

	@Test(expected = IllegalStateException.class)
	public void testRemoveFirstEmptyPipe() {
		arr_empty_3.removeFirst();
		fail();
	}
	
	@Test(expected = IllegalStateException.class)
	public void testRemoveLastEmptyPipe() {
		arr_empty_3.removeLast();
		fail();
	}
	
	@Test
	public void testABCAppendD() {
		arr_ABC_6.append("D");
		assertEquals(4, arr_ABC_6.length());
	}

	@Test
	public void testABCRemoveFirst() {
		String str = arr_ABC_6.removeFirst();
		assertEquals(2, arr_ABC_6.length());
		assertEquals("A", str);
	}

	@Test(expected = IllegalStateException.class)
	public void testAppendIntoFullPipe() {
		arr_ABC_6.append("D");
		arr_ABC_6.append("E");
		arr_ABC_6.append("F");
		arr_ABC_6.append("G");
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAppendNullIntoPipe() {
		String str = null;
		arr_ABC_6.append(str);
		fail();
	}


	@Test
	public void testABCLength() {
		assertEquals(3, arr_ABC_6.length());
	}

	@Test
	public void testABCCapacity() {
		assertEquals(6, arr_ABC_6.capacity());
	}

	@Test
	public void testEmptyPrependA() {
		arr_empty_3.prepend("A");
		assertEquals(1, arr_empty_3.length());
	}

	@Test
	public void testEmptyPrependARemoveFirst() {
		arr_empty_3.prepend("A");
		assertEquals(1, arr_empty_3.length());
		String str = arr_empty_3.removeFirst();
		assertEquals(0, arr_empty_3.length());
		assertEquals("A", str);
	}
	
	@Test
	public void testEmptyAppendBPrependARemoveFirst() {
		arr_empty_3.append("B");
		arr_empty_3.prepend("A");
		String str = arr_empty_3.removeFirst();
		assertEquals(1, arr_empty_3.length());
		assertEquals("A", str);
	}

	@Test
	public void testEmptyPrependBPrependBAppendC() {
		arr_empty_3.prepend("A");
		arr_empty_3.prepend("B");
		assertEquals(2, arr_empty_3.length());
		String str = arr_empty_3.removeFirst();
		assertEquals(1, arr_empty_3.length());
		assertEquals("B", str);
	}

	@Test
	public void testEmptyPrependARemoveLast() {
		arr_empty_3.prepend("A");
		assertEquals(1, arr_empty_3.length());
		String str = arr_empty_3.removeLast();
		assertEquals(0, arr_empty_3.length());
		assertEquals("A", str);
	}
	
	@Test
	public void testEmptyAppendAAppendBRemoveLast() {
		arr_empty_3.append("A");
		arr_empty_3.append("B");
		String str = arr_empty_3.removeLast();
		assertEquals(1, arr_empty_3.length());
		assertEquals("B", str);
	}

	@Test
	public void testEmptyPrependAPrependBRemoveLast() {
		arr_empty_3.prepend("A");
		arr_empty_3.prepend("B");
		assertEquals(2, arr_empty_3.length());
		String str = arr_empty_3.removeLast();
		assertEquals(1, arr_empty_3.length());
		assertEquals("A", str);
	}
	
	@Test
	public void testABCPrependZ() {
		arr_ABC_6.prepend("F");
		assertEquals(4, arr_ABC_6.length());
	}

	@Test(expected = IllegalStateException.class)
	public void testPrependIntoFullPipe() {
		arr_ABC_6.prepend("F");
		arr_ABC_6.prepend("E");
		arr_ABC_6.prepend("D");
		arr_ABC_6.prepend("G");
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPrependNullIntoPipe() {
		String str = null;
		arr_ABC_6.prepend(str);
		fail();
	}

	@Test
	public void testEmptyNewInstance() {
		Pipe<String> newPipe = arr_empty_3.newInstance();
		assertEquals(0, newPipe.length());
		assertEquals(3, newPipe.capacity());
	}
	
	@Test
	public void testABCNewInstance() {
		Pipe<String> newPipe = arr_ABC_6.newInstance();
		assertEquals(0, newPipe.length());
		assertEquals(6, newPipe.capacity());
	}

	@Test
	public void testEmptyCopy() {
		Pipe<String> copy = arr_empty_3.copy();
		assertEquals(0, copy.length());
		assertEquals(3, copy.capacity());
		assertEquals(arr_empty_3, copy);
	}
	
	@Test
	public void testABCCopy() {
		Pipe<String> copy = arr_ABC_6.copy();
		assertEquals(3, copy.length());
		assertEquals(6, copy.capacity());
		assertEquals(arr_ABC_6, copy);
		String s1 = arr_ABC_6.removeFirst();
		String s1_copy = copy.removeFirst();
		assertTrue(s1 == s1_copy);
	}
	
	@Test
	public void testEmptyClear() {
		arr_empty_3.clear();
		String result = "";
		for (String s : arr_empty_3) {
			if (s != null) {
				result += s;	
			}
		}
		
		assertEquals(0, arr_empty_3.length());
		assertEquals(3, arr_empty_3.capacity());
		assertEquals("", result);
	}
	
	@Test
	public void testABCClear() {
		arr_ABC_6.clear();
		String result = "";
		for (String s : arr_ABC_6) {
			if (s != null) {
				result += s;	
			}
		}
		
		assertEquals(0, arr_ABC_6.length());
		assertEquals(6, arr_ABC_6.capacity());
		assertEquals("", result);
	}
}
