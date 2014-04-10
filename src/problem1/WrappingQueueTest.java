package problem1;

/**
 * Unit tests for WrappingQueue objects
 */
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Iterator;
import org.junit.Test;

public class WrappingQueueTest
{
  // Fields 
  
  /**
   * A standard array of integers
   */
  Integer[] numbers = new Integer[] { 1, 2, 3, 4, 5, 6, 7,
                                                         8, 9, 10 };
  // Helper methods
  
  /**
   * Convert an array to a WrappingQueue
   * 
   * @throws Exception
   */
  static <T> WrappingQueue<T> toQueue(T[] array)
    throws Exception
  {
    WrappingQueue<T> result = new WrappingQueue<>(array.length);
    result.values = array;
    result.size = array.length;
    return result;
  }// toQueue(T[])

  /**
   * Convert a WrappingQueue to array
   */

  static <T> T[] toArray(WrappingQueue<T> wQ)
  {
    Iterator<T> it = (WrappingQueueIterator<T>) wQ.iterator();
    T[] result = (T[]) new Object[wQ.size];
    int i = 0;
    while (it.hasNext())
      {
        result[i++] = it.next();
      }// while
    return result;
  }// toArray(WrappingQueue<T>)



 @Test
  public void simpleTests()
    throws Exception
  {
    WrappingQueue<Integer> w = new WrappingQueue<>(2);
    w.put(new Integer(1));
    assertEquals("peeking",new Integer(1), w.peek());
    //Testing the iterator
    Iterator<Integer> it = w.iterator();
    assertEquals("iterator hasNext, true", true, it.hasNext());
    assertEquals("iterator next", new Integer(1), it.next());
    assertEquals("iterator hasNext, false", false, it.hasNext());
    w.put(new Integer(2));
    //Testing isFull and is Empty
    assertEquals("isFull, true", true, w.isFull());
    w.get();
    assertEquals("isFull, false", false, w.isFull());
    assertEquals("isEmpty, false", false, w.isEmpty());
    w.get();
    assertEquals("isEmpty, true", true, w.isEmpty());
  } // simpleTests()

  @Test
  public void testRemove()
    throws Exception
  {
    WrappingQueue<Integer> w = toQueue(numbers);
    for (int i = 0; i < 10; i++)
      {
        w.get();
        assertArrayEquals("Remove test #" + i,
                          Arrays.copyOfRange(numbers, i + 1, numbers.length),
                          toArray(w));
      }// for
  }// testRemove()

  @Test
  public void testPut()
    throws Exception
  {
    WrappingQueue<Integer> w = new WrappingQueue<>(10);
    for (int i = 0; i < 10; i++)
      {
        w.put(numbers[i]);
        assertArrayEquals("Put test #" + i,
                          Arrays.copyOfRange(numbers, 0, 1 + i), toArray(w));
      }// for
  }// testRemove()

  @Test
  public void testWrapping()
    throws Exception
  {
    WrappingQueue<Integer> w = toQueue(numbers);
    for (int i = 0; i < 5; i++)
      w.get();
    for (int i = 0; i < 5; i++)
      {
        w.put(new Integer(i + 1));
      }//for
    Integer[] outOfOrder = new Integer[] {6,7,8,9,10,1,2,3,4,5};
    assertArrayEquals("Test wrapping", outOfOrder, toArray(w));
  }// testWrapping
  
  @Test 
  public void testCycle() throws Exception
  {
    WrappingQueue<Integer> w = toQueue(numbers);
    for (int i = 0; i < 50; i++)
      {
        w.put(w.get());
      }// for
    assertArrayEquals("Test Cycle", numbers, toArray(w));
  }//testCylce()
  
  
}//
