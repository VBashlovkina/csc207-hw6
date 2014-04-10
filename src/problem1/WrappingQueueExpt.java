package problem1;

/**
 * A really simple experiment with WrappingQueues
 * based entirely on ArrayBasedQueueExpt form the
 * lab on Linear Structures by Samuel A. Rebelsky.
 *
 */
public class WrappingQueueExpt
{
  /**
   * Do all the work.  (Well, make the helpers do all the work.)
   */
  public static void main(String[] args)
    throws Exception
  {
    ReportingLinearStructure<String> expt =
        new ReportingLinearStructure<String>(new WrappingQueue<String>(8),
                                             "expt.");
    
    // Put in a few values, then remove two
    expt.isEmpty();
    expt.put("a");
    expt.peek();
    expt.put("b");
    expt.peek();
    expt.put("c");
    expt.peek();
    expt.get();
    expt.peek();
    expt.get();
    expt.put("x");
    expt.peek();
    expt.get();
    expt.get();
    // We've put and remove the same number of elements, it
    // should be empty
    expt.isEmpty();

    
            // It's always good to see what happens after you've cleared
            // out a structure.  So add a few more elements.
            // We'll leave this test until we've worked out some kinks (Dave?)
            expt.put("e");
            expt.peek();
            expt.put("f");
            expt.get();
            expt.peek();
            expt.put("g");
            expt.get();
            expt.get();
            // And we're back down to the empty queue
            expt.isEmpty();
     

    
            // An iteration test, once we've workd out some kinks (Ray?)
            expt.put("a");
            expt.put("b");
            expt.put("c");
            expt.isFull();
            for (String s : expt) {
               System.out.println(s);
            } // for
            expt.get();
            expt.get();
            expt.isFull();
            expt.put("d");
            expt.put("e");
            expt.isFull();
            expt.get();
            expt.get();
            expt.get();

  } // main(String[])
} // class ArrayBasedQueueExpt
