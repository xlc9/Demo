import java.io.File;
import java.util.Scanner;

public class Main {
    
    private static Scanner scnr = new Scanner(System.in);

    public static void main(String[] cheese)
    {
        KnightsOnAboard knight = new KnightsOnAboard();
        String fileName;
        File validated;
        do
        {
            System.out.println("Please input a valid file name: ");
            fileName = scnr.nextLine();
            File f = new File(fileName);
            validated = knight.validateFile(f);
            knight.validateData(validated);
        }while(!knight.validateData(validated));
        int[][] chessBoard = knight.populateBoard(validated);
        knight.printBoard(chessBoard);
        knight.cannotCapture(chessBoard);
    }
}

