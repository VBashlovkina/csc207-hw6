package problem2;

import java.io.PrintWriter;

/**
 *  checkMatching(String) from lab on stacks by Tyler Dewey 
 *  and Vasilisa Bashlovkina 
 */
import java.util.Stack;

public class StringUtils
{
  /**
   * Print out the matched and unmatched parens, braces, brackets, angle
   * brackets and single quotes in a string.
   * 
   * @param str
   *          the string to be checked
   * @pre none
   * @post the string and the matching are printed out
   * @throws Exception
   */

  public static void showMatching(String str, PrintWriter pen)
  {
    char c;
    Box b = null;
    Stack<Box> openParenStack = new Stack<>();
    // Print out the original string
    pen.println(str);
    // Go through the string char by char looking for supported symbols
    for (int i = 0; i < str.length(); i++)
      {
        switch ((c = str.charAt(i)))
          { // Push all opening symbols on the stack
            case '(':
            case '{':
            case '[':
            case '<':
            case '`':
              openParenStack.push(new Box(i, c));
              break;
            // Deal with closing symbols
            case ')':
              if (openParenStack.isEmpty())
                {// if the stack is empty, the current char is unmatched
                  printUnmatch(')', i, pen);
                }// if empty
              else if ((b = openParenStack.pop()).ch != '(')
                {// if the previous opening char doesn't match the current
                 // both the current and the previous are unmatched
                  printUnmatch(b.ch, b.in, pen);
                  printUnmatch(')', i, pen);
                }// if mismatch
              else
                {// otherwise, everything matches
                  printMatch('(', ')', b.in, i, pen);
                }// else match
              break;
            case '}':
              if (openParenStack.isEmpty())
                {
                  printUnmatch('}', i, pen);
                }// if empty
              else if ((b = openParenStack.pop()).ch != '{')
                {
                  printUnmatch(b.ch, b.in, pen);
                  printUnmatch('}', i, pen);
                }// if current and previous don't match
              else
                {
                  printMatch('{', '}', b.in, i, pen);
                }// else
              break;
            case ']':
              if (openParenStack.isEmpty())
                {
                  printUnmatch(']', i, pen);
                }// if empty
              else if ((b = openParenStack.pop()).ch != '[')
                {
                  printUnmatch(b.ch, b.in, pen);
                  printUnmatch(']', i, pen);
                }// if current and previous don't match
              else
                {
                  printMatch('[', ']', b.in, i, pen);
                }// else
              break;
            case '>':
              if (openParenStack.isEmpty())
                {
                  printUnmatch('>', i, pen);
                }// if empty
              else if ((b = openParenStack.pop()).ch != '<')
                {
                  printUnmatch(b.ch, b.in, pen);
                  printUnmatch('>', i, pen);
                }// if current and previous don't match
              else
                {
                  printMatch('<', '>', b.in, i, pen);
                }// else
              break;
            case '\'':
              if (openParenStack.isEmpty())
                {
                  printUnmatch('\'', i, pen);
                }// if empty
              else if ((b = openParenStack.pop()).ch != '`')
                {
                  printUnmatch(b.ch, b.in, pen);
                  printUnmatch('\'', i, pen);
                }// if current and previous don't match
              else
                {
                  printMatch('`', '\'', b.in, i, pen);
                }// else
              break;
            default:
              break;
          }// switch
      }// for

    // All opening symbols left on the stack are unmatched
    while (!openParenStack.isEmpty())
      {
        b = openParenStack.pop();
        printUnmatch(b.ch, b.in, pen);
      }// while

  }// showMatching(String)

  /**
   * Print out matching symbols.
   * 
   * @param opening
   *          opening character
   * @param closing
   *          closing character
   * @param start
   *          index of the opening character in the original string
   * @param end
   *          index of the closing character in the original string
   * @param pen
   * 
   */
  static void printMatch(char opening, char closing, int start, int end,
                         PrintWriter pen)
  {
    printChars(' ', start, pen);
    pen.print(opening);
    printChars('-', end - start - 1, pen);
    pen.println(closing);
  }// printMatch(char, char, int, int, PrintWriter)

  /**
   * Print out unmatched symbols
   * 
   * @param ch
   *          , the unmatched char
   * @param in
   *          , the position of ch in the string
   */
  static void printUnmatch(char ch, int in, PrintWriter pen)
  {
    printChars(' ', in, pen);
    pen.println(ch + " <- UNMATCHED");
  }// printUnmatch(char, int, PrintWriter)

  /**
   * Print the right number of chars
   * 
   * @param ch
   *          , the char to be printed
   * @param num
   *          , hom many times it needs to be printed
   */
  static void printChars(char ch, int num, PrintWriter pen)
  {
    for (int i = 0; i < num; i++)
      {
        pen.print(ch);
      }// for
  }// printChars

  // Helper class

  static class Box
  {
    // +--------+----------------------------------------------------------
    // | Fields |
    // +--------+
    /**
     * Index
     */
    int in;
    /**
     * Corresponding character
     */
    char ch;

    // +-------------+----------------------------------------------------
    // | Constructor |
    // +-------------+
    Box(int index, char character)
    {
      this.in = index;
      this.ch = character;
    }// Box (int, char)
  }// Box class

} // StringUtils class
