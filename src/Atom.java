import java.util.Random;

public class Atom extends Element implements Runnable{
    private String name;
    private int valence;

    public Atom(String name, int positionX, int positionY, int valence) {
        this.name = name;
        this.positionX = positionX;
        this.positionY = positionY;
        this.valence = valence;
    }

    public String getName() {
        return name;
    }

    public int getValence() {
        return valence;
    }

    public void changePosition(int size) {
        Random rand = new Random();
        positionX = rand.nextInt(size);
        positionY = rand.nextInt(size);
    }

    @Override
    public void run() {
        MapHelper.operate(this);
    }
}
