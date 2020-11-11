import java.util.Random;

public class Molecule extends Element {
    private Atom atomOne, atomTwo;

    public Molecule(Atom atomOne, Atom atomTwo, int positionX, int positionY) {
        super(positionX,positionY);
        this.atomOne = atomOne;
        this.atomTwo = atomTwo;
    }

    public String getName() {
        return atomOne.getName() + atomTwo.getName();
    }

    @Override
    public void run() {
        MapHelper.operate(this);
    }
}
