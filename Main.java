
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
//        Cell[][] grid = {{new Cell(8, 0, 0), new Cell(9, 0, 1), new Cell(4, 0, 2),
//                         new Cell(0, 0, 3), new Cell(0, 0, 4), new Cell(0, 0, 5),
//                         new Cell(0, 0, 6), new Cell(5, 0, 7), new Cell(1, 0, 8)},
//                         {new Cell(0, 1, 0), new Cell(0, 1, 1), new Cell(7, 1, 2),
//                         new Cell(0, 1, 3), new Cell(0, 1, 4), new Cell(3, 1, 5),
//                         new Cell(0, 1, 6), new Cell(6, 1, 7), new Cell(9, 1, 8)},
//                         {new Cell(0, 2, 0), new Cell(6, 2, 1), new Cell(0, 2, 2),
//                         new Cell(5, 2, 3), new Cell(0, 2, 4), new Cell(4, 2, 5),
//                         new Cell(0, 2, 6), new Cell(0, 2, 7), new Cell(0, 2, 8)},
//                         {new Cell(0, 3, 0), new Cell(3, 3, 1), new Cell(8, 3, 2),
//                         new Cell(4, 3, 3), new Cell(5, 3, 4), new Cell(1, 3, 5),
//                         new Cell(0, 3, 6), new Cell(0, 3, 7), new Cell(0, 3, 8)},
//                         {new Cell(2, 4, 0), new Cell(0, 4, 1), new Cell(0, 4, 2),
//                         new Cell(0, 4, 3), new Cell(0, 4, 4), new Cell(6, 4, 5),
//                         new Cell(8, 4, 6), new Cell(0, 4, 7), new Cell(5, 4, 8)},
//                         {new Cell(6, 5, 0), new Cell(0, 5, 1), new Cell(0, 5, 2),
//                         new Cell(0, 5, 3), new Cell(0, 5, 4), new Cell(2, 5, 5),
//                         new Cell(7, 5, 6), new Cell(0, 5, 7), new Cell(0, 5, 8)},
//                        {new Cell(3, 6, 0), new Cell(8, 6, 1), new Cell(0, 6, 2),
//                         new Cell(1, 6, 3), new Cell(7, 6, 4), new Cell(5, 6, 5),
//                         new Cell(0, 6, 6), new Cell(0, 6, 7), new Cell(0, 6, 8)},
//                         {new Cell(4, 7, 0), new Cell(0, 7, 1), new Cell(0, 7, 2),
//                         new Cell(3, 7, 3), new Cell(6, 7, 4), new Cell(9, 7, 5),
//                         new Cell(1, 7, 6), new Cell(0, 7, 7), new Cell(8, 7, 8)},
//                         {new Cell(0, 8, 0), new Cell(1, 8, 1), new Cell(0, 8, 2),
//                         new Cell(0, 8, 3), new Cell(0, 8, 4), new Cell(0, 8, 5),
//                         new Cell(5, 8, 6), new Cell(7, 8, 7), new Cell(0, 8, 8)}};


        Scanner scanner = new Scanner(System.in);
        Cell[][] grid = new Cell[9][9];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Cell(0, i, j); // Initialize each cell
                if (grid[i][j].isEmpty()) {
                    System.out.print("Enter value for cell (" + i + ", " + j + "): ");
                    int value = scanner.nextInt();
                    grid[i][j].setValue(value);
                    grid[i][j].setIsEmpty(value == 0);
                }
            }
        }

        Solver solver = new Solver(grid);

        solver.Solve();

    }
}