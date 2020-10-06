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

	public void append(Pipe<E> that) {
		if (that.length() > 0 && this.length() < this.capacity) {
			E element = that.removeFirst();
			this.append(element);
			append(that);
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
