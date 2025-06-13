public class Cell {

    private int value;
    private boolean isFixed;
    private boolean canBe;
    public Cell(int value) {
        this.value = value;
        if (value==0) {
            this.isFixed = false;
            this.canBe = true;
            
        }else{
            this.isFixed = true;
            this.canBe = false;
            
        }
    }

    public int getValue() {
        return value;
    }

    public boolean isFixed() {
        return isFixed;
    }

    public void setIsFixed(boolean isFixed) {
        this.isFixed = isFixed;
    }

    
    public boolean canBe() {
        return canBe;
    }

    public void setCanBe(boolean canBe) {
        this.canBe = canBe;
    }
    
    public void setValue(int i) {
        this.value=i;
        this.isFixed=true;
    }
    

}