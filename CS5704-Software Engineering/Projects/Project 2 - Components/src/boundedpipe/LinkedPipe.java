package boundedpipe;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * LinkedPipe Class
 * 
 * @author Gasser Ahmed
 * @version Fall 2020
 * @param <E>
 *
 */
public class LinkedPipe<E> extends AbstractPipe<E> {

    private Node first;
    private Node last;
    private int length;

    /**
     * Create a new object of linked pipe
     * 
     * @param max the maximum number of elements that this pipe can hold
     */
    @SuppressWarnings("unchecked")
    public LinkedPipe(int max) throws IllegalArgumentException {
        super(max);
        if (max < 1)
        {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void prepend(E element)
            throws IllegalStateException, IllegalArgumentException {
        if (element == null)
        {
            throw new IllegalArgumentException();
        }

        if (isFull())
        {
            throw new IllegalStateException();
        }

        Node newNode = new Node(element);
        if (isEmpty())
        {
            first = newNode;
            last = newNode;
        } 
        else
        {
            first.prev = newNode;
            newNode.next = first;
            first = newNode;
        }

        length++;
    }

    @Override
    public void append(E element)
            throws IllegalStateException, IllegalArgumentException {
        if (element == null)
        {
            throw new IllegalArgumentException();
        }

        if (isFull())
        {
            throw new IllegalStateException();
        }

        Node newNode = new Node(element);
        if (isEmpty())
        {
            last = newNode;
            first = newNode;
        } 
        else
        {
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        }

        length++;
    }

    @Override
    public E removeFirst() throws IllegalStateException {
        if (isEmpty())
        {
            throw new IllegalStateException();
        }

        E firstToBeRemoved = first.contents;
        first = first.next;
        length--;
        return firstToBeRemoved;
    }

    @Override
    public E removeLast() throws IllegalStateException {
        if (isEmpty())
        {
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
        if (!isEmpty())
        {
            Node temp = first.next;
            first = null;
            first = temp;
            length--;
            clear();
        }
    }

    @Override
    public E first() {
        return (isEmpty()) ? null : first.contents;
    }

    @Override
    public E last() {
        return (isEmpty()) ? null : last.contents;
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
            if (hasNext()) {
                E currentNodeElem = itrNode.contents;
                itrNode = itrNode.next;
                currentIndex++;
                return currentNodeElem;
            }
            
            throw new NoSuchElementException();
        }
    }

    private class Node {
        private E contents = null;
        private Node prev = null;
        private Node next = null;

        Node(E contents) {
            this.contents = contents;
        }
    }

}
