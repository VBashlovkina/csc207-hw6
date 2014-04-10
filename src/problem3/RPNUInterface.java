package problem3;

import java.io.PrintWriter;

/**
 * User Interface for reverse Polish notation calculator
 * 
 * Drawing a lot from Homework 4 Calculator.java (evaluate method) and
 * Interface.java authored by Daniel Nanetti-Palacios and Vasilisa Bashlovkina
 * Oracle documentation for String, Character
 * 
 */

public class RPNUInterface
{

  /**
   * User interface
   * 
   * Commands(descriptions taken from the problem):
   *  +, -, *, / - the standard
   * arithmetic operators; 
   * p - print the top value on the stack; 
   * s - print the whole stack; 
   * c - clear the stack; 
   * f - forget the last operation (removes the top of the stack); 
   * q - quit;
   * 
   * 
   * @pre user input must involve the allowed commands and real numbers
   * @pre operands are applied to the two preceding numbers: "2 3 +" is 2+3
   * @post the operations are executed
   */
  public static void main(String[] args)
    throws Exception
  {
    // Initializing tools
    PrintWriter pen = new PrintWriter(System.out, true);
    java.io.InputStreamReader istream =
        new java.io.InputStreamReader(System.in);
    java.io.BufferedReader eyes = new java.io.BufferedReader(istream);

    // Initializing variables
    String input = "";
    char cur;
    String[] parsed;

    // Read-Eval-Print Loop
    while (true)
      {
        input = eyes.readLine();
        parsed = input.split(" ");
        // Deal with each element in the array
        for (int i = 0; i < parsed.length; i++)
          {
            if (!Character.isDigit(cur = parsed[i].charAt(0)))
              {
                switch (cur)
                  {
                  // Evaluate the operands
                    case '+':
                    case '-':
                    case '*':
                    case '/':
                      RPNCalculator.evaluate(cur);
                      break;
                    case 'p': // Print the top
                      RPNCalculator.top(pen);
                      break;
                    case 's': // Print the whole stack
                      RPNCalculator.print(pen);
                      break;
                    case 'c': // Clear the stack
                      RPNCalculator.clear();
                      break;
                    case 'f': // Forget the previous result
                      RPNCalculator.forget();
                      break;
                    case 'q': // Quit
                      pen.close();
                      eyes.close();
                      istream.close();
                      return;
                    default:
                      pen.println("Gotta follow the rules");
                      break;
                  }// switch
              }// if the first thing is not a digit
            else
              {
                try
                  {
                    RPNStack.s.push(new Double(parsed[i]));
                  }// try
                catch (Exception e)
                  {
                    pen.println("Invalid input");
                  }// catch
              }// else it must be a number

          }// for
      } // while(true)

  } // main
}// RPNUInterface class
