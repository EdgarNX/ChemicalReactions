import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapHelper {
    private List<Element> elements = new ArrayList<Element>();
    private Map map;

    public MapHelper(List<Element> elements, Map map) {
        this.elements = elements;
        this.map = map;
    }
//map.atomGetter(element.getPositionX(),element.getPositionY())
    public void operate(Element element) {

        Random rand = new Random();
        int newpositionX = rand.nextInt(map.getSize());
        int newpositionY = rand.nextInt(map.getSize());


        if (!map.verifyIfSlotIsFree(newpositionX,newpositionY)) {
            if (element instanceof Atom) {
                for (Element e : elements) { //aici ar putea interveni o alta problema de concurenta
                    if (e.getPositionX() == newpositionX && e.getPositionY() == newpositionY) {
                        if (e instanceof Atom)
                            if (((Atom) element).getValence() + ((Atom) e).getValence() == 8) {
                                elements.add(new Molecule(((Atom) element), ((Atom) e), element.getPositionX(), element.getPositionY()));
                                elements.remove(((Atom) e));
                                elements.remove(((Atom) element));
                            }
                    }
                }
            }
        } else {
            element.setPositionX(newpositionX);
            element.setPositionX(newpositionY);
        }
    }

}
