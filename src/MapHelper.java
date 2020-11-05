import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class MapHelper {
    private static List<Element> elements;
    private static Map map;
    private static CopyOnWriteArrayList<Element> copyElements;

    public MapHelper(List<Element> elements, Map map) {
        MapHelper.elements = elements;
        MapHelper.map = map;
        copyElements = new CopyOnWriteArrayList<>(elements);
    }
//map.atomGetter(element.getPositionX(),element.getPositionY())
    public static void operate(Element element) {

        Random rand = new Random();
        int newpositionX = rand.nextInt(map.getSize());
        int newpositionY = rand.nextInt(map.getSize());


        if (!map.verifyIfSlotIsFree(newpositionX,newpositionY)) {
            if (element instanceof Atom) {
                for (Element e : copyElements) { //aici ar putea interveni o alta problema de concurenta
                    if (e.getPositionX() == newpositionX && e.getPositionY() == newpositionY) {
                        if (e instanceof Atom) {
                            if (((Atom) element).getValence() + ((Atom) e).getValence() == 8) {
                                Molecule newMolecule = new Molecule(((Atom) element), ((Atom) e), element.getPositionX(), element.getPositionY());
                                elements.add(newMolecule);
                                elements.remove(((Atom) e));
                                elements.remove(((Atom) element));
                                map.slotSetter("_", element.getPositionX(), element.getPositionY());
                                map.slotSetter("_", e.getPositionX(), e.getPositionY());
                                map.addEntry(newMolecule);
                                return;
                            }
                        }
                    }
                }
            }
            return;
        } else {
            map.slotSetter("_",element.getPositionX(),element.getPositionY());
            element.setPositionX(newpositionX);
            element.setPositionX(newpositionY);
            map.addEntry(element);
        }
    }

}
