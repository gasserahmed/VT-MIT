package boundedpipe;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CircArrayIteratorTest {

	private Pipe<String> arr_ABC_6;

	@Before
	public void setUp() throws Exception {
		arr_ABC_6 = new CircArrayPipe<String>(6);
		arr_ABC_6.append("A");
		arr_ABC_6.append("B");
		arr_ABC_6.append("C");
	}

	@Test
	public void testABCIterator() {
		String result = "";
		for (String s : arr_ABC_6) {
			if (s != null) {
				result += s;
			}
		}

		assertEquals("ABC", result);
	}

}
