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
        int count = 1;
        Pair first = new Pair(row, column);
        pairs.push(first);

        while (pairs.size() > 0){
            Pair p = pairs.pop();
            if (pixels[p.getRow()][p.getColumn()] == 0){
                pixels[p.getRow()][p.getColumn()] = count;
                count++;
            }

            if (p.getRow() + 1 < 10 && pixels[p.getRow() + 1][p.getColumn()] == 0){
                pairs.push(new Pair(p.getRow() + 1, p.getColumn()));
            }
            if (p.getColumn() + 1 < 10 && pixels[p.getRow()][p.getColumn() + 1] == 0){
                pairs.push(new Pair(p.getRow(), p.getColumn() + 1));
            }
            if (p.getRow() - 1 > -1 && pixels[p.getRow() - 1][p.getColumn()] == 0){
                pairs.push(new Pair(p.getRow() - 1, p.getColumn()));
            }
            if (p.getColumn() - 1 > -1 && pixels[p.getRow()][p.getColumn() - 1] == 0){
                pairs.push(new Pair(p.getRow(), p.getColumn() - 1));
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
