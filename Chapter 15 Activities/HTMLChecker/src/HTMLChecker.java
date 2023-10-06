import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;
/**
 * Write a program that checks whether a sequence of HTML tags
 * is properly nested. For each opening tag, such as <p>, there
 * must be a closing tag </p>. A tag such as <p> may have other
 * tags inside, for example <p> <ul> <li> </li> </ul> <a> </a> </p>
 * <p>
 * The inner tags must be closed before the outer ones.
 * Your program should process a file containing tags.
 * For simplicity, assume that the tags are separated by
 * spaces, and that there is no text inside the tags.
*/
public class HTMLChecker
{
    public static void main(String[] args)
    {
        //String filename = "Chapter 15 Activities\\HTMLChecker\\src\\TagSample1.html";
        //String filename = "Chapter 15 Activities\\HTMLChecker\\src\\TagSample2.html";
        String filename = "Chapter 15 Activities\\HTMLChecker\\src\\TagSample3.html";

        try (Scanner in = new Scanner(new File(filename)))
        {
            Stack<String> tags = new Stack<>();
            String tag = in.next();
            tags.push(tag);
            while(in.hasNext())
            {
                tag = in.next();
                if(tag.contains(tags.lastElement().substring(1,tags.lastElement().length())) && tag.contains("/"))
                {
                    tags.pop();
                }
                else
                {
                    tags.push(tag);
                }
            }

            if(tags.size() > 0)
                System.out.println("HTML sequence improperly nested.");
            else
                System.out.println("HTML sequence properly nested.");

        } catch (FileNotFoundException e)
        {
            System.out.println("Cannot open: " + filename);
        }

    }
}
