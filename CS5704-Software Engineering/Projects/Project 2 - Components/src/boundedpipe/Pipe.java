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
	
	/**
	 * Returns a new empty pipe with the same capacity as this pipe.
	 * @return a new empty pipe with the same capacity as this pipe
	 */
	public Pipe<E> newInstance();
	
	/**
	 * Clear this pipe
	 */
	public void clear();
	
	/**
	 * Returns true if this pipe is empty, otherwise, false.
	 * @return true if this pipe is empty, otherwise, false 
	 */
	public boolean isEmpty();
	
	/**
	 * Returns true if this pipe is full, otherwise, true.
	 * @return true if this pipe is full, otherwise, true
	 */
	public boolean isFull();
	
	/**
	 * Append elements of pipe into another pipe then empty that pipe.
	 * @param that pipe to be appended
	 */
	public void append(Pipe<E> that);
	
	/**
	 * Returns a copy of this pipe. The elements in the copy are
	 * references to the elements in this pipe.
	 * @return a copy of this pipe
	 */
	public Pipe<E> copy();
	
	/**
	 * Returns true if the specified object is equal to this pipe.
	 * To be equal, the specified object must be a bounded pipe with the
	 * same capacity, and it must contain the same number of elements with
	 * the same values in the same order.
	 * @param obj the object to be tested for equality to this pipe
	 * @return true if the specified object is equal to this pipe, false otherwise
	 */
	@Override
	public boolean equals(Object obj);

	/**
	 * Returns the hash code of this pipe.
	 * @return the hash code of this pipe
	 */
	@Override
	public int hashCode();

	/**
	 * Returns the string representation of this pipe. The string 
	 * representation contains a list of this pipe's element from start to
	 * end and the pipe's capacity. The list is enclosed in square brackets
	 * and adjacent elements are separated by a comma and a space. The list
	 * and capacity are separated by a colon. For example, the string
	 * <code>[A, B, C]:6</code> represents a pipe with 3 elements, where
	 * A is start element, C is the last element, and the pipe has a 
	 * capacity of 6.
	 * @return the string representation of this pipe
	 */
	@Override
	public String toString();
}
