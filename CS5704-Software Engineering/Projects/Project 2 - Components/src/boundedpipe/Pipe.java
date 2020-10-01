/**
 * Pipe Interface
 */
package boundedpipe;

/**
 * <p> 
 * A bounded data structure. Elements in the pipe may not be null.</p>
 * 
 * <p>
 * A typical string representation of a bounded pipe is 
 * <code>[e_1, e_2, ..., e_n-1, e_n]:c</code>,
 * where <code>e_1</code> is the first element of the pipe 
 * and <code>e_n</code> is the last element,
 * and <code>c</code> is the capacity.
 * </p>
 * <p>
 * Implementations of this interface should have a one-argument constructor
 * that takes the desired capacity and creates an empty pipe.
 * The capacity must be strictly greater zero.  
 * </p>
 * <p><code>public Pipe(int max)</code></p>
 * <p>
 * A pipe iterator iterates through the pipe from first/start to last/end.</p>
 * 
 * @author Gasser Ahmed
 * @param <E> the type of elements in the pipe
 */
public interface Pipe<E> extends Iterable<E>{
	/**
	 * <p>
	 * Adds the specified element into the beginning of this pipe.</p>
	 * { p = [A, B, C]:6 and x = X }
	 * p.prepend(x)
	 * 
	 * { p = [] }
	 * </p>
	 * 
	 * @param element the element to be prepended into this pipe
	 * @throws IllegalStateException if this pipe is full
	 * @throws IllegalArgumentException if the specified element is null
	 */
	public void prepend(E element) throws IllegalStateException, IllegalArgumentException;
	
	/**
	 * Adds the specified element into the end of this pipe.
	 * @param element the element to be appended into this pipe
	 * @throws IllegalStateException if this pipe is full
	 * @throws IllegalArgumentException if the specified element is null
	 */
	public void append(E element) throws IllegalStateException, IllegalArgumentException;
	
	/**
	 * Removes the first element of this pipe.
	 * @return the first element of this pipe
	 */
	public E removeFirst();
	
	/**
	 * Removes the last element of this pipe.
	 * @return the last element of this pipe
	 */
	public E removeLast();
	
	/**
	 * Returns the number of elements in this pipe.
	 * @return number of elements in this pipe
	 */
	public int length();
	
	/**
	 * Returns the number of elements that this pipe can hold.
	 * @return the number of elements that this pipe can hold
	 */
	public int capacity();
	
	
	public Pipe<E> newInstance();
	public void clear();
	public boolean isEmpty();
	public boolean isFull();
	public void append(Pipe<E> that);
	public Pipe<E> copy();
}
