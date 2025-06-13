
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Cell[][] cells = new Cell[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j]= new Cell(scan.nextInt());        
            }
        }

        Cell[][] cellsfixed = {{new Cell(9),new Cell(4),new Cell(7),new Cell(0),new Cell(0),new Cell(0),new Cell(4),new Cell(4),new Cell(4)}};

    

        SudokuMap mainMap = new SudokuMap(null,0,cells);

        Thread mainThread = new Thread(mainMap);
        mainThread.start();

        while (true) { 
            
        }
    }
    
}
