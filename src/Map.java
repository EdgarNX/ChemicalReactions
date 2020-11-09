import java.util.ArrayList;
        import java.util.List;
        import java.util.Random;

public class Map {
    private Object[][] grid;
    private int size;
    private static Map single_instance = null;

    private Map(int size) {
        this.size = size;
        grid = new Object[size][size];
        for(int i=0;i<size;i++) {
            for (int j = 0; j < size; j++)
                grid[i][j] = "_";
        }
    }

    public static Map getInstance(int size)
    {
        if (single_instance == null)
            single_instance = new Map(size);

        return single_instance;
    }

    public boolean verifyIfSlotIsFree(int x, int y) {
        return grid[x][y].equals("_");
    }

    public void slotSetter(String value, int x, int y) {
        grid[x][y] = value;
    }

    public int getSize() {
        return size;
    }

    public void addEntry(Element element) {
        grid[element.getPositionX()][element.getPositionY()] = element.getName();
    }

    public void printMap() {
        for(int i=0;i<size;i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("--------------------------------------------");
    }
}
