import java.util.Random;

public class Molecule extends Element implements Runnable{
    private Atom atomOne, atomTwo;

    public Molecule(Atom atomOne, Atom atomTwo, int positionX, int positionY) {
        this.atomOne = atomOne;
        this.atomTwo = atomTwo;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void changePosition(int size) {
        Random rand = new Random();
        positionX = rand.nextInt(size);
        positionY = rand.nextInt(size);
    }

    @Override
    public void run() {

    }
}
