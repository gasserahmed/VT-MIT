/**
 * 
 */
package boundedpipe;

import java.util.Iterator;

/**
 * @author Gasser Ahmed
 * @version Fall 2020
 * @param <E>
 *
 */
public abstract class AbstractPipe<E> implements Pipe<E> {
    private int capacity;

    /**
     * Create a new object of abstract pipe
     * 
     * @param capacity the number of elements that this pipe can hold
     */
    public AbstractPipe(int capacity) {
        this.capacity = capacity;
    }

    /**
     * {@inheritDoc}
     */
    public int capacity() {
        return capacity;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isEmpty() {
        return length() == 0;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isFull() {
        return length() == capacity;
    }

    /**
     * {@inheritDoc}
     */
    public void appendAll(Pipe<E> that) {
        if (that == null) {
            throw new IllegalArgumentException();
        }

        if (this.isFull()) {
            throw new IllegalStateException();
        }

        E element = that.removeFirst();
        this.append(element);
        if (that.length() > 0) {
            appendAll(that);
        }
    }

    @Override
    public Pipe<E> copy() {
        Pipe<E> result = this.newInstance();
        Iterator<E> itr = this.iterator();
        while (itr.hasNext()) {
            result.append(itr.next());
        }

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Pipe)) {
            return false;
        }

        Pipe<?> that = (Pipe) obj;
        if (this.capacity != that.capacity()) {
            return false;
        }

        if (this.length() != that.length()) {
            return false;
        }

        Iterator<E> thisItr = this.iterator();
        Iterator<?> thatItr = that.iterator();
        while (thisItr.hasNext()) {
            E elem = thisItr.next();
            Object o = thatItr.next();
            if (!elem.equals(o)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = 17;
        for (E element : this) {
            result = 31 * result + element.hashCode();
        }

        result = 31 * result + capacity;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator<E> itr = this.iterator();
        while (itr.hasNext()) {
            sb.append(itr.next());
            if (itr.hasNext()) {
                sb.append(", ");
            }
        }

        sb.append("]:");
        sb.append(capacity);
        return sb.toString();
    }
}
