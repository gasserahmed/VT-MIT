package boundedpipe;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Gasser Ahmed
 *
 */
public class LinkedPipeTest {

    private Pipe<String> linked_empty_3;
    private Pipe<String> linked_ABC_6;

    @Before
    public void setUp() throws Exception {
        linked_empty_3 = new LinkedPipe<>(3);
        linked_ABC_6 = new LinkedPipe<>(6);
        linked_ABC_6.append("A");
        linked_ABC_6.append("B");
        linked_ABC_6.append("C");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidParam() {
        LinkedPipe<String> linked = new LinkedPipe<>(-1);
        fail();
    }

    @Test
    public void testEmptyToString() {
        assertEquals("[]:3", linked_empty_3.toString());
    }

    @Test
    public void testABCToString() {
        assertEquals("[A, B, C]:6", linked_ABC_6.toString());
    }

    @Test
    public void testABCEqualsNull() {
        Pipe<String> p = null;
        assertFalse(linked_ABC_6.equals(p));
    }

    @Test
    public void testABCEqualsSelf() {
        assertTrue(linked_ABC_6.equals(linked_ABC_6));
    }

    @Test
    public void testABCEqualsNonPipe() {
        assertFalse(linked_ABC_6.equals("[A, B, C]:6"));
    }

    @Test
    public void testABC6EqualsDifferentABC6() {
        Pipe<String> linked_ABC_6_dup = new LinkedPipe<>(6);
        linked_ABC_6_dup.append("A");
        linked_ABC_6_dup.append("B");
        linked_ABC_6_dup.append("C");

        assertTrue(linked_ABC_6.equals(linked_ABC_6_dup));
    }

    @Test
    public void testABC6EqualsABC10() {
        Pipe<String> linked_ABC_10 = new LinkedPipe<>(10);
        linked_ABC_10.append("A");
        linked_ABC_10.append("B");
        linked_ABC_10.append("C");

        assertFalse(linked_ABC_6.equals(linked_ABC_10));
    }

    @Test
    public void testABC6EqualsAB6() {
        Pipe<String> linked_AB_6 = new LinkedPipe<>(6);
        linked_AB_6.append("A");
        linked_AB_6.append("B");

        assertFalse(linked_ABC_6.equals(linked_AB_6));
    }

    @Test
    public void testEmpty3EqualsDifferentEmpty3() {
        Pipe<String> linked_empty_6_dup = new LinkedPipe<>(3);

        assertTrue(linked_empty_3.equals(linked_empty_6_dup));
    }

    @Test
    public void testEmpty3EqualsEmpty5() {
        Pipe<String> linked_empty_5 = new LinkedPipe<>(5);

        assertFalse(linked_empty_3.equals(linked_empty_5));
    }

    @Test
    public void testABC6EqualsDEF6() {
        Pipe<String> linked_DEF_6 = new LinkedPipe<>(6);
        linked_DEF_6.append("D");
        linked_DEF_6.append("E");
        linked_DEF_6.append("F");

        assertFalse(linked_ABC_6.equals(linked_DEF_6));
    }

    @Test
    public void testABC6ArrayBasedEqualsABC6LinkedBased() {
        Pipe<String> pipe_ABC_6_array = new CircArrayPipe<>(6);
        pipe_ABC_6_array.append("A");
        pipe_ABC_6_array.append("B");
        pipe_ABC_6_array.append("C");

        assertTrue(linked_ABC_6.equals(pipe_ABC_6_array));
    }

    @Test
    public void testABC6ListBasedEqualsABC6LinkedBased() {
        Pipe<String> pipe_ABC_6_list = new ListPipe<>(6);
        pipe_ABC_6_list.append("A");
        pipe_ABC_6_list.append("B");
        pipe_ABC_6_list.append("C");

        assertTrue(linked_ABC_6.equals(pipe_ABC_6_list));
    }

    @Test
    public void testHashCodeABC6AndAB6() {
        Pipe<String> linked_AB_6 = new LinkedPipe<>(6);
        linked_AB_6.append("A");
        linked_AB_6.append("B");

        assertFalse(
                linked_ABC_6.hashCode() == linked_AB_6.hashCode());
    }

    @Test
    public void testHashCodeABC6AndDifferentABC6() {
        Pipe<String> linked_ABC_6_dup = new LinkedPipe<>(6);
        linked_ABC_6_dup.append("A");
        linked_ABC_6_dup.append("B");
        linked_ABC_6_dup.append("C");

        assertTrue(linked_ABC_6.hashCode() == linked_ABC_6_dup
                .hashCode());
    }

    @Test
    public void testHashCodeABC6AndABC10() {
        Pipe<String> linked_ABC_10 = new LinkedPipe<>(10);
        linked_ABC_10.append("A");
        linked_ABC_10.append("B");
        linked_ABC_10.append("C");

        assertFalse(
                linked_ABC_6.hashCode() == linked_ABC_10.hashCode());
    }

    @Test
    public void testHashCodeABC6ArrayBasedAndABC6LinkedBased() {
        Pipe<String> pipe_ABC_6_array = new CircArrayPipe<>(6);
        pipe_ABC_6_array.append("A");
        pipe_ABC_6_array.append("B");
        pipe_ABC_6_array.append("C");

        assertTrue(linked_ABC_6.hashCode() == pipe_ABC_6_array
                .hashCode());
    }

    @Test
    public void testHashCodeABC6ListBasedAndABC6LinkedBased() {
        Pipe<String> pipe_ABC_6_list = new ListPipe<>(6);
        pipe_ABC_6_list.append("A");
        pipe_ABC_6_list.append("B");
        pipe_ABC_6_list.append("C");

        assertTrue(linked_ABC_6.hashCode() == pipe_ABC_6_list
                .hashCode());
    }

    @Test
    public void testEmptyLength() {
        assertEquals(0, linked_empty_3.length());
        assertTrue(linked_empty_3.isEmpty());
    }

    @Test
    public void testEmptyCapacity() {
        assertEquals(3, linked_empty_3.capacity());
    }

    @Test
    public void testEmptyAppendA() {
        linked_empty_3.append("A");
        assertEquals(1, linked_empty_3.length());
        assertFalse(linked_empty_3.isEmpty());
    }

    @Test
    public void testEmptyAppendARemoveFirst() {
        linked_empty_3.append("A");
        assertEquals(1, linked_empty_3.length());
        String str = linked_empty_3.removeFirst();
        assertEquals(0, linked_empty_3.length());
        assertEquals("A", str);
    }

    @Test
    public void testEmptyAppendAAppendBRemoveFirst() {
        linked_empty_3.append("A");
        linked_empty_3.append("B");
        assertEquals(2, linked_empty_3.length());
        String str = linked_empty_3.removeFirst();
        assertEquals(1, linked_empty_3.length());
        assertEquals("A", str);
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveFirstEmptyPipe() {
        linked_empty_3.removeFirst();
        fail();
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveLastEmptyPipe() {
        linked_empty_3.removeLast();
        fail();
    }

    @Test
    public void testABCAppendD() {
        linked_ABC_6.append("D");
        assertEquals(4, linked_ABC_6.length());
    }

    @Test
    public void testABCRemoveFirst() {
        String str = linked_ABC_6.removeFirst();
        assertEquals(2, linked_ABC_6.length());
        assertEquals("A", str);
    }

    @Test(expected = IllegalStateException.class)
    public void testAppendIntoFullPipe() {
        linked_ABC_6.append("D");
        linked_ABC_6.append("E");
        linked_ABC_6.append("F");
        linked_ABC_6.append("G");
        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAppendNullIntoPipe() {
        String str = null;
        linked_ABC_6.append(str);
        fail();
    }

    @Test
    public void testABCLength() {
        assertEquals(3, linked_ABC_6.length());
    }

    @Test
    public void testABCCapacity() {
        assertEquals(6, linked_ABC_6.capacity());
    }

    @Test
    public void testEmptyPrependA() {
        linked_empty_3.prepend("A");
        assertEquals(1, linked_empty_3.length());
    }

    @Test
    public void testEmptyPrependARemoveFirst() {
        linked_empty_3.prepend("A");
        assertEquals(1, linked_empty_3.length());
        String str = linked_empty_3.removeFirst();
        assertEquals(0, linked_empty_3.length());
        assertEquals("A", str);
    }

    @Test
    public void testEmptyAppendBPrependARemoveFirst() {
        linked_empty_3.append("B");
        linked_empty_3.prepend("A");
        String str = linked_empty_3.removeFirst();
        assertEquals(1, linked_empty_3.length());
        assertEquals("A", str);
    }

    @Test
    public void testEmptyPrependARemoveLast() {
        linked_empty_3.prepend("A");
        assertEquals(1, linked_empty_3.length());
        String str = linked_empty_3.removeLast();
        assertEquals(0, linked_empty_3.length());
        assertEquals("A", str);
    }

    @Test
    public void testEmptyAppendAAppendBRemoveLast() {
        linked_empty_3.append("A");
        linked_empty_3.append("B");
        String str = linked_empty_3.removeLast();
        assertEquals(1, linked_empty_3.length());
        assertEquals("B", str);
    }

    @Test
    public void testEmptyPrependAPrependBRemoveLast() {
        linked_empty_3.prepend("A");
        linked_empty_3.prepend("B");
        assertEquals(2, linked_empty_3.length());
        String str = linked_empty_3.removeLast();
        assertEquals(1, linked_empty_3.length());
        assertEquals("A", str);
    }

    @Test
    public void testABCPrependZ() {
        linked_ABC_6.prepend("F");
        assertEquals(4, linked_ABC_6.length());
    }

    @Test(expected = IllegalStateException.class)
    public void testPrependIntoFullPipe() {
        linked_ABC_6.prepend("F");
        linked_ABC_6.prepend("E");
        linked_ABC_6.prepend("D");
        linked_ABC_6.prepend("G");
        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPrependNullIntoPipe() {
        String str = null;
        linked_ABC_6.prepend(str);
        fail();
    }

    @Test
    public void testEmptyNewInstance() {
        Pipe<String> newPipe = linked_empty_3.newInstance();
        assertEquals(0, newPipe.length());
        assertEquals(3, newPipe.capacity());
    }

    @Test
    public void testABCNewInstance() {
        Pipe<String> newPipe = linked_ABC_6.newInstance();
        assertEquals(0, newPipe.length());
        assertEquals(6, newPipe.capacity());
    }

    @Test
    public void testEmptyCopy() {
        Pipe<String> copy = linked_empty_3.copy();
        assertEquals(0, copy.length());
        assertEquals(3, copy.capacity());
        assertEquals(linked_empty_3, copy);
    }

    @Test
    public void testABCCopy() {
        Pipe<String> copy = linked_ABC_6.copy();
        assertEquals(3, copy.length());
        assertEquals(6, copy.capacity());
        assertEquals(linked_ABC_6, copy);
        String s1 = linked_ABC_6.removeFirst();
        String s1_copy = copy.removeFirst();
        assertTrue(s1 == s1_copy);
    }

    @Test
    public void testEmptyClear() {
        linked_empty_3.clear();
        String result = "";
        for (String s : linked_empty_3) {
            if (s != null) {
                result += s;
            }
        }

        assertEquals(0, linked_empty_3.length());
        assertEquals(3, linked_empty_3.capacity());
        assertEquals("", result);
    }

    @Test
    public void testABCClear() {
        linked_ABC_6.clear();
        String result = "";
        for (String s : linked_ABC_6) {
            if (s != null) {
                result += s;
            }
        }

        assertEquals(0, linked_ABC_6.length());
        assertEquals(6, linked_ABC_6.capacity());
        assertEquals("", result);
    }

    @Test
    public void testEmptyFirst() {
        assertEquals(null, linked_empty_3.first());
    }

    @Test
    public void testABCFirst() {
        assertEquals("A", linked_ABC_6.first());
    }

    @Test
    public void testEmptyLast() {
        assertEquals(null, linked_empty_3.last());
    }

    @Test
    public void testABCLast() {
        assertEquals("C", linked_ABC_6.last());
    }

    @Test
    public void testEmptyAppendAllLinked() {
        linked_empty_3.appendAll(linked_ABC_6);
        String emptyResult = "";
        for (String s : linked_empty_3) {
            if (s != null) {
                emptyResult += s;
            }
        }

        String abcResult = "";
        for (String s : linked_ABC_6) {
            if (s != null) {
                abcResult += s;
            }
        }

        assertEquals(3, linked_empty_3.length());
        assertEquals(3, linked_empty_3.capacity());
        assertEquals("ABC", emptyResult);
        assertEquals(0, linked_ABC_6.length());
        assertEquals(6, linked_ABC_6.capacity());
        assertEquals("", abcResult);
    }

    @Test(expected = IllegalStateException.class)
    public void testEmptyAppendAllLinkedWhenFull() {
        testEmptyAppendAllLinked();
        LinkedPipe<String> testList = new LinkedPipe<String>(2);
        testList.append("X");
        testList.append("Y");
        linked_empty_3.appendAll(testList);
        fail();
    }

}
