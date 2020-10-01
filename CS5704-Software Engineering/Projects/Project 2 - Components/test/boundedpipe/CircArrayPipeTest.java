package boundedpipe;

import static org.junit.Assert.assertEquals;

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
		assertEquals("B", str);		
	}
	
	
//	@Test
//	public void testABCIterator() {
//		String result = "";
//		for (String s : arr_ABC_6) {
//			result += s;
//		}
//		
//		assertEquals("ABC", result);
//	}
}
