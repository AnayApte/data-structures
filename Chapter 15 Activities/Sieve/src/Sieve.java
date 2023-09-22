import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;


/**
 * A program that implements the sieve of Eratosthenes.
*/
public class Sieve
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Compute primes up to which integer?");
        int n = in.nextInt();

        Set<Integer> numbers = new HashSet<>();

        // Your work goes here
        for (int i = 2; i < n; i++)
        {
            numbers.add(i);
        }

        Iterator<Integer> iterator = numbers.iterator();
        int num2;

        for (int x = 2; x < n/2; x++)
        {
            iterator = numbers.iterator();
            for (int y = 2; y <= numbers.size()+1; y++){
                
                num2 = iterator.next();
                if(num2 %x==0 && num2!=x)
                    iterator.remove();}
        }
        System.out.println(numbers);
    }
}
