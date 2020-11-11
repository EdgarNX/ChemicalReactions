import java.util.Random;

public class Atom extends Element {
    private String name;
    private int valence;

    public Atom(String name, int positionX, int positionY, int valence) {
        super(positionX,positionY);
        this.name = name;
        this.valence = valence;
    }

    public String getName() {
        return name;
    }

    public int getValence() {
        return valence;
    }

    @Override
    public void run() {
        MapHelper.operate(this);
    }
}
