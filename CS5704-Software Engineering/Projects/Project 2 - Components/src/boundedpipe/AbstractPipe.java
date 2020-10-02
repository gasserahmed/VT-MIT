/**
 * 
 */
package boundedpipe;

import java.util.Iterator;

/**
 * @author Gasser Ahmed
 * @param <E>
 *
 */
public abstract class AbstractPipe<E> implements Pipe<E> {
	private int capacity;
	
	public AbstractPipe() {}

	public AbstractPipe(int capacity) {
		this.capacity = capacity;
	}

	public int capacity() {
		return capacity;
	}

	public boolean isEmpty() {
		return length() == 0;
	}

	public boolean isFull() {
		return length() == capacity;
	}

//	public void append(Pipe<E> that) {
//		that.clear();
//		this.
//	}

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
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (!(o instanceof Pipe)) {
			return false;
		}
		AbstractPipe<?> that = (AbstractPipe<?>) o;
		return this.capacity == that.capacity;
	}
	
    @Override
    public int hashCode()
    {
        return 31 * 77 + capacity;
    }
    
    @Override
    public String toString()
    {
        return "(" + capacity + ")";
    }
}
