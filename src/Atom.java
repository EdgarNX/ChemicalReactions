public class Atom {
    private String name;
    private int positionX;
    private int positionY;
    private int valence;

    public Atom(String name, int positionX, int positionY, int valence) {
        this.name = name;
        this.positionX = positionX;
        this.positionY = positionY;
        this.valence = valence;
    }

    public void setPositionX(int newPositionX) {
        this.positionX = newPositionX;
    }

    public void setPositionY(int newPositionY) {
        this.positionY = newPositionY;
    }

    public String getName() {
        return name;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public int getValence() {
        return valence;
    }
}
