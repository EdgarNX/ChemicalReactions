import java.util.Random;

public class Molecule extends Element implements Runnable{
    private Atom atomOne, atomTwo;

    public Molecule(Atom atomOne, Atom atomTwo, int positionX, int positionY) {
        this.atomOne = atomOne;
        this.atomTwo = atomTwo;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public String getName() {
        return atomOne.getName() + atomTwo.getName();
    }

    @Override
    public void run() {
        MapHelper.operate(this);
    }
}
