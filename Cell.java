
import java.util.ArrayList;

public class Cell {
    
    boolean isEmpty;
    int value;
    int row;
    int column;
    ArrayList<Integer> possibleValues;



    public Cell(int value,int row, int column) {
        this.value = value;

        if (value != 0) {
            this.isEmpty = false;
        } else {
            this.isEmpty = true;
        }
        this.possibleValues = new ArrayList<>();
        this.row = row;
        this.column = column;
    }

    public void calculatePossibleValues() {
        // Logic to calculate possible values based on Sudoku rules
        // This is a placeholder for the actual logic
        if (isEmpty) {
            possibleValues.clear();
            // Check the row and column for existing values
            for (int i = 1; i <= 9; i++) {
                // Check if the value i can be placed in this cell
                boolean canPlace = true;
                for (int j = 0; j < Solver.grid.length; j++) {
                    if ((Solver.grid[row][j].getValue() == i && !Solver.grid[row][j].equals(this)) || (Solver.grid[j][column].getValue() == i && !Solver.grid[j][column].equals(this)) || (Solver.grid[row - row % 3 + j / 3][column - column % 3 + j % 3].getValue() == i && !Solver.grid[row - row % 3 + j / 3][column - column % 3 + j % 3].equals(this))) {
                        canPlace = false;
                        break;
                    }
                }
                if (canPlace) {
                    this.possibleValues.add(i);
                }
            }            
        }
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

}
