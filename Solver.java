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

    public void Solve(){
        if(isSolved()) {
            System.out.println("Sudoku is already solved.");
            printGrid();
            return;
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j].calculatePossibleValues();
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].isEmpty() && grid[i][j].possibleValues.size() == 1) {

                    grid[i][j].setValue(grid[i][j].possibleValues.get(0));
                    grid[i][j].setIsEmpty(false);

                }
            }
        }
        Solve();
    }


}
