
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

    public boolean calculatePossibleValues() {
        // Logic to calculate possible values based on Sudoku rules
        // This is a placeholder for the actual logic

        if (isEmpty) {
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
                    if(!possibleValues.contains(i)) {
                        this.possibleValues.add(i);
                    }
                }
            }

            if (this.possibleValues.size() == 2) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (Solver.grid[row - row % 3 + i][column - column % 3 + j].getValue() == 0 && !Solver.grid[row - row % 3 + i][column - column % 3 + j].equals(this) && Solver.grid[row - row % 3 + i][column - column % 3 + j].isPossibleValuesEqual(this.possibleValues)) {
                            for(int k = 0; k <3; k++) {
                                for (int l = 0; l < 3; l++) {
                                    if (Solver.grid[row - row % 3 + k][column - column % 3 + l].possibleValues.size() > 2 && Solver.grid[row - row % 3 + k][column - column % 3 + l].getValue() == 0 && !Solver.grid[row - row % 3 + k][column - column % 3 + l].equals(Solver.grid[row - row % 3 + i][column - column % 3 + j])&& !Solver.grid[row - row % 3 + k][column - column % 3 + l].equals(this)) {
                                        Solver.grid[row - row % 3 + k][column - column % 3 + l].possibleValues.remove(Solver.grid[row - row % 3 + i][column - column % 3 + j].possibleValues.get(0));
                                        Solver.grid[row - row % 3 + k][column - column % 3 + l].possibleValues.remove(Solver.grid[row - row % 3 + i][column - column % 3 + j].possibleValues.get(1));
                                    }
                                }
                            }
                        }
                    }
                }

                for(int i =0; i<9; i++){
                    if(Solver.grid[row][i].getValue() == 0 && !Solver.grid[row][i].equals(this) && Solver.grid[row][i].isPossibleValuesEqual(this.possibleValues)) {
                        for (int j = 0; j < 9; j++) {
                            if (Solver.grid[row][j].possibleValues.size() > 2 && Solver.grid[row][j].getValue() == 0 && !Solver.grid[row][j].equals(Solver.grid[row][i]) && !Solver.grid[row][j].equals(this)) {
                                Solver.grid[row][j].possibleValues.remove(this.possibleValues.get(0));
                                Solver.grid[row][j].possibleValues.remove(this.possibleValues.get(1));
                            }
                        }
                    }
                }

                for(int i =0; i<9; i++){
                    if(Solver.grid[i][column].getValue() == 0 && !Solver.grid[i][column].equals(this) && Solver.grid[i][column].isPossibleValuesEqual(this.possibleValues)) {
                        for (int j = 0; j < 9; j++) {
                            if (Solver.grid[j][column].possibleValues.size() > 2 && Solver.grid[j][column].getValue() == 0 && !Solver.grid[j][column].equals(Solver.grid[i][column]) && !Solver.grid[j][column].equals(this)) {
                                Solver.grid[j][column].possibleValues.remove(this.possibleValues.get(0));
                                Solver.grid[j][column].possibleValues.remove(this.possibleValues.get(1));
                            }
                        }
                    }
                }
            }

            
            boolean hasProgress = false;
            for (int i = 0; i < Solver.grid.length; i++) {
                for (int j = 0; j < Solver.grid[i].length; j++) {

                    if (Solver.grid[i][j].isEmpty() && Solver.grid[i][j].possibleValues.size() == 1) {
                        Solver.grid[i][j].setValue(Solver.grid[i][j].possibleValues.get(0));
                        Solver.grid[i][j].removefromPossibleValues(Solver.grid[i][j].possibleValues.get(0));
                        Solver.grid[i][j].possibleValues.clear();
                        Solver.grid[i][j].setIsEmpty(false);
                        hasProgress = true;
                        continue;
                    }
                }
            }

            return hasProgress; // Return true if any possible values were calculated
        }
        return false; // No possible values calculated
    }


    public boolean isThatOnlyOne() {

        if (this.isEmpty()&& this.possibleValues.size()>1) {
            for (int k=0;k< this.possibleValues.size();k++) {
                int possibleValue = this.possibleValues.get(k);
                for(int i=0; i<9;i++){
                    if(Solver.grid[i][column].possibleValues.contains(possibleValue)&& !Solver.grid[i][column].equals(this)) {

                            return false;
                        }
                        if(Solver.grid[row][i].possibleValues.contains(possibleValue)&& !Solver.grid[row][i].equals(this)) {

                            return false;
                        }
                    }   

                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            if (Solver.grid[row - row % 3 + i][column - column % 3 + j].possibleValues.contains(possibleValue)&& !Solver.grid[row - row % 3 + i][column - column % 3 + j].equals(this)) {
                                return false;
                            }
                        }
                    }
                    this.setValue(possibleValue);
                    this.removefromPossibleValues(possibleValue);
                    this.possibleValues.clear();
                    this.setIsEmpty(false);

                }
            }
            return true; // Only one possible value found
        }
    


    

    public void removefromPossibleValues(int value) {
        
        for(int i =0 ; i<9; i++) {
            if(Solver.grid[row][i].possibleValues.contains(value)) {
                Solver.grid[row][i].possibleValues.remove(Integer.valueOf(value));
            }
            if(Solver.grid[i][column].possibleValues.contains(value)) {
                Solver.grid[i][column].possibleValues.remove(Integer.valueOf(value));
            }
        }
        for ( int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (Solver.grid[row - row % 3 + i][column - column % 3 + j].possibleValues.contains(value)) {
                    Solver.grid[row - row % 3 + i][column - column % 3 + j].possibleValues.remove(Integer.valueOf(value));
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

}
