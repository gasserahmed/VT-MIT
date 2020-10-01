/**
 * 
 */
package boundedpipe;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @author Gasser Ahmed
 * @param <E>
 *
 */
public class CircArrayPipe<E> extends AbstractPipe<E> {

	private E[] elements;
	private int first = -1;
	private int last = -1;

	@SuppressWarnings("unchecked")
	public CircArrayPipe(int max) throws 
			IllegalArgumentException {
		super(max);
		if (max < 1) {
			throw new IllegalArgumentException();
		}
		elements = (E[]) new Object[max];
	}

	@Override
	public void prepend(E element) throws IllegalStateException, IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void append(E element) throws IllegalStateException, IllegalArgumentException {
		if (element == null) {
			throw new IllegalArgumentException();
		}
		
		if (isFull()) {
			throw new IllegalStateException();
		}
		
		// Increment first if first append
		if (last == -1) {
			first++;
		}
		
		elements[++last] = element;
	}

	@Override
	public E removeFirst() throws IllegalStateException {
		if (first == -1) {
			throw new IllegalStateException();
		}
		
		E removedElement = elements[first];
		elements[first] = null;
		if (last == 0) {
			first--;
			last--;
		}
		else if (first == capacity() - 1) {
			first = 0;
		}
		else {
			first++;
		}
		
//		System.out.println(first);
		if (first != -1 && elements[first] == null) {
			first = last;
		}
		
		return removedElement;
	}

	@Override
	public E removeLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int length() {
		int len = last + 1;
		
		// If prepend occurred
		if (first > 0) {
			len += capacity() - first;
		}
		
		return len;
	}

	@Override
	public Pipe<E> newInstance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public void append(Pipe<E> that) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterator<E> iterator() {
		return Arrays.stream(elements).iterator();
	}

}
