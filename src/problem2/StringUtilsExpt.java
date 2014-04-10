package problem2;

import java.io.PrintWriter;

/**
 * A simple experiment for StringUtils.
 * 
 * @author bashlovk
 * 
 */
public class StringUtilsExpt
{
  public static void main(String[] args)
    throws Exception
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    
    // Make an array of different strings
    String[] experimentalStrings = 
      {"(Hello) {world}",
       "{oh [boy] (I am having) (<so> much) fun matching `symbols'}",
       "([]",
       "(Hello (world))}  ]",
       "{oh [boy] (I am having) ([<so> much) fun matching `symbols'}",
       "((((((",
       "]{>`<(hi)``"
       };
    
    // Show matching symbols for each element of the array
    for (int i = 0; i < experimentalStrings.length; i++)
      {
        StringUtils.printChars('*', 20, pen);
        pen.println(" Experimental String #" + (i+1));
        StringUtils.showMatching(experimentalStrings[i], pen);
        pen.println();
      }//for

    pen.close();
  }// main
}// StringUtilsExpt class
