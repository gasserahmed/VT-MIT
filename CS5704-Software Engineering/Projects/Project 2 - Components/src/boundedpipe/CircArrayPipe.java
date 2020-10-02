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
	public CircArrayPipe(int max) throws IllegalArgumentException {
		super(max);
		if (max < 1) {
			throw new IllegalArgumentException();
		}
		elements = (E[]) new Object[max];
	}

	@Override
	public void prepend(E element) throws IllegalStateException, IllegalArgumentException {
		if (element == null) {
			throw new IllegalArgumentException();
		}

		if (isFull()) {
			throw new IllegalStateException();
		}

		// Empty pipe
		if (last == -1) {
			first = last = 0;
		} else if (first == 0) {
			first = capacity() - 1;
		} else {
			first--;
		}

		elements[first] = element;
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
		if (first == last) {
			first = last = -1;
		} else if (first + 1 == capacity()) {
			first = 0;
		} else {
			first++;
		}

		return removedElement;
	}

	@Override
	public E removeLast() throws IllegalStateException {
		if (last == -1) {
			throw new IllegalStateException();
		}

		E removedElement = elements[last];
		if (first == last) {
			first = last = -1;
		} else if (last == 0) {
			last = capacity() - 1;
		} else {
			last--;
		}

		return removedElement;
	}

	@Override
	public int length() {
		if (first == -1) {
			return 0;
		}

		if (first == last) {
			return 1;
		}

		if (first < last) {
			return last - first + 1;
		}

		// first > last
		return capacity() - first + last + 1;
	}

	@Override
	public Pipe<E> newInstance() {
		return new CircArrayPipe<>(capacity());
	}

	@Override
	public void clear() {
		first = -1;
		last = -1;
		Iterator<E> itr = iterator();
		E elem = itr.next();
		elem = null;		
	}

	@Override
	public void append(Pipe<E> that) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterator<E> iterator() {
		return new CircArrayIterator();
	}
	
	private class CircArrayIterator implements Iterator<E> {

		// for the pipe:
		// 1. starting index could be in the middle of the array
		// 2. ending index could be less than your starting index
		// 3. you will have to use modulo operator %
		
		private int currentIndex = 0;
		
		@Override
		public boolean hasNext() {
			return currentIndex < length();
		}

		@Override
		public E next() {
			return elements[currentIndex++];
		}
		
	}

}
