public class Molecule {
    private Atom atomOne, atomTwo;
    private int positionX, positionY;


    public Molecule(Atom atomOne, Atom atomTwo, int positionX, int positionY) {
        this.atomOne = atomOne;
        this.atomTwo = atomTwo;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
}
