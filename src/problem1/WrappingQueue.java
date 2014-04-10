package problem1;

/**
 *  Slightly modified version of ArrayBasedQueue.java 
 *  from the lab on Linear Structures by Samuel A. Rebelsky
 *  that implements array based queues that wrap around the 
 *  array when they run out of space.
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class WrappingQueue<T>
    implements
      Queue<T>
{
  // +--------+----------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The values stored in the queue.
   */
  T[] values;

  /**
   * The index of the front of the queue.
   */
  int front;
  /**
   * The index of the back of the queue.
   */
  int back;

  /**
   * The number of elements in the queue.
   */
  int size;

  // +--------------+----------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new queue that holds up to capacity elements.
   * 
   * @param capacity
   *          how many elements can be stored in this queue
   * @pre capacity <= 0
   */
  @SuppressWarnings({ "unchecked" })
  // Handle array casting
  public WrappingQueue(int capacity) throws Exception
  {
    if (capacity <= 0)
      {
        throw new Exception("Queues must have a positive capacity.");
      } // if (capacity <= 0)
    // Yay Java! It's not possible to say new T[capacity], so
    // we use this hack.
    this.values = (T[]) new Object[capacity];
    this.front = 0;
    this.back = 0;
    this.size = 0;
  } // WrappingQueue(int capacity)

  // +---------------+---------------------------------------------------
  // | Queue Methods |
  // +---------------+

  /**
   * Determine whether the queue is empty
   * 
   * @return true if empty, false otherwise
   */
  @Override
  public boolean isEmpty()
  {
    return this.size <= 0;
  } // isEmpty()

  /**
   * Determine whether the queue is full
   * 
   * @return true if full, false otherwise
   */
  @Override
  public boolean isFull()
  {
    return (this.front == this.back) && (this.size != 0);
  } // isFull()

  /**
   * Add a value to the queue
   * 
   * @param val
   *          value to be added
   * @pre !this.isFull()
   * @post the last element to be removed is val
   */
  @Override
  public void put(T val)
    throws Exception
  {
    if (this.isFull())
      {
        throw new Exception("no more room!");
      } // this.isFull()
    this.values[this.back] = val;
    this.size++;
    this.back = wrap(this.back);
  } // put(T)

  /**
   * Get the first value in the queue
   * 
   * @pre !this.isEmpty()
   * @post this.size is decremented
   * @post the first element was removed
   * @return the first element in the queue
   */
  @Override
  public T get()
    throws Exception
  {
    if (this.isEmpty())
      {
        throw new Exception("empty");
      } // if empty
    // Grab and clear the element at the front of the queue
    T result = this.values[this.front];
    this.values[this.front] = null;
    // Increment or wrap the front
    this.front = wrap(this.front);
    // We're removing an element, so decrement the size
    --this.size;
    // And we're done
    return result;
  } // get()

  /**
   * Peek a the first element of the queue without removing it
   * 
   * @pre !this.isEmpty
   * @post the queue is unchanged
   * @return the first element in the queue
   */
  @Override
  public T peek()
    throws Exception
  {
    if (this.isEmpty())
      {
        throw new Exception("empty");
      } // if empty
    return this.values[this.front];
  } // peek()

  @Override
  public T dequeue()
    throws Exception
  {
    return this.get();
  } // dequeue

  @Override
  public void enqueue(T val)
    throws Exception
  {
    this.put(val);
  } // enqueue

  @Override
  public Iterator<T> iterator()
  {
    return new WrappingQueueIterator<T>(this);
  } // iterator()

  // +----------------+--------------------------------------------------
  // | Helper Methods |
  // +----------------+

  /**
   * Get the index of the back of the queue. The back is where we add the next
   * element.
   */
  int back()
  {
    return this.back;
  } // back()

  /**
   * Determine the next index with wrapping around
   * 
   * @pre index < this.values.length
   * @post none
   * @return the next index
   */
  int wrap(int index)
  {
    if (++index >= this.values.length)
      { // wrap around
        index = 0;
      } // if index is out of bounds
    return index;
  }// wrap(int)

} // class ArrayBasedQueue<T>

class WrappingQueueIterator<T>
    implements
      Iterator<T>
{
  // +--------+----------------------------------------------------------
  // | Fields |
  // +--------+
  /**
   * The current position of the iterator
   */
  int cursor;

  /**
   * The instance of WrappingQueue that the iterator operates on.
   */
  WrappingQueue<T> wQueue;
  /**
   * Number of iterations
   */
  int iterations;

  // +--------------+----------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new iterator.
   */
  public WrappingQueueIterator(WrappingQueue<T> q)
  {
    this.cursor = q.front;
    this.wQueue = q;
    this.iterations = 0;
  } // WrappingQueueQueueIterator

  // +---------+---------------------------------------------------------
  // | Methods |
  // +---------+
  /**
   * Get the element after the iterator and move forward
   * 
   * @pre it.hasNext()
   * @post the iterator advanced
   * @return the next element
   * 
   */
  @Override
  public T next()
    throws NoSuchElementException
  {
    if (!this.hasNext())
      {
        throw new NoSuchElementException("no elements remain");
      } // if no elements
    else
      {
        int temp = this.cursor;
        this.cursor = this.wQueue.wrap(this.cursor);
        this.iterations++;
        return this.wQueue.values[temp];
      }// else
  } // next()

  /**
   * Determine whether the iterator has a next element
   * 
   * @return true if the next element exists, false otherwise
   */
  @Override
  public boolean hasNext()
  {
    return this.iterations < this.wQueue.size;
  } // hasNext()

  /**
   * Remove is not implemented for stacks
   * 
   * @throws UnsupportedOperationException
   */
  @Override
  public void remove()
    throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException();
  } // remove()

}// WrappingQueue class
