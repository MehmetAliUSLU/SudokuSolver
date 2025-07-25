public class Solver
{

    public static Cell[][] grid;

    public Solver(Cell[][] grid) {
        Solver.grid = grid;
    }


    public void printGrid() {
        for (Cell[] grid1 : grid) {
            for (Cell item : grid1) {
                System.out.print(item.getValue() + " ");
            }
            System.out.println();
        }
    }

    public boolean isSolved() {
        for (Cell[] grid1 : grid) {
            for (Cell item : grid1) {
                if (item.isEmpty()) {
                    return false; // If any cell is empty, the Sudoku is not solved
                }
            }
        }
        return true; // All cells are filled
    }

    public void SolveWithIsThatOnlyOne() {
        boolean isProgress = false;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].isEmpty()) {
                    System.out.println("" + i + " " + j + " is empty, trying to solve with isThatOnlyOne.");
                    isProgress = isProgress || grid[i][j].isThatOnlyOne();
                    // Debug print removed for production use
                }
            }
        }
        if (isProgress) {
            Solve(0);
        }
    }

    public void Solve(int iterate){
        if(isSolved()) {
            System.out.println("Sudoku is already solved.");
            printGrid();
            return;
        }

        
        boolean progress = false;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].isEmpty()) {
                    progress = progress || grid[i][j].calculatePossibleValues();
                }
            }
        }

        

        if (progress) {
            System.out.println(iterate + " iterations with progress.");
            Solve(0);
        } else {
            if (iterate > 3 && iterate < 5) {
                System.out.println(iterate + " iterations without progress, trying to solve with isThatOnlyOne.");
                SolveWithIsThatOnlyOne();

            }else if(iterate > 5) {
                System.out.println("No further progress can be made with current logic.");
                printGrid();
                int solvedCount = 0;
                for (Cell[] grid1 : grid) {
                    for (Cell item : grid1) {
                        if (item.isEmpty()) {
                            item.printPossibleValues();
                        } else {
                            solvedCount++;
                        }
                    }
                    System.out.println();
                }
                System.out.println("Solved cells: " + solvedCount);
                return;
            }

            iterate++;
            Solve(iterate);
        }
    }


}
