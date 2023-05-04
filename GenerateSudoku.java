import java.util.*;

public class GenerateSudoku
{
    int SIZE = 9;
    int GRID = 3;
    int[][] arr;
    
    public GenerateSudoku()
    {
        this.arr = new int[SIZE][SIZE];
    }
    
    void fillGrids()
    {
        // Filling 3 diagonal 3x3 grids first
        Diagonal3Grids();
        
        //Filling remaining 6 3x3 grids. Recursive Method
        RemainingGrids(0, GRID);
    }
    
    void Diagonal3Grids()
    {
        for(int i = 0; i < SIZE; i = i + GRID)
        {
            fill3Grid(i, i);
        }
    }
    
    void fill3Grid(int row, int col)
    {
        Random rand = new Random();
        int num;
        for(int i = 0; i < GRID; i++)
        {
            for(int j = 0; j < GRID; j++)
            {
                num = rand.nextInt(9) + 1;
                do
                {
                    num = rand.nextInt(9) + 1;
                }
                while (!isValidGrid(row, col, num));
                arr[row + i][col + j] = num;
            }
        }
    }
    
    boolean isValidGrid(int row, int col, int num)
    {
        for (int i = 0; i < GRID; i++)
        {
            for (int j = 0; j < GRID; j++)
            {
                if (arr[row + i][col + j] == num)
                {
                    return false;
                }
            }
        }
        return true;
    }
    
    boolean isValidRow(int row, int num)
    {
        for (int i = 0; i < SIZE; i++)
        {
            if (arr[row][i] == num)
            {
                return false;
            }
        }
        return true;
    }
    
    boolean isValidCol(int col, int num)
    {
        for (int i = 0; i < SIZE; i++)
        {
            if (arr[i][col] == num)
            {
                return false;
            }
        }
        return true;
    }
    
    boolean CheckIfValid(int row,int col,int num)
    {
        if((isValidRow(row, num) && isValidCol(col, num) && isValidGrid(row - row % GRID, col - col % GRID, num)))
        {
            return true;
        }
        return false;
    }
    
    boolean RemainingGrids(int i, int j)
    {
        if (j >= SIZE && i < SIZE - 1)  //If Column pointer reaches end
        {
            i = i + 1;
            j = 0;                      // Starting from 0 again
        }
    
        if (i >= SIZE && j >= SIZE)    // If both Column and row Pointers reaches end
        {
            return true;
        }
        
        if (i < GRID)
        {
            if (j < GRID)
            {
                j = GRID;
            }
        }
        else if (i < SIZE - GRID)
        {
            if (j == (int)(i / GRID) * GRID)
            {
                j = j + GRID;
            }
        }
        else
        {
            if (j == SIZE - GRID)
            {
                i = i + 1;
                j = 0;
                if (i >= SIZE)
                {
                    return true;
                }
            }
        }
    
        for (int num = 1; num <= SIZE; num++)
        {
            if (CheckIfValid(i, j, num))
            {
                arr[i][j] = num;
                if (RemainingGrids(i, j+1))
                {
                    return true;
                }
                arr[i][j] = 0;
            }
        }
        return false;
    }
    
    void print()
    {
        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        return;
    }
        
    public static void main(String[] args)
    {
    GenerateSudoku sudo1 = new  GenerateSudoku();
    sudo1.fillGrids();
    sudo1.print();
        
     }
}
