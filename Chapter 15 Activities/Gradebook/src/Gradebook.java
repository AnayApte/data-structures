import java.util.*;

/**
 * A program to add, remove, modify or print
 * student names and grades.
*/
public class Gradebook
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        Map<String, String> students = new HashMap<>();
        
        boolean done = false;
        while(!done)
        {
            System.out.println("A)dd R)emove M)odify P)rint Q)uit");
            String input = in.next().toUpperCase();
            if (input.equals("Q"))
            {
                done = true;
            } else if (input.equals("A"))
            {
                System.out.print("What is the name: ");
                String name = in.next();
                System.out.print("What is the grade: ");
                String grade = in.next();
                students.put(name, grade);

            } else if (input.equals("R"))
            {
                System.out.print("What is the name: ");
                String name = in.next();
                students.remove(name);
            } else if (input.equals("M"))
            {
                System.out.print("What is the name: ");
                String name = in.next();
                System.out.print("What is the new grade: ");
                String grade = in.next();
                students.put(name,grade);
            } else if (input.equalsIgnoreCase("P"))
            {
                Set<String> keys = students.keySet();
                ArrayList<String> arrayList = new ArrayList<>(keys);
                Collections.sort(arrayList);

                for(String name : arrayList)
                {
                    System.out.println(name +": "+students.get(name));
                }
            } else
            {
                done = true;
            }
        }
    }
}
