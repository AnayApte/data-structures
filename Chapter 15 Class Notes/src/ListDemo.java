import java.util.LinkedList;
import java.util.ListIterator;

/**
 * This program demonstrates the LinkedList class
 * and ListIterator class.
*/
public class ListDemo
{
    public static void main(String[] args)
    {
        /* The addLast method can populate a list. */
        LinkedList<String> staff = new LinkedList<>();
        staff.addLast("Tony");
        staff.addLast("Natasha");
        staff.addLast("Peter");
        staff.addLast("Gamora");

        // The list is currently: TNPG
        /*
        The listIterator method creates a new list iterator that is positioned at the head. 
        The "|" to represent the iterator position.
        */
       ListIterator<String> iterator = staff.listIterator(); // |TNPG
       /* The next method advances the iterator over the next element in the list. */
       iterator.next(); // T|NPG

       /* The next method also returns the element that the iterator is passing.  */
       String avenger = iterator.next(); // TN|PG
       System.out.println(avenger);
       System.out.println("Expected: Natasha");
       
       /* The add method inserts an element at the iterator position.
        *The iterator is then positioned after the element that is added. 
        */
        iterator.add("Bruce"); // TNB|PG
        iterator.add("Rocket"); // TNBR|PG

        /* The remove method removes the element returned by the last call
         * to next or previous.
         * The remove method can ONLY be called after calling next or previous.
         * The remove method CANNOT be called after calling add.
         */
        iterator.next(); // TNBRP|G
        iterator.remove(); // TNBR|G

        System.out.println(staff);

        /* The set method updates the element returned by the last call to
         * next or previous.
         */
        iterator.previous(); // TNB|RG
        iterator.set("Scott"); // TNB|SG

        /* The hasNext method is used to determine if there is a next node
        after the iterator. 
        The hasNext is often used in the condition of a while loop. */
        iterator = staff.listIterator(); // |TNBSG
        while(iterator.hasNext())
        {
            String n = iterator.next();
            if(n.equals("Natasha")) // TN|BSG
            {
                iterator.remove(); // T|BSG
            }
        }                          // TBSG|

        /* Enhanced for loops work with linked lists! */
        for(String n : staff)
        {
            System.out.print(n+" ");
        }
        System.out.println();
        System.out.println("Expected: Tony Bruce Scott Gamora");

        /* 
         * ConcurrentModificationException
         * 
         * CANNOT modify a linked list while also using an interator
         * UNLESS you use the iterator to do so
         */
        iterator = staff.listIterator();
        while(iterator.hasNext())
        {
            String n = iterator.next();
            if(n.equals("Scott"))
            {
                staff.remove("Scott");
            }
        }

        /* 
           ConcurrentModificationException

         * The enhanced for loop AUTOMATICALLY creates an iterator
         */
        for(String n : staff)
        {
            if(n.equals("Bruce"))
            {
                staff.add("Rocket");
            }
        }
    }
}
