
import java.util.ArrayList;

public class Cell {
    
    boolean isEmpty;
    int value;
    int row;
    int column;
    ArrayList<Integer> possibleValues;
    Solver solver;
    boolean isThatTryRandomly;

    public Cell(int value,int row, int column, Solver solver) {
        this.value = value;
        this.solver = solver;
        if (value != 0) {
            this.isEmpty = false;
        } else {
            this.isEmpty = true;
        }
        this.possibleValues = new ArrayList<>();
        this.row = row;
        this.column = column;
        this.isThatTryRandomly= false;
    }

    
    public boolean  IsItWithSameBox(Cell otherCell) {
        return (this.row / 3 == otherCell.row / 3 && this.column / 3 == otherCell.column / 3);
        
    }

    public boolean  IsItVerticallyPlacedIn3x3Box() {

        Cell[][] box = this.get3by3Box();
        // Iterate over a copy to avoid ConcurrentModificationException
        ArrayList<Integer> possibleValuesCopy = new ArrayList<>(this.possibleValues);
        for (Integer value : possibleValuesCopy) {
            ArrayList<Point2D> points = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(box[i][j].possibleValues.contains(value)&& !box[i][j].equals(this) && !points.contains(new Point2D(box[i][j].row, box[i][j].column))) {
                        points.add(new Point2D(box[i][j].row, box[i][j].column));
                    }
                }
            }

            Point2D point = new Point2D(this.row, this.column);
            if (point.IsVerticallyWith(points)){
                this.VerticallyRemovePosibles(value);
                
                //System.out.print(this.row+ " "+ this.column + "   " + value );
                //for (Point2D p : points){
                //    System.out.print(",  " + p.toString());
                //}
                //System.out.println();
            }

        }
        return false;

        
    }

    public void VerticallyRemovePosibles(int value) {

        for (int i =0 ;i<9; i++) {
            
            if (solver.getGrid()[row][i].possibleValues.contains(value)&& !solver.getGrid()[row][i].IsItWithSameBox(this)) {
                //System.out.println("verticalllllllllllllll");
                solver.getGrid()[row][i].possibleValues.remove((Integer)value);
                //System.out.println("removen value" + value + "On "+ i+ "  "+ column );

                if ( solver.getGrid()[row][i].possibleValues.size()== 1){
                    solver.getGrid()[row][i].updateCell();
                }
            }
        }
        
    }

    public boolean  IsItHorizontallyPlacedIn3x3Box() {

        Cell[][] box = this.get3by3Box();
        
        ArrayList<Integer> possibleValuesCopy = new ArrayList<>(this.possibleValues);
        // Iterate over a copy to avoid ConcurrentModificationException
        for (int value : possibleValuesCopy) {
            ArrayList<Point2D> points = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(box[i][j].possibleValues.contains(value)&& !box[i][j].equals(this) && !points.contains(new Point2D(box[i][j].row, box[i][j].column))) {
                        points.add(new Point2D(box[i][j].row, box[i][j].column));
                    }
                }
            }

            Point2D point = new Point2D(this.row, this.column);
            if (point.IsHorizontallyWith(points)){
                this.HorizontallyRemovePosibles(value);
                
                //System.out.print(this.row+ " "+ this.column + "   " + value );
                //for (Point2D p : points){
                //    System.out.print(",  " + p.toString());
                //}
                //System.out.println();
            }

        }
        return false;

        
    }

    public void HorizontallyRemovePosibles(int value) {

        for (int i =0 ;i<9; i++) {
            if (solver.getGrid()[i][column].possibleValues.contains(value)&& !solver.getGrid()[i][column].IsItWithSameBox(this)) {
                //System.out.println("dsasasasasasasasasasasasasasa");
                solver.getGrid()[i][column].possibleValues.remove((Integer)value);
                //System.out.println("removen value" + value + "On "+ i+ "  "+ column );
                if (solver.getGrid()[i][column].possibleValues.size() == 1){
                    solver.getGrid()[i][column].updateCell();
                }
            }
        }
        
    }

    public boolean calculatePossibleValues() {
        // Logic to calculate possible values based on Sudoku rules
        // This is a placeholder for the actual logic

        if (isEmpty) {
            // Check the row and column for existing values
            for (int i = 1; i <= 9; i++) {
                // Check if the value i can be placed in this cell
                boolean canPlace = true;
                for (int j = 0; j < solver.getGrid().length; j++) {
                    if ((solver.getGrid()[row][j].getValue() == i && !solver.getGrid()[row][j].equals(this)) || (solver.getGrid()[j][column].getValue() == i && !solver.getGrid()[j][column].equals(this)) || (solver.getGrid()[row - row % 3 + j / 3][column - column % 3 + j % 3].getValue() == i && !solver.getGrid()[row - row % 3 + j / 3][column - column % 3 + j % 3].equals(this))) {
                        canPlace = false;
                        break;
                    }
                }
                if (canPlace) {
                    if(!possibleValues.contains(i)) {
                        this.possibleValues.add(i);
                    }
                }
            }

            if (this.possibleValues.size() == 2) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (solver.getGrid()[row - row % 3 + i][column - column % 3 + j].getValue() == 0 && !solver.getGrid()[row - row % 3 + i][column - column % 3 + j].equals(this) && solver.getGrid()[row - row % 3 + i][column - column % 3 + j].isPossibleValuesEqual(this.possibleValues)) {
                            for(int k = 0; k <3; k++) {
                                for (int l = 0; l < 3; l++) {
                                    if (solver.getCell(row-row%3+k, column - column % 3 + l).possibleValues.size() > 2 && solver.getCell(row-row%3+k, column - column % 3 + l).getValue() == 0 && !solver.getCell(row-row%3+k, column - column % 3 + l).equals(solver.getCell(row - row % 3 + i, column - column % 3 + j))&& !solver.getCell(row-row%3+k, column - column % 3 + l).equals(this)) {
                                        solver.getCell(row-row%3+k, column - column % 3 + l).possibleValues.remove(solver.getCell(row - row % 3 + i, column - column % 3 + j).possibleValues.get(0));
                                        solver.getCell(row-row%3+k, column - column % 3 + l).possibleValues.remove(solver.getCell(row - row % 3 + i, column - column % 3 + j).possibleValues.get(1));
                                    }
                                }
                            }
                        }
                    }
                }

                for(int i =0; i<9; i++){
                    if(solver.getGrid()[row][i].getValue() == 0 && !solver.getGrid()[row][i].equals(this) && solver.getGrid()[row][i].isPossibleValuesEqual(this.possibleValues)) {
                        for (int j = 0; j < 9; j++) {
                            if (solver.getCell(row, j).possibleValues.size() > 2 && solver.getCell(row, j).getValue() == 0 && !solver.getCell(row, j).equals(solver.getCell(row, i)) && !solver.getCell(row, j).equals(this)) {
                                solver.getCell(row, j).possibleValues.remove(this.possibleValues.get(0));
                                solver.getCell(row, j).possibleValues.remove(this.possibleValues.get(1));
                            }
                        }
                    }
                }

                for(int i =0; i<9; i++){
                    if(solver.getGrid()[i][column].getValue() == 0 && !solver.getGrid()[i][column].equals(this) && solver.getGrid()[i][column].isPossibleValuesEqual(this.possibleValues)) {
                        for (int j = 0; j < 9; j++) {
                            if (solver.getCell(j,column).possibleValues.size() > 2 && solver.getCell(j,column).getValue() == 0 && !solver.getCell(j,column).equals(solver.getCell(i,column)) && !solver.getCell(j,column).equals(this)) {
                                solver.getCell(j,column).possibleValues.remove(this.possibleValues.get(0));
                                solver.getCell(j,column).possibleValues.remove(this.possibleValues.get(1));
                            }
                        }
                    }
                }
            }

            
            boolean hasProgress = false;
            for (int i = 0; i < solver.getGrid().length; i++) {
                for (int j = 0; j < solver.getGrid()[i].length; j++) {

                    if (solver.getGrid()[i][j].isEmpty() && solver.getGrid()[i][j].possibleValues.size() == 1) {
                        solver.getGrid()[i][j].updateCell();
                        hasProgress = true;
                    }
                }
            }

            return hasProgress; // Return true if any possible values were calculated
        }
        return false; // No possible values calculated
    }

    public boolean updateCell() {
        if (this.possibleValues.size() == 1) {
            this.setValue(this.possibleValues.get(0));
            this.removefromPossibleValues(this.possibleValues.get(0));
            this.possibleValues.clear();
            this.setIsEmpty(false);
            return true;
        }
        return false;
    }
    
    public void updateCell(int value) {
        
        this.setValue(value);
        this.removefromPossibleValues(value);
        this.possibleValues.clear();
        this.setIsEmpty(false);
        
    }

    public boolean isThatOnlyOne() {

        Cell[][] box = this.get3by3Box();
        ArrayList<Integer> boxPossibleValues = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (box[i][j].isEmpty() && !box[i][j].equals(this)) {
                    boxPossibleValues.addAll(box[i][j].possibleValues);
                }
            }
        }

        Cell[] rowCells = this.getRow();
        Cell[] columnCells = this.getColumn();

        ArrayList<Integer> rowPossibleValues = new ArrayList<>();
        ArrayList<Integer> columnPossibleValues = new ArrayList<>();

        for (Cell cell : rowCells) {
            if (cell.isEmpty() && !cell.equals(this)) {
                rowPossibleValues.addAll(cell.possibleValues);
            }
        }

        for (Cell cell : columnCells) {
            if (cell.isEmpty() && !cell.equals(this)) {
                columnPossibleValues.addAll(cell.possibleValues);
            }
        }

        for (int possibleValue : this.possibleValues) {
            boolean isOnlyOneInColumn = true;
            boolean isOnlyOneInRow = true;
            boolean isOnlyOneIn3x3 = true;

            if (boxPossibleValues.contains(possibleValue)) {
                isOnlyOneIn3x3 = false;
            }
            if (rowPossibleValues.contains(possibleValue)) {
                isOnlyOneInRow = false;
            }
            if (columnPossibleValues.contains(possibleValue)) {
                isOnlyOneInColumn = false;
            }

            
            // Debug print removed for production. Use logging if needed.
            // System.out.println(isOnlyOneIn3x3 + " " + isOnlyOneInRow + " " + isOnlyOneInColumn + " for value: " + possibleValue);
            if (isOnlyOneInColumn || isOnlyOneInRow || isOnlyOneIn3x3) {
                this.setValue(possibleValue);
                this.removefromPossibleValues(possibleValue);
                this.possibleValues.clear();
                this.setIsEmpty(false);
                return true; // Found a unique possible value
            }
        }
        return false; // No unique possible value found
    }
    
    public void removefromPossibleValues(int value) {
        
        for(int i =0 ; i<9; i++) {
            if(solver.getGrid()[row][i].possibleValues.contains(value)) {
                solver.getGrid()[row][i].possibleValues.remove(Integer.valueOf(value));
            }
            if(solver.getGrid()[i][column].possibleValues.contains(value)) {
                solver.getGrid()[i][column].possibleValues.remove(Integer.valueOf(value));
            }
        }
        for ( int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (solver.getGrid()[row - row % 3 + i][column - column % 3 + j].possibleValues.contains(value)) {
                    solver.getGrid()[row - row % 3 + i][column - column % 3 + j].possibleValues.remove(Integer.valueOf(value));
                }
            }
        }

    }

    public boolean isPossibleValuesEqual(ArrayList<Integer> otherPossibleValues) {
        if (this.possibleValues.size() != otherPossibleValues.size()) {
            return false;
        }
        for (Integer value : this.possibleValues) {
            if (!otherPossibleValues.contains(value)) {
                return false;
            }
        }
        return true;
        
    }
   
    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    public boolean isEmpty() {
        return isEmpty;
    }   
    public void printPossibleValues() {
        System.out.println("Possible values for cell (" + row + ", " + column + "): " + possibleValues);
    }
    public Cell[][] get3by3Box() {
        
        Cell[][] box = new Cell[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                box[i][j] = solver.getGrid()[row - row % 3 + i][column - column % 3 + j];
            }
        }
        return box;

    }
    public Cell[] getRow() {
        Cell[] rowCells = new Cell[9];
        for (int i = 0; i < 9; i++) {
            rowCells[i] = solver.getGrid()[row][i];
        }
        return rowCells;
    }
    public Cell[] getColumn() {
        Cell[] columnCells = new Cell[9];
        for (int i = 0; i < 9; i++) {
            columnCells[i] = solver.getGrid()[i][column];
        }
        return columnCells;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Cell)) return false;
        Cell other = (Cell) obj;
        return this.row == other.row && this.column == other.column;
    }

    public boolean TryRandomlySolve() {
        
        
        ArrayList<Integer> possibleValuesCopy = new ArrayList<>(this.possibleValues);
        Cell[][] temp = solver.CopyGrid();
        Cell tempCell = this.Copy();
        
        for ( int value : possibleValuesCopy){
            updateCell(value);
            if(solver.Solve(0)){
                return true;
            }else{
                solver.setGrid(temp);
                solver.getGrid()[row][column].possibleValues.remove((Integer) value);
                solver.getGrid()[row][column].isThatTryRandomly= true;
                
            }
            
        }
        return false;
    }

    public Cell Copy(){
        Cell copy =new Cell(0, this.row,  this.column, this.solver);
        copy.isEmpty= true;
        copy.possibleValues = this.possibleValues;
        return copy;
    }

}
