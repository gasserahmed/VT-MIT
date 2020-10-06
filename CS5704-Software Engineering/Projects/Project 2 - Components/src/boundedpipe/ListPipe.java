/**
 * 
 */
package boundedpipe;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Gasser Ahmed
 * @param <E>
 *
 */
public class ListPipe<E> extends AbstractPipe<E> {

	private List<E> list;

	@SuppressWarnings("unchecked")
	public ListPipe(int max) throws IllegalArgumentException {
		super(max);
		if (max < 1) {
			throw new IllegalArgumentException();
		}

		list = new LinkedList<>();
	}

	@Override
	public void prepend(E element) throws IllegalStateException, IllegalArgumentException {
		if (element == null) {
			throw new IllegalArgumentException();
		}

		if (isFull()) {
			throw new IllegalStateException();
		}

		list.add(0, element);
	}

	@Override
	public void append(E element) throws IllegalStateException, IllegalArgumentException {
		if (element == null) {
			throw new IllegalArgumentException();
		}

		if (isFull()) {
			throw new IllegalStateException();
		}

		list.add(element);
	}

	@Override
	public E removeFirst() throws IllegalStateException {
		if (list.isEmpty()) {
			throw new IllegalStateException();
		}

		return list.remove(0);
	}

	@Override
	public E removeLast() throws IllegalStateException {
		if (list.isEmpty()) {
			throw new IllegalStateException();
		}

		return list.remove(list.size() - 1);
	}

	@Override
	public int length() {
		return list.size();
	}

	@Override
	public Pipe<E> newInstance() {
		return new ListPipe<>(capacity());
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public Iterator<E> iterator() {
		return new ListPipeIterator();
	}

	private class ListPipeIterator implements Iterator<E> {

		private int currentIndex = 0;

		@Override
		public boolean hasNext() {
			return currentIndex < length();
		}

		@Override
		public E next() {
			return list.get(currentIndex++);
		}
	}

}
