import java.util.TreeMap;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class MorseCode
{
    private static final char DOT = '.';
    private static final char DASH = '-';

    private static TreeMap<Character, String> codeMap;
    private static TreeNode decodeTree;

    public static void main(String[] args)
    {
        MorseCode.start();  
        System.out.println(MorseCode.encode("Watson come here"));
        BTreePrinter.printNode(decodeTree);
    }

    public static void start()
    {
        codeMap = new TreeMap<Character, String>();
        decodeTree = new TreeNode(' ', null, null);  // autoboxing
        // put a space in the root of the decoding tree

        addSymbol('A', ".-");
        addSymbol('B', "-...");
        addSymbol('C', "-.-.");
        addSymbol('D', "-..");
        addSymbol('E', ".");
        addSymbol('F', "..-.");
        addSymbol('G', "--.");
        addSymbol('H', "....");
        addSymbol('I', "..");
        addSymbol('J', ".---");
        addSymbol('K', "-.-");
        addSymbol('L', ".-..");
        addSymbol('M', "--");
        addSymbol('N', "-.");
        addSymbol('O', "---");
        addSymbol('P', ".--.");
        addSymbol('Q', "--.-");
        addSymbol('R', ".-.");
        addSymbol('S', "...");
        addSymbol('T', "-");
        addSymbol('U', "..-");
        addSymbol('V', "...-");
        addSymbol('W', ".--");
        addSymbol('X', "-..-");
        addSymbol('Y', "-.--");
        addSymbol('Z', "--..");
        addSymbol('0', "-----");
        addSymbol('1', ".----");
        addSymbol('2', "..---");
        addSymbol('3', "...--");
        addSymbol('4', "....-");
        addSymbol('5', ".....");
        addSymbol('6', "-....");
        addSymbol('7', "--...");
        addSymbol('8', "---..");
        addSymbol('9', "----.");
        addSymbol('.', ".-.-.-");
        addSymbol(',', "--..--");
        addSymbol('?', "..--..");
    }

    /**
     * Inserts a letter and its Morse code string into the encoding map
     * and calls treeInsert to insert them into the decoding tree.
     */
    private static void addSymbol(char letter, String code)
    {
        codeMap.put(letter, code); // Calls the codeMap TreeMap and assigns the code value for each letter.
        treeInsert(letter, code); // Calls on the treeInsert method.
    }

    /**
     * Inserts a letter and its Morse code string into the
     * decoding tree.  Each dot-dash string corresponds to a path
     * in the tree from the root to a node: at a "dot" go left, at a "dash" go
     * right.  The node at the end of the path holds the symbol
     * for that code string.
     */
    private static void treeInsert(char letter, String code)
    {
        TreeNode root = decodeTree; // Starts the root at decodeTree. Changes with each subtree.
        TreeNode child; // Child node for subtrees.
        for(int i = 0; i < code.length(); i++)
        {
            String sequence = code.substring(i,i+1); // Takes each individual character.
            if(sequence.equals(".")) // If the character equals a dot, we go left 
            {
                if(root.getLeft() == null) // Checks if there is a child on the left branch, if not we create one.
                {
                    root.setLeft(new TreeNode(' '));
                }
                child = root.getLeft();
            }
            else // If the character equals a dash, we go right.
            {
                if(root.getRight() == null) // Checks if there is a child on the right branch, if not we create one.
                {
                    root.setRight(new TreeNode(' '));
                }
                child = root.getRight();
            }
            TreeNode temp = child;
            root = temp;
            child = null; 
        }
        root.setValue(letter); // Quick swap ran above to set the root as the value of the letter (so we can decode later).
    }

    /**
     * Converts text into a Morse code message.  Adds a space after a dot-dash
     * sequence for each letter.  Other spaces in the text are transferred directly
     * into the encoded message.
     * Returns the encoded message.
     */
    public static String encode(String text)
    {
        StringBuffer morse = new StringBuffer(400);
        text = text.toUpperCase(); // MUST CONVERT TO UPPER CASE SINCE OUR MAP HAS UPPER CASE CHARS.
        for(int i = 0; i < text.length(); i++)
        {
            if(text.charAt(i) == ' ') // If we have a space in our sequence, we only append one space to the morse code. This is because each individual letter also adds one.
            {
                morse.append(" ");
            }
            else
            {
                String toAppend = codeMap.get(text.charAt(i)) + " "; // Uses map to get code for each letter plus space.
                morse.append(toAppend);
            }
        }
        return morse.toString();
    }

    /**
     * Converts a Morse code message into a text string.  Assumes that dot-dash
     * sequences for each letter are separated by one space.  Additional spaces are
     * transferred directly into text.
     * Returns the plain text message.
     */
    public static String decode(String morse)
    {
        StringBuffer text = new StringBuffer(100);
        int index = 0;
        TreeNode current = decodeTree;
        while(index < morse.length())
        {
            if(morse.charAt(index) == ' ' && index != morse.length() - 1) // Must have the second condition since we go out of bounds.
            {
                text.append(current.getValue());
                current = decodeTree; // resets tree so we can go through new set of values for morse code decoding.
                index++;
                if(morse.charAt(index) == ' ') // checks if there is a series of double spaces. If there are, we append a space, if not we simply remove an index so we don't double add.
                {
                    text.append(" ");
                }
                else
                {
                    index--; 
                }
            }
            else if(morse.charAt(index) == ' ' && index == morse.length()-1) // Case if it's going to go out of bounds.
            {
                text.append(current.getValue());
                current = decodeTree;
            }
            else if(morse.charAt(index) == '.') // Case if we have a dot.
            {
                current = current.getLeft();
            }
            else if(morse.charAt(index) == '-') // Case if we have a dash.
            {
                current = current.getRight();
            }
            index++;
        }
        return text.toString();
    }
}

/**
 * BTreePrinter class courtesy of Karen Ge (@karenge1)
 */
class BTreePrinter {

    public static void printNode(TreeNode root) {
        int maxLevel = BTreePrinter.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static void printNodeInternal(List<TreeNode> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        BTreePrinter.printWhitespaces(firstSpaces);

        List<TreeNode> newNodes = new ArrayList<TreeNode>();
        for (TreeNode node : nodes) {
            if (node != null) {
                System.out.print(node.getValue());
                newNodes.add(node.getLeft());
                newNodes.add(node.getRight());
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            BTreePrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                BTreePrinter.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).getLeft() != null)
                    System.out.print("/");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(i + i - 1);

                if (nodes.get(j).getRight() != null)
                    System.out.print("\\");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static int maxLevel(TreeNode node) {
        if (node == null)
            return 0;

        return Math.max(BTreePrinter.maxLevel(node.getLeft()), 
            BTreePrinter.maxLevel(node.getRight())) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }
}

