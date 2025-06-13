
import java.util.ArrayList;



public class SudokuMap implements Runnable{

    private SudokuMap map = null;
    private Cell[][] cells = new Cell[9][9];
    private int target = 0;

    public SudokuMap(SudokuMap map, int target, Cell[][] cells) {
        
        if (map != null) {
            this.map = map;
            this.cells = map.getCells();
            this.target = target;
        }else{
            this.map = map;
            this.target= target;
            this.cells = cells;
        }

        
    }

    public synchronized  void cellFiller() {

        if(target!=0){
            SudokuMap targetMap = new SudokuMap(this,target, this.cells);
            Cell[][] targetCells= targetMap.getCells();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (targetCells[i][j].getValue() == this.target ) {
                        for (int i2 = 0; i2 < targetCells.length; i2++) {
                            targetCells[i2][j].setCanBe(false);                        
                        }
                        for (int j2 = 0; j2 < targetCells.length; j2++) {
                            targetCells[i][j2].setCanBe(false);                        
                        }
                        int def = 3;
                        int i3 =0;
                        int j3 =0;

                        while(i3<def){
                            targetCells[i3][j3].setCanBe(false);
                            j3++;
                            if(j3==3){
                                j3=0;
                                i3++;
                            }
                        }
                    }
                }
            }
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

            for (int i = 1; i <= 3; i++) {
                for (int j = 1; j <= 3; j++) {
                    ArrayList<Point> konum= new ArrayList<>();
                    for (int k = 0; k <3; k++) {
                        for (int l = 0;l < 3;l++) {
                            if(targetCells[i*k][j*l].canBe()){
                                konum.add(new Point(i*k,j*l));
                            }
                        }
                        
                    }
                    if(konum.size()==1){
                        this.cells[konum.get(0).i][konum.get(0).j].setValue(this.target);
                    }
                    
                }
                
            }
            notifyAll();
            

        }
        
    }

    public Cell[][] getCells() {
        return cells;
    }

    @Override
    public void run() {

        if (map==null) {
            SudokuMap bir=new SudokuMap(this,1,this.cells);
            SudokuMap iki=new SudokuMap(this,2,this.cells);
            SudokuMap uc=new SudokuMap(this,3,this.cells);
            SudokuMap dort=new SudokuMap(this,4,this.cells);
            SudokuMap bes=new SudokuMap(this,5,this.cells);
            SudokuMap alti=new SudokuMap(this,6,this.cells);
            SudokuMap yedi=new SudokuMap(this,7,this.cells);
            SudokuMap sekiz=new SudokuMap(this,8,this.cells);
            SudokuMap dokuz=new SudokuMap(this,9,this.cells);
            

            Thread birThread = new Thread(bir);
            birThread.start();

            Thread ikiThread = new Thread(iki);
            ikiThread.start();

            Thread ucThread = new Thread(uc);
            ucThread.start();

            Thread dortThread = new Thread(dort);
            dortThread.start();

            Thread besThread = new Thread(bes);
            besThread.start();

            Thread altiThread = new Thread(alti);
            altiThread.start();

            Thread yediThread = new Thread(yedi);
            yediThread.start();

            Thread sekizThread = new Thread(sekiz);
            sekizThread.start();

            Thread dokuzThread = new Thread(dokuz);
            dokuzThread.start();

        }
        boolean flag = false;

        while (!flag){
            cellFiller();
            flag=true;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    flag = this.cells[i][j].isFixed();
                    if (!flag) {
                        break;
                    }
                }
                if (!flag) {
                    break;
                }
            }
        }

        System.out.println("Çözüm tamamlandı: ");

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.println(cells[i][j].getValue()+" ");
            }
            System.out.println();
        }
      

    }

    

  
    
    
}