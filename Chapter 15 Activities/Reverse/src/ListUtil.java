import java.util.LinkedList;

/**
 * This class supplies a utility method to reverse the entries in a linked list.
*/
public class ListUtil
{
    /**
     * Reverses the elements in a linked list
     *
     * @param strings the linked list to reverse
    */
    public static void reverse(LinkedList<String> strings)
    {
        /*
        ListIterator<String> iterator = strings.listIterator(strings.size()-1);
        for(int i = 0; i < strings.size()-1; i++)
        {
            String n = iterator.previous();
            iterator.remove();
            strings.addLast(n);
        }
        */
        for(int i = 0; i < strings.size()/2; i++)
        {
            String temp = strings.get(i);
            strings.set(i, strings.get(strings.size()-1-i));
            strings.set(strings.size()-1-i,temp);
        }
    }
}