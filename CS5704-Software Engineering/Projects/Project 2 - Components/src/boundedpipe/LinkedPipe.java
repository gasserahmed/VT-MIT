/**
 * 
 */
package boundedpipe;

import java.util.Iterator;

import org.w3c.dom.Node;

/**
 * @author Gasser Ahmed
 * @param <E>
 *
 */
public class LinkedPipe<E> extends AbstractPipe<E> {

	private Node first;
	private Node last;
	private int length;

	@SuppressWarnings("unchecked")
	public LinkedPipe(int max) throws IllegalArgumentException {
		super(max);
		if (max < 1) {
			throw new IllegalArgumentException();
		}

		first = null;
		last = null;
	}

	@Override
	public void prepend(E element) throws IllegalStateException, IllegalArgumentException {
		if (element == null) {
			throw new IllegalArgumentException();
		}

		if (isFull()) {
			throw new IllegalStateException();
		}

		Node newNode = new Node(element);
		if (first == null) {
			first = newNode;
			last = newNode;
		} else {
			first.prev = newNode;
			newNode.next = first;
			first = newNode;
		}

		length++;
	}

	@Override
	public void append(E element) throws IllegalStateException, IllegalArgumentException {
		if (element == null) {
			throw new IllegalArgumentException();
		}

		if (isFull()) {
			throw new IllegalStateException();
		}

		Node newNode = new Node(element);
		if (last == null) {
			last = newNode;
			first = newNode;
		} else {
			last.next = newNode;
			newNode.prev = last;
			last = newNode;
		}

		length++;
	}

	@Override
	public E removeFirst() throws IllegalStateException {
		if (first == null) {
			throw new IllegalStateException();
		}

		E firstToBeRemoved = first.contents;
		first = first.next;
		length--;
		return firstToBeRemoved;
	}

	@Override
	public E removeLast() throws IllegalStateException {
		if (last == null) {
			throw new IllegalStateException();
		}

		E lastToBeRemoved = last.contents;
		last = last.prev;
		length--;
		return lastToBeRemoved;
	}

	@Override
	public int length() {
		return length;
	}

	@Override
	public Pipe<E> newInstance() {
		return new LinkedPipe<>(capacity());
	}

	@Override
	public void clear() {
		if (first != null) {
			Node temp = first.next;
			first = null;
			first = temp;
			length--;
			clear();
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new LinkedPipeIterator();
	}

	private class LinkedPipeIterator implements Iterator<E> {

		private int currentIndex = 0;
		private Node itrNode = first;

		@Override
		public boolean hasNext() {
			return currentIndex < length;
		}

		@Override
		public E next() {
			E currentNodeElem = itrNode.contents;
			itrNode = itrNode.next;
			currentIndex++;
			return currentNodeElem;
		}

	}

	private class Node {
		E contents = null;
		Node prev = null;
		Node next = null;

		Node(E contents) {
			this.contents = contents;
		}
	}

}
