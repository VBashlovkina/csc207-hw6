package problem3;

import java.io.PrintWriter;
import java.util.Iterator;

public class RPNCalculator
{

  /**
   * Evaluate the expression using the given operand
   * 
   * @pre the two top elements on the stack are reals
   * @post the two elements are popped
   * @post the result of the operation is pushed onto the stack
   * @param ch
   * @throws Exception
   */
  static void evaluate(char ch)
    throws Exception
  {
    double num1 = RPNStack.s.pop();
    double num2 = RPNStack.s.pop();
    switch (ch)
      {
        case '+':
          RPNStack.s.push(new Double(num1 + num2));
          break;
        case '-':
          RPNStack.s.push(new Double(num2 - num1));
          break;
        case '*':
          RPNStack.s.push(new Double(num2 * num1));
          break;
        case '/':
          RPNStack.s.push(new Double(num2 / num1));
          break;
        default:
          throw new Exception();
      }// switch
  }// evaluate (char)

  /**
   * Print the top value of the stack
   * 
   * @pre !RPNStack.s.isEpmty()
   * @post RPNStack.s is unchanged
   * 
   */
  static void top(PrintWriter pen)
  {
    if (!RPNStack.s.isEmpty())
      pen.println(RPNStack.s.peek());
  }// top()

  /**
   * Print out the stack
   * 
   * @pre none
   * @post the stack is unchanged
   * @param pen
   */
  static <T> void print(PrintWriter pen)
  {
    @SuppressWarnings("unchecked")
    Iterator<T> it = (Iterator<T>) RPNStack.s.iterator();
    while (it.hasNext())
      {
        pen.println(it.next());
      }// while not empty
  }// print(PrintWriter)

  /**
   * Clear the stack
   * 
   * @pre none
   * @post RPNStack.s.isEmpty() == true
   */
  static void clear()
  {
    while (!RPNStack.s.isEmpty())
      {
        RPNStack.s.pop();
      }// while not empty
  }// clear

  /**
   * Remove the top of the stack
   * 
   * @pre !s.isEmpty()
   * @post the top element is popped
   */
  static void forget()
  {
    if (!RPNStack.s.isEmpty())
      RPNStack.s.pop();
  }// forget()
}// RPNCalculator class
