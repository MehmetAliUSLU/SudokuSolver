

public class Solver
{

    private Cell[][] grid;
    
    public Cell[][] getGrid() {
        return grid;
    }
    public void setGrid(Cell[][] grid) {
        this.grid = grid;
    }
    public Cell getCell(int row, int column) {
        return grid[row][column];
        
    }
    public void setCell(int row, int column,Cell cell) {
        grid[row][column]= cell;
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
        System.out.println();
        
    }
    public boolean isSolved() {
        for (Cell[] grid1 : grid) {
            for (Cell item : grid1) {
                if (item.isEmpty()) {
                    return false; 
                }
            }
        }
        return true;
    }
    public boolean  isAllLookedForRandomSolution() {
        
        for( int i = 0; i<9;i++){
            for ( int j=0;j<9;j++){
                if(!grid[i][j].isThatTryRandomly&& grid[i][j].isEmpty){
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean  Solve(){
        boolean isSolved = Solve(0);

        printGrid();
        
        return isSolved;
    }

    public boolean Solve(int iterate){
        if(isSolved()) {

            return true;
        }
        boolean progress = false;
       
        if(iterate<20){
            progress = SolveWithCalculatePossibleValues()||progress ;
            progress = SolveWithIsThatOnlyOne()||progress ;
            progress = SolveWithVertically()||progress ;
            progress = SolveWithHorizaontally()||progress ;
            
            Cell[][] celltemp = CopyGrid();
            
            try {    
                progress =SolveWithRandomly() || progress ;
            } catch (StackOverflowError e) {
                grid = celltemp;
            }
        }

        if (progress){
            return Solve(0);
        }
        iterate ++;
        return Solve(iterate);
        
    }

    public boolean  SolveWithVertically() {
        
        boolean isProgress = false;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].isEmpty()) {
                    isProgress = grid[i][j].IsItVerticallyPlacedIn3x3Box() || isProgress; 
                }
            }
        }

        if (isProgress) {
            Solve(0);
        }

        return isProgress; 
    }       

    public boolean  SolveWithHorizaontally() {
        
        boolean isProgress = false;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].isEmpty()) {
                    isProgress = grid[i][j].IsItHorizontallyPlacedIn3x3Box() || isProgress;
                }
            }
        }

        if (isProgress) {
    
            Solve(0);
        }

        return isProgress;
    }       

    public boolean  SolveWithIsThatOnlyOne() {
        boolean isProgress = false;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].isEmpty()) {
                    isProgress = isProgress || grid[i][j].isThatOnlyOne();
                }
            }
        }

        if (isProgress) {
            Solve(0);
        }
        
        return isProgress;
    }

    public boolean  SolveWithCalculatePossibleValues() {
        boolean isProgress = false;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].isEmpty()) {
                    isProgress = isProgress || grid[i][j].calculatePossibleValues();
                }
            }
        }

        if (isProgress) {
            Solve(0);
        }
        
        return isProgress;
    }

    public boolean SolveWithRandomly() {
        
        if ( isAllLookedForRandomSolution()){
            return false; 
        }

        boolean isProgress = false;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].isEmpty()&&!grid[i][j].isThatTryRandomly &&grid[i][j].triedrandomlycount<5) {
                    Cell[][] tempGrid = CopyGrid();
                    
                    try {
                        isProgress = isProgress || grid[i][j].TryRandomlySolve();
                    } catch (StackOverflowError e) {
                        isProgress = isProgress||false;
                        grid = tempGrid;
                        grid[i][j].isThatTryRandomly = true;
                        grid[i][j].triedrandomlycount++;
                    }
                }
            }
        }
        
        if (isProgress) {
            return Solve(0);
        }
        return false;
    }
}
