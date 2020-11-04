import java.util.ArrayList;
        import java.util.List;
        import java.util.Random;

public class Map {
    private Object[][] grid;
    private int size;

    public Map(int size) {
        this.size = size;
        grid = new Object[size][size];
        for(int i=0;i<size;i++) {
            for (int j = 0; j < size; j++)
                grid[i][j] = ' ';
        }
    }

    public boolean verifyIfSlotIsFree(int x, int y) {
        if(grid[x][y].equals(' '))
            return true;
        return false;
    }

    public void slotSetter(String value, int x, int y) {
        grid[x][y] = value;
    }

    public String atomGetter(int x, int y) {
        return (String)grid[x][y];
    }

    public int getSize() {
        return size;
    }

    public void addEntry(Atom atom) {
        grid[atom.getPositionX()][atom.getPositionY()] = atom.getName();
    }

    //metoda pt printare
}
