import java.util.Scanner;
import java.util.Stack;

/**
 * Class for reversing the order of a sentence.
*/
public class SentenceReverser
{
    /**
     * Reverses the given sentence.
     *
     * @param sentence Sentence to be reversed.
     * @return reversed sentence.
    */
    public static String reverse(String sentence)
    {
    	Scanner scanner = new Scanner(sentence);

        Stack <String> words = new Stack<>();
        
        while(scanner.hasNext())
        {
            while(!scanner.next().contains("."))
            {
                
            }
        }
        
        return;
    }
}
