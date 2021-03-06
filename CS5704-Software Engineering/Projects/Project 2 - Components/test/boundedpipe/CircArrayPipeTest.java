package boundedpipe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import boundedpipe.CircArrayPipe;

/**
 * 
 * @author Gasser Ahmed
 *
 */
public class CircArrayPipeTest {

    private Pipe<String> arr_empty_3;
    private Pipe<String> arr_ABC_6;
    private Pipe<String> arr_ABC_6_test;

    @Before
    public void setUp() throws Exception {
        arr_empty_3 = new CircArrayPipe<>(3);
        arr_ABC_6 = new CircArrayPipe<>(6);
        arr_ABC_6_test = new CircArrayPipe<>(6);

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
    public void testEmptyToString() {
        assertEquals("[]:3", arr_empty_3.toString());
    }

    @Test
    public void testABCToString() {
        assertEquals("[A, B, C]:6", arr_ABC_6.toString());
    }

    @Test
    public void testABCEqualsNull() {
        Pipe<String> p = null;
        assertFalse(arr_ABC_6.equals(p));
    }

    @Test
    public void testABCEqualsSelf() {
        assertTrue(arr_ABC_6.equals(arr_ABC_6));
    }

    @Test
    public void testABCEqualsNonPipe() {
        assertFalse(arr_ABC_6.equals("[A, B, C]:6"));
    }

    @Test
    public void testABC6EqualsABC10() {
        Pipe<String> arr_ABC_10 = new CircArrayPipe<>(10);
        arr_ABC_10.append("A");
        arr_ABC_10.append("B");
        arr_ABC_10.append("C");

        assertFalse(arr_ABC_6.equals(arr_ABC_10));
    }

    @Test
    public void testABC6EqualsAB6() {
        Pipe<String> arr_AB_6 = new CircArrayPipe<>(6);
        arr_AB_6.append("A");
        arr_AB_6.append("B");

        assertFalse(arr_ABC_6.equals(arr_AB_6));
    }

    @Test
    public void testEmpty3EqualsDifferentEmpty3() {
        Pipe<String> arr_empty_6_dup = new CircArrayPipe<>(3);

        assertTrue(arr_empty_3.equals(arr_empty_6_dup));
    }

    @Test
    public void testABC6EqualsDEF6() {
        Pipe<String> arr_DEF_6 = new CircArrayPipe<>(6);
        arr_DEF_6.append("D");
        arr_DEF_6.append("E");
        arr_DEF_6.append("F");

        assertFalse(arr_ABC_6.equals(arr_DEF_6));
    }

    @Test
    public void testABC6ListBasedEqualsABC6ArrayBased() {
        Pipe<String> pipe_ABC_6_list = new ListPipe<>(6);
        pipe_ABC_6_list.append("A");
        pipe_ABC_6_list.append("B");
        pipe_ABC_6_list.append("C");

        assertTrue(arr_ABC_6.equals(pipe_ABC_6_list));
    }
    
    @Test
    public void testABCD6EqualsDifferentABCD6() {
        arr_ABC_6.append("D");
        Pipe<String> arr_ABC_6_dup = new CircArrayPipe<>(6);
        arr_ABC_6_dup.append("A");
        arr_ABC_6_dup.append("B");
        arr_ABC_6_dup.append("C");
        arr_ABC_6_dup.append(" ");
        arr_ABC_6_dup.append("D");

        assertFalse(arr_ABC_6.equals(arr_ABC_6_dup));
    }

    @Test
    public void testHashCodeABC6AndAB6() {
        Pipe<String> arr_AB_6 = new CircArrayPipe<>(6);
        arr_AB_6.append("A");
        arr_AB_6.append("B");

        assertFalse(arr_ABC_6.hashCode() == arr_AB_6.hashCode());
    }

    @Test
    public void testHashCodeABC6AndDifferentABC6() {
        Pipe<String> arr_ABC_6_dup = new CircArrayPipe<>(6);
        arr_ABC_6_dup.append("A");
        arr_ABC_6_dup.append("B");
        arr_ABC_6_dup.append("C");

        assertTrue(arr_ABC_6.hashCode() == arr_ABC_6_dup.hashCode());
    }

    @Test
    public void testHashCodeABC6AndABC10() {
        Pipe<String> arr_ABC_10 = new CircArrayPipe<>(10);
        arr_ABC_10.append("A");
        arr_ABC_10.append("B");
        arr_ABC_10.append("C");

        assertFalse(arr_ABC_6.hashCode() == arr_ABC_10.hashCode());
    }

    @Test
    public void testHashCodeABC6ListBasedAndABC6ArrayBased() {
        Pipe<String> pipe_ABC_6_list = new ListPipe<>(6);
        pipe_ABC_6_list.append("A");
        pipe_ABC_6_list.append("B");
        pipe_ABC_6_list.append("C");

        assertTrue(
                arr_ABC_6.hashCode() == pipe_ABC_6_list.hashCode());
    }

    @Test
    public void testHashCodeABC6LinkedBasedAndABC6ArrayBased() {
        Pipe<String> pipe_ABC_6_linked = new LinkedPipe<>(6);
        pipe_ABC_6_linked.append("A");
        pipe_ABC_6_linked.append("B");
        pipe_ABC_6_linked.append("C");

        assertTrue(
                arr_ABC_6.hashCode() == pipe_ABC_6_linked.hashCode());
    }

    @Test
    public void testEmptyLength() {
        assertEquals(0, arr_empty_3.length());
        assertTrue(arr_empty_3.isEmpty());
    }

    @Test
    public void testEmptyCapacity() {
        assertEquals(3, arr_empty_3.capacity());
    }

    @Test
    public void testEmptyAppendA() {
        arr_empty_3.append("A");
        assertEquals(1, arr_empty_3.length());
        assertFalse(arr_empty_3.isEmpty());
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
        arr_ABC_6.prepend("Z");
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

    @Test
    public void testEmptyFirst() {
        assertEquals(null, arr_empty_3.first());
    }

    @Test
    public void testABCFirst() {
        assertEquals("A", arr_ABC_6.first());
    }

    @Test
    public void testEmptyLast() {
        assertEquals(null, arr_empty_3.last());
    }

    @Test
    public void testABCLast() {
        assertEquals("C", arr_ABC_6.last());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyAppendAllNullArray() {
        arr_empty_3.appendAll(null);
        fail();
    }

    @Test(expected = IllegalStateException.class)
    public void testEmptyAppendAllArrayIntoFull() {
        arr_empty_3.append("A");
        arr_empty_3.append("B");
        arr_empty_3.append("C");
        arr_empty_3.appendAll(arr_ABC_6);
        fail();
    }

    @Test
    public void testEmptyAppendAllArray() {
        arr_empty_3.appendAll(arr_ABC_6);
        String emptyResult = "";
        for (String s : arr_empty_3) {
            if (s != null) {
                emptyResult += s;
            }
        }

        String abcResult = "";
        for (String s : arr_ABC_6) {
            if (s != null) {
                abcResult += s;
            }
        }

        assertEquals(3, arr_empty_3.length());
        assertEquals(3, arr_empty_3.capacity());
        assertEquals("ABC", emptyResult);
        assertEquals(0, arr_ABC_6.length());
        assertEquals(6, arr_ABC_6.capacity());
        assertEquals("", abcResult);
    }

    @Test(expected = IllegalStateException.class)
    public void testEmptyAppendAllArrayWhenFull() {
        testEmptyAppendAllArray();
        CircArrayPipe<String> testArr = new CircArrayPipe<String>(2);
        testArr.append("X");
        testArr.append("Y");
        arr_empty_3.appendAll(testArr);
        fail();
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testIteratorNextOnEmptyArray() {
        arr_empty_3.iterator().next();
        fail();
    }
    
    @Test
    public void testMultipleAppendRemoveAppend() {
        arr_empty_3.append("A");
        arr_empty_3.append("B");
        arr_empty_3.append("C");
        arr_empty_3.removeFirst();
        arr_empty_3.removeFirst();
        arr_empty_3.append("X");
        arr_empty_3.append("Y");
        String emptyResult = "";
        for (String s : arr_empty_3) {
            if (s != null) {
                emptyResult += s;
            }
        }

        assertEquals("CXY", emptyResult);
    }
    
    @Test
    public void testProject2X() {
        Pipe<String> pipe = new CircArrayPipe<>(4);
        pipe.append("W");
        pipe.append("X");
        String s1 = pipe.removeFirst();
        pipe.prepend("Y");
        String s2 = pipe.removeLast();
        pipe.prepend("Z");
        pipe.prepend(s2);

        String result = "";
        for (String s : pipe) {
            if (s != null) {
                result += s;
            }
        }

        assertEquals("XZY", result);
        assertEquals("X", pipe.first());
        assertEquals("Y", pipe.last());
        assertEquals(3, pipe.length());
        assertEquals("[X, Z, Y]:4", pipe.toString());
    }
}
