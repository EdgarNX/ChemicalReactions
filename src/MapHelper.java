import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class MapHelper {
    private static List<Element> elements;
    private static Map map;

    public MapHelper(List<Element> elements, Map map) {
        MapHelper.elements = elements;
        MapHelper.map = map;
    }

    public static boolean elementVerifier(Element e){
        boolean result = false;
        if(elements.contains(e))
                result = true;
        return result;
    }

    public synchronized static void operate(Element element) {

        Random rand = new Random();
        int newpositionX = rand.nextInt(map.getSize());
        int newpositionY = rand.nextInt(map.getSize());

        if (elementVerifier(element)) {
            if (!map.verifyIfSlotIsFree(newpositionX, newpositionY)) {
                if (element instanceof Atom) {
                    for (Element e : elements) {
                        if (e.getPositionX() == newpositionX && e.getPositionY() == newpositionY && !(e.getName().equals(element.getName()))) {
                            if (e instanceof Atom) {
                                if (((Atom) element).getValence() + ((Atom) e).getValence() == 8) {
                                    Molecule newMolecule = new Molecule(((Atom) element), ((Atom) e), e.getPositionX(), e.getPositionY());
                                    elements.remove(e);
                                    elements.remove(element);
                                    elements.add(newMolecule);
                                    map.slotSetter("_", element.getPositionX(), element.getPositionY());
                                    map.addEntry(newMolecule);
                                    break;
                                }
                            }
                        }
                    }
                }
            } else {
                map.slotSetter("_", element.getPositionX(), element.getPositionY());
                element.setPositionX(newpositionX);
                element.setPositionY(newpositionY);
                map.addEntry(element);
            }
        }
    }

}
