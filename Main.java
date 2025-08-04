
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;



public class Main {

    public static void main(String[] args) {

        RedirectOutput("log.txt");
        Solver solver = new Solver();
        solver.setGrid(ReadMapFromFile("maps/map8.txt", solver));
        try {
            solver.Solve();
        } catch (StackOverflowError e) {
            solver.printGrid();
        }
    }

    public static void RedirectOutput(String path) {
        File logfile = new File(path);
        try {
            PrintStream fos = new PrintStream(logfile);
            System.setOut(fos);
            System.setErr(fos);
        } catch (Exception e) {
            System.out.println("Çıktı dosyası ayarlanırken problem oluştu.");
            return;
        }

    }

    public static Cell[][] ReadMapFromFile(String path, Solver solver) {

        File file = new File(path);
        Cell[][] grid = new Cell[9][9]; 
        try {
            Scanner scanner = new Scanner(file);
            for (int i = 0; i < 9; i++) {
                String line = scanner.nextLine();
                for (int j = 0; j < 9; j++) {
                    int value = Character.getNumericValue(line.charAt(j));
                    grid[i][j] = new Cell(value, i, j, solver);
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Dosya okunurken hata oluştu.");
        }
        
        return grid;

    }
}