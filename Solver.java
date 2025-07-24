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
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].isEmpty()) {
                    grid[i][j].isThatOnlyOne();
                }
            }
        }
        Solve(0);
    }

    public void Solve(int iterate){
        if(isSolved()) {
            System.out.println("Sudoku is already solved.");
            printGrid();
            return;
        }

        
        boolean progres = false;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].isEmpty()) {
                    progres = progres || grid[i][j].calculatePossibleValues();
                }
            }
        }

        

        if (progres) {
            Solve(0);
        } else {
            System.out.println("iterate: " + iterate);
            iterate++;
            if (iterate == 10) {
                SolveWithIsThatOnlyOne();
                System.out.println("No further progress can be made with current logic.");
                printGrid();
                for (Cell[] grid1 : grid) {
                    for (Cell item : grid1) {
                        if (item.isEmpty()) {
                            item.printPossibleValues();
                        } 
                    }
                    System.out.println();
                }
            }
            Solve(iterate);
        }
    }


}
