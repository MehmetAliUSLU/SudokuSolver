public class Solver
{

    public Cell[][] grid;
    
    public void setCell(int row, int column,Cell cell) {
        grid[row][column]= cell;
    }

    public void setGrid(Cell[][] grid) {
        this.grid = grid;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public Cell getCell(int row, int column) {
        return grid[row][column];
        
    }

    public Cell[][] CopyGrid() {
        Cell[][] newGrid = new Cell[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            newGrid[i] = new Cell[grid[i].length];
            for (int j = 0; j < grid[i].length; j++) {
                newGrid[i][j] = new Cell(grid[i][j].getValue(), i, j, this);
            }
            
        }
        return newGrid;
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

    public boolean  SolveWithVer() {
        
        boolean isProgress = false;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].isEmpty()) {
                    if(grid[i][j].IsItVerticallyPlacedIn3x3Box()){
                        isProgress = true || isProgress; // Return true immediately if a cell is solved
                    }
                    
                }
            }
        }
        if (isProgress) {
            //System.out.println("zorttttttttttttttt");
            Solve(0);
        }
        //System.out.println("Progress made with vertical: " + isProgress);

        return isProgress; // Return true if any cell was solved
        
    }       

    public boolean  SolveWithHor() {
        
        boolean isProgress = false;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].isEmpty()) {
                    if(grid[i][j].IsItHorizontallyPlacedIn3x3Box()){
                        isProgress = true || isProgress; // Return true immediately if a cell is solved
                    }
                    
                }
            }
        }
        if (isProgress) {
            //System.out.println("aaaaaaaaaaaaah");
            Solve(0);
        }
        //System.out.println("Progress made with  horizontal: " + isProgress);

        return isProgress; // Return true if any cell was solved
        
    }       

    public boolean  SolveWithIsThatOnlyOne() {
        boolean isProgress = false;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].isEmpty()) {
                    //System.out.println("" + i + " " + j + " is empty, trying to solve with isThatOnlyOne.");
                    isProgress = isProgress || grid[i][j].isThatOnlyOne();
                    // Debug print removed for production use
                }
            }
        }
        if (isProgress) {
            Solve(0);
        }
        return isProgress;
    }

    public boolean  Solve(){
        boolean isSolved = Solve(0);

        printGrid();
        int solvedCount = 0;
        for (Cell[] grid1 : grid) {
            for (Cell item : grid1) {
                if (item.isEmpty()) {
                    item.printPossibleValues();
                    System.out.println();

                } else {
                    solvedCount++;
                }
            }
        }
        System.out.println("Solved cells: " + solvedCount);
        return isSolved;
    }

    public boolean Solve(int iterate){
        if(isSolved()) {

            return true;
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
            //System.out.println(iterate + " iterations with progress.");
            return Solve(0);
        } else {
            if (iterate > 3 && iterate < 5) {
                //System.out.println(iterate + " iterations without progress, trying to solve with isThatOnlyOne.");
                SolveWithIsThatOnlyOne();

            } else if (iterate >5 && iterate < 10) {
                //System.out.println(iterate + " iterations without progress, trying to solve with vertically");
                SolveWithVer();

            }else if (iterate>10&&iterate< 15) {
                //System.out.println(iterate + " trying horizantally");
                SolveWithHor();
            } else if (iterate > 15 && iterate<20) {
                System.out.println("iterate: " + iterate);
                SolveWithRandomly();
            }else if (iterate>20) {
                return false;
                
            }

            iterate++;
            return Solve(iterate);
        }
    }

    public boolean SolveWithRandomly() {

       boolean isProgress = false;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].isEmpty()&&!grid[i][j].isThatTryRandomly) {
                    //System.out.println("" + i + " " + j + " is empty, trying to solve with isThatOnlyOne.");
                    isProgress = isProgress || grid[i][j].TryRandomlySolve();
                    // Debug print removed for production use
                }
            }
        }
        if (isProgress) {
            return Solve(0);
        }
        return isProgress;
    }
}
