import java.util.Scanner;
import java.io.*;


public class KnightsOnAboard {

    public KnightsOnAboard() //This is the constructor
    {
      // TODO document why this constructor is empty
    }

    public File validateFile(File inputFile)
    {
        String fileName;
        //determine if file exists
        if(inputFile.isFile())
        {
            return inputFile;
        }
        else
        {
            System.out.println("File does not exist!");
            try (Scanner scnr = new Scanner(System.in)) {
                System.out.println("Please enter a valid file name: ");
                fileName = scnr.nextLine();
            }
            File f = new File(fileName);
            return validateFile(f);
            
        }

    }

    public boolean validateData(File inputFile)
    {
        try
        {
            Scanner scnr = new Scanner(inputFile);
            int count = 0;
            while(scnr.hasNext())
            {
                scnr.nextInt();
                count++;
            
                if(count == 64) // if there is more or less than 64 then invalid data
                  {
                    scnr.close();
                    return true;
                }
        }
            if(count != 64)
            {
                System.out.println("File has invalid data.");
                return false;
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }

    public int[][] populateBoard(File inputFile)
    {
        int[][] grid = new int[8][8];
        int temp = 0;
        boolean isFixed = false;
        try
        {
            Scanner inFile = new Scanner(inputFile);
            while(inFile.hasNextInt())
            {
                for(int i = 0; i < 8; i++)
                {
                    for(int j = 0; j< 8; j++)
                    {
                        temp = inFile.nextInt();
                        if(temp < 0)
                        {
                            temp = 0;
                            isFixed = true;
                        }
                        else if(temp > 1)
                        {
                            temp = 1;
                            isFixed = true;
                        }
                        grid[i][j] = temp;
                    }
                }
            }
            if(isFixed) // to determine if a message is printed
                System.out.println("Invalid data was corrected.");
            inFile.close();
            return grid;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return grid;
        }

    }	

    public boolean cannotCapture(int[][] chessBoard)
    {	// arrays that have the possible vertical and horizontal movements
        int[] vert = new int[] {1,2,2,1,-1,-2,-2,-1};
        int[] hor = new int[] {-2,-1,1,2,2,1,-1,-2};
        for(int row = 0; row < chessBoard.length; row++)
        {
            for(int col = 0; col < chessBoard[0].length; col++)
            {
                for(int i = 0; i < 8; i++)
                {	// if statements to determine if move is in bounds
                    if((vert[i] + row) >= 0 && (vert[i]+row) < 8)
                    {
                        if((hor[i] +col) >=0 && (hor[i] + col) < 8)
                        {
                            if( chessBoard[row][col] == 1 && chessBoard[vert[i]+row][hor[i]+col] != 0) // check if knight is on a possible move
                            {
                                System.out.println("There is at least one knight which can capture another knight.");
                                return false;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("No knights can capture any other knight.");
        return true; // if reaches this point then knight can't capture other knights
    }

    public void printBoard(int[][] chessBoard)
    {
        System.out.println("The board looks as follows:");
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                System.out.print(chessBoard[i][j] + " ");
            }
            System.out.println();
        }
    }
}
