
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;



public class Main {

    public static void main(String[] args) {

        File logfile = new File("log.txt");
        try {
            
            PrintStream fos = new PrintStream(logfile);
            
            System.setOut(fos);
            System.setErr(fos);
        } catch (Exception e) {
            // TODO: handle exception
        }


        Solver solver = new Solver();
        File file = new File("maps/map3.txt");
        Cell[][] grid = new Cell[9][9];
        try {
            Scanner scanner = new Scanner(file);
            for (int i = 0; i < 9; i++) {
                String line = scanner.nextLine();
                for (int j = 0; j < 9; j++) {
                    int value = Character.getNumericValue(line.charAt(j));
                    grid[i][j] = new Cell(value, i, j, solver);
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }

        solver.setGrid(grid);
        try {
            System.out.println(solver.Solve());
        } catch (StackOverflowError e) {
            solver.printGrid();
        }

    }
}