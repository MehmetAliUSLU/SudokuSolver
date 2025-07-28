
import java.util.ArrayList;



public class Point2D {
    public int row;
    public int column;
    public Point2D(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public boolean IsVerticallyWith(ArrayList<Point2D> points) {
        boolean isVertical = true;
        for (Point2D point : points) {
            if (point.row != this.row) {
                isVertical = false; // Found a point in the same row
                break;
            }
        }
        return isVertical;
    }
    public boolean IsHorizontallyWith(ArrayList<Point2D> points) {
        boolean isHorizontal = true;
        for (Point2D point : points) {
            if (point.column != this.column) {
                isHorizontal = false ; // Found a point in the same column
                break;
            }
        }
        return isHorizontal;
    }

    @Override
    public String toString() {
        return "  "+row+ "  " +column; 
    }
    

}
