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

    public void Solve(int iterate){
        if(isSolved()) {
            System.out.println("Sudoku is already solved.");
            printGrid();
            return;
        }

        
        boolean progres = false;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                boolean progress = false;
                progress= grid[i][j].calculatePossibleValues();
                progres = progress;
                
            }
        }

        

        if (progres) {
            Solve(0);
        } else {
            iterate++;
            if(iterate==100 ){
                
                System.out.println("No further progress can be made with current logic.");
                printGrid();

                for(int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (grid[i][j].isEmpty()) {
                            grid[i][j].printPossibleValues();
                        }
                    }
                }
            }else{
            Solve(iterate);

            }
        
        }
    }


}
