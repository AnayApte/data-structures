import java.util.*;

public class Grid
{
    private static final int SIZE = 10;
    int[][] pixels = new int[SIZE][SIZE];
    Stack<Pair> pairs = new Stack<>();

    /**
     * Flood fill, starting with the given row and column.
    */
    public void floodfill(int row, int column)
    {
        Pair first = new Pair(row, column);
        int count = 1;
        pairs.push(first);
        while(pairs.size()>0)
        {
            Pair pair = pairs.pop();
            if(pixels[pair.getRow()][pair.getColumn()] == 0)
            {
                pixels[pair.getRow()][pair.getColumn()] = count;
                count++;
            }

            if(pair.getRow()-1 >= 0 && pair.getRow()-1 < SIZE && pair.getColumn() >= 0 && pair.getColumn() < SIZE && pixels[pair.getRow()-1][pair.getColumn()] == 0)
            {
                pairs.push(new Pair(pair.getRow()-1,pair.getColumn()));
            }
            if(pair.getRow() >= 0 && pair.getRow() < SIZE && pair.getColumn() + 1 >= 0 && pair.getColumn() + 1 < SIZE && pixels[pair.getRow()][pair.getColumn()+1] == 0)
            {
                pairs.push(new Pair(pair.getRow(),pair.getColumn()+1));
            }
            if(pair.getRow() + 1 >= 0 && pair.getRow() + 1 < SIZE && pair.getColumn() >= 0 && pair.getColumn() < SIZE && pixels[pair.getRow()+1][pair.getColumn()] == 0)
            {
                pairs.push(new Pair(pair.getRow() + 1,pair.getColumn()));
            }
            if(pair.getRow() >= 0 && pair.getRow() < SIZE && pair.getColumn() - 1 >= 0 && pair.getColumn() - 1 < SIZE && pixels[pair.getRow()][pair.getColumn()-1] == 0)
            {
                pairs.push(new Pair(pair.getRow(),pair.getColumn()-1));
            }
        }
    }

    public String toString()
    {
        String r = "";
        for (int i = 0; i < SIZE; i++)
        {
            for (int j = 0; j < SIZE; j++)
                r = r + String.format("%3d", pixels[i][j]);
            r = r + "\n";
        }
        return r;
    }
}
