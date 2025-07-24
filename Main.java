
import java.io.File;
import java.util.Scanner;



public class Main {

    public static void main(String[] args) {

        File file = new File("map.txt");
        Cell[][] grid = new Cell[9][9];
        try {
            Scanner scanner = new Scanner(file);
            for (int i = 0; i < 9; i++) {
                String line = scanner.nextLine();
                for (int j = 0; j < 9; j++) {
                    int value = Character.getNumericValue(line.charAt(j));
                    grid[i][j] = new Cell(value, i, j);
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }

        Solver solver = new Solver(grid);

        solver.Solve(0);

    }
}