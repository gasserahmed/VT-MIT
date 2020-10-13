/**
 * 
 */
package boundedpipe;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Gasser Ahmed
 * @version Fall 2020
 * @param <E>
 *
 */
public class CircArrayPipe<E> extends AbstractPipe<E> {

    private E[] elements;
    private int first = -1;
    private int last = -1;
    private int length = 0;

    /**
     * Create a new object of circular array pipe
     * 
     * @param max the maximum number of elements that this pipe can hold
     */
    @SuppressWarnings("unchecked")
    public CircArrayPipe(int max) throws IllegalArgumentException {
        super(max);
        if (max < 1)
        {
            throw new IllegalArgumentException();
        }
        elements = (E[]) new Object[max];
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

        // Empty pipe
        if (length == 0)
        {
            first = capacity() - 1;
            last = capacity() - 1;
        } 
        else if (first == 0)
        {
            first = capacity() - 1;
        }
        else
        {
            first--;
        }

        elements[first] = element;
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

        // Increment first if first append
        if (length == 0)
        {
            first++;
        }

        elements[++last % capacity()] = element;
        length++;
    }

    @Override
    public E removeFirst() throws IllegalStateException {
        if (isEmpty())
        {
            throw new IllegalStateException();
        }

        E removedElement = elements[first];

        int secondElement = first + 1;

        if (first == last)
        {
            first = -1;
            last = -1;
        } 
        else if (secondElement == capacity())
        {
            first = 0;
        } 
        else
        {
            first++;
        }
        
        length--;

        return removedElement;
    }

    @Override
    public E removeLast() throws IllegalStateException {
        if (isEmpty())
        {
            throw new IllegalStateException();
        }

        E removedElement = elements[last % capacity()];
        if (first == last)
        {
            first = -1;
            last = -1;
        }        
        else
        {
            last--;
        }
        
        length--;

        return removedElement;
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public Pipe<E> newInstance() {
        return new CircArrayPipe<>(capacity());
    }

    @Override
    public void clear() {
        if (!isEmpty())
        {
            elements[last--] = null;   
            length--;
            clear();
        }
    }

    @Override
    public E first() {
        return (isEmpty()) ? null : elements[first];
    }

    @Override
    public E last() {
        return (isEmpty()) ? null : elements[last % capacity()];
    }

    @Override
    public Iterator<E> iterator() {
        return new CircArrayIterator();
    }

    private class CircArrayIterator implements Iterator<E> {

        private int currentIndex = 0;
    
        @Override
        public boolean hasNext() {
            return currentIndex < length();
        }

        @Override
        public E next() {
            if (hasNext()) {
                return elements[(first + currentIndex++) % capacity()];
            }
            
            throw new NoSuchElementException();
        }
    }
}
